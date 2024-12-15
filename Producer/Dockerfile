# Stage 1: Build Producer
FROM maven:3.9.5-eclipse-temurin-17 AS builder

WORKDIR /app

# First copy the parent pom.xml
COPY pom.xml ./

# Create Producer directory and copy its pom.xml
COPY Producer/pom.xml ./Producer/

# Copy Producer's source code
COPY Producer/src/ ./Producer/src/

# Build the application
RUN mvn -f Producer/pom.xml clean package -DskipTests

# Stage 2: Run Producer
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/Producer/target/*.jar app.jar

# Expose Producer's port
EXPOSE 8081

# Command to run Producer
CMD ["java", "-jar", "app.jar"]