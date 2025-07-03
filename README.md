# TaskManagement
# Project Idea: Task Management API
## Project Features:
1. User Authentication with JWT
   User registration
   User login with JWT token generation
   Password encryption
   Protected routes
2. Basic CRUD Operations
   Create, Read, Update, Delete tasks
   Database integration
3. Simple Additional Features
    Task status updates
    Basic user profile management
Technology Stack:
Spring Boot
Spring Security with JWT
Hibernate (JPA)
MySQL/PostgreSQL (you can choose)
Maven/Gradle (you can choose)
Database Schema:
1. Users Table
    id (primary key)
    username (unique)
    email (unique)
    password (encrypted)
    created_at
2. Tasks Table
   id (primary key)
   title
   description
   status (TODO, IN_PROGRESS, DONE)
   due_date
   user_id (foreign key to Users)
   created_at
   updated_at
API Endpoints:
Authentication:
POST /api/auth/register - Register new user
POST /api/auth/login - Login and get JWT token
Tasks (all require authentication):
GET /api/tasks - Get all tasks for logged-in user
POST /api/tasks - Create new task
GET /api/tasks/{id} - Get single task
PUT /api/tasks/{id} - Update task
DELETE /api/tasks/{id} - Delete task
PATCH /api/tasks/{id}/status - Update task status
User Profile:
GET /api/users/me - Get current user profile
PUT /api/users/me - Update user profile
