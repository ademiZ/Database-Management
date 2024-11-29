Overview

Java application using JDBC to manage employee data with CRUD operations.

Features

Add, retrieve, update, and delete employees.
Database Setup

Create employee_db.
Create table:
CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    position VARCHAR(100),
    salary DOUBLE,
    hire_date DATE
);

Update database connection in EmployeeData.
Run main() to test CRUD operations.
