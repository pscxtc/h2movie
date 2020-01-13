FROM java:8
MAINTAINER pscxtc <pscxtc@163.com>
ARG ARG_HEALTH_CHECK
ADD ${ARG_HEALTH_CHECK} /opt/
ARG JAR_FILE
ADD ${JAR_FILE} /root/app.jar
RUN bash -c 'touch /root/app.jar'
#HEALTHCHECK CMD /bin/bash /opt/healthCheck.sh
ENTRYPOINT [ "sh", "-c", "exec java -jar /root/app.jar"]