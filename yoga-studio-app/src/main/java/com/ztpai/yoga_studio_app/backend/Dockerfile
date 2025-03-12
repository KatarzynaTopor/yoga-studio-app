# Faza budowania - używamy obrazu Maven z JDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Ustawienie katalogu roboczego
WORKDIR /app

# Kopiowanie tylko `pom.xml` (umożliwia wykorzystanie cache Dockera)
COPY pom.xml .

# Pobieranie zależności, aby przyspieszyć budowanie
RUN mvn dependency:go-offline -B || mvn dependency:resolve -B

# Kopiowanie całej reszty kodu źródłowego
COPY src ./src

# Kompilacja aplikacji (z pominięciem testów, wielowątkowość)
RUN mvn clean package -DskipTests -T 4

# Faza uruchomienia - używamy lżejszego obrazu OpenJDK 17
FROM openjdk:17-jdk-slim

# Ustawienie katalogu roboczego
WORKDIR /app

# Kopiowanie skompilowanego pliku JAR z poprzedniej fazy
COPY --from=build /app/target/*.jar app.jar

# Otwieramy port 8080
EXPOSE 8080

# Uruchamiamy aplikację
ENTRYPOINT ["java", "-jar", "app.jar"]
