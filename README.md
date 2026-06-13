#  Banking System API

A Spring Boot REST API for a simple banking system with authentication, account management, deposits, withdrawals, and transaction tracking.

This project demonstrates backend development skills including:
- Spring Boot REST APIs
- Spring Security (JWT authentication)
- JPA/Hibernate ORM
- MySQL database integration
- Docker containerization

---

##  Features

- User authentication (JWT secured)
- Create bank accounts
- Deposit and withdraw funds
- Transaction history tracking
- Pagination and filtering of transactions
- Global exception handling
- Input validation

---

##  API Endpoints

### ➤ Auth

- POST `/auth/register`
- POST `/auth/login`

### ➤ Bank Operations

- POST /bank/create
- POST /bank/deposit
- POST /bank/withdraw

### ➤ Transactions

- GET /bank/{accNo}/transactions

Query params:

* type
* start
* end
* page
* size

---

##  Response Format

```json
{
  "status": "success",
  "message": "Transactions retrieved",
  "data": [...]
}
```

---

##  Error Handling

All errors follow a consistent structure:

```json
{
  "status": "error",
  "message": "Account not found",
  "data": null
}
```

---

##  Sample Request(s)

POST /bank/create

{
    "accountNumber": "540",
    "accountName":"Henry",
    "initialDeposit": 5000
}

---

##  Key Concepts Implemented

* JPA Entity Relationships (`@OneToMany`, `@ManyToOne`)
* DTO Mapping
* Java Streams (filtering, sorting, pagination)
* Global Exception Handling   
* Validation 

---

##  Future Improvements

* Add unit & integration tests
* Add CI/CD pipeline
* Improve security (refresh tokens, roles)
* Add Swagger API documentation
* Add Redis caching

---
##  Recent Updates

###  Account Creation Refactor
- Implemented DTO-based request handling using `CreateAccountRequest`
- Added validation with Jakarta Validation annotations
- Improved separation between API requests and database entities

###  Database & Entity Improvements
- Refactored `BankAccount` entity structure
- Separated internal database ID (`Long id`) from business account number (`String accNo`)
- Added proper auto-generated primary keys using JPA/Hibernate
- Added unique constraints for account numbers

###  Persistence Layer Enhancements
- Updated repository structure to use `JpaRepository<BankAccount, Long>`
- Added custom repository method:
  ```java
  findByAccNo(String accNo)
- Fixed Hibernate schema generation and table mapping issues

### Validation & Error Handling
## Added custom exceptions:
- InvalidAmountException
- ResourceNotFoundException
## Implemented request validation for:
- account names
- balances
- deposits
- withdrawals

### Security Improvements
- Integrated Spring Security authentication context handling
- Added ownership validation logic for secured account operations
- Improved access control structure for protected endpoints

### Transactions API
- Added transaction filtering by:
- transaction type
- date range
- Added pagination support
- Added transaction sorting by latest activity

---

### Tech Stack
- Java 21
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA
- Hibernate
- MySQL8
- Docker & Docker Compose
- Maven

---

## Project Setup (Local Development)

### 1. Clone the repository

```bash
git clone https://github.com/johnmacha/Banking-System-Api.git
cd banking-system
```

### 2. Configure database
- Update application.properties :
spring.datasource.url=jdbc:mysql://localhost:3306/Bank
spring.datasource.username=root
spring.datasource.password=my_password

### 3. Run the application
./mvnw spring-boot:run

---
## Running with Docker
### 1. Build the application
./mvnw.cmd clean package -DskipTests

### 2. Start containers
docker compose up --build
- This will start:

Spring Boot API → http://localhost:8080
MySQL Database → port 3307 (host)

### Docker Architecture
Spring Boot (banking-api)
        │
        ▼
MySQL (banking-mysql)

-Communication inside Docker:
jdbc:mysql://mysql:3306/Bank

### Environment Variables (Docker)
- Configured in docker-compose.yml :
> SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/Bank
> SPRING_DATASOURCE_USERNAME=root
> SPRING_DATASOURCE_PASSWORD=my_password

---

## Docker Services
### MySQL
- Image: mysql:8.0
- Port: 3307 (host) → 3306 (container)
- Database: Bank

### Banking API
- Spring Boot container
- Port: 8080

---

##  Author

John Macharia
