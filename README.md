# Online Quiz System

## Project Description

This project is a JavaFX-based Online Quiz System with login and registration functionality for both students and administrators. It allows for creating and taking multiple-choice quizzes with timer-based questions, and includes a leaderboard to track user scores.

## Features

* User registration/login (Students & Admins)
* Multiple-choice quizzes
* Timer-based questions
* Leaderboard to track scores

## Technology Stack

* JavaFX for the graphical user interface (GUI)
* MySQL for the database
* Java for backend logic
* Maven or Gradle for build automation
* Git for version control
* VSCode (Recommended) for IDE
* MySQL Connector/J (version 8.0.28 or later)

## Installation Instructions

1.  **Prerequisites:**

    * Java Development Kit (JDK) 11 or higher
    * MySQL Database Server
    * VSCode (or your preferred IDE)
    * MySQL Connector/J (Java driver for MySQL)

2.  **Database Setup:**

    * Create a MySQL database (e.g., `onlinequiz`).
    * Create a `config.properties` file in the `src/main/resources/com/example/onlinequiz/config/` directory with the following content:

        ```properties
        db.url=jdbc:mysql://localhost:3306/onlinequiz
        db.user=your_mysql_username
        db.password=your_mysql_password
        ```

        * Replace `your_mysql_username` and `your_mysql_password` with your actual MySQL credentials.  **IMPORTANT:** Never commit the `config.properties` file to your Git repository. This file contains sensitive database credentials.
    * Run the SQL script (see Database Schema below) to create the necessary tables.  You can use a MySQL client like MySQL Workbench or the command-line tool.

3.  **Project Setup:**

    * Clone the Git repository to your local machine:

        ```bash
        git clone <your_repo_url>
        ```

    * Open the project in VSCode (or your IDE).
    * If using Maven, ensure it is installed and configured.  If using Gradle, ensure that is installed.
    * Add the MySQL Connector/J JAR file to the project's dependencies.  If using Maven, this is handled by the `pom.xml` file.  If using Gradle, this is handled by the `build.gradle` file.
    * Build the project using Maven:

        ```bash
        mvn clean install
        ```
        Or Gradle:
        ```bash
        gradle build
        ```

4.  **Running the Application:**

    * Run the main class (`com.example.onlinequiz.App`) from your IDE, or run the generated JAR file from the command line:
        ```bash
        java -jar target/OnlineQuizSystem-1.0-SNAPSHOT.jar
        ```
        (The exact JAR name might vary depending on your project configuration.)

## Usage Instructions

1.  **Login/Registration:**

    * When the application starts, you will be presented with a login screen.
    * If you are a new user, click the "Register" button to create an account.  You will need to provide a username and password.  The first registered user will typically be an admin.
    * If you already have an account, enter your username and password and click "Login".
    * Users are assigned roles (student or admin).  The application will display different views and functionalities based on your role.

2.  **Student View:**

    * Students can browse available quizzes.
    * Students can take quizzes.  The quiz will display one question at a time, with a timer for each question.
    * After completing a quiz, the student's score is calculated and stored.
    * Students can view the leaderboard to see the scores of other students.

3.  **Admin View:**

    * Admins can manage quizzes (create, edit, delete).
    * Admins can manage questions for each quiz (add, edit, delete).
    * Admins can view all user scores.
    * Admins may have additional management capabilities.

## Database Schema

The database schema consists of the following tables:

* **`users`**

    * `user_id` (INT, PRIMARY KEY, AUTO_INCREMENT)
    * `username` (VARCHAR(255), UNIQUE, NOT NULL)
    * `password` (VARCHAR(255), NOT NULL)  **IMPORTANT: Hashed using bcrypt**
    * `role` (ENUM('student', 'admin'), NOT NULL)
    * `created_at` (TIMESTAMP DEFAULT CURRENT_TIMESTAMP)

* **`quizzes`**

    * `quiz_id` (INT, PRIMARY KEY, AUTO_INCREMENT)
    * `quiz_name` (VARCHAR(255), NOT NULL)
    * `description` (TEXT)
    * `created_at` (TIMESTAMP DEFAULT CURRENT_TIMESTAMP)

* **`questions`**

    * `question_id` (INT, PRIMARY KEY, AUTO_INCREMENT)
    * `quiz_id` (INT, FOREIGN KEY referencing `quizzes`)
    * `question_text` (TEXT, NOT NULL)
    * `option_a` (VARCHAR(255), NOT NULL)
    * `option_b` (VARCHAR(255), NOT NULL)
    * `option_c` (VARCHAR(255), NOT NULL)
    * `option_d` (VARCHAR(255), NOT NULL)
    * `correct_option` (ENUM('a', 'b', 'c', 'd'), NOT NULL)
    * `timer_seconds` (INT, NOT NULL)
    * `created_at` (TIMESTAMP DEFAULT CURRENT_TIMESTAMP)

* **`scores`**

    * `score_id` (INT, PRIMARY KEY, AUTO_INCREMENT)
    * `user_id` (INT, FOREIGN KEY referencing `users`)
    * `quiz_id` (INT, FOREIGN KEY referencing `quizzes`)
    * `score` (INT, NOT NULL)
    * `submitted_at` (TIMESTAMP DEFAULT CURRENT_TIMESTAMP)

## Relevant Information

* **Security:** Password are stored in the database using the bcrypt hashing algorithm.  It is crucial to keep your database credentials secure and never hardcode them in the application.
* **Error Handling:** The application includes error handling to gracefully handle exceptions and provide informative messages to the user.  Errors are also logged for debugging.
* **GUI Design:** The user interface is designed using JavaFX and styled with CSS.  Scene Builder is recommended for designing the FXML layout files.
* **Code Quality:** The project follows Java coding conventions and best practices to ensure clean, maintainable, and reusable code.
* **Testing:** Unit tests should be written to ensure the functionality of the service classes and other components.
* **Scalability:** The application is designed to be scalable.  The database schema and application architecture can be extended to support a larger number of users and quizzes.
