# build application
FROM maven:3.8.3-openjdk-17 as builder
WORKDIR /app
COPY /spring /app
RUN mvn clean package

# create light weigh image to run
FROM openjdk:17
EXPOSE 8080
WORKDIR /app
COPY --from=builder /app/target/website-0.0.1-SNAPSHOT.jar /app/website-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "website-0.0.1-SNAPSHOT.jar"]
