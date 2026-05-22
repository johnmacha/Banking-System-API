#  Banking System API

A RESTful backend API built with Spring Boot that simulates core banking operations such as account creation, deposits, withdrawals, and transaction tracking.

---

##  Features

* Create bank accounts
* Deposit and withdraw funds
* Track transaction history
* Filter transactions by:

  * Type (DEPOSIT / WITHDRAW)
  * Date range
* Pagination & sorting (latest first)
* Global exception handling
* Input validation
* Structured API responses

---

##  API Endpoints

### ➤ Create Account

POST `/bank/create`

### ➤ Deposit

POST `/bank/deposit?accNo=123&amount=500`

### ➤ Withdraw

POST `/bank/withdraw?accNo=123&amount=200`

### ➤ Get Transactions

GET `/bank/{accNo}/transactions`

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
  "name": "John",
  "balance": 1000
}

---

##  Key Concepts Implemented

* JPA Entity Relationships (`@OneToMany`, `@ManyToOne`)
* DTO Mapping
* Java Streams (filtering, sorting, pagination)
* Global Exception Handling (`@ControllerAdvice`)
* Validation (`@Valid`, `@NotBlank`, `@Positive`)

---

##  Future Improvements

* Add authentication (Spring Security)
* Add DTO layer fully
* Move pagination to Spring Pageable
* Add Swagger API documentation

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

### Tech Stack
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- Maven

###  Current Progress
- Account creation working successfully
- Database persistence stable
- Continuing work on authentication and secured banking operations
- Expanding transaction management features

##  Author

John Macharia
