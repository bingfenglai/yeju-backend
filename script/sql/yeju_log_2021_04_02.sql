/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.22 : Database - yeju_log
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `table_business_house_check_log` */

DROP TABLE IF EXISTS `table_business_house_check_log`;

CREATE TABLE `table_business_house_check_log` (
  `id` bigint NOT NULL COMMENT '记录标识',
  `house_id` bigint DEFAULT NULL COMMENT '房源标识',
  `account_id` bigint DEFAULT NULL COMMENT '账户人账户标识',
  `house_old_status` varchar(4) DEFAULT NULL COMMENT '房源审核前状态',
  `house_new_status` varchar(4) DEFAULT NULL COMMENT '房源审核后的状态',
  `check_time` datetime DEFAULT NULL COMMENT '审核时间',
  `opinions` varchar(256) DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`,`house_id`,`account_id`),
  KEY `house` (`house_id`),
  KEY `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='记录房源审核日志信息';

/*Table structure for table `table_message_delivery_log` */

DROP TABLE IF EXISTS `table_message_delivery_log`;

CREATE TABLE `table_message_delivery_log` (
  `log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `message_id` bigint DEFAULT NULL COMMENT '消息主键',
  `receiver_id` bigint DEFAULT NULL COMMENT '消息接收者主键',
  `principal` varchar(16) DEFAULT NULL COMMENT '接收账号',
  `delivery_status` varchar(4) DEFAULT NULL COMMENT '投递状态',
  `delivery_time` date DEFAULT NULL COMMENT '投递时间',
  `message_type` varchar(4) DEFAULT NULL COMMENT '消息类型',
  `receiver_type` varchar(4) DEFAULT NULL COMMENT '接收者类型',
  PRIMARY KEY (`log_id`),
  UNIQUE KEY `message_id` (`message_id`,`receiver_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1369849031720140802 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='消息投递记录';

/*Table structure for table `table_system_login_log` */

DROP TABLE IF EXISTS `table_system_login_log`;

CREATE TABLE `table_system_login_log` (
  `login_log_id` bigint NOT NULL COMMENT '主键',
  `account` varchar(64) DEFAULT NULL COMMENT '访问账户',
  `subject_name` varchar(32) DEFAULT NULL COMMENT '访问者姓名',
  `ip` varchar(32) DEFAULT NULL COMMENT '访问ip地址',
  `login_status` int DEFAULT NULL COMMENT '访问状态0失败1成功',
  `message` varchar(128) DEFAULT NULL COMMENT '访问失败时记录原因',
  `accent_time` datetime DEFAULT NULL COMMENT '访问时间',
  `last_ip_number` int DEFAULT NULL COMMENT 'ip地址最后一位，表分区依据',
  PRIMARY KEY (`login_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='登录日志表，仅保存最新三个月的登录日志';

/*Table structure for table `table_system_operation_log` */

DROP TABLE IF EXISTS `table_system_operation_log`;

CREATE TABLE `table_system_operation_log` (
  `operation_log_id` bigint NOT NULL COMMENT '主键',
  `title` varchar(64) DEFAULT NULL COMMENT '模块标题',
  `business_type` int DEFAULT NULL COMMENT '业务类型0其他1新增2修改3删除',
  `method` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '方法名称',
  `request_method` varchar(12) DEFAULT NULL COMMENT '请求方式',
  `operator_type` varchar(4) DEFAULT NULL COMMENT '操作类型0其他1平台业务员2客户3移动端客户',
  `operator_name` varchar(32) DEFAULT NULL COMMENT '操作者名字',
  `department_name` varchar(64) DEFAULT NULL COMMENT '部门名称',
  `url` varchar(128) DEFAULT NULL COMMENT '请求路径',
  `ip` varchar(32) DEFAULT NULL COMMENT '请求ip',
  `location` varchar(256) DEFAULT NULL COMMENT '地点',
  `param` varchar(256) DEFAULT NULL COMMENT '请求参数',
  `result` varchar(512) DEFAULT NULL COMMENT '返回的结果',
  `operation_status` int DEFAULT NULL COMMENT '操作状态0正常1异常',
  `error_message` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '错误消息',
  `operation_time` datetime DEFAULT NULL COMMENT '操作时间',
  `last_ip_number` int DEFAULT NULL COMMENT '访问ip最后一位数字，用作分区标识',
  `operator_account` varchar(32) DEFAULT NULL COMMENT '操作者账户',
  `execute_time` bigint DEFAULT NULL COMMENT '操作耗时 单位 ms',
  PRIMARY KEY (`operation_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统操作日志表，主要用户记录系统后台敏感操作信息，以ip最后一位数字分区。保存最新一个月，过时信息搬历史表保存半年';

/*Table structure for table `table_system_task_log` */

DROP TABLE IF EXISTS `table_system_task_log`;

CREATE TABLE `table_system_task_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_name` varchar(64) DEFAULT NULL COMMENT '任务名称',
  `task_group` varchar(64) DEFAULT NULL COMMENT '任务组名称',
  `invoke_target` varchar(64) DEFAULT NULL COMMENT '调用目标字符串',
  `task_log` varchar(512) DEFAULT NULL COMMENT '任务日志',
  `task_status` char(1) DEFAULT '1' COMMENT '任务执行状态1成功0失败',
  `exception_info` varchar(1024) DEFAULT NULL COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `start_time` datetime DEFAULT NULL COMMENT '任务开始时间',
  `stop_time` datetime DEFAULT NULL COMMENT '任务结束时间',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1369492327636189187 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
