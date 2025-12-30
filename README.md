# TODOLIST BACKEND API

This project is my first complete backend API built with Java and Spring Boot.  
The main goal was to apply backend fundamentals correctly, focusing on clean
architecture, security, and good practices — not complexity.

This is **NOT** a tutorial copy. Every decision was made consciously during study.

---

## Tech Stack
- Java
- Spring Boot
- Spring Security (HTTP Basic)
- Spring Data JPA
- Hibernate
- H2 / Relational Database
- Bean Validation
- Maven

---

## Project Goals
- Learn how to structure a real backend project
- Apply authentication and authorization correctly
- Avoid common beginner mistakes (fat controllers, exposed entities)
- Build a secure user-based Todo API
- Practice clean service and domain separation

---

## Architecture Overview
- **Controllers**  
  Handle HTTP requests and responses only.

- **Services**  
  Contain all business rules and validations.  
  No logic is placed in controllers.

- **Entities**  
  Represent domain models only.  
  Entities do **not** depend on DTOs.

- **DTOs**  
  Control input and output data.

- **Security**  
  Authentication handled via Spring Security.  
  Authenticated user is always resolved server-side.

---

## Security Design
- HTTP Basic Authentication
- Passwords stored using BCrypt hashing
- Authenticated user resolved via SecurityContext
- Users can only access their own data
- No endpoint trusts client-provided user IDs

---

## Main Features

### User
- Register a new user
- Authenticate using email and password
- Get own profile (`/users/me`)
- Update own username
- Update own password
- Delete own account

### Todo
- Create todo linked to authenticated user
- List only own todos
- Update only own todos
- Delete only own todos

---

## Data Model
**User (1) → (N) Todo**

- A Todo always belongs to one User
- A User cannot access another User's Todos
- All relationships enforced at the service level

---

## Error Handling
- Custom domain exceptions for business rules
- Global exception handler using `@RestControllerAdvice`
- Validation errors return field-level messages
- Consistent API error responses

---

## Why This Project Is Simple (On Purpose)
This project intentionally avoids:
- JWT
- Refresh tokens
- Roles and admin logic
- Docker
- Advanced infrastructure

The focus is correctness, clarity, and security fundamentals.  
These features can be added later as natural evolution.

---

## How To Run
1. Clone the repository
2. Open in IDE (IntelliJ recommended)
3. Run `TodolistApplication`
4. Use any REST client (Postman, Insomnia, etc.)
5. Authenticate using HTTP Basic

---

## Final Note
This is my **first backend project**.

The objective was to learn how to build things the **RIGHT way**,  
not the **FAST way**.

Code quality, structure, and security were prioritized  
over adding unnecessary features.
