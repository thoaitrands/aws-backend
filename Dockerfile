# Use the official Maven image for a build stage
# FROM maven:3.8.6-jdk-11-slim AS build

# # Copy the project files to the container
# COPY src /home/app/src
# COPY pom.xml /home/app

# # Package the application
# RUN mvn -f /home/app/pom.xml clean install -DskipTests

# Use OpenJDK for the runtime stage
FROM openjdk:11-jdk-slim

# Copy the JAR from the build stage to the runtime stage
COPY aws-backend-0.0.1-SNAPSHOT.jar /usr/local/lib/aws-backend.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-Dspring.profiles.active=default", "-jar", "/usr/local/lib/aws-backend.jar"]