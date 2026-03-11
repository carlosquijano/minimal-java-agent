package com.example.agent.controller;

import com.example.agent.component.AgentInfoProvider;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AgentPromptTest {

    @MockitoBean
    private AgentInfoProvider agentInfoProvider;

    @Test
    void shouldLoadBasePromptOnly() throws IOException {
        var base = resource("You are a base agent.");
        var result = AgentController.loadPrompt(base);
        assertThat(result).isEqualTo("You are a base agent.");
    }

    @Test
    void shouldConcatenateBaseAndExternalPrompt() throws IOException {
        var base = resource("You are a base agent.");
        var external = resource("You are also an expert.");
        var result = AgentController.loadPrompt(base, external);
        assertThat(result).isEqualTo("You are a base agent.\n\nYou are also an expert.");
    }

    @Test
    void shouldIgnoreNullResource() throws IOException {
        var base = resource("You are a base agent.");
        var result = AgentController.loadPrompt(base, null);
        assertThat(result).isEqualTo("You are a base agent.");
    }

    @Test
    void shouldIgnoreNonExistentResource() throws IOException {
        var base = resource("You are a base agent.");
        var missing = new FileSystemResource("/non/existent/path/system.st");
        var result = AgentController.loadPrompt(base, missing);
        assertThat(result).isEqualTo("You are a base agent.");
    }

    private static Resource resource(String content) {
        return new ByteArrayResource(content.getBytes(StandardCharsets.UTF_8));
    }
}
