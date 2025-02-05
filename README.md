# TestManagement_Application
## MCQ Test Application
### Overview

The MCQ Test Application provides RESTful endpoints to manage Multiple Choice Questions (MCQs) through CRUD operations.

### Technology Stack
### Java: 
Programming language
### Spring Boot: 
Framework for building and running Java applications
### Gradle: Dependency management
### RESTful API: 
Architectural style for networked applications
### PostGreSql Database:
In-memory database for storing MCQ data during development
Getting Started
To run the MCQ Test Application locally, follow these steps:

### Prerequisites

Java Development Kit (JDK) installed (version 8 or higher)

Gradle installed

Installation

### Clone the repository:

### bash
git clone  https://github.com/vishalyadav-bnt/TestManagement_Application.git
cd mcq-test-application
Build the project:

### bash
mvn clean package
Run the application:

### bash
java -jar target/mcq-test-application.jar
The application will start on http://localhost:8080.

### API Documentation
Create Question
POST /api/questions/create

### Creates a new MCQ question.

### Get All Questions
GET /api/questions/all

Retrieves all MCQ questions.

### Get Question by ID
GET /api/questions/{id}

Retrieves a specific MCQ question by its ID.

### Update Question
PUT /api/questions/{id}

Updates an existing MCQ question by its ID.

### Delete Question
DELETE /api/questions/{id}

Deletes an MCQ question by its ID.

### Response Format
All endpoints return JSON responses in the following format:

json
Copy code
{
  "message": "Success message...",
  "statusCode": 200,
  "data": {} 
}

