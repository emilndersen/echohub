# 📣 EchoHub — Forum Platform on Spring Boot

**EchoHub** is a monolithic web application that implements the basic functionality of a forum platform. It is built using **Spring Boot** and connected to a **PostgreSQL** database hosted on **Neon.tech**. The application includes standard **CRUD** operations, follows a layered architecture, and separates concerns between persistence, business logic, and controllers.

---

## 🔧 Technologies

- Java 23  
- Spring Boot 3.5.0 (M3)  
- Spring Data JPA  
- Spring MVC  
- PostgreSQL (Neon)  
- Lombok  
- Gradle  
- REST API (partial)  
- Thymeleaf (frontend placeholder)

---

## 🧱 Architecture

The project follows a monolithic structure, separated logically by responsibility:

- `model` — JPA entities representing business domain objects  
- `repository` — interfaces for database interaction via Spring Data JPA  
- `service` — business logic layer  
- `controller` — HTTP endpoint handlers  
- `config` — configuration files (e.g., for security or CORS)

---

## 📦 Features

- **User Management**  
  - Create, read, delete users  
  - Store user status (e.g., ACTIVE, BANNED)

- **Post Management**  
  - Create and retrieve posts  
  - Edit and delete posts

- **Comment System**  
  - Link comments to posts and users  
  - Update/delete comments

- **Tags**  
  - Create tags  
  - Search by tag name

- **Role System**  
  - Assign roles to users via the `UserRole` mapping entity

- **Timestamps**  
  - Automatically handled with JPA lifecycle methods (`@PrePersist`, `@PreUpdate`)

---

## 💾 Database

- Uses **PostgreSQL**, hosted on **Neon.tech**
- Tables:
  - `users`, `posts`, `comments`, `tags`, `user_roles`
- Automatic schema generation via Hibernate

---

## 🛠 Future Improvements

- Add **Spring Security** for authentication and registration  
- Implement **JWT** or **OAuth2**  
- Add frontend (React or Thymeleaf)  
- Database migration support via Flyway or Liquibase  
- Docker support for deployment  
- Modular refactoring (or microservices split)

---

## 🚀 Getting Started

```bash
./gradlew bootRun
