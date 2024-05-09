FROM openjdk:17
EXPOSE 8089
ADD target/achat-1.0.jar Achat-backend.jar
ENTRYPOINT ["java","-jar","Achat-backend.jar"]