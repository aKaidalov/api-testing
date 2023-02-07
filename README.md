# API Testing with REST Assured

We are going to be learning API testing with REST Assured, and the software under test will be  
an Open API called [Restful Booker](https://restful-booker.herokuapp.com/apidoc/index.html).

# Prerequisites

- JDK 17

## Exercises

1. Get to know the software under test
1. Let's write some tests without a robust framework
1. Let's create a framework

## Exercise 1: Get to know Restful Booker API

### Endpoints:

- GET booking
- GET booking/{id}
- POST booking

### Tests

- get all bookings should return 200
- get booking by id should return 200
- post booking should return 200
- post booking response should contain id
- post booking response should contain booking

## Exercise 2: Tests without automation framework

Using third party library core functionality.

## Exercise 3: Let's create a framework

Adding abstraction and additional functionality atop third party library.

### Framework functionality

- Efficient way to communicate with API
- Efficient way to add payload
- Efficient way to validate response
- Efficient way to create payloads with test data
- Tests speak business logic (to some extent)

## Assignment

### Goals

- Add a layer of abstraction to hide setup and repetitive tasks
- Framework enables to efficiently build requests and handle API calls
- Write tests using custom developed framework (better readability)
- Flexible to manage and use test data

### Tests

- post authentication with correct credentials returns 200
- post authentication with correct credentials returns 401
- post authentication returns token
- post booking with wrong Accept header returns 418
- put booking should return 200
- delete booking should return 201