# CarHop Backend

CarHop Backend is a Spring Boot application that provides backend services for the CarHop application. It includes features for managing users, sessions, reviews, and ride requests.

---

## Features

- **User Management**: Add, update, retrieve, and delete user information.
- **Session Management**: Start, update, retrieve, and delete ride sessions with filters.
- **Review System**: Add and retrieve reviews for users.
- **Ride Requests**: Send, approve, reject, and cancel ride requests.
- **Authentication**: User registration, login, and role assignment.
- **Security**: JWT-based authentication and role-based access control.

---

## Project Structure

The project follows a standard Maven-based structure:

```
src/
├── main/
│   ├── java/
│   │   └── com.dotoku.carhop/
│   │       ├── controller/       # REST controllers
│   │       ├── dto/              # Data Transfer Objects
│   │       ├── entity/           # JPA entities
│   │       ├── repository/       # Spring Data JPA repositories
│   │       ├── security/         # Security configurations and utilities
│   │       ├── service/          # Business logic services
│   │       └── exception/        # Global exception handling
│   └── resources/
│       ├── application.yml       # Configuration file
│       └── application.properties # Active configuration file
└── test/
    └── java/
        └── com.dotoku.carhop/    # Unit tests
```

---

## Prerequisites

- **Java**: Version 17 or higher
- **Maven**: Version 3.9.9 or higher
- **PostgreSQL**: Database server
- **Docker**: For containerization (optional)

---

## Getting Started

### 1. Clone the Repository

Clone the repository and navigate to the project directory:

```bash
git clone https://github.com/your-repo/carhop-backend.git
cd carhop-backend
```

### 2. Configure the Database

Update the `src/main/resources/application.yml` file with your PostgreSQL database credentials.

### 3. Build the Project

Use Maven to build the project:

```bash
./mvnw clean install
```

### 4. Run the Application

Start the application using the Maven wrapper:

```bash
./mvnw spring-boot:run
```

The application will be available at [http://localhost:8080](http://localhost:8080).

### 5. Run with Docker (Optional)

Build and run the Docker container:

```bash
docker build -t carhop-backend .
docker run -p 8080:8080 carhop-backend
```

---

## API Endpoints

### Authentication Endpoints

- `POST /api/v1/auth/register` - Register a new user
- `POST /api/v1/auth/login` - Login with email and password
- `POST /api/v1/auth/role` - Assign roles to a user

### User Endpoints

- `POST /api/v1/user` - Add a new user
- `GET /api/v1/user/{userId}` - Retrieve user details
- `PUT /api/v1/user/{userId}` - Update user details
- `DELETE /api/v1/user/{userId}` - Delete a user
- `PUT /api/v1/user/{userId}/vehicle` - Update user's vehicle

### Session Endpoints

- `POST /api/v1/session` - Start a new session
- `GET /api/v1/session` - Retrieve sessions with filters
- `PUT /api/v1/session/{sessionId}` - Update a session
- `DELETE /api/v1/session/{sessionId}` - Delete a session
- `POST /api/v1/session/{sessionId}/time` - Extend session time
- `GET /api/v1/session/user/{userId}` - Retrieve sessions for a specific user

### Review Endpoints

- `POST /api/v1/review/{userId}` - Add a review for a user
- `GET /api/v1/review/{userId}` - Retrieve reviews for a user

### Request Endpoints

- `POST /api/v1/request` - Send a ride request
- `DELETE /api/v1/request/{requestId}` - Cancel a ride request
- `PUT /api/v1/request/{requestId}` - Approve or reject a ride request
- `GET /api/v1/request/user/{userId}` - Retrieve requests made by a user
- `GET /api/v1/request/{requestId}` - Retrieve a specific request

---

## Technologies Used

- **Spring Boot**: Backend framework
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Database interaction
- **PostgreSQL**: Database
- **Lombok**: Simplify Java code
- **SpringDoc OpenAPI**: API documentation
- **JUnit**: Unit testing
- **Docker**: Containerization

---

## Running Tests

Run the tests using Maven:

```bash
./mvnw test
```

---

## CI/CD Pipeline

A GitHub Actions workflow is configured to build and push Docker images:

- **Workflow File**: `.github/workflows/maven.yml`
- **Docker Image**: `sandesh2020/carhop:latest`

---

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes and push them to your fork.
4. Submit a pull request.

---

## Contact

For questions or support, please contact the project maintainer:

- **Email**: sandesh.ghimire2020@gmail.com


