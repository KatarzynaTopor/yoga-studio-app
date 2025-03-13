# Build Stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copying only necessary files first
COPY pom.xml ./
RUN mvn dependency:go-offline -B || mvn dependency:resolve -B

# Copy source code
COPY src ./src

# Build the application and ensure the jar file is generated
RUN mvn clean package -DskipTests && ls -lh target/

# Runtime Stage
FROM openjdk:17-jdk-slim
WORKDIR /app

# For deployment: COPY the JAR file
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar /app/app.jar

# For debugging: Check if the file is actually present
RUN ls -lh /app/

# Open port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
