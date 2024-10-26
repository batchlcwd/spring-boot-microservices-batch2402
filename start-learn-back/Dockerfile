# Use an official OpenJDK runtime as the base image
FROM openjdk:21

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot JAR file to the container
COPY target/start-learn-back-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java","--enable-preview", "-jar", "app.jar"]
