FROM java:8
MAINTAINER pscxtc <pscxtc@163.com>
ARG JAR_FILE
ADD ${JAR_FILE} /root/app.jar
RUN bash -c 'touch /root/app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/root/app.jar"]