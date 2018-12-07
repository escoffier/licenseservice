FROM openjdk:8-jdk-alpine

VOLUME /tmp
#ARG DEPENDENCY=target/dependency
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY ${DEPENDENCY}/META-INF /app/META-INF
#COPY ${DEPENDENCY}/BOOT-INF/classes /app
RUN  apk update && apk upgrade && apk add netcat-openbsd && apk add curl
RUN echo $JAVA_HOME
#RUN ls /usr/lib/jvm/java-1.8-openjdk/jre/lib/amd64
COPY jce_policy-8.zip /tmp/jce_policy-8.zip
RUN cd /tmp/ && \
    #curl -k -LO "http://download.oracle.com/otn-pub/java/jce/8/jce_policy-8.zip" -H 'Cookie: oraclelicense=accept-securebackup-cookie' && \
    unzip jce_policy-8.zip && \
    rm jce_policy-8.zip && \
    yes |cp -v /tmp/UnlimitedJCEPolicyJDK8/*.jar /usr/lib/jvm/java-1.8-openjdk/jre/lib/security/
#ENTRYPOINT ["java","-cp","app:app/lib/*","com.licenseservice.LicenseserviceApplication"]
ARG JAR_FILE
COPY target/${JAR_FILE} /tmp/licensingservice.jar
RUN ls /tmp
ENTRYPOINT ["java","-jar","/tmp/licensingservice.jar"]