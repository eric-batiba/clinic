FROM openjdk:17-jdk
WORKDIR /app
ADD target/clinic-app*jar /app/clinic-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "clinic-app.jar"]