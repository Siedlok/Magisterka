# Project and Task Management Application

A web application developed as part of my Master's thesis for managing projects and tasks. 

The application allows users to create and manage projects, organize tasks, track their status, deadlines, and view project-related statistics. It also includes user authentication and authorization.

## Features
- User registration and login
- Authentication and authorization with Spring Security
- Project management 
- Task creation, editing, deletion
- Task status management
- Task completion tracking
- Deadlines and task scheduling
- Project and task statistics
- Server-side rendered web interface

## Tech stack
| Technology          | Purpose                          |
|---------------------|----------------------------------|
| **Java 21**         | Main programming language        |
| **Spring boot**     | Application framework            |
| **Spring Security** | Authentication and authorization |
| **MongoDB**         | NoSQL Database                   |
| **Spring Data**     | Database access                  |
| **Maven**           | Build a dependency management    |
| **Docker**          | Containerization                 |
| **CSS**             | Application appearance           |
| **Thymeleaf**       | Server-side HTML rendering       |


## Requirements
- Java 21+
- Maven
- MongoDB

## REST API

| Method   | Endpoint          | Description             |
|----------|-------------------|-------------------------|
 | `GET`    | `/api/tasks`      | Retrieve all tasks      |
 | `GET`    | `/api/tasks/{id}` | Retrieve a task by ID   |
 | `POST`   | `/api/tasks`      | Create a new task       |
 | `PUT`    | `/api/tasks/{id}` | Update an existing task |
 | `DELETE` | `/api/tasks/{id}` | Delete a task           |

The REST API accepts and returns task data in JSON format.

## Web endpoints

### Authentication
| Method | Endpoint    | Description              |
|--------|-------------|--------------------------|
| `GET`  | `/login`    | Display login page       |
| `GET`  | `/register` | Display registration page|
| `POST` | `/register` | Register a new user      |
| `GET`  | `/logout`   | Log out the new user     |

### Projects
| Method | Endpoint                | Description                          |
|--------|-------------------------|--------------------------------------|
| `GET`  | `/projects`             | Display all projects                 |
| `GET`  | `/projects/new`         | Display project creation form        |
| `POST` | `/projects`             | Create a new project                 |
| `GET`  | `/projects/{id}`        | Display tasks belonging to a project |
| `POST` | `/projects/{id}/delete` | Delete a project                     |

### Tasks
| Method | Endpoint               | Description                |
|--------|------------------------|----------------------------|
| `GET`  | `/tasks`               | Display all tasks          |
| `GET`  | `/tasks/new`           | Display task creation form |
| `POST` | `/tasks`               | Create a new task          |
| `GET`  | `/tasks/edit/{id}`     | Display task editing form  |
| `POST` | `/tasks/edit/{id}`     | Update a task              |
| `GET`  | `/tasks/delete/{id}`   | Delete a task              |
| `POST` | `/tasks/{id}/complete` | Mark a task as completed   |

### Test Data API

Additional endpoints are available for generating and removing data.

| Method   | Endpoint                                              | Description         |
|----------|-------------------------------------------------------|---------------------|
| `POST`   | `/test-data/projects/{projectId}/tasks?count={count}` | Generate test tasks |
| `DELETE` | `/test-data/projects/{projectId}/tasks`               | Delete test tasks   |






