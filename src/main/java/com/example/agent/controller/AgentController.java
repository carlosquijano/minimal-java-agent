package com.example.agent.controller;

import com.example.agent.component.AgentInfoProvider;
import com.example.agent.model.ChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@RestController
public class AgentController {
    private final ChatClient chatClient;
    private final AgentInfoProvider agentInfo;

    public AgentController(ChatClient.Builder chatClientBuilder, AgentInfoProvider agentInfo) {
        this.chatClient = chatClientBuilder.build();
        this.agentInfo = agentInfo;
    }

    @PostMapping("/api/chat")
    public Mono<ChatResponse> chat(@RequestBody String message) {
        var instanceId = agentInfo.getInstanceId();
        var thread = agentInfo.getThreadInfo();
        var agentName = agentInfo.getAgentName();

        return chatClient.prompt(message)
                .user(message)
                .stream()
                .content()
                .collect(Collectors.joining())
                .map(llmResponse -> new ChatResponse(instanceId, agentName, thread, llmResponse));
    }
}
