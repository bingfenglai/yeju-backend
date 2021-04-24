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

#### 单节点部署

yeju-all-provider-----20882

#### 日志服务提供方

yeju-log-service-provider ----> 20883

#### 对象存储服务提供方

yeju-oos-service-provider ----> 20884

#### 认证服务提供方

yeju-auth-service-provider ----> 20885

#### 平台服务提供方

yeju-platform-service-provider ----> 20886

#### 客户服务提供方

yeju-customer-service-provider ----> 20887

#### 产品服务提供方

yeju-product-service-provider ----> 20888

#### 消息服务提供方

yeju-message-service-provider -----20889

#### 定时任务

yeju-job-provider ------------------>20890

#### 支付服务提供方

yeju-payment-provider---------------20891

#### 交易服务提供方

yeju-trade-provider-----------------20892

### 服务消费方端口规划(http)

#### minio对象存储端口

yeju-oos ---- > 9000

#### 认证服务

yeju-auth-api ----> 9002

#### all-api

yeju-all-rest-api ----> 9010

#### 消息通知微服务

yeju-notice -----------> 9003

#### 产商品服务 web ------> 9005

#### 客户服务 web --------> 9006

#### 交易服务 web --------> 9007

#### 支付服务 web --------->9008

## 其他注意事项

1.部署时，即使是window系统，也需要关闭防火墙

2.关于请求参数与序列化

rest-api使用的序列化工具是jackson,其会将后端的驼峰命名法转前端的下划线分割 前端下划线分割转驼峰命名法，因此请求参数特别需要注意 如 noticeTitle 请求参数为 notice_title
前端接收到的noticeTitle为 notice_title

### 缓存规则

1.直接操作数据库的接口做缓存处理 调用其他服务的接口不做缓存处理

2.需要调用多个服务的，尽量在web层完成调用，方便更新缓存

3 消息中间件使用原则： 消息消费方为es或者是最终客户的消息 以json字符串发送 避免一些格式问题

