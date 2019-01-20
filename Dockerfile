FROM openjdk:11-jre-slim

COPY ./build/libs/app.jar /app/dist/app.jar
WORKDIR /app/dist

EXPOSE 8080

ENTRYPOINT exec java \
    -Xms150m \
    -Xmx150m \
    -XX:MetaspaceSize=200m \
    -XX:MaxMetaspaceSize=200m \
    -Xss512k \
    -jar app.jar
