echo "welcome to docker_web_run"

source /etc/environment

java -jar /tmp/app/target/spring-application-webapp-boot.jar --spring.profiles.active=prod
/bin/bash