# F1 Betting Service

## Description
F1 Betting Service is a Spring Boot application that provides a backend for Formula 1 betting. It allows users to search for F1 events, place bets, and process event outcomes. The service integrates with external F1 data providers and supports basic betting logic, wallet management, and event outcome processing.

## Features
- Search for F1 events and drivers
- Place and manage bets
- Process event outcomes and update bets
- Integrate with external F1 data providers
- Rate-limited driver market enrichment

## Technologies
- Java 17+
- Spring Boot
- Spring Data JPA
- H2 (in-memory database for development/testing)
- MapStruct (for DTO mapping)
- Maven

## Prerequisites
- Java 17 or newer
- Maven 3.8+

## Running the Application
1. **Clone the repository:**
   ```sh
   git clone <your-repo-url>
   cd f1-betting-service
   ```
2. **Build the project:**
   ```sh
   mvn clean install
   ```
3. **Run the application:**
   ```sh
   mvn spring-boot:run
   ```
   Or run the jar directly:
   ```sh
   java -jar target/f1-betting-service-0.0.1-SNAPSHOT.jar
   ```
4. **Access the API:**
   The service will start on [http://localhost:8080](http://localhost:8080)

## Testing
Run all unit and integration tests with:
```sh
mvn clean test
```

## Configuration
- Default configuration is in `src/main/resources/application.yml`.
- Test configuration is in `src/test/resources/application.yml`.
- The application uses H2 in-memory database by default for development and testing.

## API Documentation
- The API endpoints are available under `/api` (see controller classes for details).
- Models and DTOs are located in `src/main/java/com/sporty/f1_betting_service/api/dto`.

## Notes
- The OpenF1 API is rate-limited to 3 requests per second for driver data enrichment.
- The application is designed for demonstration and development purposes.

## License
MIT

