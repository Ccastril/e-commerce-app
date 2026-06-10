# E-Commerce App

An n-tier e-commerce web application built with Spring Boot, Thymeleaf, Spring Security, Spring Data JPA, and H2/MySQL database support. The project demonstrates a layered Java web architecture with MVC controllers, REST endpoints, service-layer business logic, repositories, DTOs, view models, validation, authentication, and database-backed product/cart functionality.

## Overview

This application was built as a full-stack Java/Spring portfolio project to model the core structure of a server-rendered e-commerce platform. It supports product browsing, user registration/login, cart management, and database-backed persistence using Spring Data JPA.

The project separates concerns across controller, service, repository, entity, DTO, and view model layers to keep persistence models, API responses, and Thymeleaf page data clearly separated.

## Features

* Product browsing with server-rendered Thymeleaf views
* User registration and login flow
* Spring Security authentication configuration
* Shopping cart functionality
* REST API endpoints for product and cart data
* DTO records for API response/request models
* View models for Thymeleaf page rendering
* JPA entity modeling for accounts, products, carts, cart items, orders, and wish lists
* Repository layer using Spring Data JPA
* Validation support for user input
* H2 profile for local/demo execution without MySQL
* MySQL-compatible configuration for local development

## Tech Stack

* Java 21
* Spring Boot
* Spring MVC
* Spring Security
* Spring Data JPA
* Thymeleaf
* Maven
* H2 Database
* MySQL
* Lombok
* ModelMapper
* JUnit/Spring Boot Test

## Architecture

The application follows an n-tier structure:

```text
src/main/java/io/github/ccastril/ecommerce
├── api          # REST controllers
├── aspect       # Cross-cutting concerns and controller advice
├── config       # Application and mapping configuration
├── controller   # MVC controllers for Thymeleaf views
├── dto          # API request/response records
├── entity       # JPA persistence models
├── exception    # Custom exceptions and error models
├── repository   # Spring Data JPA repositories
├── security     # Authentication and security configuration
├── service      # Business logic
├── validation   # Custom validation logic
└── viewmodel    # Page-specific models for Thymeleaf views
```

The main design goal is to keep database entities separate from the web/API layer. Entities represent persistence state, DTOs shape API responses, and view models prepare page-specific data for Thymeleaf templates.

## Running the App with H2

The easiest way to run the project locally is with the `h2` profile.

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=h2
```

Then open:

```text
http://localhost:8080
```

The H2 console is available at:

```text
http://localhost:8080/h2-console
```

Use the following H2 connection settings:

```text
JDBC URL: jdbc:h2:mem:ecommerce_demo
User Name: sa
Password:
```

Leave the password blank.

## Local Development with MySQL

For local MySQL development, create an ignored `application-local.properties` file:

```text
src/main/resources/application-local.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_demo
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
```

Run with the `local` profile:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

`application-local.properties` should not be committed to Git.

## Build

To build the project:

```bash
./mvnw clean package
```

To build while skipping tests:

```bash
./mvnw clean package -DskipTests
```

## Project Status

This project is under active portfolio development. Current focus areas include:

* Refining DTO and view model boundaries
* Improving demo seed data setup
* Expanding cart and checkout functionality
* Adding screenshots and usage examples
* Improving test coverage

## Why This Project

This project demonstrates practical experience with Java/Spring application architecture, including layered backend design, server-rendered views, authentication, persistence, validation, and API response modeling. It is intended to show the structure and maintainability of a realistic Spring Boot application rather than a minimal CRUD demo.
