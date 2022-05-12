#!/bin/sh

SERVER_PORT=1234
LOG_HOME='/var/log/pokemon-wiki'
DEPLOY_HOME='/opt/maomao/pokemon-wiki'

APP_NAME=pokemon-wiki
APP_LOG=${LOG_HOME}/${APP_NAME}.log
APP_PID=${LOG_HOME}/${APP_NAME}.pid
APP_WECHAT_TOKEN=$2

if [ -z "${APP_WECHAT_TOKEN}" ]; then
  APP_WECHAT_TOKEN='I_Love_Pokemon'
fi

JVM_OPTS="-XX:+UseG1GC -Xms500m -Xmx500m -XX:MaxGCPauseMillis=50 -XX:+HeapDumpOnOutOfMemoryError"
JVM_OPTS="${JVM_OPTS} -Xlog:gc*:file=${LOG_HOME}/gc_%p.log::filecount=5,filesize=10M"
APP_OPTS="-Dserver.port=${SERVER_PORT} -Dapp.wechat.token=${APP_WECHAT_TOKEN} -Dapp.dbfile=${DEPLOY_HOME}/pokemon_wiki.db"

wait_started() {
  PID=$1
  while true; do
    if [ -d "/proc/${PID}" ]; then
      if [ '"UP"' = "$(curl -s "http://localhost:${SERVER_PORT}/actuator/health" | jq .status)" ]; then
        echo "${APP_NAME} start success !!! pid: ${PID}"
        break
      else
        echo "${APP_NAME} starting ......"
        sleep 1
      fi
    else
      echo "${APP_NAME} start failed !!!"
      break
    fi
  done
}

wait_stopped() {
  PID=$1
  while true; do
    if [ -d "/proc/${PID}" ]; then
      echo "${APP_NAME} stopping ......"
      sleep 1
    else
      echo "${APP_NAME} has been stopped"
      break
    fi
  done
}

case "$1" in
"start")
  if [ -f ${APP_PID} ]; then
    echo "${APP_PID} exist, ${APP_NAME} started already?"
    exit 2
  fi

  test -d ${LOG_HOME} || mkdir ${LOG_HOME}
  test -f ${APP_LOG} && rm ${APP_LOG}

  # start up
  nohup java ${JVM_OPTS} ${APP_OPTS} -jar ${DEPLOY_HOME}/pokemon-wiki-web.jar >>${APP_LOG} &

  PID=$!
  echo ${PID} >${APP_PID}
  wait_started ${PID}
  ;;
"status")
  if [ -f ${APP_PID} ]; then
    echo "${APP_NAME} is running, pid: $(cat ${APP_PID})"
  else
    echo "${APP_NAME} is stopped"
  fi
  ;;
"stop" | "kill")
  if ! [ -f ${APP_PID} ]; then
    echo "${APP_PID} not exist, ${APP_NAME} stopped already?"
    exit 2
  fi

  PID=$(cat ${APP_PID})
  if [ "kill" = "$1" ]; then
    kill -9 "${PID}"
  else
    kill "${PID}"
  fi
  rm -f "${APP_PID}"
  wait_stopped "${PID}"
  ;;
*)
  echo "Usage: $0 start|status|stop|kill"
  exit 3
  ;;
esac
