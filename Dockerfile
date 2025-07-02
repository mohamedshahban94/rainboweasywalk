# Start from an official JDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built jar file
COPY target/rainboweasywalk-0.0.1-SNAPSHOT.jar app.jar

# Expose port (same as in your Spring Boot app — default 8080)
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
