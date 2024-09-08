FROM openjdk:17
EXPOSE 8080
ADD target/carhop-webapp.jar
ENTRYPOINT ["java", "-jar", "carhop-webapp.jar"]