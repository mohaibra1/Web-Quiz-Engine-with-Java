# Web Quiz Engine 🚀

This project is a RESTful web service built with Java and Spring Boot. It currently functions as a quiz engine where users can create quizzes and check their answers against a stored set of correct options.

📋 Project Status: Core Functionality
Currently, the project implements the core logic for the Web Quiz Engine, focusing on:
RESTful Endpoints: Handling GET and POST requests to manage quiz data.
Data Validation: Using jakarta.validation to ensure quizzes have titles, text, and at least two options.
Response Mapping: Custom DTOs (Data Transfer Objects) to process incoming answers and provide feedback.

🛠️ Built With
Java 17: Core programming language.
Spring Boot: Main framework for the web service.
Jackson: For parsing JSON request bodies into Java objects.
Jakarta Validation: To enforce data integrity (e.g., @NotBlank, @Size).

🚀 Key Endpoints implemented
Method	Endpoint	Description
POST	/api/quizzes	Create a new quiz with options and correct answers.
GET	/api/quizzes	Retrieve a list of all created quizzes.
GET	/api/quizzes/{id}	Get details of a specific quiz by its ID.
POST	/api/quizzes/{id}/solve	Submit an answer and get an "Correct/Wrong" result.

📖 Current Learning Progress
This project is part of a journey to master Java Backend development. The next steps for this engine include:
Spring Security: Implementing user authentication and authorization.
Persistence: Moving from in-memory storage to a real database (H2/JPA).
Advanced API: Adding pagination and sorting for quiz retrieval.
