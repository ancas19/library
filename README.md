
# Library APP
___
This is a REST API for a library management sysrtem. The project is designed to help me to practice my backend development skills with Java and Spring Boot. It provides functionalities to manage books, authors, and users. The API is secured with JWT and has a role-based access control system.

## Technologies
* Java (Spring Boot)
* PostgreSQL
* Spring Data JPA
* Spring Security
* Docker
* Gmail
* Cloudinary
* Swagger

## Database  model
The database model is shown in the following diagram:
![library .png](library%20.png)

## Installation and set up
 Ennsure you have the following installed in your machine:
* Java 21
* gradle
* PostgreSQL
* Docker(optional)
* Account in Cloudinary
* Account in Gmail

You need to generate CLOUDINARY_API_KEY,  CLOUDINARY_API_SECRET, CLOUDINARY_CLOUD_NAME to save images in Cloudinary. Also, you need to generate GMAIL_USERNAME and GMAIL_PASSWORD(It is a token to send emails) to send emails.
### 1.  Clone the repository

```bash
 https://github.com/ancas19/library.git
 cd library
```
### 2. Configure database
Create a database in PostgreSQL. The project includes a folder named sql/, which contains .sql files for database schema and initial data.

### 3. Set environment variables
You need to set the following environment variables:
```bash
# Server Configuration
APP_PORT=8080
CONTEXT_PATH=/api

# Database Configuration
POSTGRES_HOST=localhost
POSTGRES_PORT=5432
POSTGRES_DB=library_db
POSTGRES_USER=postgres
POSTGRES_PASSWORD=yourpassword

# CORS Configuration
PATHS=/**
ORIGINS=*
METHODS=GET,POST,PUT,DELETE
HEADERS=*
ALLOW_URIS=*

# Logging
LOGGING_LEVEL=INFO

# Redis Configuration (Optional)
REDIS_HOST=localhost
REDIS_PORT=6379
REDIS_TIMEOUT=2000
REDIS_PASS=

# Email Configuration
EMAIL_USER=your-email@gmail.com
EMAIL_PASSWORD=your-email-password

# Cloudinary Configuration
CLOUDINARY_CLOUD_NAME=your_cloud_name
CLOUDINARY_API_KEY=your_api_key
CLOUDINARY_API_SECRET=your_api_secret

# JWT Configuration
JWT_SECRET=your_secret_key
JWT_EXPIRATION=3600000
```

### 4. Install dependencies
Run the following command to install the dependencies:
```bash
./gradlew build  # Linux
gradlew.bat build # Windows
```

### 5. Run the application
Run the following command to start the application:
```bash
./gradlew bootRun  # Linux
java -jar build/libs/library-0.0.1-SNAPSHOT.jar # Using the jar file
```
You can run the application using Docker. The project includes a Dockerfile and a docker-compose.yml file. Run the following command to build and run the application using Docker:
```bash
docker-compose up 
```


## Testing
The project includes unit tests to ensure the reliability and correctness of the implemented functionalities. The test coverage and code quality are continuously monitored using SonarCloud.
### Test coverage report
You can check the test coverage report by opening the following link: 

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ancas19_library&metric=coverage)](https://sonarcloud.io/component_measures?id=ancas19_library&metric=coverage&view=list)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ancas19_library&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=ancas19_library)

### Runnig tests locally
Run the following command to run the tests:
```bash
./gradlew test  # Linux
gradlew.bat test # Windows
```

## Author 
 Developed by Andrés Castro.
