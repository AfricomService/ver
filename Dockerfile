FROM openjdk:8
EXPOSE 8080
ADD /target/verprojects-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java","-jar","application.jar"]
