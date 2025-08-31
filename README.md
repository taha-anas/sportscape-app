SportScape

SportScape is a modern platform for sports facility management and online booking. It enables users to discover, book, and manage sports venues while giving facility owners tools to streamline reservations.

✨ Key Features

Facility Discovery – Search and filter sports venues by location, type, and availability

Smart Booking – Real-time reservation system with conflict prevention

User Accounts – Secure authentication, registration, and profile management

Facility Management – Venue owners can manage schedules and availability

Scalable & Reliable – Built with independent microservices for resilience and growth

🏗️ Microservices Architecture

SportScape follows a domain-driven microservices design:

User Service – Authentication, profiles, and identity management

Facility Service – Facility catalog, locations, and metadata

Booking Service – Reservation logic, availability, and payment integration (future)

API Gateway – Central entry point, request routing, and load balancing

Service Discovery – Eureka registry for automatic service registration and discovery


🚀 Quick Start

Run the entire platform using Docker:

docker-compose up -d


Frontend: http://localhost:4200

API Gateway: http://localhost:8083

🛠️ Tech Stack

Backend: Spring Boot, Spring Cloud (Eureka, Gateway, Feign)

Frontend: Angular, TypeScript

Database: PostgreSQL

Infrastructure: Docker, Docker Compose

📌 About the Project

SportScape was built to demonstrate real-world microservices patterns such as service discovery, API gateway routing, and containerized deployment. It highlights how modern booking platforms can achieve scalability, modularity, and fault isolation using a microservices approach.
