#  Swallow's Nest Yoga — Yoga Studio Management App


Built with **Spring Boot**, **React**, **PostgreSQL**, and fully **Dockerized**.

---

##  Features

-  Secure user authentication with **JWT**
-  Google OAuth2 login support
-  Role-based access: **USER**, **TEACHER**, **ADMIN**
-  Booking system for yoga classes
-  Email notifications for bookings and class cancellations
-  Teacher dashboard to create, edit, and delete classes
-  Swagger API Documentation (OpenAPI 3)
-  Full Dockerized deployment with Docker Compose
-  Modern UI built with React + Vite for smooth UX

---
## Technologies:

| Layer        | Technologies Used                                                                 |
|--------------|------------------------------------------------------------------------------------|
| **Backend**  | Spring Boot 3.x, Spring Security, JWT, OAuth2, RabbitMQ, Flyway, Swagger (OpenAPI 3) |
| **Frontend** | React 18 + TypeScript, Vite, React Router, TailwindCSS                            |
| **Database** | PostgreSQL, JPA (Hibernate), UUID IDs, 3NF schema                                 |
| **Deployment** | Docker, Docker Compose (multi-service setup)                                     |

---


###  Backend
- **Spring Boot 3**: Production-ready framework for secure REST APIs and service architecture.
- **Spring Security + JWT + OAuth2**: Standard for modern, stateless authentication.
- **RabbitMQ**: Asynchronous message handling (e.g. emails, system events).
- **Flyway**: Safe and repeatable schema migrations.
- **Swagger (OpenAPI 3)**: Live API documentation for frontend/backend integration.

###  Frontend
- **React 18 + Vite**: Modern SPA stack with fast dev experience and concurrent rendering.
- **TypeScript**: Improves maintainability and prevents runtime errors.
- **React Router**: Clean client-side navigation.
- **TailwindCSS**: Rapid and responsive UI design.

###  Database
- **PostgreSQL**: Reliable relational database with UUID support and advanced indexing.
- Designed in **Third Normal Form (3NF)** to prevent redundancy and ensure relational integrity.

###  Deployment
- **Docker**: Enables consistent local and cloud deployment.
- **Docker Compose**: Manages backend, frontend, and PostgreSQL services as a stack.

---
##  Application Architecture

###  Overview

The backend follows a **layered architecture** inspired by **MVC** and **hexagonal design**:

- **Controller Layer** – Handles REST requests
- **Service Layer** – Contains business logic
- **Repository Layer** – JPA-based data access
- **DTO Layer** – Transfers sanitized request/response data
- **Exception Layer** – Global error handling using `@ControllerAdvice`

External adapters: PostgreSQL, RabbitMQ, Swagger

###  Project Structure

backend/
config – app configuration (security, Swagger, CORS) <br>
controller – API endpoints <br>
dto – request and response data objects <br>
entity – database models (JPA entities) <br>
exception – error handling <br>
repository – database access (JPA repositories) <br>
seed – initial data loaders <br>
service – business logic <br>

### Data Flow Diagram

Frontend (React) <br>
Controller (API) <br>
Service (business logic) <br>
Repository (database access) <br>
PostgreSQL (database)



## Database Architecture
![Untitled (1)](https://github.com/user-attachments/assets/36bcd2d1-93f6-4cf1-90c0-774e029aeb3e)
Key Many-to-Many Relationships
- Instructor ↔ Specialty
An instructor can have multiple specialties, and a specialty can apply to many instructors.
- User ↔ Course (via Review)
Users can leave multiple reviews on different courses; each course can be reviewed by many users.
- User Roles (Set<String> roles)
A user can have multiple roles (USER, TEACHER, ADMIN), stored in a separate user_roles table.



##  Layout of webpages

-login/registration page
![image](https://github.com/user-attachments/assets/458dc096-fab1-4de6-b414-da9028ec1ca3)

- Home Page
 ![image](https://github.com/user-attachments/assets/1070e2ff-9910-4c62-baf8-97778f2ad926)

- Schedule Page
 ![image](https://github.com/user-attachments/assets/73508510-a63b-4ad6-bd4e-be9819b6e563)

- Teacher Dashboard
![image](https://github.com/user-attachments/assets/a8544c5d-cfd2-4834-8e02-1fff474f5739)

---



## User Roles and Permissions

| Role         | Permissions            |
| --------------- | ------------------- | 
|USER | View classes, Book/Cancel classes   | 
| TEACHER | Manage own schedules (create, delete)  | 
| ADMIN     | Full access: manage users, instructors, courses, levels         | 


## Email Notifications
- Booking a class → Confirmation email
- Canceling a booking → Cancellation email
- Teacher cancels a class → Notify all booked users

All emails are sent automatically using Gmail SMTP.

## Eunning the Application with Docker
This project is fully Dockerized. You can run the entire stack (backend, frontend, and database) using Docker Compose.

Prerequisites
Docker
Docker Compose

Run the App
docker-compose up --build

## Swagger API Docs: 
http://localhost:8000/swagger-ui/index.html

## Frontend: 
http://localhost:8080


