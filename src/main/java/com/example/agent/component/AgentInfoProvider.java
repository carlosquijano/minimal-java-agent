package com.example.agent.component;

import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class AgentInfoProvider {
    private static final String AGENT_NAME = "java-agent";
    private final String instanceId = UUID.randomUUID().toString().substring(0, 4);

    public String getInstanceId() {
        return instanceId;
    }

    public String getThreadInfo() {
        return Thread.currentThread().toString();
    }

    public String getAgentName() {
        return AGENT_NAME;
    }
}
