# Access Log Parser

## Description

**Access Log Parser** is a Spring Boot application that processes HTTP access log files, stores the data in a MySQL database, and identifies IP addresses that exceed a specified number of requests within a given time period. This is useful for detecting suspicious behavior or potential attacks.

## Features

- Parses HTTP access log files in standard format.
- Stores log entries in a MySQL database.
- Identifies IP addresses exceeding a request limit within a defined time frame.
- Logs blocked IPs with explanatory messages.

## Prerequisites

- Java 8 or higher installed.
- Maven installed.
- MySQL installed and running.

## Initial Setup

1. **Configure the Database:**

   - Create a MySQL database named `parserwb`:

     ```sql
     CREATE DATABASE parserwb;
     ```

   - The necessary tables will be created automatically by the application. The schema is as follows:

     ```sql
     CREATE TABLE `logger` (
       `id` int(11) NOT NULL AUTO_INCREMENT,
       `http_code` varchar(255) DEFAULT NULL,
       `http_from` varchar(255) DEFAULT NULL,
       `http_method` varchar(255) DEFAULT NULL,
       `ip` varchar(255) DEFAULT NULL,
       `start_date` datetime DEFAULT NULL,
       PRIMARY KEY (`id`)
     );

     CREATE TABLE `blocked_ip` (
       `id` int(11) NOT NULL AUTO_INCREMENT,
       `ip` varchar(255) DEFAULT NULL,
       `message` varchar(255) DEFAULT NULL,
       PRIMARY KEY (`id`)
     );
     ```

2. **Configure the Application:**

   - Update the `src/main/resources/application.properties` file with your MySQL credentials:

     ```
     spring.datasource.url=jdbc:mysql://localhost:3306/parserwb
     spring.datasource.username=YOUR_USERNAME
     spring.datasource.password=YOUR_PASSWORD
     ```

## Build and Run

1. **Build the Application:**

   - In the project's root directory, run:

     ```bash
     mvn install
     ```

   - This will generate a JAR file in the `target` folder.

2. **Run the Application:**

   - Navigate to the `target` directory and execute the generated JAR with the required parameters:

     ```bash
     java -jar parser.jar 2017-01-01.13:00:00 hourly 100
     ```

     **Parameters:**
     - `2017-01-01.13:00:00`: The start date and time for
