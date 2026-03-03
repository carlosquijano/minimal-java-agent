# minimal-java-agent
![Coverage](https://img.shields.io/badge/coverage-100%25-brightgreen)

A minimal template built with **Java 21** and **Spring Boot 4.0.3**, designed to create modern agents that handle and orchestrate AI requests with high performance and efficiency.

## 🚀 Features
- **Spring Boot 4.0.3**: Latest version with enhanced performance and security.
- **Java 21 virtual threads**: Lightweight concurrency for massively scalable services.
- **WebFlux reactive stack**: Non-blocking I/O for optimal resource utilization.
- **REST controllers**: Clean, minimal endpoints for agent interactions.
- **Health check**: `/actuator/health` endpoint ready for orchestration tools.
- **100% test coverage**: Comprehensive unit tests with JaCoCo reporting.
- **Clean architecture**: Separated concerns with controllers, components, and models.

## 🏗️ Project Structure

```
minimal-java-agent/
├── src/
│   ├── main/
│   │   └── java/com/example/agent/
│   │       ├── AgentApplication.java
│   │       ├── controller/
│   │       │   └── AgentController.java
│   │       ├── component/
│   │       │   └── AgentInfoProvider.java
│   │       └── model/
│   │           └── ChatResponse.java
│   └── test/
│       └── java/com/example/agent/
│           ├── AgentApplicationTest.java
│           ├── controller/
│           │   └── AgentControllerTest.java
│           ├── component/
│           │   └── AgentInfoProviderTest.java
│           └── model/
│               └── ChatResponseTest.java
├── build.gradle
├── Dockerfile
└── README.md
```


## 🚀 Quick Start

### Prerequisites
- Java 21 or later
- Gradle (optional, wrapper IS NOT included)
- Docker (optional, for containerized deployment)

- No Gradle wrapper is included — the project expects you to have Gradle installed globally. This keeps the repository even smaller and lets you use your preferred Gradle version.

### Run locally

```bash
# Clone the repository
git clone https://github.com/carlosquijano/minimal-java-agent.git
cd minimal-java-agent

# Build the project
./gradlew build

# Run the application
./gradlew bootRun
```

### Run with Docker

```bash
# Build Docker image
docker build -t minimal-java-agent .

# Run container
docker run -p 8080:8080 minimal-java-agent
```

## API Endpoints

| Method | Endpoint           | Description                                                   |
|--------|--------------------|---------------------------------------------------------------|
| `POST` | `/api/chat`        | Send a message to the agent                                   |

### Example requests

```bash
# Send a message
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: text/plain" \
  -d "Hello, agent!"
```

## Testing

```bash
# Run tests
./gradlew test

# Generate coverage report
./gradlew jacocoTestReport

# View coverage report
open build/reports/jacoco/html/index.html
```

## Docker

The included `Dockerfile` uses multi-stage builds for optimal image size:

```dockerfile
# Build stage
FROM gradle:9.3.1-jdk21-alpine AS builder

WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle bootJar --no-daemon

FROM eclipse-temurin:21-jre-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY --from=builder /app/build/libs/*.jar agent.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx256m", "-jar", "/agent.jar"]
```

## Monitoring

The application exposes health and metrics endpoints via Spring Boot Actuator:

- `/actuator/health` - Application health status
- `/actuator/info` - Application information

## Contributing

Contributions, issues, and feature requests are welcome! 
Feel free to check the [issues page](https://github.com/carlosquijano/minimal-java-agent/issues).

## License

This project is [Apache 2.0](LICENSE) licensed.

## Contact

Suggestions on how to either minimize or enhance this further are welcome!
