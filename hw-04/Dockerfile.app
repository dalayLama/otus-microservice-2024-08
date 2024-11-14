FROM openjdk:17-jdk-alpine
COPY target/hw-04-*.jar /app.jar
EXPOSE 8000

ENV DB_HOST=""
ENV DB_PORT=""
ENV DB_NAME=""
ENV DB_SCHEMA=""
ENV DB_USERNAME=""
ENV DB_PASSWORD=""

ENTRYPOINT ["java", "-jar", "/app.jar"]