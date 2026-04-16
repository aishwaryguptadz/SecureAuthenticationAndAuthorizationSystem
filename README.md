# Secure Authentication and Authorization System

A secure backend authentication system built using modern backend practices.

This project demonstrates how to implement user registration, login, role-based access control, and token-based authentication using industry-standard security mechanisms.

The system is designed to be scalable, secure, and production-ready for modern web and mobile applications.

---

## Features

- User Registration and Login
- Secure Password Hashing
- JWT-based Authentication
- Role-Based Authorization (RBAC)
- Access Token Validation
- Protected API Endpoints
- Refresh Token Support
- Secure API Desing
- Input Validation and Error Handling

---

## Tech Stack

### Backend

- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)

### Database
- PostgreSQL

### Tools
- Maven
- Docker
- Postman for API testing
- Git & GitHub

## System Architecture

Client -> API Gateway -> Authentication Service -> Database

### Flow

1. User registers with email and password.
2. Password is securely hashed before storing.
3. User logs in with credentials.
4. Server verifies credentials.
5. Server generates JWT access token.
6. Client sends token with each request.
7. Server validates token before granting access.

## Installation

### 1. Clone the Repository

``` bash
git clone https://github.com/aishwaryguptadz/SecureAuthenticationAndAuthorizationSystem
```

### 2. Navigate to the Project

``` bash
cd SecureAuthenticationAndAuthorizationSystem
```

### 3. Configure Database

Update the database configuration in:

```
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/authdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword
```

---

## Running the Application

Using Maven:

```bash
mvnw spring-boot:run
```

Or build and run:

```bash
mvnw clean install
java -jar target/auth-system.jar
```

The server will start on:

http://localhost:8080

---

## API Endpoints

### Register User

POST /api/auth/register

Request Body

```json
{
  "username":"user1",
  "email":"user@email.com",
  "password":"password123"
}
```

### Login

POST /api/auth/login

Request Body

```json
{
  "email":"user@email.com",
  "password":"password123"
}
```

Response

```json
{
  "status":"success",
  "accessToken":"jwt_token_here",
  "refreshToken":"jwt_token_here"
}
```

### Access Protected Route

GET /api/user/profile

Header

Authorization: Bearer <token>

### Refresh Token

POST /api/auth/refresh

Request Body

```json
{
  "refreshToken":"refresh_token"
}
```

Response

```json
{
  "accessToken":"new_access_token",
  "refreshToken":"new_refresh_token"
}
```

---

## Security Features
- BCrypt password hashing
- JWT token verification
- Token expiration
- Role-based authorization
- Secure API routes
- Input validation
- Prevention of unauthorized access

---

## Future Improvements
- OAuth2 / Social Login
- Multi-factor authentication (MFA)
- Rate limiting
- Account verification via email
- Passowrd reset functionality
- API Gateway integration

---

## Testing

Use Postman to test the authentication APIs.

Example workflow:
1. Register a new user
2. Login to obtain JWT token
3. Use the token to access protected endpoints

---

## License
This project is licenced under the MIT License.

---

## Author

Aishwary Kumar

B.Tech. CSE (AI)

Pranveer Singh Institute of Technology
