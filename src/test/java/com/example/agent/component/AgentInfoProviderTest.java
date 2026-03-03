package com.example.agent.component;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AgentInfoProviderTest {

    @Autowired
    private AgentInfoProvider agentInfoProvider;

    @Test
    void shouldReturnInstanceId() {
        String instanceId = agentInfoProvider.getInstanceId();

        assertThat(instanceId).isNotNull();
        assertThat(instanceId.length()).isEqualTo(4);
    }

    @Test
    void shouldReturnAgentName() {
        String agentName = agentInfoProvider.getAgentName();

        assertThat(agentName).isEqualTo("java-agent");
        assertThat(agentName).isNotNull();
        assertThat(agentName).isNotEmpty();
    }

    @Test
    void shouldReturnThreadInfo() {
        String threadInfo = agentInfoProvider.getThreadInfo();

        assertThat(threadInfo).isNotNull();
        assertThat(threadInfo).isNotEmpty();
        assertThat(threadInfo).contains("Thread");

        String currentThreadName = Thread.currentThread().toString();
        assertThat(threadInfo).isEqualTo(currentThreadName);
    }

    @Test
    void instanceIdShouldBeConsistent() {
        String firstCall = agentInfoProvider.getInstanceId();
        String secondCall = agentInfoProvider.getInstanceId();

        assertThat(firstCall).isEqualTo(secondCall);
    }

    @Test
    void threadInfoShouldBeCurrentThread() {
        String threadInfo = agentInfoProvider.getThreadInfo();
        String currentThread = Thread.currentThread().toString();

        assertThat(threadInfo).isEqualTo(currentThread);
    }
}
