FROM maven:3.9.11-eclipse-temurin-21 as build

WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests