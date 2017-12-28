FROM 192.168.2.135:5000/ubuntu:latest
COPY ./target/jeedey-0.0.1-SNAPSHOT.jar /tmp
CMD ["/bin/bash", "/var/www/html/docker_web_run.sh"]