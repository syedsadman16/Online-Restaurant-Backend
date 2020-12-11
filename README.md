# Online Restaurant System
Group project for **CSC 322 @ CCNY**.

This is the backend for the [***Client Repo***](https://github.com/syedsadman16/Online-Restaurant-System-Frontend) using Spring Boot

## Project Site
https://syedsadman16.github.io/Online-Restaurant-System-Frontend/#/

------
#### Team R:
 - Syed Sadman
 - Abir Deb
 - Andrey Goryuk
 - Michal Moryosef
 - Samuel Fils

----

## Running locally

```
git clone https://github.com/syedsadman16/Online-Restaurant-Backend.git
cd Online-Restaurant-Backend
./mvnw package
java -jar target/*.jar
```

Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):

```
./mvnw spring-boot:run
```

You can then run the backend here: http://localhost:8080/

### Technologies Used
- Spring Boot (Maven)
- MVC Architecture
- Spring Security
- Spring Data JPA
- JWT Authentication
- MySQL
- H2 Database Engine
- Heroku

### Project Documentation:

1. [Phase 1 Report - Software Requirements & Description](https://github.com/syedsadman16/Online-Restaurant-Backend/blob/next/CSC32200_ORS_R.pdf)

2. [Phase 2 Report - Design Report](https://github.com/syedsadman16/Online-Restaurant-Backend/blob/next/CSC32200_ORS_R_Phase2.pdf)
