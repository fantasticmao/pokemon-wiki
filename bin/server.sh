#!/bin/bash

NAME=pokemon-wiki

LOG_HOME=/var/log/$NAME
TOMCAT_LOG=$LOG_HOME/tomcat.log
TOMCAT_PID=$LOG_HOME/tomcat.pid

JVM_OPTS="-Xms100m -Xmx200m -XX:+HeapDumpOnOutOfMemoryError -XX:+UseConcMarkSweepGC"
APP_OPTS="-Dfile.encoding=UTF-8 -Dspring.profiles.active=master"

MAOMAO_DEPLOY_HOME="/opt/maomao"

case "$1" in
    start)
        if [ -f $TOMCAT_PID ]; then
            echo "$TOMCAT_PID exist, $NAME started already?"
		    exit 2
        fi
        echo "Starting $NAME ..."
        test -f $TOMCAT_LOG && rm $TOMCAT_LOG

        # start up command
        $JAVA_HOME/bin/java $JVM_OPTS $APP_OPTS -jar $MAOMAO_DEPLOY_HOME/pokemon-wiki-1.0.jar >> $TOMCAT_LOG &

        echo $! > $TOMCAT_PID
        if [ -f $TOMCAT_PID ] && [ -n `cat $TOMCAT_PID` ]; then
            echo "$NAME started"
        else
            echo "$NAME start failed !!!"
        fi
        ;;
    stop)
        kill `cat $TOMCAT_PID`
        rm -f cat $TOMCAT_PID
        echo "$NAME was stopped"
        ;;
    kill)
        kill -9 `cat $TOMCAT_PID`
        rm -f cat $TOMCAT_PID
        echo "$NAME was killed"
        ;;
    *)
        echo "Usage: $0 {start|stop}"
        exit 3
        ;;
esac