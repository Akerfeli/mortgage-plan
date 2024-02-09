# Code Test - Mortgage Plan

This is a code test for Crosskey, featuring a Java Spring Boot backend and a React frontend client. The goal is to develop a Java application to calculate monthly mortgage payments based on loan details. The Spring Boot backend handles tasks like file operations and payment calculations, with API endpoints for interaction. 

As part of the constraints of the code test, the use of java.math or similar mathematical dependencies is prohibited. Consequently, monetary values and percentages are managed using **`long`** and **`int`** data types to mitigate potential rounding issues associated with float values. This choice necessitates significant string manipulation to accommodate differences in representation between the file, system, and client. Custom functions handle rounding and exponentiation.

## Dependencies

To run this project locally, ensure you have the following dependencies installed on your system:

- JDK-21
- Maven 3.9.6
- Node and NPM

## Instructions

Clone the repository to run on your local machine.
   ```
   git clone https://github.com/Akerfeli/mortgage-plan.git
   ```


### Run Spring Boot Web Server

**1. Navigate to the `mortgage-plan-backend` directory**

**2. In src\main\resources, open application.properties**
  
  Change the value of prospect.file.path to the **absolute path** to your **prospect.txt** file.
   ( Note: Please use double \\ )
   
   Example:
   ```
   prospect.file.path=C:\\Users\\Felicia\\IdeaProjects\\mortgage-plan\\data\\prospects.txt
   ```

**3. Install plugins**
   ```
   mvn clean install
   ```

**4. Compile and build**
   ```
   mvn clean package
   ```

**5. Run application** 
   ```
   mvn spring-boot:run
   ```

The server will be exposed at http://localhost:8080/


### Run Tests
```
mvn test
```

### Run React Frontend

**1. Navigate to the `mortgage-plan-react` directory.**

**2. Install dependencies**  
   ```
   npm install
   ```

**3. Build the project**
   ```
   npm run build
   ```

**4. Preview the application with**
   ```
   npm run preview -- --port 3001
   ```

**5. To use the web UI,** open your web browser and go to http://localhost:3001/
