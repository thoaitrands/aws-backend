
# Use OpenJDK for the runtime stage
FROM openjdk:11-jdk-slim

RUN apt-get update && apt-get install -y maven

# Set the working directory in the container
WORKDIR /app

# Copy the source code to the container
COPY . /app

# Build the application
RUN mvn clean install -DskipTests

# Copy the JAR from the build stage to the runtime stage
COPY aws-backend-0.0.1-SNAPSHOT.jar /usr/local/lib/aws-backend.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-Dspring.profiles.active=default", "-jar", "/usr/local/lib/aws-backend.jar"]clear
