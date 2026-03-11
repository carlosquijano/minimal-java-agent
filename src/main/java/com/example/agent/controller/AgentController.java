package com.example.agent.controller;

import com.example.agent.component.AgentInfoProvider;
import com.example.agent.model.ChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@RestController
public class AgentController {
    private final ChatClient chatClient;
    private final AgentInfoProvider agentInfo;

    public AgentController(
            ChatClient.Builder builder,
            AgentInfoProvider agentInfo,
            @Value("classpath:prompts/system.st") Resource base,
            @Value("${agent.system-prompt-path:}") Resource external) throws IOException {

        this.agentInfo = agentInfo;
        this.chatClient = builder
                .defaultSystem(loadPrompt(base, external))
                .build();
    }

    @PostMapping("/api/chat")
    public Mono<ChatResponse> chat(@RequestBody String message) {
        var instanceId = agentInfo.getInstanceId();
        var thread = agentInfo.getThreadInfo();
        var agentName = agentInfo.getAgentName();

        return chatClient.prompt()
                .user(message)
                .stream()
                .content()
                .collect(Collectors.joining())
                .map(response -> new ChatResponse(instanceId, agentName, thread, response));
    }

    static String loadPrompt(Resource... resources) throws IOException {
        var sb = new StringBuilder();
        for (var r : resources) {
            if (r != null && r.exists()) {
                sb.append(r.getContentAsString(StandardCharsets.UTF_8)).append("\n\n");
            }
        }
        return sb.toString().strip();
    }
}
