# Stage 1: Build Consumer
FROM maven:3.9.5-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy the parent POM and all module POMs
COPY pom.xml ./
COPY Producer/pom.xml ./Producer/pom.xml
COPY Consumer/pom.xml ./Consumer/pom.xml

# Copy all source code
COPY src ./src
COPY Producer/src ./Producer/src
COPY Consumer/src ./Consumer/src

# Cache dependencies
RUN mvn dependency:go-offline -f pom.xml

# Build the Consumer module
RUN mvn clean package -DskipTests -pl Consumer -am

# Stage 2: Run Consumer
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built Consumer JAR from the builder stage
COPY --from=builder /app/Consumer/target/*.jar app.jar

# Expose Consumer's port
EXPOSE 8082

# Command to run Consumer
CMD ["java", "-jar", "app.jar"]