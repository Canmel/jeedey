echo "welcome to docker_web_run"

source /etc/environment
cd /tmp/app
echo `pwd`
mvn spring-boot:run
/bin/bash