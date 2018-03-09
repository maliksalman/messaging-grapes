FROM openjdk:8-jdk-alpine
RUN apk update && apk add bind-tools bash
ADD build/libs/grapes-sink-0.0.1-SNAPSHOT.jar boot-app.jar
ADD start.sh start.sh
RUN chmod 755 start.sh
EXPOSE 8080
ENTRYPOINT ["./start.sh"]
