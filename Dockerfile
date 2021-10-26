FROM openjdk:8-jdk-alpine
ENV APP_LOG_DIR=/var/log/pokemon-wiki \
    APP_OPTS="-Dfile.encoding=UTF-8" \
    JVM_OPTS="-server -Xms500m -Xmx500m -XX:+HeapDumpOnOutOfMemoryError -XX:+UseConcMarkSweepGC \
-verbose:gc -Xloggc:${APP_LOG_DIR}/gc_%p.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationStoppedTime -XX:+PrintAdaptiveSizePolicy \
-XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=30m"
WORKDIR /app
COPY ./target/pokemon-wiki.jar /app
COPY ./pokemon_wiki.db /app
EXPOSE 1234
CMD java ${JVM_OPTS} ${APP_OPTS} -jar pokemon-wiki.jar