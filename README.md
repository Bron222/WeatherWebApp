# WeatherWebApp

A web-based weather application built as a hands-on learning project covering the
full professional stack: REST APIs, HTTP, relational data with CRUD, OOP,
containers, CI, and Git/GitHub workflow.

## Stack

| Layer      | Technology                                            |
| ---------- | ----------------------------------------------------- |
| Backend    | Java 25 (LTS), Spring Boot 4.1, Maven                 |
| Database   | PostgreSQL, Spring Data JPA, Flyway migrations        |
| Frontend   | React 18, Vite, JavaScript                            |
| Weather    | [Open-Meteo](https://open-meteo.com/) (free, no key)  |
| Infra      | Docker + Docker Compose                               |
| CI         | GitHub Actions                                        |

## Repository layout

```
WeatherWebApp/
├── backend/          Spring Boot REST API
├── frontend/         React single-page app
├── docker-compose.yml
└── ROADMAP.md        Phased build plan and learning goals
```

## Status

Phase 1 — backend weather proxy. See [ROADMAP.md](ROADMAP.md) for the full plan.

## Getting started

### Backend

Requires JDK 25 (Eclipse Temurin recommended). From `backend/`:

```bash
./mvnw spring-boot:run
```

The API starts on http://localhost:8080.
Health check: http://localhost:8080/actuator/health

