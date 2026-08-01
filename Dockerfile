FROM eclipse-temurin:25-jdk

WORKDIR app/

COPY target/DevAnalyzer-0.0.1-SNAPSHOT.jar DevAnalyzer.jar

ENTRYPOINT ["java", "-jar", "DevAnalyzer.jar"]

EXPOSE 8080