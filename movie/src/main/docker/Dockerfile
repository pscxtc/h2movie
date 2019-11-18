FROM java:8
ARG ARG_HEALTH_CHECK
ADD ${ARG_HEALTH_CHECK} /opt/
ARG JAR_FILE
ADD ${JAR_FILE} /root/app.jar
RUN bash -c 'touch /root/app.jar'
HEALTHCHECK CMD /bin/bash /opt/healthCheck.sh
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/root/app.jar"]