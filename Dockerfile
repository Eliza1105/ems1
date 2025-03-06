
FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /app
COPY . /app
COPY target/out/artifacts/ems1_jar/ems1.jar app.jar

RUN mvn -f /app/pom.xml clean package -DskipTests
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Копируем скомпилированный jar файл в контейнер
#COPY --from=builder /target/out/artifacts/ems1_jar/ems1.jar /app/*.jar
EXPOSE 8082
# Запускаем приложение
CMD ["java", "-jar", "app.jar"]