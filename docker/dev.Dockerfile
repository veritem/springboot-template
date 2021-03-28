# Create builder stage for build application.
FROM maven:3-openjdk-8 as builder

WORKDIR /app

COPY . /app

# Build maven application
RUN mvn clean package

RUN mv target/*.jar app.jar

# Reduce image size
FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY --from=builder /app/app.jar /app/app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]