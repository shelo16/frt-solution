# Microservices Demo

This is a demo application consisting of 5 microservices built with Spring Boot, Java 17, Gradle, RabbitMQ, and PostgreSQL.

## Services and Swagger Links

- AuthService: http://localhost:8080/swagger-ui/index.html#/
- ProductService: http://localhost:8081/swagger-ui/index.html#/
- OrderService: http://localhost:8082/swagger-ui/index.html#/
- NotificationService: http://localhost:8083/swagger-ui/index.html#/
- PadService: http://localhost:8084/swagger-ui/index.html#/

## Running the Application

To run the application in docker, navigate to the root folder and run:
     
`docker-compose up`

## Database Population

To populate the database, use the following email addresses when registering:

- ROLE = ADMIN: Use an email containing the word "admin"
- ROLE = SELLER: Use an email containing the word "seller"
- ROLE = CLIENT: Use any other email

Please note that this constraint is added only for testing purposes.

## Note

This is a demo application built in a limited amount of time, and is not intended for production use without further modifications.