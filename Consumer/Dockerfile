# Stage 1: Build Consumer
FROM maven:3.9.5-eclipse-temurin-17 AS builder

WORKDIR /app

# First copy the parent pom.xml
COPY pom.xml ./

# Create Consumer directory and copy its pom.xml
COPY Consumer/pom.xml ./Consumer/

# Copy Consumer's source code
COPY Consumer/src/ ./Consumer/src/

# Build the application
RUN mvn -f Consumer/pom.xml clean package -DskipTests

# Stage 2: Run Consumer
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/Consumer/target/*.jar app.jar

# Expose Consumer's port
EXPOSE 8082

# Command to run Consumer
CMD ["java", "-jar", "app.jar"]