# SportScape

A microservices-based platform for sports facility management and booking system built with Spring Boot and Angular.

## Overview

SportScape enables users to discover, book, and manage sports facilities through a modern web application. The platform provides comprehensive facility management, user authentication, and booking functionality with real-time availability tracking.

## Architecture

The application follows a microservices architecture with the following components:

### Backend Microservices
- **User Service** (Port 8080) - User management, authentication, and profiles
- **Facility Service** (Port 8085) - Sports facility management and location services  
- **Booking Service** (Port 8087) - Reservation management and booking logic
- **API Gateway** (Port 8083) - Centralized routing and load balancing
- **Eureka Server** (Port 8761) - Service discovery and registration

### Frontend
- **Angular Application** - Modern responsive web interface for user interactions

### Infrastructure
- **PostgreSQL** - Primary database for data persistence
- **Docker** - Containerized deployment with Docker Compose
- **Spring Cloud** - Service communication via OpenFeign clients

## Features

- **Facility Management**: Browse and search sports facilities by location and type
- **User Authentication**: Secure registration and login system
- **Booking System**: Real-time facility booking with availability checking
- **Service Discovery**: Automatic service registration and discovery
- **Scalable Architecture**: Independent microservices for easy scaling
- **Containerized Deployment**: Full Docker support for development and production

## Quick Start

### Prerequisites
- Docker and Docker Compose
- Maven 3.6+
- Java 17+
- Node.js 18+ (for frontend development)

### Running the Application

1. Build the services:
```bash
mvn clean package -Dmaven.test.skip=true
```

2. Build Docker images:
```bash
mvn compile jib:dockerBuild
```

3. Start all services:
```bash
docker-compose up -d
```

### Service URLs
- API Gateway: http://localhost:8083
- Eureka Dashboard: http://localhost:8761
- PostgreSQL: localhost:5432
- PgAdmin: http://localhost:5050

## Technology Stack

- **Backend**: Spring Boot, Spring Cloud, Spring Data JPA
- **Frontend**: Angular, TypeScript
- **Database**: PostgreSQL
- **Service Discovery**: Netflix Eureka
- **API Gateway**: Spring Cloud Gateway
- **Containerization**: Docker, Jib
- **Build Tools**: Maven