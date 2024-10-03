FROM openjdk:21-jdk

WORKDIR /bakery

COPY build/libs/*.jar /bakery/bakery_service.jar

ENV SPRING_PROFILES_ACTIVE=docker

CMD ["java", "-jar", "bakery_service.jar"]