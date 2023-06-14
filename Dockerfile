FROM bellsoft/liberica-openjdk-alpine
COPY /app.jar /app.jar
CMD ["java", "-jar", "app.jar"]
