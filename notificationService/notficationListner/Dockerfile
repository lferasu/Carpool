FROM openjdk:11
EXPOSE 8080
VOLUME /tmp
COPY target/*.jar notificationservice.jar
ENTRYPOINT ["java","-jar","/notificationservice.jar"]
