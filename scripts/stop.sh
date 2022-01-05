#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

IDLE_PROFILE=$(find_idle_profile)
IDLE_PORT=$(find_idle_port)

echo "IDLE_PROFILE : $IDLE_PROFILE"
echo "IDLE_PORT : $IDLE_PORT"

echo "> $IDLE_PORT 에서 구동 중인 앱의 pid 확인"
IDLE_PID=$(lsof -ti tcp:${IDLE_PORT})

if [ -z "$IDLE_PID" ]; then
        echo "현재 구동중인 앱이 없으므로 종료하지 않습니다."
else
        echo "> kill -15 $IDLE_PID"
        kill -15 $IDLE_PID
        sleep 5
fi