FROM maven:3.6.0-jdk-8-alpine AS builder
WORKDIR /opt/build/
COPY . .
RUN mvn org.apache.maven.plugins:maven-install-plugin:3.0.0-M1:install-file -Dfile=lib/mundo-all-1.0-SNAPSHOT.pom -DgroupId=com.mundo -DartifactId=mundo-all -Dversion=1.0-SNAPSHOT -Dpackaging=pom \
  && mvn org.apache.maven.plugins:maven-install-plugin:3.0.0-M1:install-file -Dfile=lib/mundo-core-1.0-SNAPSHOT.jar \
  && mvn org.apache.maven.plugins:maven-install-plugin:3.0.0-M1:install-file -Dfile=lib/mundo-web-1.0-SNAPSHOT.jar \
  && mvn org.apache.maven.plugins:maven-install-plugin:3.0.0-M1:install-file -Dfile=lib/mundo-data-1.0-SNAPSHOT.jar \
  && mvn org.apache.maven.plugins:maven-install-plugin:3.0.0-M1:install-file -Dfile=lib/jsoup-1.12.1-maomao-forked.jar \
  && mvn package -Dmaven.test.skip=true

FROM openjdk:8-jdk-alpine
ENV APP_VERSION=1.0 \
    APP_ENV=snapshot \
    APP_LOG_DIR=/var/log/pokemon-wiki \
    APP_OPTS="-Dfile.encoding=UTF-8 -Dspring.profiles.active=${APP_ENV}" \
    JVM_OPTS="-server -Xms400m -Xmx400m -XX:+HeapDumpOnOutOfMemoryError -XX:+UseConcMarkSweepGC \
-verbose:gc -Xloggc:${APP_LOG_DIR}/gc_%p.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationStoppedTime -XX:+PrintAdaptiveSizePolicy \
-XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=30m"
WORKDIR /opt/app/
COPY --from=builder /opt/build/target/pokemon-wiki-${APP_VERSION}.jar .
VOLUME ${APP_LOG_DIR}
EXPOSE 8080
CMD java ${JVM_OPTS} ${APP_OPTS} -jar pokemon-wiki-${APP_VERSION}.jar