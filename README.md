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

##  Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* MySQL
* Maven

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

##  Author

John Macharia
