FROM openjdk:8-jdk-alpine
VOLUME tmp
ARG JAR_FILE
#ADD target/${JAR_FILE} app.jar
ADD target/*.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]
