FROM java:8-alpine
VOLUME /tmp
COPY build/libs/demo-starter-test-1.0-SNAPSHOT.jar app.jar
#ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]
