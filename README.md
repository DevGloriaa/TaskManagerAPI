# Task Manager API

A lightweight and flexible REST API built with **Java Spring Boot** for managing tasks.  
It provides endpoints to create, update, delete, and retrieve tasks — ideal for productivity apps or as a learning project in backend development.

---

## 🚀 Features

### 🔐 User Authentication (JWT)  
- Secure login system with JWT-based authentication.  
- Each user can only manage their own tasks and categories.  

### ✅ Task Management  
- Create, read, update, and delete tasks.  
- Each task includes:
  - Title  
  - Description  
  - Due date  
  - Priority  
  - Completion status  
  - Category (linked to the user’s categories)  
- **Search and filter tasks** with flexible criteria (e.g., by title, priority, due date range, or completion status).  

### 🗂️ Category Management  
- Create and manage categories per user.  
- Each category is linked to the authenticated user.  
- Endpoints available to:
  - Get all categories for a user  
  - Fetch a category by **ID**  
  - Fetch a category by **name**  
  - Delete a category  

### 🔗 Task ↔ Category Integration  
- Assign tasks to categories during creation.  
- Fetch tasks by **category ID**.  
- Fetch tasks by **category name** (no need to remember IDs).  

### 🛡️ Security  
- Every request is scoped to the authenticated user via JWT.  
- Users can only access their own tasks and categories.  


 

---

## Tech Stack
- **Backend:** Java, Spring Boot  
- **Database:** MongoDB Atlas 
- **Build Tool:** Maven  
- **API Format:** RESTful JSON  

---

## Installation & Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/DevGloriaa/TaskManagerAPI.git

2. Build and run the project using Maven:
   ```bash
   mvn spring-boot:run








