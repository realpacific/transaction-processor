# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="prashantbarahi@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

RUN java -version
RUN pwd

EXPOSE 7080

# The application's jar file
ARG JAR_FILE=./target/transaction-processor-1.0-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} my-app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","my-app.jar"]