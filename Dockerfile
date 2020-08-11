FROM gradle:4.3.0-jdk8-alpine

MAINTAINER Luis Cataldo "lcataldo@gmail.com"

# Default to UTF-8 file.encoding
ENV LANG C.UTF-8

EXPOSE 8080

COPY build/libs/*.jar /api/app.jar

CMD java -Xms64m -Xmx1024m -Dserver.port=$PORT -jar /api/app.jar
