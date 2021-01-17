docker login -u docker_repo-1610884823374 -p 3aac8daf7ee87c6d6fd2ee024da2bdcc74793333 onedata-docker.pkg.coding.net
docker build -t yeju/docker_repo/yeju-gateway:1.0.1 -f Dockerfile .
docker push yeju/docker_repo/yeju-gateway:1.0.1