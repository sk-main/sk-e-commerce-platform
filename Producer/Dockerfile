# Stage 1: Build Producer
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

# Build the Producer module
RUN mvn clean package -DskipTests -pl Producer -am

# Stage 2: Run Producer
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built Producer JAR from the builder stage
COPY --from=builder /app/Producer/target/*.jar app.jar

# Expose Producer's port
EXPOSE 8081

# Command to run Producer
CMD ["java", "-jar", "app.jar"]