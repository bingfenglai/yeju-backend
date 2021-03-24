FROM onedata-docker.pkg.coding.net/yeju/docker_repo/openjdk18-openshift
USER root
ADD ./dist/yeju-auth-rest-api-1.0.0.jar /data/yeju-auth-rest-api-1.0.0.jar
COPY ./bin/config/ /data/config
COPY ./bin/privateKey.txt /
COPY ./bin/publicKey.txt /
COPY ./lib/ /lib
RUN chmod +x /data/yeju-auth-rest-api-1.0.0.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/data/yeju-auth-rest-api-1.0.0.jar"]
EXPOSE 9002