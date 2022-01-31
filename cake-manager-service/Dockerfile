#### Stage 1: Build the application
FROM gradle:7.3-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src

# Set the current working directory inside the image
WORKDIR /home/gradle/src

# Build the application
RUN gradle build

#### Stage 2: A minimal docker image with command to run the app
FROM openjdk:8-jre-slim

EXPOSE 8080

RUN mkdir /app

# Copy executable jar file to run the application
COPY --from=build /home/gradle/src/build/libs/cake-manager-service-0.0.1-SNAPSHOT.jar /app/cake-manager-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/cake-manager-service-0.0.1-SNAPSHOT.jar"]
