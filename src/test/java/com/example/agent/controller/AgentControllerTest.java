package com.example.agent.controller;


import com.example.agent.component.AgentInfoProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.when;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AgentControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private AgentInfoProvider agentInfoProvider;

    @Test
    void shouldReturnSuccessResponse() {
        when(agentInfoProvider.getInstanceId()).thenReturn("5ada");
        when(agentInfoProvider.getAgentName()).thenReturn("java-agent");
        when(agentInfoProvider.getThreadInfo()).thenReturn("VirtualThread");

        webTestClient.post()
                .uri("/api/chat")
                .bodyValue("Hola mundo!")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.instanceId").isEqualTo("5ada")
                .jsonPath("$.agentName").isEqualTo("java-agent")
                .jsonPath("$.thread").isEqualTo("VirtualThread")
                .jsonPath("$.response").isEqualTo("Hola mundo!");
    }
}
