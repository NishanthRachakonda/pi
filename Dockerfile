FROM openjdk:8-jre-alpine3.9
COPY target/pi-1.0.jar ./pi.jar
CMD ["java", "-jar", "pi.jar"]