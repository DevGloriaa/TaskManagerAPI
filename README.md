# Task Manager API

A lightweight and flexible REST API built with **Java Spring Boot** for managing tasks.  
It provides endpoints to create, update, delete, and retrieve tasks — ideal for productivity apps or as a learning project in backend development.

---

## Features
- ➕ Create new tasks  
- ✏️ Update existing tasks  
- 🗑️ Delete tasks  
- 📋 Retrieve all tasks or a single task  
- ✅ Mark tasks as completed  

---

## Tech Stack
- **Backend:** Java, Spring Boot  
- **Database:** H2 (in-memory) / MySQL / PostgreSQL (configurable)  
- **Build Tool:** Maven  
- **API Format:** RESTful JSON  

---

## Installation & Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/DevGloriaa/task-manager-api.git
   cd task-manager-api

2. Build and run the project using Maven:
   ```bash
   mvn spring-boot:run

 3. The API will be available at:
    ```bash
    http://localhost:8081/api/tasks


## API Endpoints

| Method | Endpoint            | Description       |
|--------|---------------------|-------------------|
| GET    | `/api/tasks`        | Get all tasks     |
| GET    | `/api/tasks/{id}`   | Get a single task |
| POST   | `/api/tasks`        | Create a new task |
| PUT    | `/api/tasks/{id}`   | Update a task     |
| DELETE | `/api/tasks/{id}`   | Delete a task     |





