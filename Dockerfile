# Use Maven to build the project
FROM maven:3.9.5-eclipse-temurin-17 as builder

# Set the working directory
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the project
RUN mvn package

# Use a smaller JDK image for running the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the builder
COPY --from=builder /app/target/your-app-name.jar /app/app.jar

# Run the application
CMD ["java", "-jar", "/app/app.jar"]
