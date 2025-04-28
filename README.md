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

-login/registration page
![image](https://github.com/user-attachments/assets/458dc096-fab1-4de6-b414-da9028ec1ca3)

- Home Page
- ![image](https://github.com/user-attachments/assets/1070e2ff-9910-4c62-baf8-97778f2ad926)

- Schedule Page
- ![image](https://github.com/user-attachments/assets/73508510-a63b-4ad6-bd4e-be9819b6e563)

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


