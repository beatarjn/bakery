FROM eclipse-temurin:21-jdk-jammy

WORKDIR /bakery

COPY ../gradle .
COPY ../build.gradle .
COPY ../gradlew .
COPY ../settings.gradle .

RUN chmod +x ./gradlew

RUN ls -al

RUN ./gradlew bakery_service:bootJar

ENV SPRING_PROFILES_ACTIVE=docker

CMD ["java", "-jar", "bakery_service-0.0.1-SNAPSHOT.jar"]