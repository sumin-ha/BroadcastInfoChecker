#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

REPOSITORY=/home/ec2-user/app/step3
PROJECT_NAME=BroadcastInfoChecker

echo "> Build File Copy"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> new Application deploy"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name : $JAR_NAME"
echo "> Add execute permission for jar"

chmod +x $JAR_NAME

echo "> Run $JAR_NAME"

IDLE_PROFILE=$(find_idle_profile)
IDLE_PORT=$(find_idle_port)

echo "IDLE_PROFILE : $IDLE_PROFILE"
echo "IDLE_PORT : $IDLE_PORT"

echo "> $JAR_NAME App run with profile = $IDLE_PROFILE"

nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties,classpath:/application-$IDLE_PROFILE.properties \
    -Dspring.config.location=classpath:/application.properties,/home/ec2-user/app/application-account.properties, classpath:/application-$IDLE_PROFILE.properties \
    -Dspring.profiles.active=$IDLE_PROFILE \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &