# API Testing Automation with Rest Assured (Java)
This project provides a Java-based framework for automated API testing using the Rest Assured library. It focuses on testing a to-do list application's functionalities, including registration, login, and adding entries.

## Key Features:

- **Comprehensive Testing**: Covers both positive and negative scenarios to ensure thorough API validation.
- **Independent Test Cases**: Each test case executes independently, eliminating dependencies and enhancing maintainability.
- **Reusable Code**: Minimizes code repetition by leveraging methods for generating users, invalid data, and more.
- **Adaptable to Changes**: Easily accommodates endpoint and URL modifications by storing them separately from test cases.
- **Environment Flexibility**: Allows switching between testing environments (local, production, etc.) through command-line arguments.
- **Detailed Reporting**: Generates reports using Allure, providing comprehensive insights into test execution and results.

## Code structure:
- ![image](https://github.com/Laithy/API_AutomatedTesting_RestAssured/assets/34172861/8ed8ce2e-4aff-4a11-b1b9-367896ad74e8)

## Reporting:
- Allure reports are generated after each test run for comprehensive analysis.
- ![image](https://github.com/Laithy/API_AutomatedTesting_RestAssured/assets/34172861/1243d133-aebe-48f6-a99e-10227c0d17e7)

## Test cases:
- ![image](https://github.com/Laithy/API_AutomatedTesting_RestAssured/assets/34172861/49d3fb2a-d24d-481c-b51e-e12b5ab6a557)

## Getting Started:
1. **Prerequisites:**
- Java Development Kit (JDK)
- Maven
- Allure (For reporting)
2. **Run Tests:**
- Execute tests from the command line using, 
``` mvn clean test ``` or ``` mvn clean test -DLOCAL ``` to specify a certain environment (the local environment in that case), and use ``` allure serve ``` to generate the report in HTML format
- Note: Tests are run on the production environment by default.

## Additional Notes:
- For further details on test cases, code explanations, and customization options, refer to the provided Java source code.
- Feel free to contribute to this project by creating pull requests with improvements or additional functionalities.
