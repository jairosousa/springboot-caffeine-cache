FROM openjdk:21

ENV JAVA_OPS "-Xms256m -Xmx1024m"


RUN apt-get update && \
    apt-get install wget ca-certificates -y && \
    mkdir /app && \
    adduser javauser && \
    adduser javauser javauser && \
    cd /app && \
    wget -O dd-java-agent.jar 'https://dtdg.co/latest-java-tracer'

COPY target/*.jar /app/app.jar
COPY entrypoint.sh /app/entrypoint.sh

RUN chown -R javauser:javauser /app
USER javauser

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["sh","entrypoint.sh"]