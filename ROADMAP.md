# Roadmap

## M1 — Repo & Docs

> Establish the open source foundation: contribution guidelines, code of conduct,
> spec structure, and an updated README that reflects the project's full capabilities.

- `CONTRIBUTING.md` — conventional commits + PR process
- `CODE_OF_CONDUCT.md` — Contributor Covenant
- `docs/specs/` — directory structure + spec template
- `README.md` — document Spring AI abstraction and supported providers

---

## M2 — Conversation Memory

> Give the agent memory. Persist conversation history per `conversationId` using
> Spring Data + H2 (in-memory by default). The `conversationId` is optional —
> if omitted, the interaction remains stateless.

- `ConversationMessage` JPA entity
- `ConversationRepository` via Spring Data
- H2 as the default in-memory store (zero external dependencies)
- `conversationId` as optional field in the chat request
- `agent.context.limit` — configurable context window parameter

---

## M3 — Enhanced Conversation Engine

> Improve the quality and control of the conversation loop — streaming responses,
> configurable model parameters, and better prompt composition.

- Streaming responses (`/api/chat/stream`)
- Configurable model parameters (temperature, top-p) via `application.properties`
- Docker Compose with Ollama — single `docker compose up` to run everything

---

## M4 — Structured Output

> Enable the agent to respond with reliable, schema-bound JSON — essential for
> agents that feed downstream systems or other agents.

- Structured JSON responses via Spring AI
- Output schema configurable per deployment
- Validation and error handling for malformed outputs

---

## M5 — Observability & Production Readiness

> Make the template ready for real deployments: metrics, structured logging,
> rate limiting, and basic authentication.

- Structured logging (JSON)
- Custom Actuator metrics (token usage, model latency)
- Rate limiting
- API Key authentication (header-based)
