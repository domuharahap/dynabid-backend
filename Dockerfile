# Use a lightweight OpenJDK 21 runtime image
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copy the pre-built JAR file from your local target directory
COPY target/bidding-backend-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]