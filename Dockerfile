FROM openjdk:18-alpine3.13
EXPOSE 8080
ADD target/*.jar ssn_validator_api.jar
ENTRYPOINT ["java", "-jar", "/ssn_validator_api.jar"]
