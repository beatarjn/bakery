FROM openjdk:21-jdk

COPY build/libs/*.jar /bakery/bakery_service-0.0.1-SNAPSHOT.jar

WORKDIR /bakery

ENV SPRING_PROFILES_ACTIVE=docker

CMD ["java", "-jar", "bakery_service-0.0.1-SNAPSHOT.jar"]