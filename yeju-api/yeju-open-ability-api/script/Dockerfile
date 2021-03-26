FROM onedata-docker.pkg.coding.net/yeju/docker_repo/openjdk18-openshift
USER root
ADD ./dist/yeju-open-ability-api-1.0.0.jar /data/yeju-open-ability-api-1.0.0.jar
COPY ./bin/config/ /data/config
COPY ./lib/ /lib
RUN chmod +x /data/yeju-open-ability-api-1.0.0.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/data/yeju-open-ability-api-1.0.0.jar"]
EXPOSE 9010