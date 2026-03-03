package com.example.agent.controller;

import com.example.agent.component.AgentInfoProvider;
import com.example.agent.model.ChatResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AgentController {

    private final AgentInfoProvider agentInfo;

    public AgentController(AgentInfoProvider agentInfo) {
        this.agentInfo = agentInfo;
    }

    @PostMapping("/api/chat")
    public Mono<ChatResponse> chat(@RequestBody String message) {
        var instanceId = agentInfo.getInstanceId();
        var thread = agentInfo.getThreadInfo();
        var agentName = agentInfo.getAgentName();
        return Mono.just(new ChatResponse(instanceId, agentName, thread, message));
    }
}
