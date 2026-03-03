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
