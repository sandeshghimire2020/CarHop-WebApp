
# CarHop Backend

CarHop Backend is a Spring Boot application that provides backend services for the CarHop application. It includes features for managing users, sessions, reviews, and ride requests.

## Features

- **User Management**: Add, update, retrieve, and delete user information.
- **Session Management**: Start and retrieve ride sessions with filters.
- **Review System**: Add and retrieve reviews for users.
- **Ride Requests**: Send and cancel ride requests.

## Project Structure

The project follows a standard Maven-based structure:

```
src/
├── main/
│   ├── java/
│   │   └── com.dotoku.carhop/
│   │       ├── controller/    # REST controllers
│   │       ├── dto/           # Data Transfer Objects
│   │       ├── entity/        # JPA entities
│   │       ├── repository/    # Spring Data JPA repositories
│   │       └── service/       # Business logic services
│   └── resources/
│       ├── application.yml    # Configuration file (commented out)
│       └── application.properties # Active configuration file
└── test/
    └── java/
        └── com.dotoku.carhop/ # Unit tests
```

## Prerequisites

- Java 17 or higher
- Maven 3.9.9 or higher
- PostgreSQL database

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-repo/carhop-backend.git
cd carhop-backend
```

### 2. Configure the Database

Update the `src/main/resources/application.properties` file with your PostgreSQL database credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/carhop
spring.datasource.username=your_username
spring.datasource.password=your_password
```

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

The application will be available at `http://localhost:8080`.

## API Endpoints

### User Endpoints

- **POST** `/api/v1/user` - Add a new user
- **GET** `/api/v1/user/{userId}` - Retrieve user details
- **PUT** `/api/v1/user/{userId}` - Update user details
- **DELETE** `/api/v1/user/{userId}` - Delete a user

### Session Endpoints

- **POST** `/api/v1/session` - Start a new session
- **GET** `/api/v1/session` - Retrieve sessions with filters

### Review Endpoints

- **POST** `/api/v1/review/{userId}` - Add a review for a user
- **GET** `/api/v1/review/{userId}` - Retrieve reviews for a user

### Request Endpoints

- **POST** `/api/v1/request` - Send a ride request
- **DELETE** `/api/v1/request/{requestId}` - Cancel a ride request

## Technologies Used

- **Spring Boot**: Backend framework
- **Spring Data JPA**: Database interaction
- **PostgreSQL**: Database
- **Lombok**: Simplify Java code
- **SpringDoc OpenAPI**: API documentation
- **JUnit**: Unit testing

## Running Tests

Run the tests using Maven:

```bash
./mvnw test
```

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

## Contact

For questions or support, please contact the project maintainer at `sandesh.ghimire2020@gmail.com`.
```


![My Image](images/swagger.png)
