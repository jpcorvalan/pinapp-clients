# Etapa 1: Build con Maven y JDK 21 (compilacion)
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn -DskipTests clean package

# Etapa 2: Runtime con JDK 21 (ejecución)
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar clients-0.0.1-SNAPSHOT.jar

# Cloud Run escucha en $PORT
ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/clients-0.0.1-SNAPSHOT.jar"]