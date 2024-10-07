FROM eclipse-temurin:21.0.4_7-jre-noble
LABEL authors="denis"
COPY out/*.jar app.jar
ENTRYPOINT ["java", ".jar","/app.jar"]