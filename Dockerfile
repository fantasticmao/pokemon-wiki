FROM eclipse-temurin:21-alpine

ENV APP_WECHAT_TOKEN="I_Love_Pokemon"
ENV APP_DBFILE="/app/pokemon_wiki.db"
ENV JVM_OPTS="-XX:+UseG1GC -Xms500m -Xmx500m -XX:MaxGCPauseMillis=50 -XX:+HeapDumpOnOutOfMemoryError -Xlog:gc*:file=gc_%p.log::filecount=5,filesize=10M"

WORKDIR /app
COPY ./pokemon-wiki-web/target/pokemon-wiki-web.jar /app
COPY ./pokemon_wiki.db /app

EXPOSE 8080

CMD java -Dapp.wechat.token=${APP_WECHAT_TOKEN} -Dapp.dbfile=${APP_DBFILE} ${JVM_OPTS} -jar pokemon-wiki-web.jar
