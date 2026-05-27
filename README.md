# Banking HRMS Automation Framework

## Project Overview

This project is a Hybrid Test Automation Framework developed for a Orange HRMS (Human Resource Management System) application using Selenium WebDriver, Java, and TestNG.

The framework follows the Page Object Model (POM) design pattern with reusable utilities, reporting, and parallel test execution support.


---

# Technologies Used

- Java
- Selenium WebDriver
- TestNG
- Maven
- Extent Reports
- Log4j
- Apache POI
- GitHub
- Page Object Model (POM)

---

# Framework Features

## Core Features

- Page Object Model (POM) Design Pattern
- Reusable BasePage Methods
- Parallel Test Execution
- Cross Browser Support
- Explicit Wait Handling
- Dynamic Web Element Handling
- Screenshot Capture on Failure
- Extent HTML Reports
- Logging using Log4j
- Maven Project Structure
- Utility Classes for Reusability
- Data Driven Testing Support

---

# Modules Automated

## HRMS Modules

- Admin Login
- Dashboard Validation
- Logout Functionality

---

# Framework Architecture

src/test/java

- TestBase
- drivers
- PagesObjects
- TestCases
- Utilities
- Listeners

src/test/resources

- Config Files
- log4j2 Files


---

# Utilities Implemented

## Reusable Utility Classes

- Excel Utility
- Date Provider 

---

# Reporting

The framework generates detailed Extent HTML Reports after test execution.

Features included in reports:

- Pass/Fail Status
- Test Execution Time
- Screenshots on Failure
- Logs and Exception Details

---

# Parallel Testing

Implemented parallel execution using TestNG to reduce execution time and improve framework scalability.

Supported execution:

- Multiple test classes
- Multiple browsers
- Parallel suite execution

---

# Maven Dependencies Used

- Selenium Java
- TestNG
- Extent Reports
- WebDriverManager
- Apache POI
- Log4j





---

# GitHub Repository

Orange HRMS Automation Framework
