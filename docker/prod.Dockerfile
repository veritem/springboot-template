FROM openjdk:11
ADD target/template.jar template.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","template.jar"]