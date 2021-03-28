FROM openjdk:7
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN javac Application.jar

CMD ["java","Application.jar"]