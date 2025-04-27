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

##  Technology Stack

| Backend         | Frontend            | Database     | Deployment              |
| --------------- | ------------------- | ------------ | ----------------------- |
| Spring Boot 3.x | React + TypeScript   | PostgreSQL   | Docker, Docker Compose  |
| Spring Security | Vite                 | Flyway       | Swagger UI (OpenAPI 3)   |
| JWT, OAuth2     | React Router         |              |                         |

---

##  Layout of webpages


- Home Page
- Schedule Page
- Teacher Dashboard

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


