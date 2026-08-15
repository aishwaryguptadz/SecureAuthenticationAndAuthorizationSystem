# Secure Authentication and Authorization System

<p align="center">
  <strong>Production-Oriented JWT Authentication & Role-Based Authorization Backend</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17">
  <img src="https://img.shields.io/badge/Spring%20Boot-4.0.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white" alt="Spring Security">
  <img src="https://img.shields.io/badge/JWT-Authentication-000000?style=for-the-badge" alt="JWT">
  <img src="https://img.shields.io/badge/PostgreSQL-Database-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL">
  <img src="https://img.shields.io/badge/Docker-Containerized-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker">
  <img src="https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven">
</p>

<p align="center">
  A secure Spring Boot REST backend implementing user registration, authentication, JWT access and refresh tokens, role-based authorization, protected endpoints, validation, and PostgreSQL persistence.
</p>

---

## Screenshots / Demo

This project is a **backend REST API**, so there is no graphical mobile or web interface.

The API can be tested using:

* Postman
* curl
* Any REST client
* Browser for public GET endpoints

### Authentication Flow

```text
┌──────────────┐
│    Client    │
└──────┬───────┘
       │
       │ Register
       ▼
┌──────────────────────┐
│ POST /api/auth/      │
│ register             │
└──────────┬───────────┘
           │
           ▼
     Password Hashing
           │
           ▼
       PostgreSQL
           │
           │
           ▼
┌──────────────────────┐
│ POST /api/auth/login │
└──────────┬───────────┘
           │
           ▼
    Credential Check
           │
           ▼
    JWT Access Token
    JWT Refresh Token
           │
           ▼
┌──────────────────────┐
│ Protected API Route  │
└──────────┬───────────┘
           │
           ▼
     JwtFilter
           │
           ▼
    Token Validation
           │
           ▼
    Role Authorization
           │
           ▼
      API Response
```

---

## Overview

**Secure Authentication and Authorization System** is a Spring Boot backend designed to provide a reusable authentication and authorization layer for modern web and mobile applications.

The system implements token-based authentication using **JSON Web Tokens (JWT)** and protects application resources using **Spring Security**.

Users can register and authenticate using their credentials. After successful authentication, the server issues an access token and refresh token. The access token is subsequently supplied with protected API requests and validated by a custom JWT security filter.

The project also implements **role-based access control (RBAC)**, allowing protected resources to be restricted according to the authenticated user's role.

### Core Capabilities

* User registration
* User login
* Secure password hashing
* JWT access tokens
* JWT refresh tokens
* Access-token validation
* Role-based authorization
* Protected REST endpoints
* Input validation
* Exception handling
* PostgreSQL persistence
* Docker containerization
* Maven build system

The repository is organized into dedicated configuration, controller, DTO, exception, model, repository, security, and service layers.

---

## Features

### User Registration

Users can create accounts through:

```http
POST /api/auth/register
```

Registration data is validated before being persisted.

Passwords are not stored as plaintext. The authentication system uses password hashing through the Spring Security stack.

### User Login

Users authenticate through:

```http
POST /api/auth/login
```

After successful authentication, the backend generates:

* Access token
* Refresh token

The access token is then used to access protected resources.

### JWT Authentication

JWTs are used for stateless authentication.

The security layer contains:

```text
security/
├── JwtFilter.java
└── JwtService.java
```

`JwtService` is responsible for JWT-related operations while `JwtFilter` participates in validating authenticated requests.

### Refresh Token Support

The application includes a dedicated:

```text
RefreshTokenService.java
```

for refresh-token operations.

Clients can request new authentication tokens through:

```http
POST /api/auth/refresh
```

This allows access tokens to have a limited lifetime while providing a mechanism for obtaining new tokens without requiring the user to authenticate again.

### Role-Based Authorization

The system supports role-based access control.

Different controllers are provided for different application responsibilities:

```text
AdminController.java
UserController.java
AuthController.java
HomeController.java
```

This allows authorization rules to be applied according to the authenticated user's role.

### Protected API Endpoints

Protected routes require an authenticated request containing a valid JWT.

```http
Authorization: Bearer <access-token>
```

The JWT filter processes incoming requests before access is granted to secured resources.

### Password Hashing

User passwords are securely hashed rather than stored directly.

The project uses Spring Security's password-encoding infrastructure for credential protection.

### Input Validation

The project includes:

```text
spring-boot-starter-validation
```

for request validation and invalid-input handling.

### Exception Handling

A dedicated exception package provides centralized application-level error handling.

```text
exception/
```

This keeps error handling separate from controller and service logic.

### PostgreSQL Persistence

User and authentication-related data is persisted using:

* PostgreSQL
* Spring Data JPA
* Hibernate

The project includes the PostgreSQL JDBC driver and Spring Data JPA starter.

### Docker Support

The repository contains:

```text
Dockerfile
docker-compose.yml
```

allowing the application to be containerized and deployed in a reproducible environment.

---

## Tech Stack

### Backend

| Technology         | Usage                            |
| ------------------ | -------------------------------- |
| Java 17            | Primary programming language     |
| Spring Boot 4.0.5  | Backend framework                |
| Spring Web MVC     | REST API                         |
| Spring Security    | Authentication and authorization |
| Spring Data JPA    | Persistence abstraction          |
| Hibernate          | ORM                              |
| Jakarta Validation | Request validation               |
| Lombok             | Boilerplate reduction            |

The Maven configuration confirms Java 17, Spring Boot 4.0.5, Spring Data JPA, Spring Security, validation, Web MVC, PostgreSQL, and Lombok dependencies.

### Authentication & Security

| Technology      | Usage                                   |
| --------------- | --------------------------------------- |
| JWT             | Stateless authentication                |
| JJWT 0.13.0     | JWT creation and validation             |
| Spring Security | Security filter chain and authorization |
| BCrypt          | Password hashing                        |
| RBAC            | Role-based access control               |
| Refresh Tokens  | Access-token renewal                    |

The project uses JJWT `0.13.0` and Spring Security.

### Database

| Technology | Usage                      |
| ---------- | -------------------------- |
| PostgreSQL | Relational database        |
| JPA        | Object-relational mapping  |
| Hibernate  | Persistence implementation |

### DevOps / Tools

| Technology     | Usage                           |
| -------------- | ------------------------------- |
| Maven          | Build and dependency management |
| Maven Wrapper  | Reproducible Maven execution    |
| Docker         | Containerization                |
| Docker Compose | Multi-container development     |
| Postman        | API testing                     |
| Git            | Version control                 |
| GitHub         | Source-code hosting             |

---

## Architecture

The project follows a **layered Spring Boot architecture** with dedicated security components.

```text
                         ┌─────────────────────┐
                         │       Client        │
                         │ Postman / Web / App │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │   Spring Security   │
                         │    Security Chain   │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │      JwtFilter      │
                         │ Token Extraction &   │
                         │     Validation      │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │    Controllers      │
                         │ Auth / User / Admin │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │      Services       │
                         │ Auth / Refresh /    │
                         │ User Details        │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │    Repositories     │
                         │    Spring Data JPA  │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │     PostgreSQL      │
                         └─────────────────────┘
```

### Authentication Flow

```text
User
 │
 │ Email + Password
 ▼
AuthController
 │
 ▼
AuthService
 │
 ├── Find User
 │
 ├── Verify Password
 │
 └── Generate Tokens
        │
        ├───────────────┐
        ▼               ▼
 Access Token      Refresh Token
        │               │
        └───────┬───────┘
                ▼
             Client
```

### Protected Request Flow

```text
Client
  │
  │ Authorization: Bearer <JWT>
  ▼
Spring Security Filter Chain
  │
  ▼
JwtFilter
  │
  ├── Extract JWT
  │
  ├── Validate JWT
  │
  ├── Extract User Identity
  │
  └── Build Authentication
          │
          ▼
      Controller
          │
          ▼
       Service
          │
          ▼
      Repository
          │
          ▼
      PostgreSQL
```

### Refresh Token Flow

```text
Client
  │
  │ Refresh Token
  ▼
POST /api/auth/refresh
  │
  ▼
RefreshTokenService
  │
  ├── Validate Refresh Token
  │
  └── Generate New Tokens
          │
          ▼
        Client
```

### Authorization Flow

```text
Authenticated User
        │
        ▼
     JWT Claims
        │
        ▼
      User Role
        │
   ┌────┴─────┐
   │          │
   ▼          ▼
 USER       ADMIN
   │          │
   ▼          ▼
User API   Admin API
```

---

## API / Database

### Base URL

When running locally:

```text
http://localhost:8080
```

---

### 1. Register User

```http
POST /api/auth/register
```

#### Request

```json
{
  "username": "user1",
  "email": "user@email.com",
  "password": "password123"
}
```

The registration flow validates the request and persists the user using the application service and repository layers.

---

### 2. Login

```http
POST /api/auth/login
```

#### Request

```json
{
  "email": "user@email.com",
  "password": "password123"
}
```

#### Response

```json
{
  "status": "success",
  "accessToken": "<access-token>",
  "refreshToken": "<refresh-token>"
}
```

The repository documents access-token and refresh-token generation after successful authentication.

---

### 3. Protected User Route

```http
GET /api/user/profile
```

#### Header

```http
Authorization: Bearer <access-token>
```

A valid access token is required before the request can reach the protected resource.

---

### 4. Refresh Token

```http
POST /api/auth/refresh
```

#### Request

```json
{
  "refreshToken": "<refresh-token>"
}
```

#### Response

```json
{
  "accessToken": "<new-access-token>",
  "refreshToken": "<new-refresh-token>"
}
```

Refresh-token handling is implemented through the dedicated `RefreshTokenService`.

---

### 5. Authentication Headers

Protected requests use:

```http
Authorization: Bearer <access-token>
```

The `JwtFilter` is responsible for processing JWT-bearing requests before protected resources are accessed.

---

### Database

The system uses **PostgreSQL** as its relational persistence layer.

```text
Spring Boot
     │
     ▼
Spring Data JPA
     │
     ▼
Hibernate
     │
     ▼
PostgreSQL
```

The application includes the PostgreSQL runtime driver and Spring Data JPA.

### Database Configuration

Configure database properties through environment variables or an externalized configuration file.

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/authdb
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Do not commit real credentials.

---

## Project Structure

```text
SecureAuthenticationAndAuthorizationSystem/
│
├── .mvn/
│   └── wrapper/
│
├── src/
│   │
│   ├── main/
│   │   │
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── aishwary/
│   │   │           └── authsystem/
│   │   │               │
│   │   │               ├── config/
│   │   │               │
│   │   │               ├── controller/
│   │   │               │   ├── AdminController.java
│   │   │               │   ├── AuthController.java
│   │   │               │   ├── HomeController.java
│   │   │               │   └── UserController.java
│   │   │               │
│   │   │               ├── dto/
│   │   │               │
│   │   │               ├── exception/
│   │   │               │
│   │   │               ├── model/
│   │   │               │
│   │   │               ├── repository/
│   │   │               │
│   │   │               ├── security/
│   │   │               │   ├── JwtFilter.java
│   │   │               │   └── JwtService.java
│   │   │               │
│   │   │               ├── service/
│   │   │               │   ├── AuthService.java
│   │   │               │   ├── CustomUserDetailsService.java
│   │   │               │   └── RefreshTokenService.java
│   │   │               │
│   │   │               └── AuthsystemApplication.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── aishwary/
│                   └── authsystem/
│
├── .env
├── .gitignore
├── Dockerfile
├── docker-compose.yml
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

The actual repository separates controllers, DTOs, exceptions, models, repositories, security components, and services under `com.aishwary.authsystem`.

### Package Responsibilities

| Package      | Responsibility                         |
| ------------ | -------------------------------------- |
| `config`     | Spring/application configuration       |
| `controller` | REST API endpoints                     |
| `dto`        | Request and response models            |
| `exception`  | Application exception handling         |
| `model`      | JPA/domain entities                    |
| `repository` | Database access                        |
| `security`   | JWT processing and security components |
| `service`    | Authentication and business logic      |

---

## Setup

### Prerequisites

Install:

* Java 17
* Maven or use the included Maven Wrapper
* PostgreSQL
* Docker / Docker Compose
* Postman or another REST client
* Git

The project is configured for Java 17 and Spring Boot 4.0.5.

---

### 1. Clone the Repository

```bash
git clone https://github.com/aishwaryguptadz/SecureAuthenticationAndAuthorizationSystem.git
cd SecureAuthenticationAndAuthorizationSystem
```

---

### 2. Configure PostgreSQL

Create the database:

```sql
CREATE DATABASE authdb;
```

Configure the application using environment variables:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/authdb
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

The repository's documented configuration uses PostgreSQL on the `authdb` database.

---

### 3. Configure JWT Secrets

Keep JWT signing secrets outside the repository.

Example environment variables:

```env
JWT_SECRET=<strong-secret>
JWT_EXPIRATION=<access-token-expiration>
JWT_REFRESH_EXPIRATION=<refresh-token-expiration>
```

Never commit production JWT secrets to Git.

---

### 4. Build the Project

Using the Maven Wrapper:

#### Windows

```bash
mvnw.cmd clean install
```

#### Linux / macOS

```bash
./mvnw clean install
```

---

### 5. Run the Application

#### Windows

```bash
mvnw.cmd spring-boot:run
```

#### Linux / macOS

```bash
./mvnw spring-boot:run
```

The application runs on:

```text
http://localhost:8080
```

---

### 6. Run with Docker

Build the application:

```bash
./mvnw clean package
```

Then build the Docker image:

```bash
docker build -t authsystem .
```

Run the container:

```bash
docker run -p 8080:8080 authsystem
```

The repository's Dockerfile uses Eclipse Temurin JDK 17, copies the generated Spring Boot JAR, exposes port `8080`, and starts the application with `java -jar`.

---

### 7. Run with Docker Compose

The repository also contains:

```text
docker-compose.yml
```

Run:

```bash
docker compose up --build
```

This provides a convenient containerized development/deployment workflow.

---

### 8. Test the API

#### Register

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d "{\"username\":\"user1\",\"email\":\"user@email.com\",\"password\":\"password123\"}"
```

#### Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d "{\"email\":\"user@email.com\",\"password\":\"password123\"}"
```

Copy the returned access token.

#### Protected Endpoint

```bash
curl http://localhost:8080/api/user/profile \
  -H "Authorization: Bearer <access-token>"
```

---

## My Role

I designed and implemented this project as a **Java Spring Boot backend focused on authentication, authorization, security architecture, and persistence**.

### Backend Development

* Designed the authentication REST API.
* Implemented user registration.
* Implemented login and credential verification.
* Implemented JWT-based authentication.
* Implemented access-token generation.
* Implemented refresh-token functionality.
* Implemented protected API endpoints.
* Implemented role-based authorization.
* Implemented request validation.
* Implemented application-level exception handling.

### Spring Security

I worked with Spring Security to implement:

* Authentication flow.
* Security filter processing.
* JWT request interception.
* Token validation.
* Authenticated user context.
* Protected routes.
* Role-based access control.

The security layer is explicitly separated into `JwtFilter` and `JwtService`, while authentication and refresh-token logic reside in dedicated services.

### Database

I implemented the persistence side using:

* PostgreSQL.
* Spring Data JPA.
* Hibernate.
* Repository abstraction.
* Entity-based persistence.

### API Design

The API was structured around separate responsibilities:

```text
AuthController
      │
      ├── Registration
      ├── Login
      └── Refresh

UserController
      │
      └── Protected User APIs

AdminController
      │
      └── Role-Protected Admin APIs
```

This separation makes the authentication system easier to extend as additional application resources are introduced.

### Containerization

I also configured the project for containerized execution using:

* Dockerfile.
* Docker Compose.
* Java 17 runtime.
* Spring Boot executable JAR.

The repository currently includes both Docker configuration files.

---

## Future Improvements

### Security

* [ ] Add OAuth 2.0 / OpenID Connect.
* [ ] Add Google/GitHub social login.
* [ ] Implement multi-factor authentication.
* [ ] Add email verification.
* [ ] Add password reset functionality.
* [ ] Add account lockout after repeated failed attempts.
* [ ] Add login attempt monitoring.
* [ ] Add IP-based rate limiting.
* [ ] Add refresh-token rotation and revocation.
* [ ] Add token blacklisting where required.
* [ ] Strengthen JWT key management.
* [ ] Move all secrets to a dedicated secrets manager.

### Authorization

* [ ] Introduce fine-grained permissions.
* [ ] Add permission-based authorization alongside roles.
* [ ] Support dynamic role management.
* [ ] Add resource-level authorization.
* [ ] Add admin user management APIs.

### Database

* [ ] Add database indexes for authentication queries.
* [ ] Add audit tables.
* [ ] Track authentication events.
* [ ] Store refresh-token metadata securely.
* [ ] Add database migration tooling using Flyway or Liquibase.

### Testing

* [ ] Expand controller tests.
* [ ] Add authentication integration tests.
* [ ] Add JWT filter tests.
* [ ] Add authorization/RBAC tests.
* [ ] Add repository integration tests.
* [ ] Add negative security tests.
* [ ] Add Testcontainers for PostgreSQL.
* [ ] Add automated API security testing.

### Performance & Scalability

* [ ] Add Redis for distributed session/token metadata where appropriate.
* [ ] Add distributed rate limiting.
* [ ] Add connection-pool tuning.
* [ ] Add asynchronous audit logging.
* [ ] Add horizontal scaling support.
* [ ] Add load-balancer-ready deployment.

### DevOps

* [ ] Add GitHub Actions CI/CD.
* [ ] Add automated security scanning.
* [ ] Add container vulnerability scanning.
* [ ] Add environment-specific Spring profiles.
* [ ] Add production Kubernetes manifests.
* [ ] Add centralized logging.
* [ ] Add Prometheus metrics.
* [ ] Add Grafana monitoring.
* [ ] Add OpenTelemetry tracing.

### API Documentation

* [ ] Add OpenAPI/Swagger documentation.
* [ ] Publish an importable Postman collection.
* [ ] Document authentication flows.
* [ ] Document RBAC behavior.
* [ ] Document error response formats.
* [ ] Add API versioning.

---

## License

This project is licensed under the **MIT License**.

See the repository's license information for the applicable terms.

---

<p align="center">
  <strong>Secure Authentication and Authorization System</strong>
</p>

<p align="center">
  Java • Spring Boot • Spring Security • JWT • PostgreSQL • Docker
</p>

<p align="center">
  Built as a backend engineering project focused on secure authentication, authorization, and scalable API design.
</p>
