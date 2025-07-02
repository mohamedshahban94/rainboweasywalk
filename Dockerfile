# Use official Maven image to build the JAR
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy the full project (including pom.xml and src/)
COPY . .

# Build the project (creates target/*.jar)
RUN ./mvnw clean package -DskipTests

# Use lightweight OpenJDK image to run the app
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built jar from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the Spring Boot default port
EXPOSE 8080

# Start the app
ENTRYPOINT ["java", "-jar", "app.jar"]
