**Project Overview**

- **Name**: `gigforce` — a Spring Boot backend for managing contractors, assignments, timesheets, invoices and related workflows.
- **Location**: repository root (Spring Boot Maven project).
- **Primary language**: Java (Spring Boot, Java 21).

**Tech Stack**

- **Framework**: Spring Boot (starter-parent `3.5.14`).
- **Web**: `spring-boot-starter-web`.
- **Data**: `spring-boot-starter-data-jpa` with MySQL connector (`mysql-connector-j`).
- **Security**: `spring-boot-starter-security` + JWT (`jjwt`).
- **API docs**: `springdoc-openapi-starter-webmvc-ui` (OpenAPI/Swagger).
- **Build**: Maven (`mvnw`/`mvnw.cmd`, `spring-boot-maven-plugin`).

**High-level Architecture**

- Spring Boot monolith with conventional package structure under `src/main/java/com/demo/gigforce`.
- Key packages:
  - `controller` — REST controllers (API endpoints).
  - `service` — business logic (interfaces + implementations under `service/impl`).
  - `repository` — Spring Data JPA repositories for persistence.
  - `entity` — JPA entities representing the data model.
  - `dto` — request/response and auth DTOs.
  - `config` — application configuration (e.g., `SwaggerConfig`).
  - `security` — JWT and Spring Security integration (token filter, user details, etc.).

**Build & Run**

- Build (Windows PowerShell):

```
./mvnw.cmd -DskipTests package
```

- Run with Maven:

```
./mvnw.cmd spring-boot:run
```

- Run built jar:

```
java -jar target/gigforce-0.0.1-SNAPSHOT.jar
```

- Default server port: `8090` (see `src/main/resources/application.properties`).

**Configuration**

- Primary config file: `src/main/resources/application.properties`.
- Important properties:
  - `spring.datasource.url` — JDBC URL for MySQL (default `jdbc:mysql://localhost:3306/gigforce_db`).
  - `spring.datasource.username` / `password` — DB credentials.
  - `spring.jpa.hibernate.ddl-auto=update` — schema auto-update.
  - `server.port=8090` — HTTP port.
  - `jwt.secret` / `jwt.expiration` — JWT signing and expiration settings.

**API Documentation (Swagger/OpenAPI)**

- OpenAPI config is in `config/SwaggerConfig.java`. Security scheme `bearerAuth` (JWT) is added globally.
- Swagger UI is available by default at `/swagger-ui/index.html` (springdoc default).

**Security & Authentication**

- JWT-based authentication. Token secret configured in `application.properties` (`jwt.secret`).
- `SwaggerConfig` declares a bearer token security scheme so secured endpoints can be exercised via Swagger UI.

Authentication flow (typical):
- `POST /api/auth/register` — create a new user (see `AuthController`).
- `POST /api/auth/login` — obtain JWT token; subsequent requests include `Authorization: Bearer <token>` header.

**Packages and Main Classes**

- `com.demo.gigforce.GigforceApplication` — Spring Boot entry point.
- `com.demo.gigforce.config.SwaggerConfig` — OpenAPI security setup.

**Controllers & Main Endpoints**

The project exposes REST endpoints grouped by controller. Base paths and a sample of mapped methods (extracted from source):

- `AssignmentController` — base: `/api/assignments`
  - `POST` (create assignment)
  - `PUT /{id}/terminate` (terminate assignment)

- `AssignmentAmendmentController` — base: `/api/amendments`
  - `POST` (create amendment)

- `AuthController` — base: `/api/auth`
  - `POST /register` (user registration)
  - `POST /login` (user login — returns JWT)

- `ContractorCertificationController` — base: `/api/certifications`
  - `POST` (add certification)
  - `GET /contractor/{contractorId}` (list contractor certifications)
  - `PUT /{certId}` (update)
  - `DELETE /{certId}` (delete)

- `ContractorProfileController` — base: `/api/contractors`
  - `POST` (create contractor profile)
  - `GET` (list contractors)
  - `GET /{id}` (get contractor)
  - `GET /search` (search contractors)
  - `PUT /{id}` (update)
  - `DELETE /{id}` (delete)
  - `GET /{id}/engagement-history` (get engagement history)

- `InvoiceController` — base likely `/api/invoices` (controller exists)
- `PaymentController` — base likely `/api/payments` (controller exists)
- `ResourceRequisitionController` — base likely `/api/requisitions` (controller exists)
- `TimesheetController` — base likely `/api/timesheets` (controller exists)
- `UserController` — base likely `/api/users` (controller exists)
- `VendorSubmissionController` — base likely `/api/submissions` (controller exists)

Note: Exact method signatures, request/response DTOs and validation rules are defined in each controller and associated DTO classes under `dto/` and should be consulted for precise contract details.

**Entities (data model)**

Key JPA entities (files under `entity/`):

- `Assignment` — assignment records (status, dates, contractor link, etc.).
- `AssignmentAmendment` — amendment details for assignments.
- `AuditLog` — application/audit events.
- `ContractorCertification` — certifications tied to contractors.
- `ContractorProfile` — primary contractor profile information.
- `EngagementHistory` — historical engagement records for contractors.
- `Invoice` — invoicing information.
- `Payment` — payment records.
- `ResourceRequisition` — requisition requests for resources.
- `Timesheet` — time entry data for assignments.
- `User` — application users (roles, status).
- `VendorSubmission` — vendor-submitted items.

These entities are persisted using Spring Data JPA repositories found under `repository/` (one repository per entity conventionally).

**Enums**

The `enums/` package contains domain enums used across entities and DTOs, e.g. `AssignmentStatus`, `UserRole`, `UserStatus`, `AmendmentStatus`, `RequisitionStatus`, `CertificationStatus`, etc.

**Persistence**

- Spring Data JPA repositories are under `repository/` (e.g., `UserRepository`, `AssignmentRepository`, etc.).
- Default dialect is `MySQLDialect`; DB connection is configured in `application.properties`.

**Testing**

- Test class `src/test/java/com/demo/gigforce/GigforceApplicationTests.java` exists — standard Spring Boot test harness.
- To run tests with Maven:

```
./mvnw.cmd test
```

**Developer Workflow & Extension Notes**

- Common change pattern:
  1. Add/modify an `entity` (annotate with JPA annotations).
  2. Add/modify a `repository` interface (extends `JpaRepository`).
  3. Add business logic to `service` and implement it under `service/impl`.
  4. Expose via `controller` endpoints using DTOs (`dto/request`, `dto/response`).
  5. Add tests under `src/test/java`.

- To add a secured endpoint:
  - Add mapping in controller.
  - Enforce authorization in security config and/or check roles from JWT claims.

**Observations & Recommendations**

- JWT secret is hard-coded in `application.properties` — consider moving to environment variables or a secret manager for production.
- `spring.jpa.hibernate.ddl-auto=update` is convenient for development but may be dangerous in production; prefer migrations (Flyway/Liquibase) for production environments.
- Swagger/OpenAPI is configured with JWT security — verify Swagger UI path after running (commonly `/swagger-ui/index.html`).

**Where to Look Next (important files)**

- `pom.xml` — dependency and build configuration.
- `src/main/java/com/demo/gigforce/GigforceApplication.java` — app entry.
- `src/main/resources/application.properties` — runtime configuration.
- `src/main/java/com/demo/gigforce/config/SwaggerConfig.java` — OpenAPI config.
- `src/main/java/com/demo/gigforce/security` — JWT and Spring Security code (filters, providers, user details).
- `src/main/java/com/demo/gigforce/controller` — API surface.
- `src/main/java/com/demo/gigforce/entity` — domain model.

**Quick Start (developer)**

1. Ensure MySQL is running and a database `gigforce_db` exists, or change the JDBC URL to match your environment.
2. Update DB credentials in `application.properties` or set as environment variables.
3. Build and run with:

```
./mvnw.cmd spring-boot:run
```

4. Open Swagger UI: `http://localhost:8090/swagger-ui/index.html` and use `POST /api/auth/login` to retrieve a JWT token to authorize other calls.

