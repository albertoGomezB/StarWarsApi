# Use a Java base image with Java 17 from AdoptOpenJDK
FROM openjdk:17-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the application JAR into the container
COPY target/SWAPI-0.0.1-SNAPSHOT.jar /app

# Expose port 8080
EXPOSE 8080

# Command to run the application when the container starts
CMD ["java", "-jar", "SWAPI-0.0.1-SNAPSHOT.jar"]
