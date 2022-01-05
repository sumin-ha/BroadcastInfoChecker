#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=BroadcastInfoChecker

echo "> Build File Copy"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> check now running application pid"

CURRENT_PID=$(pgrep -fl BroadcastInfoChecker | awk '{print $1}')

echo "now running application pid : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
        echo "현재 구동중인 앱이 없으므로 종료하지 않습니다."
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi

echo "> new Application deploy"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name : $JAR_NAME"
echo "> Add execute permission for jar"

chmod +x $JAR_NAME

echo "> Run $JAR_NAME"

nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,/home/ec2-user/app/application-account.properties \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &



-----------------------


REPOSITORY=/home/ec2-user/app/step1
PROJECT_NAME=sm

cd $REPOSITORY/$PROJECT_NAME

echo "> git pull"

git pull

echo "> build start"

./gradlew build

cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/

echo "> check now running application pid"

CURRENT_PID=$(pgrep -f ${PROJECT_NAME}.*.jar)

echo "now running application pid : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
        echo "현재 구동중인 앱이 없으므로 종료하지 않습니다."
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi

echo "> new Application deploy"

JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | tail -n 1)

echo "> JAR Name : $JAR_NAME"

echo "> Run $JAR_NAME"

nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,/home/ec2-user/app/application-account.properties \
    $REPOSITORY/$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
