FROM maven:3.9.8-sapmachine-21 AS build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests=true

FROM openjdk:21
ENV JAVA_OPS "-Xms256m -Xmx1024m"
WORKDIR /app
COPY --from=build ./build/target/*.jar ./application.jar

EXPOSE 8080

ENTRYPOINT java -jar application.jar