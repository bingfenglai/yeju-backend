docker build -t onedata-docker.pkg.coding.net/yeju/docker_repo/yeju-message-rest -api:1.0.0 -f Dockerfile .
docker images
docker login -u docker_repo-1610931949880 -p 41b18693d8d34c75dcdd5267a488504c3bc638e9 onedata-docker.pkg.coding.net
docker push onedata-docker.pkg.coding.net/yeju/docker_repo/yeju-message-rest -api:1.0.0