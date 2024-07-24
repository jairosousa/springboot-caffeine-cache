FROM openjdk:21

ENV JAVA_OPS "-Xms256m -Xmx1024m"

WORKDIR /app
COPY target/*.jar /app/app.jar
COPY entrypoint.sh /app/entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["sh","entrypoint.sh"]