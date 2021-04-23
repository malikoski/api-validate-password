FROM maven:3.8.1-openjdk-15-slim AS build

COPY ./pom.xml ./pom.xml

# build all dependencies
RUN mvn dependency:go-offline -B

ADD . /usr/app/src
WORKDIR /usr/app/src

RUN mvn package

FROM openjdk:15.0.2-jdk-slim

VOLUME /tmp

COPY --from=build "/usr/app/src/target/validate-password*.jar" app.jar
CMD [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]