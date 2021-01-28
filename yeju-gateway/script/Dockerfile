FROM onedata-docker.pkg.coding.net/yeju/docker_repo/openjdk18-openshift
USER root
ADD ./dist/*.jar /data/app.jar
COPY ./bin/config/ /data/config
COPY ./lib/ /lib
RUN chmod +x /data/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/data/app.jar"]
EXPOSE 81 81