FROM openjdk:21-jdk-slim

COPY build/libs/gestioninventarios-0.0.1-SNAPSHOT.jar /app/gestioninventarios.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "gestioninventarios.jar"]
