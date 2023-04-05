FROM openjdk:17.0.1-jdk-slim

WORKDIR  /app

COPY ./target/Lot_2-0.0.1-SNAPSHOT.jar lot-2-app.jar

ENTRYPOINT ["java", "-jar", "lot-2-app.jar"]