FROM openjdk:17
EXPOSE 8090
ADD target/achat-1.0.jar Achat-backend.jar
ENTRYPOINT ["java","-jar","Achat-backend.jar"]