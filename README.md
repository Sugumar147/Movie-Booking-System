# Movie Booking System

## Overview

This Spring Boot project is a Movie Booking System that allows users to book movie tickets and manage their bookings. It uses PostgreSQL as the database to store user and booking information.

## Prerequisites

- JDK 21 or later
- Gradle
- PostgreSQL
- An IDE (such as IntelliJ IDEA or Eclipse) or a text editor

## Database Schema

The application requires a PostgreSQL database with the following schema:

### `bookings` Table

```sql
CREATE TABLE bookings (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    booking_id VARCHAR(50) NOT NULL,
    movie VARCHAR(100) NOT NULL,
    theatre VARCHAR(100) NOT NULL,
    timing VARCHAR(100) NOT NULL,
    seatlist TEXT[] NOT NULL,
    amount INTEGER NOT NULL
);
```

### `bookings` Table

```sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    country_code VARCHAR(10) NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);
```

## Database Configuration

Before running the application, ensure you have PostgreSQL installed and configured. The application connects to a PostgreSQL database with the following details:

- **Database Name**: `postgres`
- **Username**: `postgres`
- **Password**: `password`

## Contributing

Contributions are welcome! Please create issues or submit pull requests. Ensure you follow the project's coding standards and verify that all tests pass before submitting your changes.

Contact
For questions or comments, please contact:

Author: Sugumar

Email: sugumarjayasurya7@gmail.com
