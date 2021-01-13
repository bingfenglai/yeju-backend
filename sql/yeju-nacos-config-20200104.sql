/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.21 : Database - nacos_config
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`nacos_config` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `nacos_config`;

/*Table structure for table `config_info` */

DROP TABLE IF EXISTS `config_info`;

CREATE TABLE `config_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1468 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';

/*Data for the table `config_info` */

insert  into `config_info`(`id`,`data_id`,`group_id`,`content`,`md5`,`gmt_create`,`gmt_modified`,`src_user`,`src_ip`,`app_name`,`tenant_id`,`c_desc`,`c_use`,`effect`,`type`,`c_schema`) values (20,'white-config-dev.properties','DEFAULT_GROUP','# ignore.whites= /auth/logout, /auth/login, /*/v2/api-docs, /csrf, /auth/refeshToken, /auth/code/image, /auth/phone/, /auth/phone/**\r\nignore.whites[0]=/auth/**\r\nignore.whites[1]=/authserver/**','6f5701b3ea1bdc43259b06d6b76d5e68','2020-11-26 08:22:00','2021-01-01 14:40:03',NULL,'223.198.49.70','','ed63a92c-9b8c-4169-9a60-bc19059ae061','','','','properties',''),(25,'jackjson-default-config.yml','DEFAULT_GROUP','spring:\r\n  jackson:\r\n    # 设置属性命名策略,对应jackson下PropertyNamingStrategy中的常量值，SNAKE_CASE-返回的json驼峰式转下划线，json body下划线传到后端自动转驼峰式\r\n    property-naming-strategy: SNAKE_CASE\r\n    # 全局设置@JsonFormat的格式pattern\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    # 当地时区\r\n    locale: zh\r\n    # 设置全局时区\r\n    time-zone: GMT+8\r\n    # 常用，全局设置pojo或被@JsonInclude注解的属性的序列化方式\r\n    default-property-inclusion: NON_NULL #不为空的属性才会序列化,具体属性可看JsonInclude.Include\r\n    # 常规默认,枚举类SerializationFeature中的枚举属性为key，值为boolean设置jackson序列化特性,具体key请看SerializationFeature源码\r\n    serialization:\r\n      WRITE_DATES_AS_TIMESTAMPS: true # 返回的java.util.date转换成timestamp\r\n      FAIL_ON_EMPTY_BEANS: true # 对象为空时是否报错，默认true\r\n    # 枚举类DeserializationFeature中的枚举属性为key，值为boolean设置jackson反序列化特性,具体key请看DeserializationFeature源码\r\n    deserialization:\r\n      # 常用,json中含pojo不存在属性时是否失败报错,默认true\r\n      FAIL_ON_UNKNOWN_PROPERTIES: false\r\n    # 枚举类MapperFeature中的枚举属性为key，值为boolean设置jackson ObjectMapper特性\r\n    # ObjectMapper在jackson中负责json的读写、json与pojo的互转、json tree的互转,具体特性请看MapperFeature,常规默认即可\r\n    mapper:\r\n      # 使用getter取代setter探测属性，如类中含getName()但不包含name属性与setName()，传输的vo json格式模板中依旧含name属性\r\n      USE_GETTERS_AS_SETTERS: true #默认false\r\n    # 枚举类JsonParser.Feature枚举类中的枚举属性为key，值为boolean设置jackson JsonParser特性\r\n    # JsonParser在jackson中负责json内容的读取,具体特性请看JsonParser.Feature，一般无需设置默认即可\r\n    parser:\r\n      ALLOW_SINGLE_QUOTES: true # 是否允许出现单引号,默认false\r\n    # 枚举类JsonGenerator.Feature枚举类中的枚举属性为key，值为boolean设置jackson JsonGenerator特性，一般无需设置默认即可\r\n    # JsonGenerator在jackson中负责编写json内容,具体特性请看JsonGenerator.Feature\r\n','01b9f67b00879b2f44aebf7382b0ecee','2020-11-27 01:25:22','2020-11-27 01:25:22',NULL,'127.0.0.1','','ed63a92c-9b8c-4169-9a60-bc19059ae061',NULL,NULL,NULL,'yaml',NULL),(26,'mybatis-plus-config.properties','DEFAULT_GROUP','#mybatis plus 设置\r\nmybatis-plus.mapper-locations=classpath:/mapper/*Mapper.xml\r\n#实体扫描，多个package用逗号或者分号分隔\r\nmybatis-plus.typeAliasesPackage=pers.lbf.yeju.common.domain.entity\r\n#主键类型  0:\"数据库ID自增\", 1:\"用户输入ID\",2:\"全局唯一ID (数字类型唯一ID)\", 3:\"全局唯一ID UUID\";\r\nmybatis-plus.global-config.id-type=2\r\n#字段策略 0:\"忽略判断\",1:\"非 NULL 判断\"),2:\"非空判断\"\r\nmybatis-plus.global-config.field-strategy=2\r\n#驼峰下划线转换\r\nmybatis-plus.global-config.db-column-underline=true\r\n#刷新mapper 调试神器\r\nmybatis-plus.global-config.refresh-mapper=true\r\n#数据库大写下划线转换\r\n#mybatis-plus.global-config.capital-mode=true\r\n#序列接口实现类配置\r\n#mybatis-plus.global-config.key-generator=com.baomidou.springboot.xxx\r\n#逻辑删除配置\r\nmybatis-plus.global-config.logic-delete-value=1\r\nmybatis-plus.global-config.logic-not-delete-value=0\r\n#自定义填充策略接口实现\r\n#mybatis-plus.global-config.meta-object-handler=com.baomidou.springboot.xxx\r\n#自定义SQL注入器\r\n#mybatis-plus.global-config.sql-injector=com.baomidou.springboot.xxx\r\nmybatis-plus.configuration.map-underscore-to-camel-case=true\r\nmybatis-plus.configuration.cache-enabled=false','eeae79fcf3ddbe6f7f7a81f114d69b3e','2020-11-29 13:04:22','2020-12-13 02:40:37',NULL,'127.0.0.1','','ed63a92c-9b8c-4169-9a60-bc19059ae061','','','','properties',''),(27,'log-config.xml','DEFAULT_GROUP','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<configuration>\r\n    <!-- %m输出的信息,%p日志级别,%t线程名,%d日期,%c类的全名,%i索引【从数字0开始递增】,,, -->\r\n    <!-- appender是configuration的子节点，是负责写日志的组件。 -->\r\n    <!-- ConsoleAppender：把日志输出到控制台 -->\r\n    <appender name=\"STDOUT\" class=\"ch.qos.logback.core.ConsoleAppender\">\r\n        <encoder>\r\n            <pattern>%d %p (%file:%line\\)- %m%n</pattern>\r\n            <!-- 控制台也要使用UTF-8，不要使用GBK，否则会中文乱码 -->\r\n            <charset>UTF-8</charset>\r\n        </encoder>\r\n    </appender>\r\n    <!-- RollingFileAppender：滚动记录文件，先将日志记录到指定文件，当符合某个条件时，将日志记录到其他文件 -->\r\n    <!-- 以下的大概意思是：1.先按日期存日志，日期变了，将前一天的日志文件名重命名为XXX%日期%索引，新的日志仍然是demo.log -->\r\n    <!--             2.如果日期没有发生变化，但是当前日志的文件大小超过1KB时，对当前日志进行分割 重命名-->\r\n    <appender name=\"demolog\" class=\"ch.qos.logback.core.rolling.RollingFileAppender\">\r\n        <File>./log/yeju.log</File>\r\n        <!-- rollingPolicy:当发生滚动时，决定 RollingFileAppender 的行为，涉及文件移动和重命名。 -->\r\n        <!-- TimeBasedRollingPolicy： 最常用的滚动策略，它根据时间来制定滚动策略，既负责滚动也负责出发滚动 -->\r\n        <rollingPolicy class=\"ch.qos.logback.core.rolling.TimeBasedRollingPolicy\">\r\n            <!-- 活动文件的名字会根据fileNamePattern的值，每隔一段时间改变一次 -->\r\n            <!-- 文件名：log/demo.2017-12-05.0.log -->\r\n            <fileNamePattern>./yeju.%d.%i.log</fileNamePattern>\r\n            <!-- 每产生一个日志文件，该日志文件的保存期限为30天 -->\r\n            <maxHistory>30</maxHistory>\r\n            <timeBasedFileNamingAndTriggeringPolicy  class=\"ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP\">\r\n                <!-- maxFileSize:这是活动文件的大小，默认值是10MB，测试时可改成1KB看效果 -->\r\n                <maxFileSize>2MB</maxFileSize>\r\n            </timeBasedFileNamingAndTriggeringPolicy>\r\n        </rollingPolicy>\r\n        <encoder>\r\n            <!-- pattern节点，用来设置日志的输入格式 -->\r\n            <pattern>\r\n                %d %p (%file:%line\\)- %m%n\r\n            </pattern>\r\n            <!-- 记录日志的编码:此处设置字符集 - -->\r\n            <charset>UTF-8</charset>\r\n        </encoder>\r\n    </appender>\r\n    <!-- 控制台输出日志级别 -->\r\n    <root level=\"debug\">\r\n        <appender-ref ref=\"STDOUT\" />\r\n    </root>\r\n    <!-- 指定项目中某个包，当有日志操作行为时的日志记录级别 -->\r\n    <!-- pers.lbf为根包，也就是只要是发生在这个根包下面的所有日志操作行为的权限都是DEBUG -->\r\n    <!-- 级别依次为【从高到低】：FATAL > ERROR > WARN > INFO > DEBUG > TRACE  -->\r\n    <logger name=\"pers.lbf\" level=\"DEBUG\">\r\n        <appender-ref ref=\"demolog\" />\r\n    </logger>\r\n</configuration>\r\n','7527d3bf58b1d28eebd218da0785e244','2020-11-29 13:06:37','2020-11-29 13:06:37',NULL,'127.0.0.1','','ed63a92c-9b8c-4169-9a60-bc19059ae061',NULL,NULL,NULL,'xml',NULL),(28,'mysql-datasource-dev.properties','DEFAULT_GROUP','spring.datasource.driver-class-name=com.alibaba.druid.proxy.DruidDriver\r\nspring.datasource.password=yeju@20200306\r\nspring.datasource.username=yeju\r\nspring.datasource.url=jdbc:mysql://8.129.77.225:3388/yeju_core?userSSL=false&serverTimezone=GMT%2B8\r\nspring.datasource.druid.db-type=com.alibaba.druid.pool.DruidDataSource\r\nspring.datasource.druid.driver-class-name=com.mysql.cj.jdbc.Driver\r\nspring.datasource.druid.initial-size=1\r\nspring.datasource.druid.min-idle=1\r\nspring.datasource.druid.max-active=10\r\nspring.datasource.druid.max-wait=3000\r\nspring.datasource.druid.validation-query= select now()\r\nspring.datasource.druid.filter.wall.enabled=true\r\nspring.datasource.druid.filter.wall.db-type=mysql\r\nspring.datasource.druid.filter.wall.config.delete-allow=false\r\nspring.datasource.druid.filter.wall.config.drop-table-allow=false\r\nspring.datasource.druid.filter.wall.config.multi-statement-allow=true\r\nspring.datasource.druid.filter.stat.slow-sql-millis=3000\r\nspring.datasource.druid.filter.stat.enabled=true\r\nspring.datasource.druid.filter.stat.db-type=mysql\r\nspring.datasource.druid.filter.stat.log-slow-sql=true\r\nspring.datasource.druid.filter.stat.merge-sql=true\r\nspring.datasource.druid.filter.stat.connection-stack-trace-enable=true','3ff53c00d3af02b18e012c5728ff0bae','2020-11-29 13:32:57','2021-01-02 16:06:53',NULL,'112.67.90.162','','ed63a92c-9b8c-4169-9a60-bc19059ae061','','','','properties',''),(34,'log4j.properties','DEFAULT_GROUP','log4j.rootLogger=DEBUG,console,dailyFile,rollingFile,logFile\r\nlog4j.additivity.org.apache=true\r\n# 控制台(console)\r\nlog4j.appender.console=org.apache.log4j.ConsoleAppender\r\nlog4j.appender.console.Threshold=info\r\nlog4j.appender.console.ImmediateFlush=true\r\nlog4j.appender.console.Target=System.err\r\nlog4j.appender.console.layout=org.apache.log4j.PatternLayout\r\nlog4j.appender.console.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} [%p] %m%n\r\n# 日志文件(logFile)\r\nlog4j.appender.logFile=org.apache.log4j.FileAppender\r\nlog4j.appender.logFile.Threshold=info\r\nlog4j.appender.logFile.ImmediateFlush=true\r\nlog4j.appender.logFile.Append=true\r\nlog4j.appender.logFile.File=./logs/yeju.log\r\nlog4j.appender.logFile.layout=org.apache.log4j.PatternLayout\r\nlog4j.appender.logFile.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} [%p] %m%n\r\n# 滚动文件(rollingFile)\r\nlog4j.appender.rollingFile=org.apache.log4j.RollingFileAppender\r\nlog4j.appender.rollingFile.Threshold=info\r\nlog4j.appender.rollingFile.ImmediateFlush=true\r\nlog4j.appender.rollingFile.Append=true\r\nlog4j.appender.rollingFile.File=./logs/yeju.log\r\nlog4j.appender.rollingFile.MaxFileSize=1024KB\r\nlog4j.appender.rollingFile.MaxBackupIndex=50\r\nlog4j.appender.rollingFile.layout=org.apache.log4j.PatternLayout\r\nlog4j.appender.rollingFile.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} [%p] %m%n\r\n# 定期滚动日志文件(dailyFile)\r\nlog4j.appender.dailyFile=org.apache.log4j.DailyRollingFileAppender\r\nlog4j.appender.dailyFile.Threshold=info\r\nlog4j.appender.dailyFile.ImmediateFlush=true\r\nlog4j.appender.dailyFile.Append=true\r\nlog4j.appender.dailyFile.File=./logs/yeju.log\r\nlog4j.appender.dailyFile.DatePattern=\'.\'yyyy-MM-dd\r\nlog4j.appender.dailyFile.layout=org.apache.log4j.PatternLayout\r\nlog4j.appender.dailyFile.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} [%p] %m%n','a646984ad095c1e8d4b1ca6b0e72fae7','2020-11-29 13:48:30','2020-11-29 13:50:16',NULL,'127.0.0.1','','ed63a92c-9b8c-4169-9a60-bc19059ae061','','','','properties',''),(77,'jackson-config.properties','DEFAULT_GROUP','# 设置属性命名策略,对应jackson下PropertyNamingStrategy中的常量值，SNAKE_CASE-返回的json驼峰式转下划线，json body下划线传到后端自动转驼峰式\r\nspring.jackson.property-naming-strategy=SNAKE_CASE\r\n# 全局设置@JsonFormat的格式pattern\r\nspring.jackson.date-format=yyyy-MM-dd HH:mm:ss\r\n# 当地时区\r\nspring.jackson.locale=zh\r\n# 设置全局时区\r\nspring.jackson.time-zone=GMT+8\r\n#不为空的属性才会序列化,具体属性可看JsonInclude.Include\r\nspring.jackson.default-property-inclusion=non_null\r\n# 返回的java.util.date转换成timestamp\r\nspring.jackson.serialization.write-dates-as-timestamps=false\r\n# 对象为空时是否报错，默认true\r\nspring.jackson.serialization..fail-on-empty-beans=true\r\n# 常用,json中含pojo不存在属性时是否失败报错,默认true\r\nspring.jackson.deserialization.fail-on-unknown-properties=false\r\n# 使用getter取代setter探测属性，如类中含getName()但不包含name属性与setName()，传输的vo json格式模板中依旧含name属性\r\nspring.jackson.mapper.use-getters-as-setters=true\r\n# 是否允许出现单引号,默认false\r\nspring.jackson.parser.allow-single-quotes=true\r\n','0eed057762a0be591f36ac80bf887de5','2020-11-30 03:23:59','2020-11-30 03:23:59',NULL,'127.0.0.1','','ed63a92c-9b8c-4169-9a60-bc19059ae061',NULL,NULL,NULL,'properties',NULL),(175,'redis-config-dev.properties','DEFAULT_GROUP','spring.redis.host=8.129.77.225\r\nspring.redis.port=3307\r\nspring.redis.password=hewenping\r\nspring.redis.timeout=3000\r\nspring.redis.database=1\r\nspring.redis.jedis.pool.max-active=8\r\nspring.redis.jedis.pool.max-idle=8\r\nspring.redis.jedis.pool.min-idle=3\r\n','fa1c9a8b459014d68a680538a8276d60','2020-12-06 02:57:42','2020-12-24 05:12:17',NULL,'112.67.93.206','','ed63a92c-9b8c-4169-9a60-bc19059ae061','','','','properties',''),(196,'rsa-privateKey.properties','DEFAULT_GROUP','rsa.private.path=privateKey.txt\r\nrsa.private.value=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlHC4JKC6yYVibmM3iek5KWNSS9m1YogVaFDhQBhKcGHfw4EbjxU9zs4r1VFxxvu094bdiqbrKAzT0LiXnY1H9H+g1oKIxUmKzX2rUdW1EiHixN6a/fKQMPVZr0N4iI8hvWbJaQsgqD3UEaxkRh3GnbWMJ8sGp4a5xJarFx9a9+IJxcWLi2K79hh/DYLBhJE+gQpLB7b6tCmzDMlkamPafkf2G+CzC46RdBpcEecCUhipMbuNSpkDKFP/6elVRgeajsZ6CCSGthHaLpV5fImfh3Kkudn01Or5vBfQy7HmUK+9BPWdAxkIOnhCulQBqW4HKLGu2ZR0EbuaJqjEfyavcQIDAQAB','32f8f2a7ffd9387fd077492758c90942','2020-12-13 16:20:03','2020-12-24 05:45:12',NULL,'112.67.93.206','','ed63a92c-9b8c-4169-9a60-bc19059ae061','','','','properties',''),(198,'rsa-publicKey.properties','DEFAULT_GROUP','rsa.public.key.path=publicKey.txt\r\nrsa.public.key.value=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsCiEyh+Nkl00/tSiVtHQ0g2D/iGj9vMthBNnBMt81dzetNHAL5r6sgAlOBgb9zdzV98aDB+OCTLQVqO4SaFpIPX2fO6tKPDMVcdxxX6nnzVn9xI24CIBZti7nt7ywY+4dE1g/rEtWl/Fra/5oAWBGNC0WLVXWlclcAgwr3/XZRC5mUhej9e92WeNCpoicI1rRTUom+AtjBzWXD+K7g0iZVyT4vTdaB1VUGK7fV3Gn4ccS2fl6+n6GnPKbWxDs1pxBRIcpN6gwtdGdZclWCxNSDBvrXPkFbDzzRQTKZgwWGqfHfj43MO+jv7lvLxduIZnR3xEAwaa/VHE6sUu7X3aiQIDAQAB\r\n','7d7ced125a75dfc9c3be8a3a5a884557','2020-12-13 16:24:27','2020-12-24 05:52:03',NULL,'112.67.93.206','','ed63a92c-9b8c-4169-9a60-bc19059ae061','','','','properties',''),(814,'verification-code-config.properties','DEFAULT_GROUP','# 是否开启验证码验证\r\nverification.code.enable=false\r\n# 验证码key前缀\r\nverification.code.prefix=captcha:key:\r\nverification.code.width=140\r\nverification.code.height=60\r\nverification.code.length=6\r\n# 验证码超时时间 时间单位 分钟\r\nverification.code.timeout=3','2dd6111937a17ac6f07d052230ad53aa','2020-12-24 06:00:39','2020-12-29 02:36:57',NULL,'112.67.88.99','','ed63a92c-9b8c-4169-9a60-bc19059ae061','','','','properties',''),(1077,'gateway-routes-config.properties','DEFAULT_GROUP','# 认证服务\r\nspring.cloud.gateway.routes.id[0]=authserver\r\nspring.cloud.gateway.routes.uri[0]=lb://authserver\r\nspring.cloud.gateway.routes.predicates[0].Path[0]=/authserver/**','034f43fe0be4661d6f2c8a65131c7a6c','2020-12-30 08:44:23','2020-12-30 08:44:58',NULL,'121.58.66.66','','ed63a92c-9b8c-4169-9a60-bc19059ae061','','','','properties',''),(1092,'pers.lbf.yeju.authserver.interfaces.interfaces.IAccountService:::consumer:gateway-service','dubbo','{\"init\":\"false\",\"side\":\"consumer\",\"release\":\"2.7.8\",\"methods\":\"updatePassword,findSimpleAccountByPrincipal\",\"dubbo\":\"2.0.2\",\"check\":\"false\",\"interface\":\"pers.lbf.yeju.authserver.interfaces.interfaces.IAccountService\",\"qos.enable\":\"false\",\"revision\":\"1.0.0\",\"metadata-type\":\"remote\",\"application\":\"gateway-service\",\"sticky\":\"false\"}','b10ee2b812ea2fe1f57a23b005ccbdcd','2020-12-30 09:56:07','2020-12-30 09:59:50',NULL,'121.58.66.66','','',NULL,NULL,NULL,NULL,NULL),(1093,'pers.lbf.yeju.authserver.interfaces.interfaces.IVerificationCodeService:::consumer:gateway-service','dubbo','{\"init\":\"false\",\"side\":\"consumer\",\"release\":\"2.7.8\",\"methods\":\"getVerificationCode,verify\",\"dubbo\":\"2.0.2\",\"check\":\"false\",\"interface\":\"pers.lbf.yeju.authserver.interfaces.interfaces.IVerificationCodeService\",\"qos.enable\":\"false\",\"revision\":\"1.0.0\",\"metadata-type\":\"remote\",\"application\":\"gateway-service\",\"sticky\":\"false\"}','aca80653bbf43ff240ec5f07c5b3c243','2020-12-30 09:56:07','2020-12-30 09:59:50',NULL,'121.58.66.66','','',NULL,NULL,NULL,NULL,NULL),(1096,'IAuthService:::provider:authServer','dubbo','{\"parameters\":{\"side\":\"provider\",\"release\":\"2.7.8\",\"methods\":\"getVerificationCode,logout,login\",\"deprecated\":\"false\",\"dubbo\":\"2.0.2\",\"interface\":\"IAuthService\",\"qos.enable\":\"false\",\"generic\":\"false\",\"default\":\"true\",\"metadata-type\":\"remote\",\"application\":\"authServer\",\"dynamic\":\"true\",\"anyhost\":\"true\"},\"canonicalName\":\"IAuthService\",\"codeSource\":\"file:/G:/graduation%20project/yeju_code/yeju_dev/yeju-dubbo-service-interfaces/yeju-auth-service-interfaces/target/classes/\",\"methods\":[{\"name\":\"login\",\"parameterTypes\":[\"java.lang.String\"],\"returnType\":\"pers.lbf.yeju.common.core.result.IResult\"},{\"name\":\"logout\",\"parameterTypes\":[\"java.lang.String\"],\"returnType\":\"pers.lbf.yeju.common.core.result.IResult\"},{\"name\":\"getVerificationCode\",\"parameterTypes\":[],\"returnType\":\"pers.lbf.yeju.common.core.result.IResult\"}],\"types\":[{\"type\":\"pers.lbf.yeju.common.core.result.IResult\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"char\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"int\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"}]}','0485a09024eedd7431305dddd89309c3','2020-12-30 16:09:11','2021-01-03 15:08:32',NULL,'112.67.90.162','','',NULL,NULL,NULL,NULL,NULL),(1097,'IAccountResourcesService:::provider:authServer','dubbo','{\"parameters\":{\"side\":\"provider\",\"release\":\"2.7.8\",\"methods\":\"*\",\"deprecated\":\"false\",\"dubbo\":\"2.0.2\",\"interface\":\"IAccountResourcesService\",\"qos.enable\":\"false\",\"generic\":\"false\",\"default\":\"true\",\"metadata-type\":\"remote\",\"application\":\"authServer\",\"dynamic\":\"true\",\"anyhost\":\"true\"},\"canonicalName\":\"IAccountResourcesService\",\"codeSource\":\"file:/G:/graduation%20project/yeju_code/yeju_dev/yeju-dubbo-service-interfaces/yeju-auth-service-interfaces/target/classes/\",\"types\":[]}','121a238c6c49fc5bc0ae585727170cd9','2020-12-30 16:09:12','2021-01-03 15:08:32',NULL,'112.67.90.162','','',NULL,NULL,NULL,NULL,NULL),(1098,'IAccountService:::provider:authServer','dubbo','{\"parameters\":{\"side\":\"provider\",\"release\":\"2.7.8\",\"methods\":\"updatePassword,findSimpleAccountByPrincipal\",\"deprecated\":\"false\",\"dubbo\":\"2.0.2\",\"interface\":\"IAccountService\",\"qos.enable\":\"false\",\"generic\":\"false\",\"default\":\"true\",\"metadata-type\":\"remote\",\"application\":\"authServer\",\"dynamic\":\"true\",\"anyhost\":\"true\"},\"canonicalName\":\"IAccountService\",\"codeSource\":\"file:/G:/graduation%20project/yeju_code/yeju_dev/yeju-dubbo-service-interfaces/yeju-auth-service-interfaces/target/classes/\",\"methods\":[{\"name\":\"updatePassword\",\"parameterTypes\":[\"java.lang.String\",\"java.lang.String\"],\"returnType\":\"pers.lbf.yeju.common.core.result.IResult\"},{\"name\":\"findSimpleAccountByPrincipal\",\"parameterTypes\":[\"java.lang.String\"],\"returnType\":\"pers.lbf.yeju.common.core.result.IResult\"}],\"types\":[{\"type\":\"pers.lbf.yeju.common.core.result.IResult\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"char\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"int\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"}]}','d9b5eef19a8f9c0258f9fd818677236f','2020-12-30 16:09:12','2021-01-03 15:08:33',NULL,'112.67.90.162','','',NULL,NULL,NULL,NULL,NULL),(1099,'IResourcesService:::provider:authServer','dubbo','{\"parameters\":{\"side\":\"provider\",\"release\":\"2.7.8\",\"methods\":\"findAuthorityListByPrincipal\",\"deprecated\":\"false\",\"dubbo\":\"2.0.2\",\"interface\":\"IResourcesService\",\"qos.enable\":\"false\",\"generic\":\"false\",\"default\":\"true\",\"metadata-type\":\"remote\",\"application\":\"authServer\",\"dynamic\":\"true\",\"anyhost\":\"true\"},\"canonicalName\":\"IResourcesService\",\"codeSource\":\"file:/G:/graduation%20project/yeju_code/yeju_dev/yeju-dubbo-service-interfaces/yeju-auth-service-interfaces/target/classes/\",\"methods\":[{\"name\":\"findAuthorityListByPrincipal\",\"parameterTypes\":[\"java.lang.String\"],\"returnType\":\"pers.lbf.yeju.common.core.result.IResult\"}],\"types\":[{\"type\":\"pers.lbf.yeju.common.core.result.IResult\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"char\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"int\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"}]}','2ddb003c6ffd55e864c820b05ef40834','2020-12-30 16:09:12','2021-01-03 15:08:33',NULL,'112.67.90.162','','',NULL,NULL,NULL,NULL,NULL),(1100,'IVerificationCodeService:::provider:authServer','dubbo','{\"parameters\":{\"side\":\"provider\",\"release\":\"2.7.8\",\"methods\":\"getVerificationCode,verify\",\"deprecated\":\"false\",\"dubbo\":\"2.0.2\",\"interface\":\"IVerificationCodeService\",\"qos.enable\":\"false\",\"generic\":\"false\",\"default\":\"true\",\"metadata-type\":\"remote\",\"application\":\"authServer\",\"dynamic\":\"true\",\"anyhost\":\"true\"},\"canonicalName\":\"IVerificationCodeService\",\"codeSource\":\"file:/G:/graduation%20project/yeju_code/yeju_dev/yeju-dubbo-service-interfaces/yeju-auth-service-interfaces/target/classes/\",\"methods\":[{\"name\":\"verify\",\"parameterTypes\":[\"java.lang.String\",\"java.lang.String\"],\"returnType\":\"pers.lbf.yeju.common.core.result.IResult\"},{\"name\":\"getVerificationCode\",\"parameterTypes\":[\"VerificationCodeTypeEnum\"],\"returnType\":\"pers.lbf.yeju.common.core.result.IResult\"}],\"types\":[{\"type\":\"pers.lbf.yeju.common.core.result.IResult\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"char\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"int\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"VerificationCodeTypeEnum\",\"enum\":[\"MOBILE_VERIFICATION_CODE\",\"PICTURE_VERIFICATION_CODE\"],\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.EnumTypeBuilder\"}]}','622fec81c3b436d5edd1b605c7bf7df4','2020-12-30 16:09:12','2021-01-03 15:08:33',NULL,'112.67.90.162','','',NULL,NULL,NULL,NULL,NULL),(1101,'IAccountService:::consumer:gateway-service','dubbo','{\"init\":\"false\",\"side\":\"consumer\",\"metadata-type\":\"remote\",\"application\":\"gateway-service\",\"release\":\"2.7.8\",\"methods\":\"updatePassword,findSimpleAccountByPrincipal\",\"sticky\":\"false\",\"dubbo\":\"2.0.2\",\"check\":\"false\",\"interface\":\"IAccountService\",\"qos.enable\":\"false\"}','b716360a9d08af817369824153acc16d','2020-12-30 16:22:24','2021-01-03 15:54:54',NULL,'112.67.90.162','','',NULL,NULL,NULL,NULL,NULL),(1102,'IVerificationCodeService:::consumer:gateway-service','dubbo','{\"init\":\"false\",\"side\":\"consumer\",\"metadata-type\":\"remote\",\"application\":\"gateway-service\",\"release\":\"2.7.8\",\"methods\":\"getVerificationCode,verify\",\"sticky\":\"false\",\"dubbo\":\"2.0.2\",\"check\":\"false\",\"interface\":\"IVerificationCodeService\",\"qos.enable\":\"false\"}','2e232099885bef2392f7771517ab0473','2020-12-30 16:22:24','2021-01-03 15:54:55',NULL,'112.67.90.162','','',NULL,NULL,NULL,NULL,NULL),(1108,'IAuthService:::consumer:authserver','dubbo','{\"init\":\"false\",\"side\":\"consumer\",\"metadata-type\":\"remote\",\"application\":\"authserver\",\"release\":\"2.7.8\",\"methods\":\"getVerificationCode,logout,login\",\"sticky\":\"false\",\"dubbo\":\"2.0.2\",\"check\":\"false\",\"interface\":\"IAuthService\",\"qos.enable\":\"false\"}','e05fd04811ff1aab4ee2cc3a1d0998e8','2020-12-30 16:42:42','2021-01-01 14:42:24',NULL,'223.198.49.70','','',NULL,NULL,NULL,NULL,NULL),(1113,'redis-server','mapping-pers.lbf.yeju.redisserver.service.interfaces.IRedisService','1609690050950','ea8f94cea0b4d5597d59cdb09502c548','2020-12-30 17:23:50','2021-01-03 16:07:32',NULL,'112.67.90.162','','',NULL,NULL,NULL,NULL,NULL),(1114,'pers.lbf.yeju.redisserver.service.interfaces.IRedisService:::provider:redis-server','dubbo','{\"parameters\":{\"side\":\"provider\",\"release\":\"2.7.8\",\"methods\":\"getCacheMapValue,getCacheList,keys,addCacheMapValue,getCacheObject,addCacheMap,getCacheMap,getCacheSet,addCacheObject,expire,deleteObject,addCacheSet,addCacheList,getMultiCacheMapValue\",\"deprecated\":\"false\",\"dubbo\":\"2.0.2\",\"interface\":\"pers.lbf.yeju.redisserver.service.interfaces.IRedisService\",\"qos.enable\":\"false\",\"generic\":\"false\",\"revision\":\"1.0.0\",\"default\":\"true\",\"metadata-type\":\"remote\",\"application\":\"redis-server\",\"dynamic\":\"true\",\"anyhost\":\"true\"},\"canonicalName\":\"pers.lbf.yeju.redisserver.service.interfaces.IRedisService\",\"codeSource\":\"yeju-redis-service-interfaces-1.0.0.jar\",\"methods\":[{\"name\":\"keys\",\"parameterTypes\":[\"java.lang.String\"],\"returnType\":\"java.util.Collection\\u003cjava.lang.String\\u003e\"},{\"name\":\"getCacheMapValue\",\"parameterTypes\":[\"java.lang.String\",\"java.lang.String\"],\"returnType\":\"java.lang.Object\"},{\"name\":\"addCacheObject\",\"parameterTypes\":[\"java.lang.String\",\"java.lang.Object\"],\"returnType\":\"void\"},{\"name\":\"addCacheObject\",\"parameterTypes\":[\"java.lang.String\",\"java.lang.Object\",\"java.lang.Long\",\"java.util.concurrent.TimeUnit\"],\"returnType\":\"void\"},{\"name\":\"getCacheObject\",\"parameterTypes\":[\"java.lang.String\"],\"returnType\":\"java.lang.Object\"},{\"name\":\"addCacheMapValue\",\"parameterTypes\":[\"java.lang.String\",\"java.lang.String\",\"java.lang.Object\"],\"returnType\":\"void\"},{\"name\":\"addCacheList\",\"parameterTypes\":[\"java.lang.String\",\"java.util.List\\u003cT\\u003e\"],\"returnType\":\"java.lang.Long\"},{\"name\":\"addCacheMap\",\"parameterTypes\":[\"java.lang.String\",\"java.util.Map\\u003cjava.lang.String,T\\u003e\"],\"returnType\":\"void\"},{\"name\":\"getCacheSet\",\"parameterTypes\":[\"java.lang.String\"],\"returnType\":\"java.util.Set\\u003cjava.lang.Object\\u003e\"},{\"name\":\"getCacheList\",\"parameterTypes\":[\"java.lang.String\"],\"returnType\":\"java.util.List\\u003cjava.lang.Object\\u003e\"},{\"name\":\"expire\",\"parameterTypes\":[\"java.lang.String\",\"long\",\"java.util.concurrent.TimeUnit\"],\"returnType\":\"java.lang.Boolean\"},{\"name\":\"deleteObject\",\"parameterTypes\":[\"java.util.Collection\\u003cjava.lang.String\\u003e\"],\"returnType\":\"java.lang.Long\"},{\"name\":\"deleteObject\",\"parameterTypes\":[\"java.lang.String\"],\"returnType\":\"java.lang.Boolean\"},{\"name\":\"addCacheSet\",\"parameterTypes\":[\"java.lang.String\",\"java.util.Set\\u003cT\\u003e\"],\"returnType\":\"long\"},{\"name\":\"getCacheMap\",\"parameterTypes\":[\"java.lang.String\"],\"returnType\":\"java.util.Map\\u003cjava.lang.Object,java.lang.Object\\u003e\"},{\"name\":\"getMultiCacheMapValue\",\"parameterTypes\":[\"java.lang.String\",\"java.util.Collection\\u003cjava.lang.Object\\u003e\"],\"returnType\":\"java.util.List\\u003cjava.lang.Object\\u003e\"}],\"types\":[{\"type\":\"char\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"int\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"java.lang.Object\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"long\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"void\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"java.lang.Boolean\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"boolean\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"java.lang.Long\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"java.util.concurrent.TimeUnit\",\"enum\":[\"NANOSECONDS\",\"MICROSECONDS\",\"MILLISECONDS\",\"SECONDS\",\"MINUTES\",\"HOURS\",\"DAYS\"],\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.EnumTypeBuilder\"}]}','d7166b4028ae04f234e6620505c4bc22','2020-12-30 17:23:50','2021-01-03 16:07:32',NULL,'112.67.90.162','','',NULL,NULL,NULL,NULL,NULL),(1150,'pers.lbf.yeju.logserver.interfaces.ILoginLogService:::provider:log-server','dubbo','{\"parameters\":{\"side\":\"provider\",\"release\":\"2.7.8\",\"methods\":\"addLog\",\"deprecated\":\"false\",\"dubbo\":\"2.0.2\",\"interface\":\"pers.lbf.yeju.logserver.interfaces.ILoginLogService\",\"qos.enable\":\"false\",\"generic\":\"false\",\"revision\":\"1.0.0\",\"default\":\"true\",\"metadata-type\":\"remote\",\"application\":\"log-server\",\"dynamic\":\"true\",\"anyhost\":\"true\"},\"canonicalName\":\"pers.lbf.yeju.logserver.interfaces.ILoginLogService\",\"codeSource\":\"yeju-log-service-interfaces-1.0.0.jar\",\"methods\":[{\"name\":\"addLog\",\"parameterTypes\":[\"pers.lbf.yeju.logserver.interfaces.dto.AddLoginLogDTO\"],\"returnType\":\"void\"}],\"types\":[{\"type\":\"java.util.Date\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"pers.lbf.yeju.logserver.interfaces.dto.AddLoginLogDTO\",\"properties\":{\"lastIpNumber\":{\"type\":\"java.lang.Integer\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"accentTime\":{\"type\":\"java.util.Date\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"ip\":{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"loginStatus\":{\"type\":\"java.lang.Integer\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"message\":{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"account\":{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"subjectName\":{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"}},\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"char\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"int\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"void\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"java.lang.Integer\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"}]}','5647efa91a562cbffb7f05a596c28351','2021-01-01 09:13:27','2021-01-03 16:08:08',NULL,'112.67.90.162','','',NULL,NULL,NULL,NULL,NULL),(1158,'pers.lbf.yeju.logserver.interfaces.ILoginLogService:::consumer:authserver','dubbo','{\"init\":\"false\",\"side\":\"consumer\",\"metadata-type\":\"remote\",\"application\":\"authserver\",\"release\":\"2.7.8\",\"methods\":\"addLog\",\"sticky\":\"false\",\"dubbo\":\"2.0.2\",\"check\":\"false\",\"interface\":\"pers.lbf.yeju.logserver.interfaces.ILoginLogService\",\"qos.enable\":\"false\"}','cbb15cb1e8f3faf0293fede8489a5b14','2021-01-01 09:53:49','2021-01-01 14:42:28',NULL,'223.198.49.70','','',NULL,NULL,NULL,NULL,NULL),(1168,'pers.lbf.yeju.logserver.interfaces.IOperationLogService:::provider:log-server','dubbo','{\"parameters\":{\"side\":\"provider\",\"release\":\"2.7.8\",\"methods\":\"addOperationLog\",\"deprecated\":\"false\",\"dubbo\":\"2.0.2\",\"interface\":\"pers.lbf.yeju.logserver.interfaces.IOperationLogService\",\"qos.enable\":\"false\",\"generic\":\"false\",\"revision\":\"1.0.0\",\"default\":\"true\",\"metadata-type\":\"remote\",\"application\":\"log-server\",\"dynamic\":\"true\",\"anyhost\":\"true\"},\"canonicalName\":\"pers.lbf.yeju.logserver.interfaces.IOperationLogService\",\"codeSource\":\"yeju-log-service-interfaces-1.0.0.jar\",\"methods\":[{\"name\":\"addOperationLog\",\"parameterTypes\":[\"pers.lbf.yeju.logserver.interfaces.dto.AddOperationLogDTO\"],\"returnType\":\"void\"}],\"types\":[{\"type\":\"java.util.Date\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"pers.lbf.yeju.logserver.interfaces.dto.AddOperationLogDTO\",\"properties\":{\"departmentName\":{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"lastIpNumber\":{\"type\":\"java.lang.Integer\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"operationStatus\":{\"type\":\"java.lang.Integer\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"method\":{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"requestMethod\":{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"ip\":{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"errorMessage\":{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"title\":{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"operatorName\":{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"url\":{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"operationTime\":{\"type\":\"java.util.Date\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"result\":{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"param\":{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"location\":{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"operatorType\":{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"businessType\":{\"type\":\"java.lang.Integer\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},\"operatorId\":{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"}},\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"char\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"int\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"void\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"java.lang.Integer\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"},{\"type\":\"java.lang.String\",\"typeBuilderName\":\"org.apache.dubbo.metadata.definition.builder.DefaultTypeBuilder\"}]}','938d2c9da485f6d03a840bbc73fca1af','2021-01-01 11:54:06','2021-01-03 16:08:08',NULL,'112.67.90.162','','',NULL,NULL,NULL,NULL,NULL),(1170,'pers.lbf.yeju.logserver.interfaces.IOperationLogService:::consumer:log-server','dubbo','{\"init\":\"false\",\"side\":\"consumer\",\"release\":\"2.7.8\",\"methods\":\"addOperationLog\",\"injvm\":\"true\",\"dubbo\":\"2.0.2\",\"check\":\"false\",\"interface\":\"pers.lbf.yeju.logserver.interfaces.IOperationLogService\",\"qos.enable\":\"false\",\"revision\":\"1.0.0\",\"metadata-type\":\"remote\",\"application\":\"log-server\",\"sticky\":\"false\"}','972da0e22ae4e16bf63832e18e6167e3','2021-01-01 11:54:06','2021-01-03 16:08:08',NULL,'112.67.90.162','','',NULL,NULL,NULL,NULL,NULL),(1198,'pers.lbf.yeju.logserver.interfaces.ILoginLogService:::consumer:gateway-service','dubbo','{\"init\":\"false\",\"side\":\"consumer\",\"metadata-type\":\"remote\",\"application\":\"gateway-service\",\"release\":\"2.7.8\",\"methods\":\"addLog\",\"sticky\":\"false\",\"dubbo\":\"2.0.2\",\"check\":\"false\",\"interface\":\"pers.lbf.yeju.logserver.interfaces.ILoginLogService\",\"qos.enable\":\"false\"}','98ac528aab76d6fcd3f9507a7a248782','2021-01-01 15:20:02','2021-01-03 15:54:57',NULL,'112.67.90.162','','',NULL,NULL,NULL,NULL,NULL),(1326,'spring-rabbitmq-config.properties','DEFAULT_GROUP','spring.rabbitmq.addresses=127.0.0.1:5672\r\nspring.rabbitmq.username=guest\r\nspring.rabbitmq.password=guest\r\n\r\nspring.rabbitmq.virtual-host=/\r\nspring.rabbitmq.connection-timeout=3000\r\n\r\n# 开启confirm机制\r\nspring.rabbitmq.publisher-confirms=true\r\n# 开启return模式\r\nspring.rabbitmq.publisher-returns=true\r\n# 配合return机制使用，表示接收路由不可达的消息\r\nspring.rabbitmq.template.mandatory=true\r\n\r\n# 消费端\r\n# 设置签收模式：AUTO(自动签收)、MANUAL(手工签收)、NONE(不签收，没有任何操作)\r\nspring.rabbitmq.listener.simple.acknowledge-mode=AUTO\r\n# 设置当前消费者数量(线程数)\r\nspring.rabbitmq.listener.simple.concurrency=5\r\n# 设置消费者最大并发数量\r\nspring.rabbitmq.listener.simple.max-concurrency=10','cd3a1a99c7513a93cdf1f0c228f0cf66','2021-01-02 05:55:58','2021-01-03 09:41:40',NULL,'112.67.90.162','','ed63a92c-9b8c-4169-9a60-bc19059ae061','','','','properties',''),(1385,'rabbitmq-producer-base-config.properties','DEFAULT_GROUP','# 开启confirm机制\r\nspring.rabbitmq.publisher-confirms=true\r\n# 开启return模式\r\nspring.rabbitmq.publisher-returns=true\r\n# 配合return机制使用，表示接收路由不可达的消息\r\nspring.rabbitmq.template.mandatory=true','beb9a386ec84165eb9dcadc669c7f6e2','2021-01-03 12:34:50','2021-01-03 12:34:50',NULL,'112.67.90.162','','ed63a92c-9b8c-4169-9a60-bc19059ae061',NULL,NULL,NULL,'properties',NULL),(1386,'rabbitmq-base-connet-config.properties','DEFAULT_GROUP','spring.rabbitmq.addresses=8.129.77.225:5672\r\nspring.rabbitmq.username=yeju\r\nspring.rabbitmq.password=yeju@20200306','8ef28f76ee2843f585ab4d950d6651b3','2021-01-03 12:35:22','2021-01-03 12:35:22',NULL,'112.67.90.162','','ed63a92c-9b8c-4169-9a60-bc19059ae061',NULL,NULL,NULL,'properties',NULL),(1388,'rabbitmq-consumer-base-config.properties','DEFAULT_GROUP','# 设置签收模式：AUTO(自动签收)、MANUAL(手工签收)、NONE(不签收，没有任何操作)\r\nspring.rabbitmq.listener.simple.acknowledge-mode=MANUAL\r\n# 设置当前消费者数量(线程数)\r\nspring.rabbitmq.listener.simple.concurrency=1\r\n# 设置消费者最大并发数量\r\nspring.rabbitmq.listener.simple.max-concurrency=2','3272975c1ffa1a941c77bf971d78d18a','2021-01-03 13:57:34','2021-01-03 13:57:34',NULL,'112.67.90.162','','ed63a92c-9b8c-4169-9a60-bc19059ae061',NULL,NULL,NULL,'properties',NULL),(1397,'rabbitmq-login-log-config.properties','DEFAULT_GROUP','spring.rabbitmq.listener.login-log.queue-name=queue-login-log\r\nspring.rabbitmq.listener.login-log.queue-durable=true\r\nspring.rabbitmq.listener.login-log.exchange-name=exchange-log\r\nspring.rabbitmq.listener.login-log.exchange-durable=true\r\nspring.rabbitmq.listener.login-log.exchange-type=topic\r\nspring.rabbitmq.listener.login-log.exchange-ignoreDeclarationExceptions=true\r\nspring.rabbitmq.listener.login-log.key=log.login','d6c24995bdf9572f91f2421031cf4251','2021-01-03 14:22:48','2021-01-03 14:24:23',NULL,'112.67.90.162','','ed63a92c-9b8c-4169-9a60-bc19059ae061','','','','properties',''),(1426,'rabbitmq-login-log-config-new.properties','DEFAULT_GROUP','spring.rabbitmq.listener.login.log.queue.name=queue-login-log\r\nspring.rabbitmq.listener.login.log.queue.durable=true\r\nspring.rabbitmq.listener.login.log.exchange.name=exchange-log\r\nspring.rabbitmq.listener.login.log.exchange.durable=true\r\nspring.rabbitmq.listener.login.log.exchange.type=topic\r\nspring.rabbitmq.listener.login.log.exchange.ignoreDeclarationExceptions=true\r\nspring.rabbitmq.listener.login.log.exchange.key=log.login','8f911d8a51326a62fc336d1751657b57','2021-01-03 15:24:22','2021-01-03 15:36:13',NULL,'112.67.90.162','','ed63a92c-9b8c-4169-9a60-bc19059ae061','','','','properties','');

/*Table structure for table `config_info_aggr` */

DROP TABLE IF EXISTS `config_info_aggr`;

CREATE TABLE `config_info_aggr` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';

/*Data for the table `config_info_aggr` */

/*Table structure for table `config_info_beta` */

DROP TABLE IF EXISTS `config_info_beta`;

CREATE TABLE `config_info_beta` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';

/*Data for the table `config_info_beta` */

/*Table structure for table `config_info_tag` */

DROP TABLE IF EXISTS `config_info_tag`;

CREATE TABLE `config_info_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';

/*Data for the table `config_info_tag` */

/*Table structure for table `config_tags_relation` */

DROP TABLE IF EXISTS `config_tags_relation`;

CREATE TABLE `config_tags_relation` (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';

/*Data for the table `config_tags_relation` */

/*Table structure for table `group_capacity` */

DROP TABLE IF EXISTS `group_capacity`;

CREATE TABLE `group_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';

/*Data for the table `group_capacity` */

/*Table structure for table `his_config_info` */

DROP TABLE IF EXISTS `his_config_info`;

CREATE TABLE `his_config_info` (
  `id` bigint unsigned NOT NULL,
  `nid` bigint unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin,
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1551 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';

/*Data for the table `his_config_info` */


/*Table structure for table `permissions` */

DROP TABLE IF EXISTS `permissions`;

CREATE TABLE `permissions` (
  `role` varchar(50) NOT NULL,
  `resource` varchar(255) NOT NULL,
  `action` varchar(8) NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `permissions` */

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `roles` */

insert  into `roles`(`username`,`role`) values ('nacos','ROLE_ADMIN'),('yeju','yeju');

/*Table structure for table `tenant_capacity` */

DROP TABLE IF EXISTS `tenant_capacity`;

CREATE TABLE `tenant_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';

/*Data for the table `tenant_capacity` */

/*Table structure for table `tenant_info` */

DROP TABLE IF EXISTS `tenant_info`;

CREATE TABLE `tenant_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

/*Data for the table `tenant_info` */

insert  into `tenant_info`(`id`,`kp`,`tenant_id`,`tenant_name`,`tenant_desc`,`create_source`,`gmt_create`,`gmt_modified`) values (1,'1','ed63a92c-9b8c-4169-9a60-bc19059ae061','yeju','椰居平台','nacos',1606372910844,1606372910844),(2,'1','b6649ae6-6781-4a30-94a0-132f823a00eb','yeju-dubbo','椰居平台dubbo服务','nacos',1608781579812,1608781579812);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users` */

insert  into `users`(`username`,`password`,`enabled`) values ('nacos','$2a$10$/8S0rbkYyR2bcA5ZsGhPweEh6yADA/ZlX6UhSEjP6zt0WR3X0LBVy',1),('yeju','$2a$10$wAgBO9rH.8i9C/3wWR04PuLscc6kgm9J10AdaWSmeRvZkrn0Dm0oK',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;