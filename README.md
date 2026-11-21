# Employee Management System

## Project Overview
This project is a Java Swing-based Employee Management System developed using JDBC and MySQL.

## Technologies Used
- Java
- Java Swing
- JDBC
- MySQL
- MySQL Connector/J

## Features
1. Add new employee details.
2. Store employee records in MySQL database.
3. View all employee records in JTable.
4. Connect Java application with MySQL using JDBC.

## Database Details
Database Name: company_db

Table Name: employees

| Column | Data Type |
|---|---|
| emp_id | INT PRIMARY KEY |
| name | VARCHAR(100) |
| department | VARCHAR(50) |
| salary | DECIMAL(10,2) |

## How to Run
1. Install JDK and MySQL.
2. Create the company_db database and employees table.
3. Add MySQL Connector/J JAR to the project library.
4. Update the MySQL root password in EmployeeManagement.java.
5. Compile and run EmployeeManagement.java.

## Developed For
Core Java – Module 6 Practical Assignment
