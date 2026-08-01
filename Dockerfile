
FROM eclipse-temurin:25-jdk AS build

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar DevAnalyzer.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "DevAnalyzer.jar"]