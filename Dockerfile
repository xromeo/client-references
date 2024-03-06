
FROM openjdk:17-jdk-alpine


WORKDIR /app


COPY target/client-references-0.0.1-SNAPSHOT.jar app.jar

# Define el punto de entrada para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
