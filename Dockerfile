FROM 192.168.2.135:5000/ubuntu:latest
COPY ./ /tmp/app/
CMD ["/bin/bash", "/tmp/app/docker_web_run.sh"]