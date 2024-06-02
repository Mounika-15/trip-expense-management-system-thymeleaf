# Trip Expense Management System

This is a Spring Boot application for managing trips and expenses.

## Table of Contents

- [Requirements](#requirements)
- [Installation](#installation)
- [Configuration](#configuration)
- [Database Setup](#database-setup)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Swagger Openapi](#swagger-openapi)

## Requirements

- Java 17 or higher
- Maven 3.6.0 or higher
- MySQL

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Mounika-15/trip-expense-management-system.git
   ```

2. **Build the application:**
   ```bash
   mvn clean install
   ```

## Configuration

1. **Database Configuration:**
   ```properties
   spring.application.name=trip-expense-management-system
   spring.datasource.url=jdbc:mysql://localhost:3306/trip_expense_db
   spring.datasource.username=root
   spring.datasource.password=password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
   
   ##JWT
   app.jwt.secret=#JWT secret
   app.jwt-expiration-milliseconds=#JWT expirt time in millisec
   ```

## Database Setup

1. **Create the MySQL database:**
   ```sql
   CREATE DATABASE trip_expense_db;
   ```

2. **Tables will be created automatically** based on the JPA entities when you run the application for the first time.

## Running the Application

1. **Run the Spring Boot application:**
   ```bash
   mvn spring-boot:run
   ```
   Alternatively, you can run the application from your IDE by running the main method in the
   com.example.TripExpenseManagementSystemApplication class.

## API EndPoints

**User Endpoints:**

- `POST /api/user/register` - Create a new user
- `POST /api/user/login` - Login

**Trip Endpoints:**

- `POST /api/trips` - Create a new trip
- `PUT /api/trips/{id}` - Update a trip
- `GET /api/trips` - Get All trip details
- `DELETE /api/trips/{id}` - Delete trip by ID

**Expense Endpoints:**

- `POST /api/expenses` - Create a new expense
- `PUT /api/expesnes/{id}` - Update expense
- `GET /api/expenses` - Get expense details by ID
- `DELETE /api/expenses/{id}` - Delete expense by ID

## Swagger Openapi
**Swagger UI:** http://localhost:8080/swagger-ui/index.html
- Open the above Swagger Openapi link to access all APIs