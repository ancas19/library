FROM eclipse-temurin:21.0.4_7-jdk AS build
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew clean bootJar


FROM eclipse-temurin:21.0.4_7-jre AS run
WORKDIR /app
COPY --from=build /app/build/libs/entrypoints-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8585
CMD ["java", "-jar", "/app/app.jar"]
