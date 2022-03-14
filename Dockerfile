FROM maven:3.8.4-amazoncorretto-17
EXPOSE 8080
WORKDIR /app
COPY . .
RUN mvn clean install
ENTRYPOINT ["java","-jar","target/template.jar"]