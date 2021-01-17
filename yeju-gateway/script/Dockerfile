FROM registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift
USER root
ADD ./dist/yeju-gateway-1.0.0.jar /data/yeju-gateway-1.0.0.jar
COPY ./bin/config/ /data/config
COPY ./lib/ /lib
RUN chmod +x /data/yeju-gateway-1.0.0.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/data/yeju-gateway-1.0.0.jar"]
EXPOSE 81 81