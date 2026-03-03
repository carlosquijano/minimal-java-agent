package com.example.agent.model;

public record ChatResponse(
    String instanceId,
    String agentName,
    String thread,
    String response
) {}