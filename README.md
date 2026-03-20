# 📝 Todo API

A RESTful API for task management built with Java 17 and Spring Boot 3.2.

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.4-brightgreen?style=flat-square&logo=springboot)
![Maven](https://img.shields.io/badge/Maven-3.9-red?style=flat-square&logo=apachemaven)
![H2](https://img.shields.io/badge/H2-Database-blue?style=flat-square)
![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)

---

## 📌 About

Todo API is a backend project that implements a full CRUD for task management, following development best practices such as MVC pattern, layered architecture, DTOs, global exception handling, and data validation.

Built as a study project to demonstrate the Spring Boot ecosystem with an in-memory H2 database.

---

## 🚀 Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 17 | Main language |
| Spring Boot | 3.2.4 | Core framework |
| Spring Data JPA | 3.2.4 | Data persistence |
| Spring Validation | 3.2.4 | DTO validation |
| H2 Database | Runtime | In-memory database |
| Lombok | Latest | Boilerplate reduction |
| JUnit 5 | Latest | Unit testing |
| Mockito | Latest | Dependency mocking |

---

## 🏗️ Architecture
```
src/main/java/com/example/todoapi/
│
├── controller/          # Presentation layer — handles HTTP requests
├── service/             # Business layer — application logic and rules
│   └── impl/            # Service implementations
├── repository/          # Data layer — database communication
├── model/               # JPA entities
├── dto/                 # Data transfer objects
│   ├── TaskRequestDTO   # Input data
│   └── TaskResponseDTO  # Output data
└── exception/           # Global error handling
    ├── TaskNotFoundException
    ├── ErrorResponse
    └── GlobalExceptionHandler
```

---

## ⚙️ Prerequisites

- [Java 17+](https://adoptium.net/)
- [Maven 3.9+](https://maven.apache.org/)
- [Git](https://git-scm.com/)

---

## 🔧 Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/henriquebl/todo-api.git
cd todo-api
```

### 2. Run with Maven
```bash
./mvnw spring-boot:run
```

### 3. Or build and run the JAR
```bash
./mvnw clean package
java -jar target/todo-api-0.0.1-SNAPSHOT.jar
```

The application will be available at `http://localhost:8080`

---

## 📡 Endpoints

### Base URL: `http://localhost:8080/api/tasks`

| Method | Endpoint | Description | Status |
|---|---|---|---|
| `GET` | `/api/tasks` | List all tasks | `200 OK` |
| `GET` | `/api/tasks?completed=true` | Filter by status | `200 OK` |
| `GET` | `/api/tasks/{id}` | Get task by ID | `200 OK` |
| `POST` | `/api/tasks` | Create a new task | `201 Created` |
| `PUT` | `/api/tasks/{id}` | Update an existing task | `200 OK` |
| `DELETE` | `/api/tasks/{id}` | Delete a task | `204 No Content` |

---

## 📋 Usage Examples

### Create a task
```http
POST /api/tasks
Content-Type: application/json

{
  "title": "Study Spring Boot",
  "description": "Finish the To-Do List project",
  "completed": false
}
```

**Response `201 Created`:**
```json
{
  "id": 1,
  "title": "Study Spring Boot",
  "description": "Finish the To-Do List project",
  "completed": false,
  "createdAt": "2026-03-20T02:30:00",
  "updatedAt": "2026-03-20T02:30:00"
}
```

---

### List all tasks
```http
GET /api/tasks
```

**Response `200 OK`:**
```json
[
  {
    "id": 1,
    "title": "Study Spring Boot",
    "description": "Finish the To-Do List project",
    "completed": false,
    "createdAt": "2026-03-20T02:30:00",
    "updatedAt": "2026-03-20T02:30:00"
  }
]
```

---

### Update a task
```http
PUT /api/tasks/1
Content-Type: application/json

{
  "title": "Study Spring Boot",
  "description": "Finish the To-Do List project",
  "completed": true
}
```

---

### Delete a task
```http
DELETE /api/tasks/1
```

**Response `204 No Content`** — no body.

---

## ⚠️ Error Handling

### Resource not found `404`
```json
{
  "status": 404,
  "message": "Task not found with id: 99",
  "timestamp": "2026-03-20T02:30:00"
}
```

### Invalid data `400`
```json
{
  "status": 400,
  "message": "Validation error",
  "timestamp": "2026-03-20T02:30:00",
  "errors": [
    "title: Title must be between 3 and 100 characters"
  ]
}
```

---

## 🗄️ Database

This project uses **H2 in-memory database** — data is lost when the application restarts.

Access the visual console at: `http://localhost:8080/h2-console`

| Field | Value |
|---|---|
| JDBC URL | `jdbc:h2:mem:tododb` |
| User Name | `sa` |
| Password | *(leave blank)* |

---

## 🧪 Tests
```bash
# Run all tests
./mvnw test

# Run with coverage report
./mvnw test jacoco:report
```

Tests cover the service layer using JUnit 5 and Mockito, validating both success scenarios and exception handling.

---

## 📁 Configuration

Located at `src/main/resources/application.properties`:
```properties
server.port=8080
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```

---

## 🔮 Roadmap

- [ ] Authentication with Spring Security + JWT
- [ ] API documentation with Swagger / OpenAPI
- [ ] Migrate to PostgreSQL
- [ ] Pagination and sorting on list endpoints
- [ ] Deploy to Railway or Render

---

## 👨‍💻 Author

Made by **[Henrique](https://github.com/henriquebl)**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue?style=flat-square&logo=linkedin)](https://linkedin.com/in/henriquebdl)
[![GitHub](https://img.shields.io/badge/GitHub-Follow-black?style=flat-square&logo=github)](https://github.com/henriquebl)

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
