# Stage 1: Build the application using Maven
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

# Copy the Maven configuration files first (to leverage caching)
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copy the entire project source code
COPY . .

# Build the application (skip tests for faster build)
RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight image to run the application
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080 for the Spring Boot application
EXPOSE 8080

# Set the MongoDB connection string as an environment variable (optional)
ENV SPRING_DATA_MONGODB_URI=mongodb+srv://ryoshwaa:MashaAllah1996@cluster0.j23ve.mongodb.net/nfc?retryWrites=true&w=majority&appName=Cluster0

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
