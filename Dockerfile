FROM eclipse-temurin:21-jre-slim AS run
USER 1000:1000
WORKDIR /app
COPY infrastructure/entrypoints/build/libs/entrypoints-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/app/app.jar"]