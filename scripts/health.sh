#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh
source ${ABSDIR}/switch.sh

IDLE_PROFILE=$(find_idle_profile)
IDLE_PORT=$(find_idle_port)

echo "IDLE_PROFILE : $IDLE_PROFILE"
echo "IDLE_PORT : $IDLE_PORT"

echo "> Health Check Start!"
echo "> IDLE_PORT: $IDLE_PORT"
echo "> curl -s http://localhost:$IDLE_PORT/profile"
sleep 10

for RETRY_COUNT in {1..10}
do
  RESPONSE=$(curl -s http://localhost:${IDLE_PORT}/profile)
  UP_COUNT=$(echo ${RESPONSE} | grep 'real' | wc -l)

  echo "RESPONSE $RESPONSE"

  echo "UP_COUNT $UP_COUNT"

  if [ ${UP_COUNT} -ge 1 ]
  then # real문자열이 있는지 검증
      echo "> Health Check Success!"
      switch_proxy
      break
  else
      echo "> Health Check Failed!"
      echo "> Health Check : ${RESPONSE}"
  fi

  if [ ${RETRY_COUNT} -eq 10 ]
  then
      echo "> Health Check Failed!"
      echo "> Not Connecting Nginx, Deploy End"
      exit 1
  fi

  echo "> Health Check Failed, Retry..."
  sleep 10
done