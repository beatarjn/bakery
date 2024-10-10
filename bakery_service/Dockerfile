FROM eclipse-temurin:21-jdk-jammy

WORKDIR /bakery

COPY ../gradle/wrapper/* gradle/wrapper/
COPY ../build.gradle .
COPY ../gradlew .
COPY ../settings.gradle .
COPY . .


RUN chmod +x ./gradlew

RUN ./gradlew bakery_service:bootJar --no-daemon --stacktrace --info

ENV SPRING_PROFILES_ACTIVE=docker

CMD ["java", "-jar", "bakery_service/build/libs/bakery_service-0.0.1-SNAPSHOT.jar"]