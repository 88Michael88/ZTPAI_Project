# GIT HUB LINK:
https://github.com/88Michael88/ZTPAI_Project

# Execution:

# Project Setup Guide

## Prerequisites

Before running the project, make sure the following software is installed and configured on your machine:

- IntelliJ IDEA
- RabbitMQ
- PostgreSQL
- Node.js
- npm (comes with Node.js)

---

# 1. Open the Backend Projects in IntelliJ IDEA

This project consists of two separate backend applications:

- `shop`
- `reviewer`

### Steps

1. Launch IntelliJ IDEA.
2. Select **Open** from the main menu.
3. Navigate to the `shop` project directory and open it.
4. Repeat the process for the `reviewer` project.

You may open them:
- in separate IntelliJ windows, or
- as separate modules inside the same workspace.

Allow IntelliJ to:
- index the projects,
- download dependencies,
- configure the JDK if prompted.

---

# 2. Install and Configure RabbitMQ

RabbitMQ is used for asynchronous communication between services.

## Installation

Download RabbitMQ from the official website:

- RabbitMQ Server
- Erlang Runtime (required by RabbitMQ)

Install Erlang first, then RabbitMQ.

---

## Start RabbitMQ

After installation, start the RabbitMQ service.

Use the correct version depending on your setup.

---

## Access RabbitMQ Management Panel

Open your browser and navigate to:

```text
http://localhost:15672
```

### Default Credentials

| Field | Value |
|---|---|
| Username | `guest` |
| Password | `guest` |

---

# 3. Install and Configure PostgreSQL

PostgreSQL is used as the main database for the application.

## Installation

Download PostgreSQL from the official website and complete the installation process.

During installation:
- remember the PostgreSQL password,
- keep note of the default port (`5432`).

---

## Create the Database

Create the required databases for:
- `shop`
- `reviewer`

You can use:
- pgAdmin,
- IntelliJ database tools,
- or the PostgreSQL terminal.

Example:

```sql
CREATE DATABASE ztpai_shop_db;
CREATE DATABASE ztpai_reviewer_db;
```

---

## Configure Database Connection

Update the database configuration inside the application configuration files.

Typically located in:

```text
src/main/resources/application.properties
```

Full configuration will be put at the bottom of this section.

---

# 4. Install Node.js and npm

The frontend application requires Node.js and npm.

## Installation

Download and install Node.js from the official website.

npm is included automatically with Node.js.

---

## Verify Installation

Run the following commands in a terminal:

```bash
node -v
npm -v
```

Both commands should return version numbers.

---

# 5. Run the Backend Applications

Both backend services should be started from IntelliJ IDEA.

---

## Run the `shop` Application

1. Open the `shop` project in IntelliJ.
2. Locate the main application class.
3. Run the Spring Boot application.

```text
ZtpaiProjectApplication.java
```

You can:
- right-click the file,
- select **Run**.

Wait until:
- Spring Boot starts,
- the server initializes successfully,
- database connection is established.

---

## Run the `reviewer` Application

Repeat the same process for the `reviewer` project.

Ensure:
- RabbitMQ is running,
- PostgreSQL is running,
- all required ports are available.

---

# 6. Run the React Frontend

Open a terminal inside the frontend directory.

Example:

```bash
cd frontend
```

---

## Install Dependencies

If running for the first time:

```bash
npm install
```

This installs all required frontend packages.

---

## Start the React Development Server

Run:

```bash
npm start
```


---

# 7. Access the Application

After all services are running:

- Backend APIs should be available on their configured ports.
- React frontend should open automatically in your browser.

```text
http://localhost:8081
```

---

# Example config:

## shop
```text
server.port                         =   8080

spring.application.name             =   ztpai_project

spring.datasource.url               =   jdbc:postgresql://localhost:5432/ztpai_shop_db
spring.datasource.username          =   ztpai
spring.datasource.password          =   ztpai2026

spring.jpa.hibernate.ddl-auto       =   update
spring.jpa.show-sql                 =   true

spring.rabbitmq.host                =   localhost
spring.rabbitmq.port                =   5672
spring.rabbitmq.username            =   guest
spring.rabbitmq.password            =   guest

app.rabbitmq.exchange               =   review.exchange
app.rabbitmq.routing-key            =   review.created
app.rabbitmq.queue                  =   review.queue

app.rabbitmq.review-queue           =   review.queue
app.rabbitmq.review-routing-key     =   review.created

app.rabbitmq.reply-queue            =   review.reply.queue
app.rabbitmq.reply-routing-key      =   review.processed

app.rabbitmq.exchange               =   review.exchange
```

## reviewer
```text
server.port                         =   3030

spring.application.name             =   ztpai_project

spring.datasource.url               =   jdbc:postgresql://localhost:5432/ztpai_reviewer_db
spring.datasource.username          =   ztpai
spring.datasource.password          =   ztpai2026

spring.jpa.hibernate.ddl-auto       =   update
spring.jpa.show-sql                 =   true

spring.rabbitmq.host                =   localhost
spring.rabbitmq.port                =   5672
spring.rabbitmq.username            =   guest
spring.rabbitmq.password            =   guest

app.rabbitmq.exchange               =   review.exchange
app.rabbitmq.routing-key            =   review.created
app.rabbitmq.queue                  =   review.queue

app.rabbitmq.reply-queue            =   review.reply.queue
app.rabbitmq.reply-routing-key      =   review.processed

app.rabbitmq.review-queue           =   review.queue
app.rabbitmq.review-routing-key     =   review.created

app.rabbitmq.exchange               =   review.exchange
```

# Shop directory:
``` bash
tree .
.
├── controller
│   ├── ProductController.java
│   ├── ReviewController.java
│   └── UserController.java
├── dto
│   ├── Product
│   │   ├── ProductMapper.java
│   │   ├── ProductRequest.java
│   │   └── ProductResponse.java
│   ├── Review
│   │   ├── NewReviewResponse.java
│   │   ├── ReviewMapper.java
│   │   ├── ReviewRequest.java
│   │   └── ReviewResponse.java
│   └── User
│       ├── AuthResponse.java
│       ├── LoginRequest.java
│       └── RegisterRequest.java
├── frontend
├── messaging
│   ├── config
│   │   └── RabbitConfig.java
│   ├── event
│   │   ├── ReviewCreatedEvent.java
│   │   └── ReviewProcessedEvent.java
│   ├── listener
│   │   └── ReviewReplyListener.java
│   └── publisher
│       └── ReviewEventPublisher.java
├── model
│   ├── AppUser.java
│   ├── Product.java
│   ├── Review.java
│   ├── Role.java
│   └── Status.java
├── repository
│   ├── ProductRepository.java
│   ├── ReviewRepository.java
│   └── UserRepository.java
├── security
│   ├── config
│   │   ├── CorsConfig.java
│   │   └── SecurityConfig.java
│   ├── CustomUserDetailsService.java
│   ├── JwtAuthenticationFilter.java
│   └── JwtUtil.java
├── service
│   ├── ProductService.java
│   ├── ReviewService.java
│   └── UserService.java
├── tree
└── ZtpaiProjectApplication.java

4535 directories, 37145 files most of them for the frontend...
```

# Reviewer Directory
``` bash
tree . 
.
├── controller
│   ├── APIController.java
│   ├── UserController.java
│   └── VerifyReviewController.java
├── dto
│   ├── API
│   │   └── ApiResponse.java
│   ├── Product
│   │   ├── ProductMapper_
│   │   ├── ProductRequest_
│   │   └── ProductResponse_
│   ├── Review
│   │   ├── NewReviewResponse_
│   │   ├── ReviewMapper_
│   │   ├── ReviewRequest_
│   │   └── ReviewResponse_
│   ├── User
│   │   ├── AuthResponse.java
│   │   ├── LoginRequest.java
│   │   └── RegisterRequest.java
│   └── VerifyReview
├── messaging
│   ├── config
│   │   └── RabbitConfig.java
│   ├── event
│   │   ├── ReviewCreatedEvent.java
│   │   └── ReviewProcessedEvent.java
│   ├── listener
│   │   └── ReviewConsumer.java
│   └── publisher
│       └── ReviewReplyPublisher.java
├── model
│   ├── API.java
│   ├── API_TIER.java
│   ├── AppUser.java
│   └── Role.java
├── repository
│   ├── API_Repository.java
│   └── UserRepository.java
├── security
│   ├── config
│   │   └── SecurityConfig.java
│   ├── CustomUserDetailsService.java
│   ├── JwtAuthenticationFilter.java
│   ├── JwtUtil.java
│   └── principals
│       └── UserPrincipal.java
├── service
│   ├── API_Service.java
│   └── UserService.java
└── ZtpaiProjectApplication.java

19 directories, 33 files
```

# Documentation:

# ZtpaiProject

## Project Overview

This project is a full-stack product review platform built with a Java backend Spring Boot and a separate frontend application.

The system supports:

- User authentication and authorization using JWT
- Product management
- Product reviews
- Asynchronous messaging using RabbitMQ
- REST API architecture
- DTO-based request/response handling
- Layered service/repository architecture

The application follows a clean separation of concerns using:

- Controllers → API layer
- Services → Business logic
- Repositories → Database access
- DTOs → Data transfer and mapping
- Security → Authentication and authorization
- Messaging → Event-driven communication

---

# Project Structure

```text
controller/     -> REST API endpoints
dto/            -> Request/response DTOs and mappers
frontend/       -> Frontend application
messaging/      -> RabbitMQ events and listeners
model/          -> Entity/domain models
repository/     -> Database repositories
security/       -> JWT authentication and security config
service/        -> Business logic layer
```

---

# Backend Architecture

## 1. Controllers Layer

Located in:

```text
controller/
```

Responsible for handling incoming HTTP requests and returning API responses.

### Files

| File | Responsibility |
|---|---|
| `ProductController.java` | Product CRUD/API operations |
| `ReviewController.java` | Review submission and retrieval |
| `Userontroller.java` | Authentication endpoints |

### Typical Responsibilities

- Validate requests
- Call service methods
- Return DTO responses
- Handle HTTP status codes

---

## 2. DTO Layer

Located in:

```text
dto/
```

DTOs (Data Transfer Objects) isolate internal models from external API contracts.

---

### Product DTOs

```text
dto/Product/
```

| File | Purpose |
|---|---|
| `ProductRequest.java` | Incoming product creation/update payload |
| `ProductResponse.java` | Outgoing product data |
| `ProductMapper.java` | Converts entities ↔ DTOs |

---

### Review DTOs

```text
dto/Review/
```

| File | Purpose |
|---|---|
| `ReviewRequest.java` | Incoming review payload |
| `ReviewResponse.java` | Review API response |
| `NewReviewResponse.java` | Specialized response after review creation |
| `ReviewMapper.java` | Entity ↔ DTO conversion |

---

### User DTOs

```text
dto/User/
```

| File | Purpose |
|---|---|
| `LoginRequest.java` | Login payload |
| `RegisterRequest.java` | User registration payload |
| `AuthResponse.java` | JWT/token response |

---

# Messaging System

Located in:

```text
messaging/
```

Implements asynchronous communication using RabbitMQ.

---

## RabbitMQ Configuration

### `RabbitConfig.java`

Defines:

- Exchanges
- Queues
- Bindings
- RabbitMQ beans/configuration

---

## Events

### `ReviewCreatedEvent.java`

Triggered when a new review is created.

### `ReviewProcessedEvent.java`

Triggered after review processing/moderation/completion.

---

## Publisher

### `ReviewEventPublisher.java`

Publishes review-related events to RabbitMQ queues.

---

## Listener

### `ReviewReplyListener.java`

Consumes RabbitMQ messages and processes asynchronous review events.

---

# Domain Models

Located in:

```text
model/
```

Represents database entities and core business objects.

| File | Description |
|---|---|
| `AppUser.java` | Application user entity |
| `Product.java` | Product entity |
| `Review.java` | Review entity |
| `Role.java` | ENUM User roles/permissions |
| `Status.java` | ENUM Review/product/user statuses |

---

# Repository Layer

Located in:

```text
repository/
```

Uses Spring Data repositories for database operations.

| File | Responsibility |
|---|---|
| `ProductRepository.java` | Product persistence |
| `ReviewRepository.java` | Review persistence |
| `UserRepository.java` | User persistence |

### Responsibilities

- CRUD operations
- Query methods
- Database abstraction

---

# Security Layer

Located in:

```text
security/
```

Handles authentication and authorization using JWT.

---

## Configuration

### `SecurityConfig.java`

Defines:

- Protected routes
- Authentication filters
- Authorization rules
- Password encoding

### `CorsConfig.java`

Configures frontend/backend cross-origin communication.

---

## Authentication

### `JwtAuthenticationFilter.java`

Intercepts requests and validates JWT tokens.

### `JwtUtil.java`

Utility class for:

- Token generation
- Token validation
- Extracting claims/user data

### `CustomUserDetailsService.java`

Loads users from the database for Spring Security authentication.

---

# Service Layer

Located in:

```text
service/
```

Contains business logic between controllers and repositories.

| File | Responsibility |
|---|---|
| `ProductService.java` | Product-related business logic |
| `ReviewService.java` | Review handling/business rules |
| `UserService.java` | Authentication and user operations |

### Responsibilities

- Validation
- Transactions
- Business rules
- Event publishing
- Data orchestration

---

# Frontend

Located in:

```text
frontend/
```

Contains the client-side application.

The frontend contains the majority of project files:

```text
4535 directories, 37145 files
```

we know that it was built in:

- React

The frontend communicates with the backend through REST APIs secured with JWT authentication.

---

# Technologies Used

| Technology | Purpose |
|---|---|
| Spring Boot | Backend framework |
| Spring Security | Authentication/authorization |
| JWT | Stateless authentication |
| RabbitMQ | Messaging/event system |
| Spring Data JPA | Database access |
| REST API | Client-server communication |
| Maven  | Dependency management |
| Java | Backend language |


# Entry Point

## `ZtpaiProjectApplication.java`

Main Spring Boot application startup class.

Responsible for:

- Bootstrapping the application
- Initializing Spring context
- Starting embedded server

---

# Summary

This project follows a standard enterprise-style Spring Boot architecture with:

- Clean layered separation
- JWT-based security
- DTO abstraction
- RabbitMQ event-driven communication
- RESTful API design
- Scalable backend structure

The system is structured well for future expansion and maintainability.
