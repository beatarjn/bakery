FROM eclipse-temurin:21-jdk-jammy

WORKDIR /bakery

COPY ../gradle/wrapper/* gradle/wrapper/
COPY ../build.gradle .
COPY ../gradlew .
COPY ../settings.gradle .
COPY . .

RUN chmod +x ./gradlew && ./gradlew buns_store:bootJar --no-daemon

ENV SPRING_PROFILES_ACTIVE=docker

CMD ["java", "-jar", "buns_store/build/libs/buns_store-0.0.1-SNAPSHOT.jar"]