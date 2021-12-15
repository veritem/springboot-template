## Spring boot project template


opinionated springboot  template that every body agrees on


### Motivation

There a lot of tools that can help you get start with a spring boot project like spring initializer and many others, i created this project to reduce the manual tasks i know run into when starting a new project from scratch like setup authentication, documentation and many more.


### Getting started

> Make sure you have java installed on your system.

clone the repo

```bash
git clone https://github.com/veritem/springboot-project-template.git
```

Run `docker-compose up` to start a postgres container

Run `docker exec -it <container_name> bash` to enter the container

```bash
psql -U postgres
create database sptest;
```

Open in you favorite Java IDE (IntelliJ or eclipse)

Start button

Open the Browser and navigate to `http://localhost:8000/swagger-ui/index.html#/`

### Features

1. Authentication with jwt
2. Role based Authorization
3. Postgres Database Setup
4. Swagger api documentation
5. Smart Exception handling


### Toolkit

- Spring 2.4.4
- maven package manager
- Java 11 or higher
- Packaging with `jar`
- Containerized with Docker
