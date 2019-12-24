FROM openjdk:8-jdk-alpine

ENV APP_NAME=pokemon-wiki
ARG APP_ENV=master
ARG APP_LOG_DIR=/var/log/${APP_NAME}
ENV APP_OPTS="-Dfile.encoding=UTF-8 -Dspring.profiles.active=${APP_ENV}"
ENV JVM_OPTS="-server -Xms400m -Xmx400m -XX:+HeapDumpOnOutOfMemoryError -XX:+UseConcMarkSweepGC \
-verbose:gc -Xloggc:${APP_LOG_DIR}/gc_%p.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationStoppedTime -XX:+PrintAdaptiveSizePolicy \
-XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=30m"

WORKDIR /opt/${APP_NAME}
COPY target/pokemon-wiki-1.0.jar ${APP_NAME}.jar

CMD java ${JVM_OPTS} ${APP_OPTS} -jar ${APP_NAME}.jar