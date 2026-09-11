# WeatherWebApp — Roadmap

A living plan for building the app phase by phase. Each phase is a set of small,
committable steps done on a feature branch and merged via a pull request.

## Learning objectives

- The stack: Java/Spring Boot, React, PostgreSQL, Maven, Docker
- Professional coding concepts: layered architecture, DTOs, dependency injection,
  error handling, testing
- Software development strategy: incremental delivery, feature branches, PR review
- Scaffolding & configuration: project generators, build files, env config
- API / HTTP / REST: designing resource endpoints, status codes, content types
- OOP: entities, services, interfaces, encapsulation
- CRUD & SQL: hand-written Flyway migrations, JPA repositories, query methods
- Git & GitHub: branching, conventional commits, pull requests, CI

## Architecture

```
React SPA  ──HTTP/JSON──▶  Spring Boot API  ──▶  Open-Meteo API
                                  │
                                  ▼
                            PostgreSQL
```

## REST API surface

| Method | Path                     | Purpose                          |
| ------ | ------------------------ | -------------------------------- |
| GET    | /api/geocode?q=          | Search cities (proxy Open-Meteo) |
| GET    | /api/weather?lat=&lon=   | Forecast (proxy Open-Meteo)      |
| GET    | /api/favorites           | List saved locations             |
| POST   | /api/favorites           | Save a location                  |
| PUT    | /api/favorites/{id}      | Update a saved location          |
| DELETE | /api/favorites/{id}      | Remove a saved location          |
| GET    | /api/search-history      | List recent searches             |
| DELETE | /api/search-history/{id} | Delete one history entry         |
| DELETE | /api/search-history      | Clear history                    |

## Data model

- **favorite_location**: id, name, latitude, longitude, country, created_at
- **search_history**: id, query_text, resolved_name, latitude, longitude, searched_at

## Phases

### Phase 0 — Setup (done)
- [x] Verify toolchain (JDK, Node, Maven, Docker, git, gh)
- [x] `git init`, local ignore rules, README, this roadmap
- [x] First commit + create public GitHub repo `WeatherWebApp` + push

### Phase 1 — Backend weather proxy (no database)
- [x] Generate Spring Boot project — Spring Boot 4.1.1, Java 25, Maven;
      dependencies: Web (MVC), Validation, Actuator, DevTools
- [x] Rename base package to `com.weatherwebapp` (project-name-based, no
      personal identifiers, instead of `io.github.bron222`)
- [ ] Add springdoc-openapi to `pom.xml` by hand
- [x] Geocode DTOs — outbound (`GeocodeResult`, `GeocodeResponse`) and inbound
      (`OpenMeteoPlace`, `OpenMeteoGeocodingResponse`)
- [x] Typed config (`OpenMeteoProperties` via `@ConfigurationProperties`) and
      `OpenMeteoGeocodingClient` (`RestClient` call to Open-Meteo geocoding)
- [ ] `GeocodeService` (map Open-Meteo results → `GeocodeResult`) and
      `GeocodeController` exposing `GET /api/geocode`
- [ ] Weather DTOs, client, service, controller for `GET /api/weather`
- [ ] `@RestControllerAdvice` error handling
- [ ] Unit tests (Mockito) + web-layer tests (MockMvc)
- [ ] Swagger UI available at `/swagger-ui/index.html`

### Phase 2 — Frontend
- [ ] Generate Vite React app
- [ ] Search box → geocode results → current conditions + multi-day forecast
- [ ] Components, custom hooks, TanStack Query for data fetching
- [ ] Backend CORS config; loading and error states

### Phase 3 — Database + CRUD
- [ ] `docker-compose.yml` with PostgreSQL
- [ ] Flyway migrations (hand-written SQL) for both tables
- [ ] JPA entities + Spring Data repositories
- [ ] Favorites CRUD endpoints + tests (Testcontainers)
- [ ] Auto-record searches into search_history
- [ ] Frontend: favorites list (add/remove), history view

### Phase 4 — Dockerize
- [ ] Multi-stage Dockerfile for backend
- [ ] Multi-stage Dockerfile for frontend (build + nginx serve)
- [ ] Full-stack `docker-compose up`
- [ ] Environment-based configuration

### Phase 5 — CI + documentation
- [ ] GitHub Actions: build + test backend and frontend on every PR
- [ ] Formatting/lint gates (Spotless, ESLint/Prettier)
- [ ] Complete README run instructions and API docs

### Phase 6 — Stretch goals
- [ ] User accounts + authentication (password hashing, JWT)
- [ ] Cache weather responses
- [ ] Add a second, API-key-based weather provider behind the same interface
- [ ] Deploy to a free host

## Git workflow

1. `git switch -c phase-N/short-description`
2. Small commits, conventional messages: `feat:`, `fix:`, `test:`, `chore:`, `docs:`
3. `git push -u origin <branch>`
4. `gh pr create` — review your own diff before merging
5. Squash-merge into `main`, delete the branch
