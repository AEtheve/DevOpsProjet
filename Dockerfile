FROM maven:3.8.3-openjdk-17-slim
COPY ./. /
RUN mvn clean install
