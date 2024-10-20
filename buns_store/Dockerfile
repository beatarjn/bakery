FROM eclipse-temurin:21-jdk-jammy

WORKDIR /bakery

COPY . .

RUN chmod +x ./gradlew && ./gradlew buns_store:bootJar --no-daemon --stacktrace --info

ENV SPRING_PROFILES_ACTIVE=docker

CMD ["java", "-jar", "buns_store/build/libs/buns_store-0.0.1-SNAPSHOT.jar"]