FROM eclipse-temurin:21.0.5_11-jdk
WORKDIR /app
COPY infrastructure/entrypoints/build/libs/entrypoints-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/app/app.jar"]