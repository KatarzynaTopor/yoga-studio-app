# Build Stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B || mvn dependency:resolve -B
COPY src ./src
RUN mvn clean package -DskipTests && ls -lh target/

# Runtime Stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8000
CMD ["java", "-jar", "/app/app.jar"]
