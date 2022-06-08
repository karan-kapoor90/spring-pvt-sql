FROM openjdk:8-jre
COPY target/spring-boot-hello-world-1.0.0-SNAPSHOT.jar /tmp
COPY client-cert.pem /tmp
COPY client-key.pem /tmp
COPY server-ca.pem /tmp
WORKDIR /tmp
RUN ls -ltr
CMD ["java","-jar","spring-boot-hello-world-1.0.0-SNAPSHOT.jar"]