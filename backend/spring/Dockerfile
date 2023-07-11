# Use a base image with Java installed
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the source code into the container
COPY . /spring

# Build the application (if required)
RUN ./gradlew build

# Expose the port on which your Spring application listens
EXPOSE 8080

# Run the Java application
CMD ["java", "-jar", "build/libs/your-application.jar"]
