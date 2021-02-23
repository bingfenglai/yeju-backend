# yeju

## yeju rest web api （gateway）

http://127.0.0.1:81/swagger-ui/index.html

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

## 服务端口规划(http、dubbo)

### 微服务关端口 (http)

yeju-gateway ---- > 81

### 服务提供方端口规划(dubbo)

#### 认证服务提供方

yeju-auth-service-provider ----> 20885

#### 客户服务提供方

yeju-customer-service-provider ----> 20887

#### 员工服务提供方

yeju-employee-service-provider ----> 20886

#### 日志服务提供方

yeju-log-service-provider ----> 20883

#### 对象存储服务提供方

yeju-oos-service-provider ----> 20884

#### 产品服务提供方

yeju-product-service-provider ----> 20888

#### 消息服务提供方

yeju-message-service-provider -----20889

#### 定时任务

yeju-job-provider ------------------>20890

### 服务消费方端口规划(http)

#### minio对象存储端口

yeju-oos ---- > 9000

#### 认证服务

yeju-auth-api ----> 9002

#### all-api

yeju-all-rest-api ----> 9001

#### 消息通知微服务

yeju-notice -----------> 9003

## 其他注意事项

部署时，即使是window系统，也需要关闭防火墙

