package com.example.agent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

@RestController
public class AgentController {

    private final String instanceId;

    public AgentController() {
        this.instanceId = UUID.randomUUID().toString().substring(0, 4);
    }

    @GetMapping("/api/public/info")
    public Mono<Map<String, String>> publicInfo() {
        return Mono.just(Map.of(
                "version", "1.0",
                "name", "minimal-java-agent",
                "instance", instanceId,
                "thread", Thread.currentThread().toString(),
                "virtual", String.valueOf(Thread.currentThread().isVirtual())
        ));
    }
}