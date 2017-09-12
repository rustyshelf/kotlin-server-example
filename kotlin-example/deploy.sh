#!/usr/bin/env bash
set -x
#Build project
./gradlew shadowJar

#Deploy jar
scp build/libs/kotlin-example-fat.jar root@50.116.29.20:~/example/service_new.jar

#Stop the service, move the new jar file into place then restart it
ssh root@50.116.29.20 '/root/example/finish_deployment.sh';

osascript -e 'display notification "Example project deployed!" with title "Service Deployed"'