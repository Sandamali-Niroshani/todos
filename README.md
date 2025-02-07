# Selenium, TestNG with Java Automation project

## Introduction
This project is built using Java, Selenium WebDriver, and TestNG.
It is designed for automating ToDos web applications and follows best practices such as Page Object Model (POM) and TestNG annotations.

## Note
**Please note that to execute testcase, you need to run the TestNg.xml file. Because testdata file passed as parameter in TestNg.xml file.**
This project contain only one testcase under testScripts folder(TC01_ToDos.java). Inside this testcase include multiple test methods to test the ToDos application.

## Features
- Selenium WebDriver for web automation
- TestNG for test execution and Assertions
- Page Object Model (POM) for maintainability
- Maven for dependency management
- Use TestNG annotations for setup and teardown
- Capture screenshots as needed and stored in `Screenshots/` folder

## Technologies Used
- Java 11
- Selenium
- TestNG
- Maven

## Prerequisites
Ensure you have the following installed:
- Java JDK (Using java 11 for this project)
- Maven
- IDE (IntelliJ IDEA)
- Google Chrome
- ChromeDriver (compatible with your Chrome version) stored in `Drivers/` folder

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/selenium-testng-framework.git
   ```
2. Install dependencies:
   ```sh
   mvn clean install
   ```
## Running Tests
### Using IDE:
1. Run using TestNG.xml file
    - Right-click on `TestNG.xml` file and select **Run 'TestNG.xml'**

## Project Structure
```
Todos/
│-- src/
│   ├── main/
│   │   ├── java/
│   ├── test/
│   │   ├── java/
│   │   │   ├── pages/            # pageBase class to manage common methods and Page Object Model (POM) classes
|   |   |   ├── testData/         # Test data (use properties file for test data)
│   │   │   ├── testScripts/      # TestNG test scripts
│   │   │   ├── tests/            # BaseTest class for setup and teardown and TestNG test cases
│   │   │   ├── utilities/        # ReadConfig class to read configuration file and TestDataReader class to read test data
│-- configuration/                # Configuration files (config.properties) 
│-- drivers/                      # ChromeDriver
│-- screenshots/                  # Screenshots captured during test execution
|-- pom.xml                       # Maven dependencies
│-- TestNG.xml                    # TestNG suite configuration
│-- README.md
```

## Explaination of the project
This is Selenium, TestNG with Java Maven project for ToDos web application.
All the Acceptance criteria for the given user story documented in Acceptance criteria (AC) for todos.pdf file. And it is shared with email.

- **pages/**: Contains Page Object Model (POM) classes for each page of the application. This application has only one page (ToDosPage).
- **pages/BasePage.java**: Contains common methods for all pages.
- **testData/**: Contains test data in properties file format.
- **testScripts/**: Contains TestNG test scripts.
- **testScripts/BaseTest.java**: Contains setup and teardown methods.
- **tests/**: Contains BaseTest class for setup and teardown and TestNG test cases.
- **utilities/**: Contains ReadConfig class to read configuration file and TestDataReader class to read test data.
- **configuration/**: Contains configuration files (config.properties). Browser and url details are stored in config.properties file.
- **drivers/**: Contains ChromeDriver.
- **screenshots/**: Contains screenshots captured during test execution.
- **pom.xml**: Maven dependencies.
- **TestNG.xml**: To execute testcases using TestNG suite configuration. Testdata file passed as parameter in TestNg.xml file.
- **README.md**: Project documentation.

### Test Case
- **TC01_ToDos.java**: Contains test methods to test the ToDos application.
- Testcase extends BaseTest class.
- Testcase includes multiple test methods to test the ToDos application.


   1) addEditCompleteToDo(): Test method to add, edit, and complete a ToDo. Verified following
scenarios under this test method.
      - Add a new ToDo - Verify the ToDo is added under All and Active filter
      - Edit the ToDo - Edit the added ToDo and verify the ToDo is updated under All and Active filter
      - Complete the ToDo - Completed the edited ToDo and
      -                     Verify completed item cannot be displayed under the active tab.
                            Verify the completed item displayed under complete tab.
                            Verify the completed item displayed as checked under complete tab.
                            Verify the Users should be able to toggle a completed item back to 
                            active by clicking the checkbox again and verify task should again be displayed under the active tab and 
                            verify task is not displayed under the complete tab.


   2) deleteToDo(): Delete ToDo items under each filter(Tab). Verified following scenarios under this test method.
      - Delete ToDo items under All filter - Add multiple ToDo items and delete one ToDo item and verify the ToDo item is removed from the All list
      - Delete ToDo items under Active filter - Add multiple ToDo items, navigate to Active Tab and delete one ToDo item and verify the ToDo item is removed from the Active list
      - Delete ToDo items under Completed filter - Add multiple ToDo items, mark all itm as completed, navigate to Completed Tab and delete one ToDo item and verify the ToDo item is removed from the Completed list
      

   3) verifyViewToDoList(): Test method to verify the view of ToDo list. Verified following scenarios under this test method.
      - Add multiple ToDos and Verify item order with the newest at the bottom
      - Verify user can see active item counter correctly
      - Verify active count displayed correctly as items completed
      

   4) filterToDos(): This method is used to filter to do items based on tabs and verify the items are displayed under correct tabs. Verified following scenarios under this test method.
      - Filter ToDos
      - Verify ToDos are displayed
      

   5) clearCompleted(): Test method to clear completed ToDos. Verified following scenarios under this test method.
      - Clear completed ToDos
      - Verify completed ToDos are removed
      

   6) selectUnselectAllToDos: Test method to select and unselect all ToDos. Verified following scenarios under this test method.
      - Select all ToDos
      - Verify all ToDos are selected
      - Unselect all ToDos
      - Verify all ToDos are unselected



