FROM openjdk:21-jdk

COPY build/libs/*.jar /bakery/bakery_service-0.0.1-SNAPSHOT.jar

WORKDIR /bakery

ENV DATABASE_HOST localhost
ENV DATABASE_PORT 5555
ENV DATABASE_USERNAME x
ENV DATABASE_PASSWORD x
ENV DATABASE_NAME bakerydb

ENV SPRING_PROFILES_ACTIVE=docker

EXPOSE 8080

CMD ["java", "-jar", "bakery_service-0.0.1-SNAPSHOT.jar"]