FROM eclipse-temurin:21-jdk-jammy

WORKDIR /bakery

COPY ../gradle .
COPY ../build.gradle .
COPY ../gradlew .
COPY ../settings.gradle .

RUN chmod +x ./gradlew

RUN ./gradlew bakery_service:bootJar
