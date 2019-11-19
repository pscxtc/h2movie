FROM java:8
MAINTAINER pscxtc <pscxtc@163.com>
ARG ARG_HEALTH_CHECK
ADD ${ARG_HEALTH_CHECK} /opt/
ARG JAR_FILE
ADD ${JAR_FILE} /root/app.jar
RUN bash -c 'touch /root/app.jar'
HEALTHCHECK --interval=10s --timeout=3s --retries=3 CMD /bin/bash /opt/healthCheck.sh
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/root/app.jar"]