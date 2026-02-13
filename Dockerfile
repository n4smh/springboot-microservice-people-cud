# Use a lightweight base image with Java 21 JRE installed, such as Eclipse Temurin JRE-21
FROM eclipse-temurin:21

# Define the user and group name
ARG USER_NAME=appuser
ARG GROUP_NAME=appgroup

# Create a group and a user, then add the user to the group
# The --disabled-login option is a security best practice for container users
# The --gecos "" part is often used to avoid being prompted for user information
RUN addgroup $GROUP_NAME && \
    adduser --disabled-login --ingroup $GROUP_NAME --gecos "" $USER_NAME
	
# Set the working directory inside the container
WORKDIR /app

# Copy your application JAR file into the container at /app/
COPY ./target/person-0.0.1-SNAPSHOT.jar /app/people.jar
RUN chown -R $USER_NAME:$GROUP_NAME /app

# Switch to the new non-root user
USER $USER_NAME

# Command to run the application when the container starts
CMD ["java", "-jar", "people.jar"]
