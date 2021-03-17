FROM openjdk:8-jdk-alpine
ENV APP_ENV=snapshot \
    APP_LOG_DIR=/var/log/pokemon-wiki \
    APP_OPTS="-Dfile.encoding=UTF-8 -Dspring.profiles.active=${APP_ENV}" \
    JVM_OPTS="-server -Xms500m -Xmx500m -XX:+HeapDumpOnOutOfMemoryError -XX:+UseConcMarkSweepGC \
-verbose:gc -Xloggc:${APP_LOG_DIR}/gc_%p.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationStoppedTime -XX:+PrintAdaptiveSizePolicy \
-XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=30m"
WORKDIR /opt/app/
COPY ./target/pokemon-wiki.jar .
VOLUME ${APP_LOG_DIR}
EXPOSE 8080
CMD java ${JVM_OPTS} ${APP_OPTS} -jar pokemon-wiki.jar