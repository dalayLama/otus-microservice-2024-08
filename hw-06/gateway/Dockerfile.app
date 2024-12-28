FROM openjdk:17-jdk-alpine
COPY target/*.jar /app.jar
EXPOSE 8080

ENV AUTH_SERVICE_URI=""
ENV PROFILE_SERVICE_URI=""

ENTRYPOINT ["java", "-jar", "/app.jar"]