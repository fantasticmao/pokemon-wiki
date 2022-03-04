FROM openjdk:11
ENV APP_OPTS="-Dapp.dbfile=/app/pokemon_wiki.db" \
    JVM_OPTS="-XX:+UseG1GC -Xms500m -Xmx500m -XX:MaxGCPauseMillis=50 -XX:+HeapDumpOnOutOfMemoryError \
-Xlog:gc*:file=gc_%p.log::filecount=5,filesize=10M"
WORKDIR /app
COPY ./pokemon-wiki-web/target/pokemon-wiki-web.jar /app
COPY ./pokemon_wiki.db /app
EXPOSE 1234
CMD java ${JVM_OPTS} ${APP_OPTS} -jar pokemon-wiki-web.jar