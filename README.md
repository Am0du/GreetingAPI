# GreetAPI Documentation

## Overview

The `GreetAPI` is a REST API implemented using Spring Boot. This API offers a personalized greeting, displays the client's IP address, locates the client, and provides the current temperature.


## Table of Contents

1. [Features](#features)
2. [Endpoints](#endpoints)
3. [Request Parameters](#request-parameters)
4. [Example Requests and Responses](#example-requests-and-responses)
5. [Getting Started](#getting-started)
6. [Installation](#installation)
7. [Project Structure](#project-structure)
8. [Dependencies](#dependencies)


## Features

- **Greeting Service**: Responds with a personalized greeting message.
- **IP Address Retrieval**: Detects and returns the client's IP address.
- **Location Lookup**: Provides location information based on a fixed IP address.
- **Weather Data**: Returns the current temperature for Lagos, Nigeria.

## Endpoints

### `GET /api/hello`

This endpoint greets the visitor by name and provides additional information such as the client's IP address, the location derived from a fixed IP, and the current temperature in Lagos.

## Request Parameters

| Parameter      | Type   | Required | Description                  |
|----------------|--------|----------|------------------------------|
| `visitor_name` | String | Yes      | The name of the visitor.     |

## Example Requests and Responses

### Example Request

http
GET /api/hello?visitor_name=John

**Example Response**
````
json
{
  "client_ip": "192.168.1.1",
  "location": "Lagos",
  "greeting": "Hello, John! The temperature is 29.0 degrees Celsius in Lagos."
}
````

**Error Response**

If the visitor_name parameter is missing or empty:
````
json
{
    "status": 400,
    "error": "Bad Request",
    "message": "visitor_name is required"
}

````

## Getting Started

* Prerequisites
* Java 17 or higher
* Maven or Gradle build tool
* Spring Boot framework

## Installation
Clone the repository:
````
git clone https://github.com/yourusername/task1.git
cd task1
````
Build the project:
````
mvn clean install
````

Run the application:
````
mvn spring-boot:run
````

## Project Structure
* **com.example.task1.apiController.TaskController:** The main REST controller handling /api/hello requests.

* **com.example.task1.dto.ResponseDTO**: A Data Transfer Object that encapsulates response data.

* **com.example.task1.service.TaskService:** Service layer that provides methods for IP retrieval, location lookup, and weather data fetching.
## Dependencies
**Spring Boot Starter Web:** To build and run RESTful web services.
**Spring Boot Starter Test:** For testing the application.