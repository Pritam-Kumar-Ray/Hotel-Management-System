# 🏨 Hotel Management System — Microservices

A **Spring Boot-based Hotel Management System** built using a microservices architecture.

The project demonstrates how multiple independently deployable services communicate with each other using **REST APIs, OpenFeign, Eureka Service Discovery, Spring Cloud Gateway, Centralized Configuration, Docker, Kafka, Redis, MongoDB, PostgreSQL, and OpenTelemetry**.

The project is organized as **independent GitHub repositories for each microservice**, allowing developers to clone and work with only the service they need.

---

## 🏗️ Architecture

```text
                         ┌─────────────────────┐
                         │      Client         │
                         │ Postman / Frontend  │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │    API Gateway      │
                         │      :8084          │
                         └──────────┬──────────┘
                                    │
                 ┌──────────────────┼──────────────────┐
                 │                  │                  │
                 ▼                  ▼                  ▼
        ┌────────────────┐ ┌────────────────┐ ┌────────────────┐
        │  User Service  │ │ Hotel Service  │ │ Rating Service │
        │     :8081      │ │     :8082      │ │     :8083      │
        └───────┬────────┘ └────────────────┘ └───────┬────────┘
                │                                      │
                └──────────────┐          ┌────────────┘
                               ▼          ▼
                         ┌─────────────────────┐
                         │   Service Registry  │
                         │      Eureka :8761   │
                         └─────────────────────┘

                         ┌─────────────────────┐
                         │   Config Server     │
                         │      :8085           │
                         └─────────────────────┘
```

---

## 📦 Microservices

Each microservice is maintained as an **independent GitHub repository**.

| Service             | Description                                                                | Repository                                                             |
| ------------------- | -------------------------------------------------------------------------- | ---------------------------------------------------------------------- |
| 🚪 API Gateway      | Single entry point for client requests, routing and cross-cutting concerns | [ApiGateway](https://github.com/Pritam-Kumar-Ray/ApiGateway)           |
| ⚙️ Config Server    | Centralized configuration management                                       | [ConfigServer](https://github.com/Pritam-Kumar-Ray/ConfigServer)       |
| 🏨 Hotel Service    | Manages hotel-related operations                                           | [HotelService](https://github.com/Pritam-Kumar-Ray/HotelService)       |
| ⭐ Rating Service    | Manages user and hotel ratings                                             | [RatingService](https://github.com/Pritam-Kumar-Ray/RatingService)     |
| 🔎 Service Registry | Eureka-based service discovery                                             | [ServiceRegistry](https://github.com/Pritam-Kumar-Ray/ServiceRegistry) |
| 👤 User Service     | Manages users and user-related operations                                  | [UserService](https://github.com/Pritam-Kumar-Ray/UserService)         |

> **Note:** Repository links will become active once the individual repositories are created.

---

## 🎯 Why Separate Repositories?

Each microservice is independently maintained so that developers do not need to clone the entire system.

For example, if you only want to work with the **Hotel Service**:

```bash
git clone https://github.com/Pritam-Kumar-Ray/HotelService.git
```

Similarly, you can clone only the services you need.

This approach makes the project closer to a real-world **multi-repository microservices architecture**.

---

## 🛠️ Technology Stack

### Backend

* Java
* Spring Boot
* Spring MVC
* Spring Data JPA
* Spring Data MongoDB
* Spring Cloud
* Spring Cloud OpenFeign

### Microservices & Cloud

* Spring Cloud Gateway
* Netflix Eureka
* Spring Cloud Config
* OpenFeign
* Circuit Breaker
* Rate Limiting
* Retry

### Databases & Messaging

* PostgreSQL
* MongoDB
* Redis
* Apache Kafka

### Observability

* OpenTelemetry
* OTLP
* Micrometer
* Zipkin / jaeger
* OpenTelemetry Collector

### Containerization

* Docker
* Docker Compose

### Development

* Maven
* IntelliJ IDEA
* Git
* GitHub

---

## 🔄 Communication Between Services

The services communicate with each other using different mechanisms depending on the use case.

### REST API

Synchronous communication using REST APIs.

```text
User Service → Rating Service
```

### OpenFeign

Declarative HTTP communication between microservices.

```text
User Service
     │
     ├── Feign Client
     │
     ▼
Rating Service
```

### Service Discovery

Services register themselves with **Eureka Service Registry**.

```text
                Eureka
                  │
       ┌──────────┼──────────┐
       ▼          ▼          ▼
     USER       HOTEL      RATING
   SERVICE     SERVICE     SERVICE
```

Services can therefore communicate using service names instead of hard-coded hostnames.

---

## 🚪 API Gateway

The API Gateway acts as the single entry point for external clients.

Example routes:

```text
http://localhost:8084/user-service/**
http://localhost:8084/hotel-service/**
http://localhost:8084/rating-service/**
```

The Gateway forwards requests to the appropriate service using Eureka service discovery.

---

## ⚙️ Centralized Configuration

Configuration is managed using **Spring Cloud Config Server**.

```text
                    Config Server
                         │
          ┌──────────────┼──────────────┐
          ▼              ▼              ▼
      User Service   Hotel Service   Rating Service
```

This allows common and environment-specific configuration to be maintained centrally.

---

## 📊 Observability

The project also demonstrates distributed tracing and observability using:

```text
Application
     │
     ▼
OpenTelemetry
     │
     ▼
OTLP
     │
     ▼
OpenTelemetry Collector
     │
     ▼
   Zipkin / jaeger
```

This allows requests to be traced across multiple microservices using distributed tracing.

---

## 🐳 Docker

The services can be containerized using Docker.

Docker Compose can be used to start the required infrastructure and services.

Example:

```bash
docker compose up --build
```

To stop the containers:

```bash
docker compose down
```

---

## 🚀 Getting Started

### Prerequisites

Make sure the following are installed:

* Java
* Maven
* Docker
* Docker Compose
* Git
* IntelliJ IDEA (recommended)

Depending on the service, you may also need:

* PostgreSQL
* MongoDB
* Redis
* Kafka

---

## ▶️ Recommended Startup Order

For a local environment, the services can be started in the following order:

```text
1. Infrastructure
      │
      ├── PostgreSQL
      ├── MongoDB
      ├── Redis
      └── Kafka
      │
      ▼
2. Config Server
      │
      ▼
3. Service Registry (Eureka)
      │
      ▼
4. Microservices
      │
      ├── User Service
      ├── Hotel Service
      └── Rating Service
      │
      ▼
5. API Gateway
      │
      ▼
6. OpenTelemetry / Zipkin / jaeger
```

---

## 🔌 Default Ports

| Component               |   Port |
| ----------------------- | -----: |
| User Service            | `8081` |
| Hotel Service           | `8082` |
| Rating Service          | `8083` |
| API Gateway             | `8084` |
| Config Server           | `8085` |
| Eureka Service Registry | `8761` |
| Zipkin                  | `9411` |
| jaeger                  | `4317` |
| OTLP gRPC               | `4317` |
| OTLP HTTP               | `4318` |

---

## 📁 Project Structure

The parent repository contains the overall project documentation and architecture.

Individual microservices are maintained independently:

```text
Hotel-Management-System
│
├── ApiGateway
├── ConfigServer
├── HotelService
├── RatingService
├── ServiceRegistry
└── UserService
```

Each service has its own repository and can be developed, built, tested and deployed independently.

---

## 📚 Concepts Demonstrated

This project is primarily a learning and practical implementation project covering:

* Microservices Architecture
* Service Discovery
* API Gateway
* Centralized Configuration
* Synchronous Communication
* OpenFeign
* REST APIs
* Circuit Breaker
* Retry
* Rate Limiting
* Distributed Tracing
* Correlation ID / Trace ID
* OpenTelemetry
* Micrometer
* Zipkin / jaeger
* Kafka
* Redis
* PostgreSQL
* MongoDB
* Docker
* Docker Compose
* Saga Pattern
* Fault Tolerance
* Centralized Logging
* Observability

---

## 🔮 Future Improvements

Some possible improvements planned for the project:

* Kubernetes deployment
* CI/CD pipeline
* Prometheus and Grafana integration
* Improved centralized logging
* Authentication and authorization using JWT/OAuth2
* Distributed configuration management
* Production-ready Docker images
* Automated testing
* Cloud deployment

---

## 👨‍💻 Author

**Pritam Kumar Ray**

GitHub:
https://github.com/Pritam-Kumar-Ray

---

## ⭐ If You Find This Project Useful

Feel free to explore the individual microservice repositories, raise issues, suggest improvements, or use the project as a reference for learning Spring Boot and microservices architecture.
