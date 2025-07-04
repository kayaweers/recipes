FROM alpine/java:21-jre

COPY target/recipes-0.0.1-SNAPSHOT.jar recipes-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/recipes-0.0.1-SNAPSHOT.jar"]

#Expose the port the application runs on
EXPOSE 8081

