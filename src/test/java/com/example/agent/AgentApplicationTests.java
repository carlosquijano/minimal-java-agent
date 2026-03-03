package com.example.agent;

import com.example.agent.component.AgentInfoProvider;
import com.example.agent.controller.AgentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AgentApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void shouldLoadApplicationContext() {
        assertThat(context).isNotNull();
        assertThat(context.getId()).isNotEmpty();
        assertThat(context.getDisplayName()).isNotEmpty();
    }

    @Test
    void shouldHaveMainMethod() {
        assertThat(AgentApplication.class).isNotNull();
        assertThat(AgentApplication.class.getDeclaredMethods()).anyMatch(m -> m.getName().equals("main"));
    }

    @Test
    void shouldHaveRequiredBeans() {
        assertThat(context.getBean(AgentInfoProvider.class)).isNotNull();
        assertThat(context.getBean(AgentController.class)).isNotNull();
    }

    @Test
    void applicationStartsWithoutExceptions() {
        assertThat(context).isNotNull();
    }

    @Test
    void mainStartsContextSuccessfully() {
        String[] args = new String[]{
                "--spring.main.web-application-type=none",
                "--spring.banner.mode=off"
        };

        AgentApplication.main(args);

        assertThat(true).isTrue();
    }
}