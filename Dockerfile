FROM openjdk:17-alpine
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
COPY target/*.jar /opt/grocery-booking-application.jar
ENTRYPOINT exec java $JAVA_OPS -jar grocery-booking-application.jar