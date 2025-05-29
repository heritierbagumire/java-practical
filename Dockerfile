FROM maven:3.8.4-openjdk-17 AS builder

LABEL authors="cielo"

WORKDIR /app

COPY . .

RUN mvn clean install

FROM openjdk:17

WORKDIR /app

COPY --from=builder /app/target/*.jar NE.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","NE.jar"]