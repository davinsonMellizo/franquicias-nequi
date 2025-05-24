FROM openjdk:17
WORKDIR /app
COPY applications/app-service/build/libs/app-service.jar /app/
EXPOSE 8080
CMD ["java", "-jar", "app-service.jar"]