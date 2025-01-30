FROM openjdk:17-jdk-alpine
WORKDIR app
COPY . .
RUN ./gradlew bootJar && cp build/libs/*.jar ./statistic-service.jar
ENTRYPOINT ["java", "-jar", "statistic-service.jar"]