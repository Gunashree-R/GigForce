
***

# GigForce

Contract & Gig Workforce Management Platform

***

## Contents

- [1. Overview](#1-overview)
- [2. Technology Stack](#2-technology-stack)
- [3. Architecture Overview](#3-architecture-overview)
- [4. Modules Implemented](#4-modules-implemented)
- [5. Modules Partially Implemented / Pending Testing](#5-modules-partially-implemented--pending-testing)
- [6. Authentication & Security](#6-authentication--security)
- [7. Swagger API Documentation](#7-swagger-api-documentation)
- [8. API Endpoints](#8-api-endpoints)
- [9. Role-Based API Access](#9-role-based-api-access)
- [10. Data Flow](#10-data-flow)
- [11. Audit Logging](#11-audit-logging)
- [12. Non-Functional Aspects](#12-non-functional-aspects)
- [13. Future Enhancements](#13-future-enhancements)
- [14. Conclusion](#14-conclusion)

***

## 1. Overview

GigForce is a web-based contract and gig workforce management platform designed to manage the complete lifecycle of freelance, contract, and project-based workforce.

The system enables organizations to:

* Manage contractor profiles and skills
* Handle hiring workflows
* Track assignments, timesheets, and payments
* Monitor compliance and workforce analytics

The backend is implemented using Spring Boot with REST APIs and JWT-based security.

***

## 2. Technology Stack

Backend: Spring Boot (Java)  
Security: Spring Security + JWT Authentication  
Database: MySQL (Relational Database)  
API Documentation: Swagger (Springdoc OpenAPI)  
Architecture: REST API-based layered architecture

***

## 3. Architecture Overview

The application follows a layered architecture:

```
Controller Layer → Service Layer → Repository Layer → Database
```

Controller Layer: Handles HTTP requests  
Service Layer: Implements business logic  
Repository Layer: Handles database operations using JPA  
Security Layer: Handles authentication and authorization using JWT

***

## 4. Modules Implemented

### 4.1 Identity & Access Management

Features:

* User registration and login
* JWT-based authentication
* Role-based access control (RBAC)
* Audit logging

Entities:
User  
AuditLog

***

### 4.2 Contractor Profile & Skill Management

Features:

* Create and manage contractor profiles
* Store skills and experience details
* Track availability and engagement type

Entities:
ContractorProfile  
ContractorCertification  
EngagementHistory

***

### 4.3 Certification & Engagement Management

Features:

* Add and update contractor certifications
* Track previous engagement history

***

### 4.4 Resource Requisition

Features:

* Create and manage requisitions (business unit, skill, dates, rates)
* Update requisition status (DRAFT, OPEN, IN_PROGRESS, FILLED, CANCELLED)

Entities:
ResourceRequisition

***

### 4.5 Vendor Submission

Features:

* Vendors submit responses to requisitions
* Track submission status and attach proposals or rates

Entities:
VendorSubmission

***

### 4.6 Assignment Management

Features:

* Create and manage assignments linking contractors to requisitions/clients
* Track assignment lifecycle, start/end dates, and status
* Terminate or close assignments

Entities:
Assignment

***

### 4.7 Assignment Amendment

Features:

* Request assignment amendments (role, duration, rate changes)
* Approve or reject amendments and apply changes to assignments

Entities:
AssignmentAmendment

***

### 4.8 Timesheet Workflow

Features:

* Submit and manage timesheets for assignments
* Support submission, review/approval and submission for invoicing

Entities:
Timesheet

***

## 5. Modules Partially Implemented / Pending Testing

- Invoice & Payment workflow (basic)

These modules still require deeper end-to-end testing and additional fine tuned features are being added.

***

## 6. Authentication & Security

Authentication is implemented using JWT.

Flow:

1. User logs in using email and password
2. Server validates credentials
3. JWT token is generated
4. Token is sent in request header:

```
Authorization: Bearer <token>
```

Role-based access control is enforced using Spring Security.

***

## 7. Swagger API Documentation

Swagger is used to visualize and test APIs.

### Access Swagger UI

```
http://localhost:8090/swagger-ui/index.html
```

### Features

* View all APIs
* Test APIs directly
* Send request bodies
* Authenticate using JWT

### Authentication in Swagger

1. Login using `/api/auth/login`
2. Copy token
3. Click "Authorize"
4. Enter:

```
Bearer <token>
```

***

## 8. API Endpoints

### Authentication APIs

POST /api/auth/register  
POST /api/auth/login

***

### User APIs (ADMIN only)

POST /api/users  
GET /api/users  
GET /api/users/{id}  
PUT /api/users/{id}  
DELETE /api/users/{id}

***

### Contractor APIs

POST /api/contractors  
GET /api/contractors  
GET /api/contractors/{id}  
PUT /api/contractors/{id}  
DELETE /api/contractors/{id}  
GET /api/contractors/search

***

### Certification APIs

POST /api/certifications  
GET /api/certifications/contractor/{id}  
PUT /api/certifications/{id}  
DELETE /api/certifications/{id}

***

### Engagement APIs

POST /api/contractors/{id}/engagement-history  
GET /api/contractors/{id}/engagement-history

***

### Requisition APIs

POST /api/requisitions  
GET /api/requisitions  
PUT /api/requisitions/{id}/status

***

### Vendor Submission APIs

POST /api/submissions  
GET /api/submissions/requisition/{id}  
PUT /api/submissions/{id}/status

***

### Assignment APIs

POST /api/assignments  
PUT /api/assignments/{id}/terminate

***

### Assignment Amendment APIs

POST /api/amendments

***

### Timesheet APIs

POST /api/timesheets  
PUT /api/timesheets/{id}/submit  
GET /api/timesheets/{id}

***

### Invoice APIs

POST /api/invoice/{timesheetId}  
GET /api/invoice/{id}

***

### Payment APIs

POST /api/payment/{invoiceId}  
GET /api/payment/{id}

***

## 9. Role-Based API Access

### Public APIs

* /api/auth/\*\*
* /api/timesheets/\*\*
* /api/invoice/\*\*
* /api/payment/\*\*

***

### ADMIN

Full access to:

* User APIs
* Contractor APIs (all operations)
* Certification APIs (all operations)

***

### CONTRACTOR

Access:

* View contractor profiles
* Update own profile
* Manage certifications

***

### HIRING\_MANAGER

Access:

* View contractors
* Create contractor profiles
* View certifications

***

### VENDOR

Access:

* View contractor profiles
* View certifications

***

### Other Roles (Vendor Manager, Finance)

Currently authentication supported  
Role-specific restrictions can be extended

***

## 10. Data Flow

### Authentication Flow

```
Login Request → AuthController → AuthService → JWT Generation → Response
```

***

### Protected API Flow

```
Request → JwtFilter → Validate Token → SecurityConfig → Controller → Service → Repository → Database
```

***

### General CRUD Flow

```
Client → Controller → Service → Repository → Database → Response
```

***

## 11. Audit Logging

Audit logs track system actions such as:

* CREATE
* UPDATE
* DELETE

Each log contains:

* userId
* action
* entityType
* timestamp

***

## 12. Non-Functional Aspects

Security:

* JWT-based authentication
* Role-based access control

Scalability:

* Modular design for future expansion

Maintainability:

* Layered architecture
* DTO-based communication

***

## 13. Future Enhancements

* Complete Requisition to Assignment flow
* Timesheet approval workflow
* Invoice approval process
* Notification system
* External integrations (ERP, Payroll)

***

## 14. Conclusion

The current system provides:

* Secure authentication using JWT
* Role-based authorization
* Fully functional contractor management module
* API documentation using Swagger
* Modular architecture ready for extension

This forms a strong foundation for building a complete workforce management platform.

***

