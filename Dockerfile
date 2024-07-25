
# Use OpenJDK for the runtime stage
FROM openjdk:11-jdk-slim

# Copy the JAR from the build stage to the runtime stage
COPY aws-backend-0.0.1-SNAPSHOT.jar /usr/local/lib/aws-backend.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-Dspring.profiles.active=default", "-jar", "/usr/local/lib/aws-backend.jar"]