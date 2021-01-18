#yeju

## yeju rest web api

http://127.0.0.1:9001/swagger-ui/index.html
-----------------------------------
椰居后端代码库



## 打包步骤

### 1. 打包基础依赖

```shell
mvn package
```

### 2. 打包软件包(不含lib)

此环境的软件包依赖于maven私有仓库当中的jar。此环境软件包不能直接跑在镜像中

```shell
mvn package -P prod
```

### 3. 打包容器化软件包（含lib）

此环境的软件包**不依赖**于maven私有仓库当中的jar。打成镜像时连同lib目录下的依赖一同打进镜像。

```shell
mvn package -P images-prod
```

2、3步骤不能颠倒。