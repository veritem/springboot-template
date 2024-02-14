## Spring boot project template


This is another opinionated springboot template. 


### Motivation

TLDR: I was tired of repeating the same manual tasks every time I started a new spring project. 

Many tools can help generate a new spring boot project from scratch, like Spring Initializer and many others. But Why does this template exist? Well, Springboot is a weird framework with a lot of boilerplate. 

### Getting started

> Make sure you have Java installed on your system.

1. clone the repo

```bash
git clone https://github.com/veritem/springboot-project-template.git
```

2. run `docker-compose up` to start a Postgres container

3. run  `docker exec -it <container_name> bash` to enter the container

```bash
psql -U postgres
create database <your-database-name>;
```

4. Open in you favorite Java IDE (IntelliJ or eclipse)


5. Open the Browser and navigate to `http://localhost:8000/swagger-ui/index.html#/`


### Features

1. Authentication with JWT
2. Role-based Authorization
3. Postgres Database Setup
4. Swagger API documentation
5. Smart Exception handling


### Toolkit

- Spring 2.4.4
- maven package manager
- Java 11 or higher
- Packaging with `jar`
- Containerized with Docker

If you run into any issues, feel free to open a new issue. Have a good rest of your day!
