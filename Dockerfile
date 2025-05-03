FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/pg-management-docker.jar pg-management-docker.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","pg-management-docker.jar"]