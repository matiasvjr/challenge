FROM openjdk:8-alpine as build

LABEL maintainer="matias.valdir@gmail.com"

RUN mkdir /home/build

WORKDIR /home/build

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw test package


FROM openjdk:8-jre-alpine as release

LABEL maintainer="matias.valdir@gmail.com"

RUN mkdir /home/app

WORKDIR /home/app

EXPOSE 8080

COPY --from=build /home/build/target/*.jar /home/app/application.jar

CMD ["java", "-jar", "application.jar"]
