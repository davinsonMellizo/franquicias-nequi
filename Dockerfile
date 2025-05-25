FROM openjdk:17
WORKDIR /app
COPY applications/app-service/build/libs/ms_franquicias.jar /app/
EXPOSE 8080
CMD ["java", "-jar", "ms_franquicias.jar"]