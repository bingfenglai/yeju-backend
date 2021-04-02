/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.22 : Database - yeju_core
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `join_rent_invitation` */

DROP TABLE IF EXISTS `join_rent_invitation`;

CREATE TABLE `join_rent_invitation` (
  `invitation_id` bigint NOT NULL COMMENT '邀请标识',
  `initiator_id` bigint DEFAULT NULL COMMENT '邀请人标识',
  `house_id` bigint DEFAULT NULL COMMENT '房源标识',
  `status` char(1) DEFAULT '1' COMMENT '邀请状态0失效1生效2已邀请到',
  `number_of_people` int DEFAULT NULL COMMENT '预邀请人数',
  `invitation_type` char(1) DEFAULT NULL COMMENT '0发帖1邀请码',
  `is_recommended` char(1) DEFAULT '1' COMMENT '是否支持系统推荐',
  `roommates_gender` char(1) DEFAULT NULL COMMENT '舍友性别限制0不限1男2女',
  `is_pet` char(1) DEFAULT '0' COMMENT '是否接受宠物',
  `min_age` int DEFAULT '18' COMMENT '舍友最小年龄',
  `max_age` int DEFAULT NULL COMMENT '舍友最大年龄',
  `other` varchar(128) DEFAULT NULL COMMENT '其他要求',
  `email` varchar(32) DEFAULT NULL COMMENT '安全邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`invitation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='合租申请表。当意向客户邀请合租后，创建合租申请工单。这张表用于存储合租邀请流程信息';

/*Data for the table `join_rent_invitation` */

/*Table structure for table `r_t_account_message_group` */

DROP TABLE IF EXISTS `r_t_account_message_group`;

CREATE TABLE `r_t_account_message_group` (
  `account_id` bigint NOT NULL COMMENT '系统账户主键',
  `message_group_id` bigint NOT NULL COMMENT '消息群组主键',
  `group_type` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '消息组类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `version` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`account_id`,`message_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `r_t_account_message_group` */

insert  into `r_t_account_message_group`(`account_id`,`message_group_id`,`group_type`,`create_time`,`create_by`,`update_time`,`changed_by`,`version`,`is_delete`,`remark`) values (1,1,'1',NULL,NULL,NULL,NULL,NULL,0,NULL),(1,2,'1',NULL,NULL,NULL,NULL,0,0,NULL),(2,1,'1',NULL,NULL,NULL,NULL,0,0,NULL),(2,3,'1',NULL,NULL,NULL,NULL,0,0,NULL);

/*Table structure for table `r_t_account_role` */

DROP TABLE IF EXISTS `r_t_account_role`;

CREATE TABLE `r_t_account_role` (
  `account_id` bigint NOT NULL COMMENT '账号主键',
  `role_id` bigint NOT NULL COMMENT '角色主键',
  `status` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '1' COMMENT '状态1启用0不启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `versions` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `account_number` varchar(16) NOT NULL COMMENT '系统账户',
  `phone_number` char(11) NOT NULL COMMENT '手机号',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色标识符，冗余字段',
  `valid_period` datetime DEFAULT NULL COMMENT '有效期至',
  PRIMARY KEY (`account_id`,`role_id`),
  UNIQUE KEY `account_id_2` (`account_id`,`role_id`),
  UNIQUE KEY `account_number` (`account_number`),
  UNIQUE KEY `phome_number` (`phone_number`),
  KEY `account_id` (`account_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账户-角色关系表 N-1';

/*Data for the table `r_t_account_role` */

insert  into `r_t_account_role`(`account_id`,`role_id`,`status`,`create_time`,`create_by`,`update_time`,`changed_by`,`versions`,`is_delete`,`remark`,`account_number`,`phone_number`,`role_code`,`valid_period`) values (1,1,'1',NULL,NULL,NULL,NULL,NULL,0,NULL,'969391','17330937086','admin',NULL),(2,1,'1','2021-04-01 16:52:23',NULL,NULL,NULL,NULL,0,NULL,'969392','17330937087','admin',NULL);

/*Table structure for table `r_t_department_role` */

DROP TABLE IF EXISTS `r_t_department_role`;

CREATE TABLE `r_t_department_role` (
  `department_id` bigint NOT NULL COMMENT '部门主键',
  `role_id` bigint NOT NULL COMMENT '角色主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `versions` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`department_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门-角色表 N-1. 这个表的作用在于：员工分配岗位后，即关联了部门，获得部门的角色。例如财务部的员工可以登录平台获取';

/*Data for the table `r_t_department_role` */

/*Table structure for table `r_t_role_resource` */

DROP TABLE IF EXISTS `r_t_role_resource`;

CREATE TABLE `r_t_role_resource` (
  `role_id` bigint NOT NULL COMMENT '角色主键',
  `resource_id` bigint NOT NULL COMMENT '资源主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `versions` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `valid_period` datetime DEFAULT NULL COMMENT '有效期至',
  `authorized_type` varchar(4) DEFAULT NULL COMMENT '授权类型 详见数据字典',
  PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色-资源关系表 N-N';

/*Data for the table `r_t_role_resource` */

/*Table structure for table `t_r_account_resources` */

DROP TABLE IF EXISTS `t_r_account_resources`;

CREATE TABLE `t_r_account_resources` (
  `id` bigint NOT NULL COMMENT '主键',
  `account_number` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `phone_number` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号关联的手机号',
  `resource_id` bigint NOT NULL COMMENT '资源主键',
  `status` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '1' COMMENT '状态 0未启用1启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` bigint NOT NULL COMMENT '创建者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `changed_by` bigint NOT NULL COMMENT '更改者',
  `version_number` int NOT NULL DEFAULT '0' COMMENT '字段版本',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '删除标识',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `valid_period` datetime DEFAULT NULL COMMENT '有效期至',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='这是一张冗余的关系表。主要是用来便于权限查询';

/*Data for the table `t_r_account_resources` */

insert  into `t_r_account_resources`(`id`,`account_number`,`phone_number`,`resource_id`,`status`,`create_time`,`create_by`,`update_time`,`changed_by`,`version_number`,`is_delete`,`remark`,`valid_period`) values (1,'969391','17330937086',1,'1','2020-12-23 14:22:48',1,'2020-12-23 14:23:06',1,0,0,NULL,NULL);

/*Table structure for table `table_business_account_assets` */

DROP TABLE IF EXISTS `table_business_account_assets`;

CREATE TABLE `table_business_account_assets` (
  `assets_id` bigint NOT NULL COMMENT '资产标识',
  `account_id` bigint DEFAULT NULL COMMENT '账户主键',
  `customer_id` bigint DEFAULT NULL COMMENT '客户主键。\n            当账户未系统账户时，这个客户标识是哪个客户托管到平台的押金',
  `balance` decimal(12,2) DEFAULT NULL COMMENT '余额',
  `unit` bigint DEFAULT NULL COMMENT '资产计量单位\n            0，元（现金和平台补贴金的计量单位）\n            1，个（积分的计量单位）',
  `assets_type` int DEFAULT NULL COMMENT '0现金\n            1平台补贴金\n            2积分\n            3押金\n            ',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  PRIMARY KEY (`assets_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账户资产表。记录账户资产。包括现金、积分等';

/*Data for the table `table_business_account_assets` */

/*Table structure for table `table_business_account_withholding` */

DROP TABLE IF EXISTS `table_business_account_withholding`;

CREATE TABLE `table_business_account_withholding` (
  `withholding_id` bigint NOT NULL COMMENT '主键',
  `transfer_out_account_id` bigint DEFAULT NULL COMMENT '转出账户id',
  `ransfer_to_account_id` bigint DEFAULT NULL COMMENT '目标账户id',
  `payment_id` bigint NOT NULL COMMENT '支付流水号',
  `sums` decimal(12,2) DEFAULT NULL COMMENT '最终应扣除金额',
  `status` int DEFAULT NULL COMMENT '0待扣1已扣2已返',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账户交易预扣款信息表.一个场景是，意向客户支付房租后，相应现金进入预扣状态，当房东交房后，相应现金转入房东账户';

/*Data for the table `table_business_account_withholding` */

/*Table structure for table `table_business_check_out_to_apply_for` */

DROP TABLE IF EXISTS `table_business_check_out_to_apply_for`;

CREATE TABLE `table_business_check_out_to_apply_for` (
  `check_out_apply_for_id` bigint NOT NULL COMMENT '主键',
  `tenant_id` bigint DEFAULT NULL COMMENT '房客id',
  `landlord_id` bigint DEFAULT NULL COMMENT '房东id',
  `type` varchar(4) DEFAULT NULL COMMENT '0正常到期结算申请\n            1房客提前退房\n            2房客欠交租金退房\n            3其他',
  `status` varchar(4) DEFAULT NULL COMMENT '申请状态，详见数据字典\n            0申请发起中\n            1平台预受理\n            3平台受理中\n            4费用结算中\n            5待补交费用\n            6费用清算完毕\n            7待平台下发退款\n            8待第三方支付下发退款\n            9退款金额到账\n            10退房成功\n            11退房失败\n            12申请取消',
  `trading_id` bigint DEFAULT NULL COMMENT '交易记录主键',
  `house_id` bigint DEFAULT NULL COMMENT '房源id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`check_out_apply_for_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='退房申请信息表,完成搬历史表，以创建者IP最后两位数字%12作为表分区';

/*Data for the table `table_business_check_out_to_apply_for` */

/*Table structure for table `table_business_collection` */

DROP TABLE IF EXISTS `table_business_collection`;

CREATE TABLE `table_business_collection` (
  `collection_id` bigint NOT NULL COMMENT '主键',
  `customer_id` bigint DEFAULT NULL COMMENT '客户主键',
  `house_id` bigint DEFAULT NULL COMMENT '房源标识',
  `status` int DEFAULT NULL COMMENT '收藏状态0取消收藏1收藏',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`collection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='客户收藏信息表,用于记录用户的收藏页信息。收藏的内容可以是图文、视频和房源此表存于mongodb。本表主要用来记录用户收';

/*Data for the table `table_business_collection` */

/*Table structure for table `table_business_comment` */

DROP TABLE IF EXISTS `table_business_comment`;

CREATE TABLE `table_business_comment` (
  `answer_id` bigint DEFAULT NULL COMMENT '主键',
  `reviewers_name` varchar(32) DEFAULT NULL COMMENT '评论者名字',
  `commented_object_id` bigint NOT NULL COMMENT '被评论对象的主键（针对评论的评论时，则为评论的主键）',
  `content` varchar(512) DEFAULT NULL COMMENT '内容',
  `comment_type` varchar(4) DEFAULT NULL COMMENT '评论类型0图文评论1提问评论2评论的评论.详情见数据字典',
  `comment_images_url` varchar(1024) DEFAULT NULL COMMENT '图片评论路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`commented_object_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论信息表,可以是针对【提问】的回答，亦可以是针对图文的评论。此表存于mongodb';

/*Data for the table `table_business_comment` */

/*Table structure for table `table_business_community` */

DROP TABLE IF EXISTS `table_business_community`;

CREATE TABLE `table_business_community` (
  `community_id` bigint NOT NULL COMMENT '主键',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `province_id` bigint DEFAULT NULL COMMENT '所在省',
  `city_id` bigint DEFAULT NULL COMMENT '所在城市',
  `area_id` bigint DEFAULT NULL COMMENT '所在区（镇）',
  `detailed_address` varchar(256) DEFAULT NULL COMMENT '详细地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`community_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='小区信息表';

/*Data for the table `table_business_community` */

/*Table structure for table `table_business_customer` */

DROP TABLE IF EXISTS `table_business_customer`;

CREATE TABLE `table_business_customer` (
  `customer_id` bigint NOT NULL COMMENT '主键',
  `account_id` bigint DEFAULT NULL COMMENT '账户id',
  `customer_name` varchar(32) DEFAULT NULL COMMENT '客户姓名',
  `gender` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '3' COMMENT '性别值',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号',
  `phone_number_prefix` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机区号值',
  `customer_status` varchar(4) DEFAULT NULL COMMENT '客户认证状态0未认证1已认证',
  `province` bigint DEFAULT NULL COMMENT '所在省、自治州',
  `city` bigint DEFAULT NULL COMMENT '所在城市',
  `avatar` varchar(500) DEFAULT NULL COMMENT '客户头像地址',
  `email` varchar(32) DEFAULT NULL COMMENT '安全邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  `month_certified` int DEFAULT NULL COMMENT '完成认证时所在月份，table_business_customer_valid分区标识',
  `month_added` int DEFAULT NULL COMMENT '客户信息添加时的月份',
  `month_outmoded` int DEFAULT NULL COMMENT '客户信息过时时的月份。历史表的分区依据',
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='客户信息表，实名认证完成后搬table_business_customer_valid.所属账户超半年未登录系统，客户信';

/*Data for the table `table_business_customer` */

insert  into `table_business_customer`(`customer_id`,`account_id`,`customer_name`,`gender`,`phone_number`,`phone_number_prefix`,`customer_status`,`province`,`city`,`avatar`,`email`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`,`month_certified`,`month_added`,`month_outmoded`) values (1,2,'喜小乐','1','17330937087','+86',NULL,9,120,NULL,NULL,NULL,NULL,'2021-03-22 23:41:54',969391,NULL,0,0,NULL,NULL,NULL);

/*Table structure for table `table_business_customer_head_portrait` */

DROP TABLE IF EXISTS `table_business_customer_head_portrait`;

CREATE TABLE `table_business_customer_head_portrait` (
  `customer_id` bigint NOT NULL COMMENT '客户主键',
  `customer_name` varchar(16) DEFAULT NULL COMMENT '客户名字',
  `image_url` varchar(128) DEFAULT NULL COMMENT '头像路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='客户头像信息表.这张表主要是用于资讯业务客户信息的展示。客户名字的显示策略采取仅保留姓氏的脱敏策略';

/*Data for the table `table_business_customer_head_portrait` */

/*Table structure for table `table_business_customer_valid` */

DROP TABLE IF EXISTS `table_business_customer_valid`;

CREATE TABLE `table_business_customer_valid` (
  `customer_id` bigint NOT NULL COMMENT '主键',
  `account_id` bigint DEFAULT NULL COMMENT '账户id',
  `customer_name` varchar(32) DEFAULT NULL COMMENT '客户姓名',
  `gender` varchar(4) DEFAULT NULL COMMENT '性别0男1女，详见属性表',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号',
  `phone_number_prefix` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '+86' COMMENT '手机区号值',
  `customer_status` varchar(4) DEFAULT NULL COMMENT '客户认证状态0未认证1已认证',
  `province` bigint DEFAULT NULL COMMENT '所在省、自治州',
  `city` bigint DEFAULT NULL COMMENT '所在城市',
  `avatar` varchar(500) DEFAULT NULL COMMENT '客户头像地址',
  `email` varchar(32) DEFAULT NULL COMMENT '安全邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  `month_certified` int DEFAULT NULL COMMENT '完成认证时所在月份，table_business_customer_valid分区标识',
  `month_added` int DEFAULT NULL COMMENT '客户信息添加时的月份',
  `month_outmoded` int DEFAULT NULL COMMENT '客户信息过时时的月份。历史表的分区依据',
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='客户信息表，实名认证完成后搬table_business_customer_valid.所属账户超半年未登录系统，客户信';

/*Data for the table `table_business_customer_valid` */

insert  into `table_business_customer_valid`(`customer_id`,`account_id`,`customer_name`,`gender`,`phone_number`,`phone_number_prefix`,`customer_status`,`province`,`city`,`avatar`,`email`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`,`month_certified`,`month_added`,`month_outmoded`) values (1,2,'喜小乐','1','17330937087','+86','1',9,120,NULL,'bingfengdev@aliyun.com','2021-03-23 22:08:36',NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL);

/*Table structure for table `table_business_deposit_withdrawal_log` */

DROP TABLE IF EXISTS `table_business_deposit_withdrawal_log`;

CREATE TABLE `table_business_deposit_withdrawal_log` (
  `log_id` bigint NOT NULL COMMENT '主键',
  `account_id` bigint DEFAULT NULL COMMENT '账户标识',
  `type` int DEFAULT NULL COMMENT '0充值\n            1提现\n            2付押金\n            3付房租\n            4收到房租\n            ',
  `source` int DEFAULT NULL COMMENT '0银行卡充值\n            1支付宝账户充值\n            2微信账户充值\n            3房租收入',
  `sums` decimal(12,2) DEFAULT NULL COMMENT '转入转出金额',
  `third_party_serial_number` bigint DEFAULT NULL COMMENT '如果是第三方支付的话需要记录第三方流水号\n            ',
  `original_balance` decimal(12,2) DEFAULT NULL COMMENT '余额变动前总数',
  `after_balance` decimal(12,2) DEFAULT NULL COMMENT '变动之后的金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='存取款记录表。对于平台内部充值账户的余额转入转出记录,以月份作为分区标识\n账户余额变动记录表';

/*Data for the table `table_business_deposit_withdrawal_log` */

/*Table structure for table `table_business_district` */

DROP TABLE IF EXISTS `table_business_district`;

CREATE TABLE `table_business_district` (
  `district_id` bigint NOT NULL COMMENT '主键',
  `parent_id` bigint DEFAULT NULL COMMENT '所属上一级城市id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '城市名称',
  `type` int DEFAULT NULL COMMENT '城市类型0国1省2市3区',
  `hierarchy` int DEFAULT NULL COMMENT '地区所处的层级',
  `district_sequence` varchar(12) DEFAULT NULL COMMENT '层级序列',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `versions` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`district_id`),
  KEY `type` (`type`),
  KEY `parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='中国省市表';

/*Data for the table `table_business_district` */

insert  into `table_business_district`(`district_id`,`parent_id`,`name`,`type`,`hierarchy`,`district_sequence`,`create_time`,`create_by`,`update_time`,`changed_by`,`versions`,`is_delete`,`remark`) values (1,0,'中国',0,1,'.1.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(2,1,'北京',1,2,'.1.2.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(3,1,'安徽',1,2,'.1.3.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(4,1,'福建',1,2,'.1.4.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(5,1,'甘肃',1,2,'.1.5.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(6,1,'广东',1,2,'.1.6.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(7,1,'广西',1,2,'.1.7.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(8,1,'贵州',1,2,'.1.8.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(9,1,'海南',1,2,'.1.9.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(10,1,'河北',1,2,'.1.10.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(11,1,'河南',1,2,'.1.11.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(12,1,'黑龙江',1,2,'.1.12.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(13,1,'湖北',1,2,'.1.13.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(14,1,'湖南',1,2,'.1.14.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(15,1,'吉林',1,2,'.1.15.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(16,1,'江苏',1,2,'.1.16.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(17,1,'江西',1,2,'.1.17.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(18,1,'辽宁',1,2,'.1.18.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(19,1,'内蒙古',1,2,'.1.19.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(20,1,'宁夏',1,2,'.1.20.','2021-03-17 21:13:19',NULL,NULL,NULL,0,0,NULL),(21,1,'青海',1,2,'.1.21.','2021-03-17 21:13:20',NULL,NULL,NULL,0,0,NULL),(22,1,'山东',1,2,'.1.22.','2021-03-17 21:13:20',NULL,NULL,NULL,0,0,NULL),(23,1,'山西',1,2,'.1.23.','2021-03-17 21:13:20',NULL,NULL,NULL,0,0,NULL),(24,1,'陕西',1,2,'.1.24.','2021-03-17 21:13:20',NULL,NULL,NULL,0,0,NULL),(25,1,'上海',1,2,'.1.25.','2021-03-17 21:13:20',NULL,NULL,NULL,0,0,NULL),(26,1,'四川',1,2,'.1.26.','2021-03-17 21:13:20',NULL,NULL,NULL,0,0,NULL),(27,1,'天津',1,2,'.1.27.','2021-03-17 21:13:20',NULL,NULL,NULL,0,0,NULL),(28,1,'西藏',1,2,'.1.28.','2021-03-17 21:13:20',NULL,NULL,NULL,0,0,NULL),(29,1,'新疆',1,2,'.1.29.','2021-03-17 21:13:20',NULL,NULL,NULL,0,0,NULL),(30,1,'云南',1,2,'.1.30.','2021-03-17 21:13:20',NULL,NULL,NULL,0,0,NULL),(31,1,'浙江',1,2,'.1.31.','2021-03-17 21:13:20',NULL,NULL,NULL,0,0,NULL),(32,1,'重庆',1,2,'.1.32.','2021-03-17 21:13:21',NULL,NULL,NULL,0,0,NULL),(33,1,'香港',1,2,'.1.33.','2021-03-17 21:13:21',NULL,NULL,NULL,0,0,NULL),(34,1,'澳门',1,2,'.1.34.','2021-03-17 21:13:21',NULL,NULL,NULL,0,0,NULL),(35,1,'台湾',1,2,'.1.35.','2021-03-17 21:13:21',NULL,NULL,NULL,0,0,NULL),(36,3,'安庆',2,3,'.1.3.36.','2021-03-17 21:13:21',NULL,NULL,NULL,0,0,NULL),(37,3,'蚌埠',2,3,'.1.3.37.','2021-03-17 21:13:21',NULL,NULL,NULL,0,0,NULL),(38,3,'巢湖',2,3,'.1.3.38.','2021-03-17 21:13:21',NULL,NULL,NULL,0,0,NULL),(39,3,'池州',2,3,'.1.3.39.','2021-03-17 21:13:21',NULL,NULL,NULL,0,0,NULL),(40,3,'滁州',2,3,'.1.3.40.','2021-03-17 21:13:21',NULL,NULL,NULL,0,0,NULL),(41,3,'阜阳',2,3,'.1.3.41.','2021-03-17 21:13:21',NULL,NULL,NULL,0,0,NULL),(42,3,'淮北',2,3,'.1.3.42.','2021-03-17 21:13:21',NULL,NULL,NULL,0,0,NULL),(43,3,'淮南',2,3,'.1.3.43.','2021-03-17 21:13:21',NULL,NULL,NULL,0,0,NULL),(44,3,'黄山',2,3,'.1.3.44.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(45,3,'六安',2,3,'.1.3.45.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(46,3,'马鞍山',2,3,'.1.3.46.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(47,3,'宿州',2,3,'.1.3.47.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(48,3,'铜陵',2,3,'.1.3.48.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(49,3,'芜湖',2,3,'.1.3.49.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(50,3,'宣城',2,3,'.1.3.50.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(51,3,'亳州',2,3,'.1.3.51.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(52,2,'市辖区',2,3,'.1.2.52.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(53,4,'福州',2,3,'.1.4.53.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(54,4,'龙岩',2,3,'.1.4.54.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(55,4,'南平',2,3,'.1.4.55.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(56,4,'宁德',2,3,'.1.4.56.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(57,4,'莆田',2,3,'.1.4.57.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(58,4,'泉州',2,3,'.1.4.58.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(59,4,'三明',2,3,'.1.4.59.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(60,4,'厦门',2,3,'.1.4.60.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(61,4,'漳州',2,3,'.1.4.61.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(62,5,'兰州',2,3,'.1.5.62.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(63,5,'白银',2,3,'.1.5.63.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(64,5,'定西',2,3,'.1.5.64.','2021-03-17 21:13:22',NULL,NULL,NULL,0,0,NULL),(65,5,'甘南',2,3,'.1.5.65.','2021-03-17 21:13:23',NULL,NULL,NULL,0,0,NULL),(66,5,'嘉峪关',2,3,'.1.5.66.','2021-03-17 21:13:23',NULL,NULL,NULL,0,0,NULL),(67,5,'金昌',2,3,'.1.5.67.','2021-03-17 21:13:23',NULL,NULL,NULL,0,0,NULL),(68,5,'酒泉',2,3,'.1.5.68.','2021-03-17 21:13:23',NULL,NULL,NULL,0,0,NULL),(69,5,'临夏',2,3,'.1.5.69.','2021-03-17 21:13:23',NULL,NULL,NULL,0,0,NULL),(70,5,'陇南',2,3,'.1.5.70.','2021-03-17 21:13:23',NULL,NULL,NULL,0,0,NULL),(71,5,'平凉',2,3,'.1.5.71.','2021-03-17 21:13:23',NULL,NULL,NULL,0,0,NULL),(72,5,'庆阳',2,3,'.1.5.72.','2021-03-17 21:13:23',NULL,NULL,NULL,0,0,NULL),(73,5,'天水',2,3,'.1.5.73.','2021-03-17 21:13:23',NULL,NULL,NULL,0,0,NULL),(74,5,'武威',2,3,'.1.5.74.','2021-03-17 21:13:23',NULL,NULL,NULL,0,0,NULL),(75,5,'张掖',2,3,'.1.5.75.','2021-03-17 21:13:23',NULL,NULL,NULL,0,0,NULL),(76,6,'广州',2,3,'.1.6.76.','2021-03-17 21:13:23',NULL,NULL,NULL,0,0,NULL),(77,6,'深圳',2,3,'.1.6.77.','2021-03-17 21:13:24',NULL,NULL,NULL,0,0,NULL),(78,6,'潮州',2,3,'.1.6.78.','2021-03-17 21:13:24',NULL,NULL,NULL,0,0,NULL),(79,6,'东莞',2,3,'.1.6.79.','2021-03-17 21:13:24',NULL,NULL,NULL,0,0,NULL),(80,6,'佛山',2,3,'.1.6.80.','2021-03-17 21:13:24',NULL,NULL,NULL,0,0,NULL),(81,6,'河源',2,3,'.1.6.81.','2021-03-17 21:13:24',NULL,NULL,NULL,0,0,NULL),(82,6,'惠州',2,3,'.1.6.82.','2021-03-17 21:13:24',NULL,NULL,NULL,0,0,NULL),(83,6,'江门',2,3,'.1.6.83.','2021-03-17 21:13:24',NULL,NULL,NULL,0,0,NULL),(84,6,'揭阳',2,3,'.1.6.84.','2021-03-17 21:13:24',NULL,NULL,NULL,0,0,NULL),(85,6,'茂名',2,3,'.1.6.85.','2021-03-17 21:13:24',NULL,NULL,NULL,0,0,NULL),(86,6,'梅州',2,3,'.1.6.86.','2021-03-17 21:13:24',NULL,NULL,NULL,0,0,NULL),(87,6,'清远',2,3,'.1.6.87.','2021-03-17 21:13:24',NULL,NULL,NULL,0,0,NULL),(88,6,'汕头',2,3,'.1.6.88.','2021-03-17 21:13:24',NULL,NULL,NULL,0,0,NULL),(89,6,'汕尾',2,3,'.1.6.89.','2021-03-17 21:13:24',NULL,NULL,NULL,0,0,NULL),(90,6,'韶关',2,3,'.1.6.90.','2021-03-17 21:13:24',NULL,NULL,NULL,0,0,NULL),(91,6,'阳江',2,3,'.1.6.91.','2021-03-17 21:13:24',NULL,NULL,NULL,0,0,NULL),(92,6,'云浮',2,3,'.1.6.92.','2021-03-17 21:13:24',NULL,NULL,NULL,0,0,NULL),(93,6,'湛江',2,3,'.1.6.93.','2021-03-17 21:13:24',NULL,NULL,NULL,0,0,NULL),(94,6,'肇庆',2,3,'.1.6.94.','2021-03-17 21:13:24',NULL,NULL,NULL,0,0,NULL),(95,6,'中山',2,3,'.1.6.95.','2021-03-17 21:13:25',NULL,NULL,NULL,0,0,NULL),(96,6,'珠海',2,3,'.1.6.96.','2021-03-17 21:13:25',NULL,NULL,NULL,0,0,NULL),(97,7,'南宁',2,3,'.1.7.97.','2021-03-17 21:13:25',NULL,NULL,NULL,0,0,NULL),(98,7,'桂林',2,3,'.1.7.98.','2021-03-17 21:13:25',NULL,NULL,NULL,0,0,NULL),(99,7,'百色',2,3,'.1.7.99.','2021-03-17 21:13:25',NULL,NULL,NULL,0,0,NULL),(100,7,'北海',2,3,'.1.7.100.','2021-03-17 21:13:25',NULL,NULL,NULL,0,0,NULL),(101,7,'崇左',2,3,'.1.7.101.','2021-03-17 21:13:25',NULL,NULL,NULL,0,0,NULL),(102,7,'防城港',2,3,'.1.7.102.','2021-03-17 21:13:25',NULL,NULL,NULL,0,0,NULL),(103,7,'贵港',2,3,'.1.7.103.','2021-03-17 21:13:25',NULL,NULL,NULL,0,0,NULL),(104,7,'河池',2,3,'.1.7.104.','2021-03-17 21:13:25',NULL,NULL,NULL,0,0,NULL),(105,7,'贺州',2,3,'.1.7.105.','2021-03-17 21:13:25',NULL,NULL,NULL,0,0,NULL),(106,7,'来宾',2,3,'.1.7.106.','2021-03-17 21:13:25',NULL,NULL,NULL,0,0,NULL),(107,7,'柳州',2,3,'.1.7.107.','2021-03-17 21:13:25',NULL,NULL,NULL,0,0,NULL),(108,7,'钦州',2,3,'.1.7.108.','2021-03-17 21:13:25',NULL,NULL,NULL,0,0,NULL),(109,7,'梧州',2,3,'.1.7.109.','2021-03-17 21:13:25',NULL,NULL,NULL,0,0,NULL),(110,7,'玉林',2,3,'.1.7.110.','2021-03-17 21:13:25',NULL,NULL,NULL,0,0,NULL),(111,8,'贵阳',2,3,'.1.8.111.','2021-03-17 21:13:25',NULL,NULL,NULL,0,0,NULL),(112,8,'安顺',2,3,'.1.8.112.','2021-03-17 21:13:25',NULL,NULL,NULL,0,0,NULL),(113,8,'毕节',2,3,'.1.8.113.','2021-03-17 21:13:26',NULL,NULL,NULL,0,0,NULL),(114,8,'六盘水',2,3,'.1.8.114.','2021-03-17 21:13:26',NULL,NULL,NULL,0,0,NULL),(115,8,'黔东南',2,3,'.1.8.115.','2021-03-17 21:13:26',NULL,NULL,NULL,0,0,NULL),(116,8,'黔南',2,3,'.1.8.116.','2021-03-17 21:13:27',NULL,NULL,NULL,0,0,NULL),(117,8,'黔西南',2,3,'.1.8.117.','2021-03-17 21:13:27',NULL,NULL,NULL,0,0,NULL),(118,8,'铜仁',2,3,'.1.8.118.','2021-03-17 21:13:27',NULL,NULL,NULL,0,0,NULL),(119,8,'遵义',2,3,'.1.8.119.','2021-03-17 21:13:28',NULL,NULL,NULL,0,0,NULL),(120,9,'海口',2,3,'.1.9.120.','2021-03-17 21:13:29',NULL,NULL,NULL,0,0,NULL),(121,9,'三亚',2,3,'.1.9.121.','2021-03-17 21:13:29',NULL,NULL,NULL,0,0,NULL),(122,9,'白沙',2,3,'.1.9.122.','2021-03-17 21:13:29',NULL,NULL,NULL,0,0,NULL),(123,9,'保亭',2,3,'.1.9.123.','2021-03-17 21:13:31',NULL,NULL,NULL,0,0,NULL),(124,9,'昌江',2,3,'.1.9.124.','2021-03-17 21:13:31',NULL,NULL,NULL,0,0,NULL),(125,9,'澄迈县',2,3,'.1.9.125.','2021-03-17 21:13:33',NULL,NULL,NULL,0,0,NULL),(126,9,'定安县',2,3,'.1.9.126.','2021-03-17 21:13:33',NULL,NULL,NULL,0,0,NULL),(127,9,'东方',2,3,'.1.9.127.','2021-03-17 21:13:33',NULL,NULL,NULL,0,0,NULL),(128,9,'乐东',2,3,'.1.9.128.','2021-03-17 21:13:33',NULL,NULL,NULL,0,0,NULL),(129,9,'临高县',2,3,'.1.9.129.','2021-03-17 21:13:33',NULL,NULL,NULL,0,0,NULL),(130,9,'陵水',2,3,'.1.9.130.','2021-03-17 21:13:33',NULL,NULL,NULL,0,0,NULL),(131,9,'琼海',2,3,'.1.9.131.','2021-03-17 21:13:34',NULL,NULL,NULL,0,0,NULL),(132,9,'琼中',2,3,'.1.9.132.','2021-03-17 21:13:34',NULL,NULL,NULL,0,0,NULL),(133,9,'屯昌县',2,3,'.1.9.133.','2021-03-17 21:13:34',NULL,NULL,NULL,0,0,NULL),(134,9,'万宁',2,3,'.1.9.134.','2021-03-17 21:13:34',NULL,NULL,NULL,0,0,NULL),(135,9,'文昌',2,3,'.1.9.135.','2021-03-17 21:13:34',NULL,NULL,NULL,0,0,NULL),(136,9,'五指山',2,3,'.1.9.136.','2021-03-17 21:13:34',NULL,NULL,NULL,0,0,NULL),(137,9,'儋州',2,3,'.1.9.137.','2021-03-17 21:13:34',NULL,NULL,NULL,0,0,NULL),(138,10,'石家庄',2,3,'.1.10.138.','2021-03-17 21:13:34',NULL,NULL,NULL,0,0,NULL),(139,10,'保定',2,3,'.1.10.139.','2021-03-17 21:13:34',NULL,NULL,NULL,0,0,NULL),(140,10,'沧州',2,3,'.1.10.140.','2021-03-17 21:13:34',NULL,NULL,NULL,0,0,NULL),(141,10,'承德',2,3,'.1.10.141.','2021-03-17 21:13:34',NULL,NULL,NULL,0,0,NULL),(142,10,'邯郸',2,3,'.1.10.142.','2021-03-17 21:13:35',NULL,NULL,NULL,0,0,NULL),(143,10,'衡水',2,3,'.1.10.143.','2021-03-17 21:13:35',NULL,NULL,NULL,0,0,NULL),(144,10,'廊坊',2,3,'.1.10.144.','2021-03-17 21:13:35',NULL,NULL,NULL,0,0,NULL),(145,10,'秦皇岛',2,3,'.1.10.145.','2021-03-17 21:13:35',NULL,NULL,NULL,0,0,NULL),(146,10,'唐山',2,3,'.1.10.146.','2021-03-17 21:13:35',NULL,NULL,NULL,0,0,NULL),(147,10,'邢台',2,3,'.1.10.147.','2021-03-17 21:13:36',NULL,NULL,NULL,0,0,NULL),(148,10,'张家口',2,3,'.1.10.148.','2021-03-17 21:13:36',NULL,NULL,NULL,0,0,NULL),(149,11,'郑州',2,3,'.1.11.149.','2021-03-17 21:13:36',NULL,NULL,NULL,0,0,NULL),(150,11,'洛阳',2,3,'.1.11.150.','2021-03-17 21:13:36',NULL,NULL,NULL,0,0,NULL),(151,11,'开封',2,3,'.1.11.151.','2021-03-17 21:13:36',NULL,NULL,NULL,0,0,NULL),(152,11,'安阳',2,3,'.1.11.152.','2021-03-17 21:13:36',NULL,NULL,NULL,0,0,NULL),(153,11,'鹤壁',2,3,'.1.11.153.','2021-03-17 21:13:36',NULL,NULL,NULL,0,0,NULL),(154,11,'济源',2,3,'.1.11.154.','2021-03-17 21:13:36',NULL,NULL,NULL,0,0,NULL),(155,11,'焦作',2,3,'.1.11.155.','2021-03-17 21:13:36',NULL,NULL,NULL,0,0,NULL),(156,11,'南阳',2,3,'.1.11.156.','2021-03-17 21:13:36',NULL,NULL,NULL,0,0,NULL),(157,11,'平顶山',2,3,'.1.11.157.','2021-03-17 21:13:36',NULL,NULL,NULL,0,0,NULL),(158,11,'三门峡',2,3,'.1.11.158.','2021-03-17 21:13:36',NULL,NULL,NULL,0,0,NULL),(159,11,'商丘',2,3,'.1.11.159.','2021-03-17 21:13:36',NULL,NULL,NULL,0,0,NULL),(160,11,'新乡',2,3,'.1.11.160.','2021-03-17 21:13:36',NULL,NULL,NULL,0,0,NULL),(161,11,'信阳',2,3,'.1.11.161.','2021-03-17 21:13:36',NULL,NULL,NULL,0,0,NULL),(162,11,'许昌',2,3,'.1.11.162.','2021-03-17 21:13:36',NULL,NULL,NULL,0,0,NULL),(163,11,'周口',2,3,'.1.11.163.','2021-03-17 21:13:36',NULL,NULL,NULL,0,0,NULL),(164,11,'驻马店',2,3,'.1.11.164.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(165,11,'漯河',2,3,'.1.11.165.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(166,11,'濮阳',2,3,'.1.11.166.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(167,12,'哈尔滨',2,3,'.1.12.167.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(168,12,'大庆',2,3,'.1.12.168.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(169,12,'大兴安岭',2,3,'.1.12.169.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(170,12,'鹤岗',2,3,'.1.12.170.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(171,12,'黑河',2,3,'.1.12.171.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(172,12,'鸡西',2,3,'.1.12.172.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(173,12,'佳木斯',2,3,'.1.12.173.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(174,12,'牡丹江',2,3,'.1.12.174.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(175,12,'七台河',2,3,'.1.12.175.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(176,12,'齐齐哈尔',2,3,'.1.12.176.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(177,12,'双鸭山',2,3,'.1.12.177.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(178,12,'绥化',2,3,'.1.12.178.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(179,12,'伊春',2,3,'.1.12.179.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(180,13,'武汉',2,3,'.1.13.180.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(181,13,'仙桃',2,3,'.1.13.181.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(182,13,'鄂州',2,3,'.1.13.182.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(183,13,'黄冈',2,3,'.1.13.183.','2021-03-17 21:13:37',NULL,NULL,NULL,0,0,NULL),(184,13,'黄石',2,3,'.1.13.184.','2021-03-17 21:13:38',NULL,NULL,NULL,0,0,NULL),(185,13,'荆门',2,3,'.1.13.185.','2021-03-17 21:13:38',NULL,NULL,NULL,0,0,NULL),(186,13,'荆州',2,3,'.1.13.186.','2021-03-17 21:13:38',NULL,NULL,NULL,0,0,NULL),(187,13,'潜江',2,3,'.1.13.187.','2021-03-17 21:13:38',NULL,NULL,NULL,0,0,NULL),(188,13,'神农架林区',2,3,'.1.13.188.','2021-03-17 21:13:38',NULL,NULL,NULL,0,0,NULL),(189,13,'十堰',2,3,'.1.13.189.','2021-03-17 21:13:38',NULL,NULL,NULL,0,0,NULL),(190,13,'随州',2,3,'.1.13.190.','2021-03-17 21:13:38',NULL,NULL,NULL,0,0,NULL),(191,13,'天门',2,3,'.1.13.191.','2021-03-17 21:13:38',NULL,NULL,NULL,0,0,NULL),(192,13,'咸宁',2,3,'.1.13.192.','2021-03-17 21:13:38',NULL,NULL,NULL,0,0,NULL),(193,13,'襄樊',2,3,'.1.13.193.','2021-03-17 21:13:38',NULL,NULL,NULL,0,0,NULL),(194,13,'孝感',2,3,'.1.13.194.','2021-03-17 21:13:38',NULL,NULL,NULL,0,0,NULL),(195,13,'宜昌',2,3,'.1.13.195.','2021-03-17 21:13:38',NULL,NULL,NULL,0,0,NULL),(196,13,'恩施',2,3,'.1.13.196.','2021-03-17 21:13:38',NULL,NULL,NULL,0,0,NULL),(197,14,'长沙',2,3,'.1.14.197.','2021-03-17 21:13:38',NULL,NULL,NULL,0,0,NULL),(198,14,'张家界',2,3,'.1.14.198.','2021-03-17 21:13:38',NULL,NULL,NULL,0,0,NULL),(199,14,'常德',2,3,'.1.14.199.','2021-03-17 21:13:38',NULL,NULL,NULL,0,0,NULL),(200,14,'郴州',2,3,'.1.14.200.','2021-03-17 21:13:39',NULL,NULL,NULL,0,0,NULL),(201,14,'衡阳',2,3,'.1.14.201.','2021-03-17 21:13:39',NULL,NULL,NULL,0,0,NULL),(202,14,'怀化',2,3,'.1.14.202.','2021-03-17 21:13:39',NULL,NULL,NULL,0,0,NULL),(203,14,'娄底',2,3,'.1.14.203.','2021-03-17 21:13:39',NULL,NULL,NULL,0,0,NULL),(204,14,'邵阳',2,3,'.1.14.204.','2021-03-17 21:13:39',NULL,NULL,NULL,0,0,NULL),(205,14,'湘潭',2,3,'.1.14.205.','2021-03-17 21:13:39',NULL,NULL,NULL,0,0,NULL),(206,14,'湘西',2,3,'.1.14.206.','2021-03-17 21:13:39',NULL,NULL,NULL,0,0,NULL),(207,14,'益阳',2,3,'.1.14.207.','2021-03-17 21:13:39',NULL,NULL,NULL,0,0,NULL),(208,14,'永州',2,3,'.1.14.208.','2021-03-17 21:13:40',NULL,NULL,NULL,0,0,NULL),(209,14,'岳阳',2,3,'.1.14.209.','2021-03-17 21:13:40',NULL,NULL,NULL,0,0,NULL),(210,14,'株洲',2,3,'.1.14.210.','2021-03-17 21:13:40',NULL,NULL,NULL,0,0,NULL),(211,15,'长春',2,3,'.1.15.211.','2021-03-17 21:13:40',NULL,NULL,NULL,0,0,NULL),(212,15,'吉林',2,3,'.1.15.212.','2021-03-17 21:13:40',NULL,NULL,NULL,0,0,NULL),(213,15,'白城',2,3,'.1.15.213.','2021-03-17 21:13:40',NULL,NULL,NULL,0,0,NULL),(214,15,'白山',2,3,'.1.15.214.','2021-03-17 21:13:40',NULL,NULL,NULL,0,0,NULL),(215,15,'辽源',2,3,'.1.15.215.','2021-03-17 21:13:40',NULL,NULL,NULL,0,0,NULL),(216,15,'四平',2,3,'.1.15.216.','2021-03-17 21:13:40',NULL,NULL,NULL,0,0,NULL),(217,15,'松原',2,3,'.1.15.217.','2021-03-17 21:13:40',NULL,NULL,NULL,0,0,NULL),(218,15,'通化',2,3,'.1.15.218.','2021-03-17 21:13:40',NULL,NULL,NULL,0,0,NULL),(219,15,'延边',2,3,'.1.15.219.','2021-03-17 21:13:40',NULL,NULL,NULL,0,0,NULL),(220,16,'南京',2,3,'.1.16.220.','2021-03-17 21:13:41',NULL,NULL,NULL,0,0,NULL),(221,16,'苏州',2,3,'.1.16.221.','2021-03-17 21:13:41',NULL,NULL,NULL,0,0,NULL),(222,16,'无锡',2,3,'.1.16.222.','2021-03-17 21:13:41',NULL,NULL,NULL,0,0,NULL),(223,16,'常州',2,3,'.1.16.223.','2021-03-17 21:13:41',NULL,NULL,NULL,0,0,NULL),(224,16,'淮安',2,3,'.1.16.224.','2021-03-17 21:13:42',NULL,NULL,NULL,0,0,NULL),(225,16,'连云港',2,3,'.1.16.225.','2021-03-17 21:13:44',NULL,NULL,NULL,0,0,NULL),(226,16,'南通',2,3,'.1.16.226.','2021-03-17 21:13:46',NULL,NULL,NULL,0,0,NULL),(227,16,'宿迁',2,3,'.1.16.227.','2021-03-17 21:13:46',NULL,NULL,NULL,0,0,NULL),(228,16,'泰州',2,3,'.1.16.228.','2021-03-17 21:13:46',NULL,NULL,NULL,0,0,NULL),(229,16,'徐州',2,3,'.1.16.229.','2021-03-17 21:13:46',NULL,NULL,NULL,0,0,NULL),(230,16,'盐城',2,3,'.1.16.230.','2021-03-17 21:13:46',NULL,NULL,NULL,0,0,NULL),(231,16,'扬州',2,3,'.1.16.231.','2021-03-17 21:13:46',NULL,NULL,NULL,0,0,NULL),(232,16,'镇江',2,3,'.1.16.232.','2021-03-17 21:13:46',NULL,NULL,NULL,0,0,NULL),(233,17,'南昌',2,3,'.1.17.233.','2021-03-17 21:13:46',NULL,NULL,NULL,0,0,NULL),(234,17,'抚州',2,3,'.1.17.234.','2021-03-17 21:13:46',NULL,NULL,NULL,0,0,NULL),(235,17,'赣州',2,3,'.1.17.235.','2021-03-17 21:13:46',NULL,NULL,NULL,0,0,NULL),(236,17,'吉安',2,3,'.1.17.236.','2021-03-17 21:13:46',NULL,NULL,NULL,0,0,NULL),(237,17,'景德镇',2,3,'.1.17.237.','2021-03-17 21:13:47',NULL,NULL,NULL,0,0,NULL),(238,17,'九江',2,3,'.1.17.238.','2021-03-17 21:13:47',NULL,NULL,NULL,0,0,NULL),(239,17,'萍乡',2,3,'.1.17.239.','2021-03-17 21:13:47',NULL,NULL,NULL,0,0,NULL),(240,17,'上饶',2,3,'.1.17.240.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(241,17,'新余',2,3,'.1.17.241.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(242,17,'宜春',2,3,'.1.17.242.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(243,17,'鹰潭',2,3,'.1.17.243.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(244,18,'沈阳',2,3,'.1.18.244.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(245,18,'大连',2,3,'.1.18.245.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(246,18,'鞍山',2,3,'.1.18.246.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(247,18,'本溪',2,3,'.1.18.247.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(248,18,'朝阳',2,3,'.1.18.248.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(249,18,'丹东',2,3,'.1.18.249.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(250,18,'抚顺',2,3,'.1.18.250.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(251,18,'阜新',2,3,'.1.18.251.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(252,18,'葫芦岛',2,3,'.1.18.252.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(253,18,'锦州',2,3,'.1.18.253.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(254,18,'辽阳',2,3,'.1.18.254.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(255,18,'盘锦',2,3,'.1.18.255.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(256,18,'铁岭',2,3,'.1.18.256.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(257,18,'营口',2,3,'.1.18.257.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(258,19,'呼和浩特',2,3,'.1.19.258.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(259,19,'阿拉善盟',2,3,'.1.19.259.','2021-03-17 21:13:48',NULL,NULL,NULL,0,0,NULL),(260,19,'巴彦淖尔盟',2,3,'.1.19.260.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(261,19,'包头',2,3,'.1.19.261.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(262,19,'赤峰',2,3,'.1.19.262.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(263,19,'鄂尔多斯',2,3,'.1.19.263.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(264,19,'呼伦贝尔',2,3,'.1.19.264.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(265,19,'通辽',2,3,'.1.19.265.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(266,19,'乌海',2,3,'.1.19.266.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(267,19,'乌兰察布市',2,3,'.1.19.267.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(268,19,'锡林郭勒盟',2,3,'.1.19.268.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(269,19,'兴安盟',2,3,'.1.19.269.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(270,20,'银川',2,3,'.1.20.270.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(271,20,'固原',2,3,'.1.20.271.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(272,20,'石嘴山',2,3,'.1.20.272.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(273,20,'吴忠',2,3,'.1.20.273.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(274,20,'中卫',2,3,'.1.20.274.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(275,21,'西宁',2,3,'.1.21.275.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(276,21,'果洛',2,3,'.1.21.276.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(277,21,'海北',2,3,'.1.21.277.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(278,21,'海东',2,3,'.1.21.278.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(279,21,'海南',2,3,'.1.21.279.','2021-03-17 21:13:49',NULL,NULL,NULL,0,0,NULL),(280,21,'海西',2,3,'.1.21.280.','2021-03-17 21:13:50',NULL,NULL,NULL,0,0,NULL),(281,21,'黄南',2,3,'.1.21.281.','2021-03-17 21:13:50',NULL,NULL,NULL,0,0,NULL),(282,21,'玉树',2,3,'.1.21.282.','2021-03-17 21:13:50',NULL,NULL,NULL,0,0,NULL),(283,22,'济南',2,3,'.1.22.283.','2021-03-17 21:13:50',NULL,NULL,NULL,0,0,NULL),(284,22,'青岛',2,3,'.1.22.284.','2021-03-17 21:13:50',NULL,NULL,NULL,0,0,NULL),(285,22,'滨州',2,3,'.1.22.285.','2021-03-17 21:13:50',NULL,NULL,NULL,0,0,NULL),(286,22,'德州',2,3,'.1.22.286.','2021-03-17 21:13:50',NULL,NULL,NULL,0,0,NULL),(287,22,'东营',2,3,'.1.22.287.','2021-03-17 21:13:50',NULL,NULL,NULL,0,0,NULL),(288,22,'菏泽',2,3,'.1.22.288.','2021-03-17 21:13:50',NULL,NULL,NULL,0,0,NULL),(289,22,'济宁',2,3,'.1.22.289.','2021-03-17 21:13:51',NULL,NULL,NULL,0,0,NULL),(290,22,'莱芜',2,3,'.1.22.290.','2021-03-17 21:13:51',NULL,NULL,NULL,0,0,NULL),(291,22,'聊城',2,3,'.1.22.291.','2021-03-17 21:13:51',NULL,NULL,NULL,0,0,NULL),(292,22,'临沂',2,3,'.1.22.292.','2021-03-17 21:13:51',NULL,NULL,NULL,0,0,NULL),(293,22,'日照',2,3,'.1.22.293.','2021-03-17 21:13:51',NULL,NULL,NULL,0,0,NULL),(294,22,'泰安',2,3,'.1.22.294.','2021-03-17 21:13:51',NULL,NULL,NULL,0,0,NULL),(295,22,'威海',2,3,'.1.22.295.','2021-03-17 21:13:51',NULL,NULL,NULL,0,0,NULL),(296,22,'潍坊',2,3,'.1.22.296.','2021-03-17 21:13:51',NULL,NULL,NULL,0,0,NULL),(297,22,'烟台',2,3,'.1.22.297.','2021-03-17 21:13:51',NULL,NULL,NULL,0,0,NULL),(298,22,'枣庄',2,3,'.1.22.298.','2021-03-17 21:13:51',NULL,NULL,NULL,0,0,NULL),(299,22,'淄博',2,3,'.1.22.299.','2021-03-17 21:13:51',NULL,NULL,NULL,0,0,NULL),(300,23,'太原',2,3,'.1.23.300.','2021-03-17 21:13:51',NULL,NULL,NULL,0,0,NULL),(301,23,'长治',2,3,'.1.23.301.','2021-03-17 21:13:51',NULL,NULL,NULL,0,0,NULL),(302,23,'大同',2,3,'.1.23.302.','2021-03-17 21:13:51',NULL,NULL,NULL,0,0,NULL),(303,23,'晋城',2,3,'.1.23.303.','2021-03-17 21:13:51',NULL,NULL,NULL,0,0,NULL),(304,23,'晋中',2,3,'.1.23.304.','2021-03-17 21:13:52',NULL,NULL,NULL,0,0,NULL),(305,23,'临汾',2,3,'.1.23.305.','2021-03-17 21:13:52',NULL,NULL,NULL,0,0,NULL),(306,23,'吕梁',2,3,'.1.23.306.','2021-03-17 21:13:52',NULL,NULL,NULL,0,0,NULL),(307,23,'朔州',2,3,'.1.23.307.','2021-03-17 21:13:52',NULL,NULL,NULL,0,0,NULL),(308,23,'忻州',2,3,'.1.23.308.','2021-03-17 21:13:52',NULL,NULL,NULL,0,0,NULL),(309,23,'阳泉',2,3,'.1.23.309.','2021-03-17 21:13:52',NULL,NULL,NULL,0,0,NULL),(310,23,'运城',2,3,'.1.23.310.','2021-03-17 21:13:52',NULL,NULL,NULL,0,0,NULL),(311,24,'西安',2,3,'.1.24.311.','2021-03-17 21:13:52',NULL,NULL,NULL,0,0,NULL),(312,24,'安康',2,3,'.1.24.312.','2021-03-17 21:13:52',NULL,NULL,NULL,0,0,NULL),(313,24,'宝鸡',2,3,'.1.24.313.','2021-03-17 21:13:52',NULL,NULL,NULL,0,0,NULL),(314,24,'汉中',2,3,'.1.24.314.','2021-03-17 21:13:52',NULL,NULL,NULL,0,0,NULL),(315,24,'商洛',2,3,'.1.24.315.','2021-03-17 21:13:52',NULL,NULL,NULL,0,0,NULL),(316,24,'铜川',2,3,'.1.24.316.','2021-03-17 21:13:52',NULL,NULL,NULL,0,0,NULL),(317,24,'渭南',2,3,'.1.24.317.','2021-03-17 21:13:52',NULL,NULL,NULL,0,0,NULL),(318,24,'咸阳',2,3,'.1.24.318.','2021-03-17 21:13:52',NULL,NULL,NULL,0,0,NULL),(319,24,'延安',2,3,'.1.24.319.','2021-03-17 21:13:52',NULL,NULL,NULL,0,0,NULL),(320,24,'榆林',2,3,'.1.24.320.','2021-03-17 21:13:52',NULL,NULL,NULL,0,0,NULL),(321,25,'上海',2,3,'.1.25.321.','2021-03-17 21:13:52',NULL,NULL,NULL,0,0,NULL),(322,26,'成都',2,3,'.1.26.322.','2021-03-17 21:13:53',NULL,NULL,NULL,0,0,NULL),(323,26,'绵阳',2,3,'.1.26.323.','2021-03-17 21:13:53',NULL,NULL,NULL,0,0,NULL),(324,26,'阿坝',2,3,'.1.26.324.','2021-03-17 21:13:53',NULL,NULL,NULL,0,0,NULL),(325,26,'巴中',2,3,'.1.26.325.','2021-03-17 21:13:53',NULL,NULL,NULL,0,0,NULL),(326,26,'达州',2,3,'.1.26.326.','2021-03-17 21:13:53',NULL,NULL,NULL,0,0,NULL),(327,26,'德阳',2,3,'.1.26.327.','2021-03-17 21:13:53',NULL,NULL,NULL,0,0,NULL),(328,26,'甘孜',2,3,'.1.26.328.','2021-03-17 21:13:53',NULL,NULL,NULL,0,0,NULL),(329,26,'广安',2,3,'.1.26.329.','2021-03-17 21:13:53',NULL,NULL,NULL,0,0,NULL),(330,26,'广元',2,3,'.1.26.330.','2021-03-17 21:13:53',NULL,NULL,NULL,0,0,NULL),(331,26,'乐山',2,3,'.1.26.331.','2021-03-17 21:13:53',NULL,NULL,NULL,0,0,NULL),(332,26,'凉山',2,3,'.1.26.332.','2021-03-17 21:13:53',NULL,NULL,NULL,0,0,NULL),(333,26,'眉山',2,3,'.1.26.333.','2021-03-17 21:13:53',NULL,NULL,NULL,0,0,NULL),(334,26,'南充',2,3,'.1.26.334.','2021-03-17 21:13:53',NULL,NULL,NULL,0,0,NULL),(335,26,'内江',2,3,'.1.26.335.','2021-03-17 21:13:53',NULL,NULL,NULL,0,0,NULL),(336,26,'攀枝花',2,3,'.1.26.336.','2021-03-17 21:13:54',NULL,NULL,NULL,0,0,NULL),(337,26,'遂宁',2,3,'.1.26.337.','2021-03-17 21:13:54',NULL,NULL,NULL,0,0,NULL),(338,26,'雅安',2,3,'.1.26.338.','2021-03-17 21:13:54',NULL,NULL,NULL,0,0,NULL),(339,26,'宜宾',2,3,'.1.26.339.','2021-03-17 21:13:54',NULL,NULL,NULL,0,0,NULL),(340,26,'资阳',2,3,'.1.26.340.','2021-03-17 21:13:54',NULL,NULL,NULL,0,0,NULL),(341,26,'自贡',2,3,'.1.26.341.','2021-03-17 21:13:54',NULL,NULL,NULL,0,0,NULL),(342,26,'泸州',2,3,'.1.26.342.','2021-03-17 21:13:54',NULL,NULL,NULL,0,0,NULL),(343,27,'天津',2,3,'.1.27.343.','2021-03-17 21:13:54',NULL,NULL,NULL,0,0,NULL),(344,28,'拉萨',2,3,'.1.28.344.','2021-03-17 21:13:54',NULL,NULL,NULL,0,0,NULL),(345,28,'阿里',2,3,'.1.28.345.','2021-03-17 21:13:54',NULL,NULL,NULL,0,0,NULL),(346,28,'昌都',2,3,'.1.28.346.','2021-03-17 21:13:55',NULL,NULL,NULL,0,0,NULL),(347,28,'林芝',2,3,'.1.28.347.','2021-03-17 21:13:55',NULL,NULL,NULL,0,0,NULL),(348,28,'那曲',2,3,'.1.28.348.','2021-03-17 21:13:55',NULL,NULL,NULL,0,0,NULL),(349,28,'日喀则',2,3,'.1.28.349.','2021-03-17 21:13:55',NULL,NULL,NULL,0,0,NULL),(350,28,'山南',2,3,'.1.28.350.','2021-03-17 21:13:55',NULL,NULL,NULL,0,0,NULL),(351,29,'乌鲁木齐',2,3,'.1.29.351.','2021-03-17 21:13:55',NULL,NULL,NULL,0,0,NULL),(352,29,'阿克苏',2,3,'.1.29.352.','2021-03-17 21:13:55',NULL,NULL,NULL,0,0,NULL),(353,29,'阿拉尔',2,3,'.1.29.353.','2021-03-17 21:13:55',NULL,NULL,NULL,0,0,NULL),(354,29,'巴音郭楞',2,3,'.1.29.354.','2021-03-17 21:13:55',NULL,NULL,NULL,0,0,NULL),(355,29,'博尔塔拉',2,3,'.1.29.355.','2021-03-17 21:13:55',NULL,NULL,NULL,0,0,NULL),(356,29,'昌吉',2,3,'.1.29.356.','2021-03-17 21:13:55',NULL,NULL,NULL,0,0,NULL),(357,29,'哈密',2,3,'.1.29.357.','2021-03-17 21:13:55',NULL,NULL,NULL,0,0,NULL),(358,29,'和田',2,3,'.1.29.358.','2021-03-17 21:13:55',NULL,NULL,NULL,0,0,NULL),(359,29,'喀什',2,3,'.1.29.359.','2021-03-17 21:13:55',NULL,NULL,NULL,0,0,NULL),(360,29,'克拉玛依',2,3,'.1.29.360.','2021-03-17 21:13:55',NULL,NULL,NULL,0,0,NULL),(361,29,'克孜勒苏',2,3,'.1.29.361.','2021-03-17 21:13:55',NULL,NULL,NULL,0,0,NULL),(362,29,'石河子',2,3,'.1.29.362.','2021-03-17 21:13:55',NULL,NULL,NULL,0,0,NULL),(363,29,'图木舒克',2,3,'.1.29.363.','2021-03-17 21:13:55',NULL,NULL,NULL,0,0,NULL),(364,29,'吐鲁番',2,3,'.1.29.364.','2021-03-17 21:13:56',NULL,NULL,NULL,0,0,NULL),(365,29,'五家渠',2,3,'.1.29.365.','2021-03-17 21:13:56',NULL,NULL,NULL,0,0,NULL),(366,29,'伊犁',2,3,'.1.29.366.','2021-03-17 21:13:56',NULL,NULL,NULL,0,0,NULL),(367,30,'昆明',2,3,'.1.30.367.','2021-03-17 21:13:56',NULL,NULL,NULL,0,0,NULL),(368,30,'怒江',2,3,'.1.30.368.','2021-03-17 21:13:56',NULL,NULL,NULL,0,0,NULL),(369,30,'普洱',2,3,'.1.30.369.','2021-03-17 21:13:56',NULL,NULL,NULL,0,0,NULL),(370,30,'丽江',2,3,'.1.30.370.','2021-03-17 21:13:56',NULL,NULL,NULL,0,0,NULL),(371,30,'保山',2,3,'.1.30.371.','2021-03-17 21:13:56',NULL,NULL,NULL,0,0,NULL),(372,30,'楚雄',2,3,'.1.30.372.','2021-03-17 21:13:56',NULL,NULL,NULL,0,0,NULL),(373,30,'大理',2,3,'.1.30.373.','2021-03-17 21:13:56',NULL,NULL,NULL,0,0,NULL),(374,30,'德宏',2,3,'.1.30.374.','2021-03-17 21:13:56',NULL,NULL,NULL,0,0,NULL),(375,30,'迪庆',2,3,'.1.30.375.','2021-03-17 21:13:56',NULL,NULL,NULL,0,0,NULL),(376,30,'红河',2,3,'.1.30.376.','2021-03-17 21:13:56',NULL,NULL,NULL,0,0,NULL),(377,30,'临沧',2,3,'.1.30.377.','2021-03-17 21:13:56',NULL,NULL,NULL,0,0,NULL),(378,30,'曲靖',2,3,'.1.30.378.','2021-03-17 21:13:56',NULL,NULL,NULL,0,0,NULL),(379,30,'文山',2,3,'.1.30.379.','2021-03-17 21:13:56',NULL,NULL,NULL,0,0,NULL),(380,30,'西双版纳',2,3,'.1.30.380.','2021-03-17 21:13:56',NULL,NULL,NULL,0,0,NULL),(381,30,'玉溪',2,3,'.1.30.381.','2021-03-17 21:13:56',NULL,NULL,NULL,0,0,NULL),(382,30,'昭通',2,3,'.1.30.382.','2021-03-17 21:13:57',NULL,NULL,NULL,0,0,NULL),(383,31,'杭州',2,3,'.1.31.383.','2021-03-17 21:13:57',NULL,NULL,NULL,0,0,NULL),(384,31,'湖州',2,3,'.1.31.384.','2021-03-17 21:13:57',NULL,NULL,NULL,0,0,NULL),(385,31,'嘉兴',2,3,'.1.31.385.','2021-03-17 21:13:57',NULL,NULL,NULL,0,0,NULL),(386,31,'金华',2,3,'.1.31.386.','2021-03-17 21:13:57',NULL,NULL,NULL,0,0,NULL),(387,31,'丽水',2,3,'.1.31.387.','2021-03-17 21:13:58',NULL,NULL,NULL,0,0,NULL),(388,31,'宁波',2,3,'.1.31.388.','2021-03-17 21:13:58',NULL,NULL,NULL,0,0,NULL),(389,31,'绍兴',2,3,'.1.31.389.','2021-03-17 21:13:58',NULL,NULL,NULL,0,0,NULL),(390,31,'台州',2,3,'.1.31.390.','2021-03-17 21:13:58',NULL,NULL,NULL,0,0,NULL),(391,31,'温州',2,3,'.1.31.391.','2021-03-17 21:13:58',NULL,NULL,NULL,0,0,NULL),(392,31,'舟山',2,3,'.1.31.392.','2021-03-17 21:13:58',NULL,NULL,NULL,0,0,NULL),(393,31,'衢州',2,3,'.1.31.393.','2021-03-17 21:13:58',NULL,NULL,NULL,0,0,NULL),(394,32,'重庆',2,3,'.1.32.394.','2021-03-17 21:13:58',NULL,NULL,NULL,0,0,NULL),(395,33,'香港',2,3,'.1.33.395.','2021-03-17 21:13:58',NULL,NULL,NULL,0,0,NULL),(396,34,'澳门',2,3,'.1.34.396.','2021-03-17 21:13:58',NULL,NULL,NULL,0,0,NULL),(397,35,'台湾',2,3,'.1.35.397.','2021-03-17 21:13:58',NULL,NULL,NULL,0,0,NULL),(398,36,'迎江区',3,4,'.1.3.36.398.','2021-03-17 21:13:58',NULL,NULL,NULL,0,0,NULL),(399,36,'大观区',3,4,'.1.3.36.399.','2021-03-17 21:13:58',NULL,NULL,NULL,0,0,NULL),(400,36,'宜秀区',3,4,'.1.3.36.400.','2021-03-17 21:13:58',NULL,NULL,NULL,0,0,NULL),(401,36,'桐城市',3,4,'.1.3.36.401.','2021-03-17 21:13:58',NULL,NULL,NULL,0,0,NULL),(402,36,'怀宁县',3,4,'.1.3.36.402.','2021-03-17 21:13:58',NULL,NULL,NULL,0,0,NULL),(403,36,'枞阳县',3,4,'.1.3.36.403.','2021-03-17 21:13:58',NULL,NULL,NULL,0,0,NULL),(404,36,'潜山县',3,4,'.1.3.36.404.','2021-03-17 21:13:59',NULL,NULL,NULL,0,0,NULL),(405,36,'太湖县',3,4,'.1.3.36.405.','2021-03-17 21:13:59',NULL,NULL,NULL,0,0,NULL),(406,36,'宿松县',3,4,'.1.3.36.406.','2021-03-17 21:13:59',NULL,NULL,NULL,0,0,NULL),(407,36,'望江县',3,4,'.1.3.36.407.','2021-03-17 21:13:59',NULL,NULL,NULL,0,0,NULL),(408,36,'岳西县',3,4,'.1.3.36.408.','2021-03-17 21:13:59',NULL,NULL,NULL,0,0,NULL),(409,37,'中市区',3,4,'.1.3.37.409.','2021-03-17 21:13:59',NULL,NULL,NULL,0,0,NULL),(410,37,'东市区',3,4,'.1.3.37.410.','2021-03-17 21:13:59',NULL,NULL,NULL,0,0,NULL),(411,37,'西市区',3,4,'.1.3.37.411.','2021-03-17 21:13:59',NULL,NULL,NULL,0,0,NULL),(412,37,'郊区',3,4,'.1.3.37.412.','2021-03-17 21:13:59',NULL,NULL,NULL,0,0,NULL),(413,37,'怀远县',3,4,'.1.3.37.413.','2021-03-17 21:13:59',NULL,NULL,NULL,0,0,NULL),(414,37,'五河县',3,4,'.1.3.37.414.','2021-03-17 21:13:59',NULL,NULL,NULL,0,0,NULL),(415,37,'固镇县',3,4,'.1.3.37.415.','2021-03-17 21:13:59',NULL,NULL,NULL,0,0,NULL),(416,38,'居巢区',3,4,'.1.3.38.416.','2021-03-17 21:13:59',NULL,NULL,NULL,0,0,NULL),(417,38,'庐江县',3,4,'.1.3.38.417.','2021-03-17 21:14:00',NULL,NULL,NULL,0,0,NULL),(418,38,'无为县',3,4,'.1.3.38.418.','2021-03-17 21:14:00',NULL,NULL,NULL,0,0,NULL),(419,38,'含山县',3,4,'.1.3.38.419.','2021-03-17 21:14:00',NULL,NULL,NULL,0,0,NULL),(420,38,'和县',3,4,'.1.3.38.420.','2021-03-17 21:14:00',NULL,NULL,NULL,0,0,NULL),(421,39,'贵池区',3,4,'.1.3.39.421.','2021-03-17 21:14:00',NULL,NULL,NULL,0,0,NULL),(422,39,'东至县',3,4,'.1.3.39.422.','2021-03-17 21:14:00',NULL,NULL,NULL,0,0,NULL),(423,39,'石台县',3,4,'.1.3.39.423.','2021-03-17 21:14:00',NULL,NULL,NULL,0,0,NULL),(424,39,'青阳县',3,4,'.1.3.39.424.','2021-03-17 21:14:00',NULL,NULL,NULL,0,0,NULL),(425,40,'琅琊区',3,4,'.1.3.40.425.','2021-03-17 21:14:00',NULL,NULL,NULL,0,0,NULL),(426,40,'南谯区',3,4,'.1.3.40.426.','2021-03-17 21:14:01',NULL,NULL,NULL,0,0,NULL),(427,40,'天长市',3,4,'.1.3.40.427.','2021-03-17 21:14:01',NULL,NULL,NULL,0,0,NULL),(428,40,'明光市',3,4,'.1.3.40.428.','2021-03-17 21:14:01',NULL,NULL,NULL,0,0,NULL),(429,40,'来安县',3,4,'.1.3.40.429.','2021-03-17 21:14:02',NULL,NULL,NULL,0,0,NULL),(430,40,'全椒县',3,4,'.1.3.40.430.','2021-03-17 21:14:02',NULL,NULL,NULL,0,0,NULL),(431,40,'定远县',3,4,'.1.3.40.431.','2021-03-17 21:14:02',NULL,NULL,NULL,0,0,NULL),(432,40,'凤阳县',3,4,'.1.3.40.432.','2021-03-17 21:14:02',NULL,NULL,NULL,0,0,NULL),(433,41,'蚌山区',3,4,'.1.3.41.433.','2021-03-17 21:14:02',NULL,NULL,NULL,0,0,NULL),(434,41,'龙子湖区',3,4,'.1.3.41.434.','2021-03-17 21:14:02',NULL,NULL,NULL,0,0,NULL),(435,41,'禹会区',3,4,'.1.3.41.435.','2021-03-17 21:14:02',NULL,NULL,NULL,0,0,NULL),(436,41,'淮上区',3,4,'.1.3.41.436.','2021-03-17 21:14:02',NULL,NULL,NULL,0,0,NULL),(437,41,'颍州区',3,4,'.1.3.41.437.','2021-03-17 21:14:02',NULL,NULL,NULL,0,0,NULL),(438,41,'颍东区',3,4,'.1.3.41.438.','2021-03-17 21:14:02',NULL,NULL,NULL,0,0,NULL),(439,41,'颍泉区',3,4,'.1.3.41.439.','2021-03-17 21:14:02',NULL,NULL,NULL,0,0,NULL),(440,41,'界首市',3,4,'.1.3.41.440.','2021-03-17 21:14:02',NULL,NULL,NULL,0,0,NULL),(441,41,'临泉县',3,4,'.1.3.41.441.','2021-03-17 21:14:05',NULL,NULL,NULL,0,0,NULL),(442,41,'太和县',3,4,'.1.3.41.442.','2021-03-17 21:14:06',NULL,NULL,NULL,0,0,NULL),(443,41,'阜南县',3,4,'.1.3.41.443.','2021-03-17 21:14:06',NULL,NULL,NULL,0,0,NULL),(444,41,'颖上县',3,4,'.1.3.41.444.','2021-03-17 21:14:06',NULL,NULL,NULL,0,0,NULL),(445,42,'相山区',3,4,'.1.3.42.445.','2021-03-17 21:14:06',NULL,NULL,NULL,0,0,NULL),(446,42,'杜集区',3,4,'.1.3.42.446.','2021-03-17 21:14:06',NULL,NULL,NULL,0,0,NULL),(447,42,'烈山区',3,4,'.1.3.42.447.','2021-03-17 21:14:06',NULL,NULL,NULL,0,0,NULL),(448,42,'濉溪县',3,4,'.1.3.42.448.','2021-03-17 21:14:06',NULL,NULL,NULL,0,0,NULL),(449,43,'田家庵区',3,4,'.1.3.43.449.','2021-03-17 21:14:06',NULL,NULL,NULL,0,0,NULL),(450,43,'大通区',3,4,'.1.3.43.450.','2021-03-17 21:14:06',NULL,NULL,NULL,0,0,NULL),(451,43,'谢家集区',3,4,'.1.3.43.451.','2021-03-17 21:14:06',NULL,NULL,NULL,0,0,NULL),(452,43,'八公山区',3,4,'.1.3.43.452.','2021-03-17 21:14:06',NULL,NULL,NULL,0,0,NULL),(453,43,'潘集区',3,4,'.1.3.43.453.','2021-03-17 21:14:07',NULL,NULL,NULL,0,0,NULL),(454,43,'凤台县',3,4,'.1.3.43.454.','2021-03-17 21:14:07',NULL,NULL,NULL,0,0,NULL),(455,44,'屯溪区',3,4,'.1.3.44.455.','2021-03-17 21:14:07',NULL,NULL,NULL,0,0,NULL),(456,44,'黄山区',3,4,'.1.3.44.456.','2021-03-17 21:14:07',NULL,NULL,NULL,0,0,NULL),(457,44,'徽州区',3,4,'.1.3.44.457.','2021-03-17 21:14:07',NULL,NULL,NULL,0,0,NULL),(458,44,'歙县',3,4,'.1.3.44.458.','2021-03-17 21:14:07',NULL,NULL,NULL,0,0,NULL),(459,44,'休宁县',3,4,'.1.3.44.459.','2021-03-17 21:14:07',NULL,NULL,NULL,0,0,NULL),(460,44,'黟县',3,4,'.1.3.44.460.','2021-03-17 21:14:07',NULL,NULL,NULL,0,0,NULL),(461,44,'祁门县',3,4,'.1.3.44.461.','2021-03-17 21:14:08',NULL,NULL,NULL,0,0,NULL),(462,45,'金安区',3,4,'.1.3.45.462.','2021-03-17 21:14:08',NULL,NULL,NULL,0,0,NULL),(463,45,'裕安区',3,4,'.1.3.45.463.','2021-03-17 21:14:08',NULL,NULL,NULL,0,0,NULL),(464,45,'寿县',3,4,'.1.3.45.464.','2021-03-17 21:14:08',NULL,NULL,NULL,0,0,NULL),(465,45,'霍邱县',3,4,'.1.3.45.465.','2021-03-17 21:14:08',NULL,NULL,NULL,0,0,NULL),(466,45,'舒城县',3,4,'.1.3.45.466.','2021-03-17 21:14:08',NULL,NULL,NULL,0,0,NULL),(467,45,'金寨县',3,4,'.1.3.45.467.','2021-03-17 21:14:08',NULL,NULL,NULL,0,0,NULL),(468,45,'霍山县',3,4,'.1.3.45.468.','2021-03-17 21:14:08',NULL,NULL,NULL,0,0,NULL),(469,46,'雨山区',3,4,'.1.3.46.469.','2021-03-17 21:14:08',NULL,NULL,NULL,0,0,NULL),(470,46,'花山区',3,4,'.1.3.46.470.','2021-03-17 21:14:08',NULL,NULL,NULL,0,0,NULL),(471,46,'金家庄区',3,4,'.1.3.46.471.','2021-03-17 21:14:09',NULL,NULL,NULL,0,0,NULL),(472,46,'当涂县',3,4,'.1.3.46.472.','2021-03-17 21:14:09',NULL,NULL,NULL,0,0,NULL),(473,47,'埇桥区',3,4,'.1.3.47.473.','2021-03-17 21:14:09',NULL,NULL,NULL,0,0,NULL),(474,47,'砀山县',3,4,'.1.3.47.474.','2021-03-17 21:14:09',NULL,NULL,NULL,0,0,NULL),(475,47,'萧县',3,4,'.1.3.47.475.','2021-03-17 21:14:09',NULL,NULL,NULL,0,0,NULL),(476,47,'灵璧县',3,4,'.1.3.47.476.','2021-03-17 21:14:09',NULL,NULL,NULL,0,0,NULL),(477,47,'泗县',3,4,'.1.3.47.477.','2021-03-17 21:14:09',NULL,NULL,NULL,0,0,NULL),(478,48,'铜官山区',3,4,'.1.3.48.478.','2021-03-17 21:14:09',NULL,NULL,NULL,0,0,NULL),(479,48,'狮子山区',3,4,'.1.3.48.479.','2021-03-17 21:14:09',NULL,NULL,NULL,0,0,NULL),(480,48,'郊区',3,4,'.1.3.48.480.','2021-03-17 21:14:09',NULL,NULL,NULL,0,0,NULL),(481,48,'铜陵县',3,4,'.1.3.48.481.','2021-03-17 21:14:09',NULL,NULL,NULL,0,0,NULL),(482,49,'镜湖区',3,4,'.1.3.49.482.','2021-03-17 21:14:09',NULL,NULL,NULL,0,0,NULL),(483,49,'弋江区',3,4,'.1.3.49.483.','2021-03-17 21:14:09',NULL,NULL,NULL,0,0,NULL),(484,49,'鸠江区',3,4,'.1.3.49.484.','2021-03-17 21:14:09',NULL,NULL,NULL,0,0,NULL),(485,49,'三山区',3,4,'.1.3.49.485.','2021-03-17 21:14:09',NULL,NULL,NULL,0,0,NULL),(486,49,'芜湖县',3,4,'.1.3.49.486.','2021-03-17 21:14:09',NULL,NULL,NULL,0,0,NULL),(487,49,'繁昌县',3,4,'.1.3.49.487.','2021-03-17 21:14:09',NULL,NULL,NULL,0,0,NULL),(488,49,'南陵县',3,4,'.1.3.49.488.','2021-03-17 21:14:09',NULL,NULL,NULL,0,0,NULL),(489,50,'宣州区',3,4,'.1.3.50.489.','2021-03-17 21:14:09',NULL,NULL,NULL,0,0,NULL),(490,50,'宁国市',3,4,'.1.3.50.490.','2021-03-17 21:14:10',NULL,NULL,NULL,0,0,NULL),(491,50,'郎溪县',3,4,'.1.3.50.491.','2021-03-17 21:14:10',NULL,NULL,NULL,0,0,NULL),(492,50,'广德县',3,4,'.1.3.50.492.','2021-03-17 21:14:10',NULL,NULL,NULL,0,0,NULL),(493,50,'泾县',3,4,'.1.3.50.493.','2021-03-17 21:14:10',NULL,NULL,NULL,0,0,NULL),(494,50,'绩溪县',3,4,'.1.3.50.494.','2021-03-17 21:14:10',NULL,NULL,NULL,0,0,NULL),(495,50,'旌德县',3,4,'.1.3.50.495.','2021-03-17 21:14:10',NULL,NULL,NULL,0,0,NULL),(496,51,'涡阳县',3,4,'.1.3.51.496.','2021-03-17 21:14:10',NULL,NULL,NULL,0,0,NULL),(497,51,'蒙城县',3,4,'.1.3.51.497.','2021-03-17 21:14:10',NULL,NULL,NULL,0,0,NULL),(498,51,'利辛县',3,4,'.1.3.51.498.','2021-03-17 21:14:10',NULL,NULL,NULL,0,0,NULL),(499,51,'谯城区',3,4,'.1.3.51.499.','2021-03-17 21:14:10',NULL,NULL,NULL,0,0,NULL),(500,52,'东城区',3,4,'.1.2.52.500.','2021-03-17 21:14:10',NULL,NULL,NULL,0,0,NULL),(501,52,'西城区',3,4,'.1.2.52.501.','2021-03-17 21:14:10',NULL,NULL,NULL,0,0,NULL),(502,52,'海淀区',3,4,'.1.2.52.502.','2021-03-17 21:14:10',NULL,NULL,NULL,0,0,NULL),(503,52,'朝阳区',3,4,'.1.2.52.503.','2021-03-17 21:14:10',NULL,NULL,NULL,0,0,NULL),(504,52,'崇文区',3,4,'.1.2.52.504.','2021-03-17 21:14:10',NULL,NULL,NULL,0,0,NULL),(505,52,'宣武区',3,4,'.1.2.52.505.','2021-03-17 21:14:10',NULL,NULL,NULL,0,0,NULL),(506,52,'丰台区',3,4,'.1.2.52.506.','2021-03-17 21:14:10',NULL,NULL,NULL,0,0,NULL),(507,52,'石景山区',3,4,'.1.2.52.507.','2021-03-17 21:14:11',NULL,NULL,NULL,0,0,NULL),(508,52,'房山区',3,4,'.1.2.52.508.','2021-03-17 21:14:11',NULL,NULL,NULL,0,0,NULL),(509,52,'门头沟区',3,4,'.1.2.52.509.','2021-03-17 21:14:11',NULL,NULL,NULL,0,0,NULL),(510,52,'通州区',3,4,'.1.2.52.510.','2021-03-17 21:14:11',NULL,NULL,NULL,0,0,NULL),(511,52,'顺义区',3,4,'.1.2.52.511.','2021-03-17 21:14:11',NULL,NULL,NULL,0,0,NULL),(512,52,'昌平区',3,4,'.1.2.52.512.','2021-03-17 21:14:12',NULL,NULL,NULL,0,0,NULL),(513,52,'怀柔区',3,4,'.1.2.52.513.','2021-03-17 21:14:12',NULL,NULL,NULL,0,0,NULL),(514,52,'平谷区',3,4,'.1.2.52.514.','2021-03-17 21:14:12',NULL,NULL,NULL,0,0,NULL),(515,52,'大兴区',3,4,'.1.2.52.515.','2021-03-17 21:14:12',NULL,NULL,NULL,0,0,NULL),(516,52,'密云县',3,4,'.1.2.52.516.','2021-03-17 21:14:12',NULL,NULL,NULL,0,0,NULL),(517,52,'延庆县',3,4,'.1.2.52.517.','2021-03-17 21:14:12',NULL,NULL,NULL,0,0,NULL),(518,53,'鼓楼区',3,4,'.1.4.53.518.','2021-03-17 21:14:12',NULL,NULL,NULL,0,0,NULL),(519,53,'台江区',3,4,'.1.4.53.519.','2021-03-17 21:14:12',NULL,NULL,NULL,0,0,NULL),(520,53,'仓山区',3,4,'.1.4.53.520.','2021-03-17 21:14:12',NULL,NULL,NULL,0,0,NULL),(521,53,'马尾区',3,4,'.1.4.53.521.','2021-03-17 21:14:12',NULL,NULL,NULL,0,0,NULL),(522,53,'晋安区',3,4,'.1.4.53.522.','2021-03-17 21:14:12',NULL,NULL,NULL,0,0,NULL),(523,53,'福清市',3,4,'.1.4.53.523.','2021-03-17 21:14:12',NULL,NULL,NULL,0,0,NULL),(524,53,'长乐市',3,4,'.1.4.53.524.','2021-03-17 21:14:12',NULL,NULL,NULL,0,0,NULL),(525,53,'闽侯县',3,4,'.1.4.53.525.','2021-03-17 21:14:12',NULL,NULL,NULL,0,0,NULL),(526,53,'连江县',3,4,'.1.4.53.526.','2021-03-17 21:14:12',NULL,NULL,NULL,0,0,NULL),(527,53,'罗源县',3,4,'.1.4.53.527.','2021-03-17 21:14:12',NULL,NULL,NULL,0,0,NULL),(528,53,'闽清县',3,4,'.1.4.53.528.','2021-03-17 21:14:12',NULL,NULL,NULL,0,0,NULL),(529,53,'永泰县',3,4,'.1.4.53.529.','2021-03-17 21:14:12',NULL,NULL,NULL,0,0,NULL),(530,53,'平潭县',3,4,'.1.4.53.530.','2021-03-17 21:14:12',NULL,NULL,NULL,0,0,NULL),(531,54,'新罗区',3,4,'.1.4.54.531.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(532,54,'漳平市',3,4,'.1.4.54.532.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(533,54,'长汀县',3,4,'.1.4.54.533.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(534,54,'永定县',3,4,'.1.4.54.534.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(535,54,'上杭县',3,4,'.1.4.54.535.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(536,54,'武平县',3,4,'.1.4.54.536.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(537,54,'连城县',3,4,'.1.4.54.537.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(538,55,'延平区',3,4,'.1.4.55.538.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(539,55,'邵武市',3,4,'.1.4.55.539.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(540,55,'武夷山市',3,4,'.1.4.55.540.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(541,55,'建瓯市',3,4,'.1.4.55.541.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(542,55,'建阳市',3,4,'.1.4.55.542.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(543,55,'顺昌县',3,4,'.1.4.55.543.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(544,55,'浦城县',3,4,'.1.4.55.544.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(545,55,'光泽县',3,4,'.1.4.55.545.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(546,55,'松溪县',3,4,'.1.4.55.546.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(547,55,'政和县',3,4,'.1.4.55.547.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(548,56,'蕉城区',3,4,'.1.4.56.548.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(549,56,'福安市',3,4,'.1.4.56.549.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(550,56,'福鼎市',3,4,'.1.4.56.550.','2021-03-17 21:14:13',NULL,NULL,NULL,0,0,NULL),(551,56,'霞浦县',3,4,'.1.4.56.551.','2021-03-17 21:14:14',NULL,NULL,NULL,0,0,NULL),(552,56,'古田县',3,4,'.1.4.56.552.','2021-03-17 21:14:14',NULL,NULL,NULL,0,0,NULL),(553,56,'屏南县',3,4,'.1.4.56.553.','2021-03-17 21:14:14',NULL,NULL,NULL,0,0,NULL),(554,56,'寿宁县',3,4,'.1.4.56.554.','2021-03-17 21:14:14',NULL,NULL,NULL,0,0,NULL),(555,56,'周宁县',3,4,'.1.4.56.555.','2021-03-17 21:14:15',NULL,NULL,NULL,0,0,NULL),(556,56,'柘荣县',3,4,'.1.4.56.556.','2021-03-17 21:14:15',NULL,NULL,NULL,0,0,NULL),(557,57,'城厢区',3,4,'.1.4.57.557.','2021-03-17 21:14:15',NULL,NULL,NULL,0,0,NULL),(558,57,'涵江区',3,4,'.1.4.57.558.','2021-03-17 21:14:15',NULL,NULL,NULL,0,0,NULL),(559,57,'荔城区',3,4,'.1.4.57.559.','2021-03-17 21:14:15',NULL,NULL,NULL,0,0,NULL),(560,57,'秀屿区',3,4,'.1.4.57.560.','2021-03-17 21:14:15',NULL,NULL,NULL,0,0,NULL),(561,57,'仙游县',3,4,'.1.4.57.561.','2021-03-17 21:14:15',NULL,NULL,NULL,0,0,NULL),(562,58,'鲤城区',3,4,'.1.4.58.562.','2021-03-17 21:14:15',NULL,NULL,NULL,0,0,NULL),(563,58,'丰泽区',3,4,'.1.4.58.563.','2021-03-17 21:14:15',NULL,NULL,NULL,0,0,NULL),(564,58,'洛江区',3,4,'.1.4.58.564.','2021-03-17 21:14:15',NULL,NULL,NULL,0,0,NULL),(565,58,'清濛开发区',3,4,'.1.4.58.565.','2021-03-17 21:14:15',NULL,NULL,NULL,0,0,NULL),(566,58,'泉港区',3,4,'.1.4.58.566.','2021-03-17 21:14:15',NULL,NULL,NULL,0,0,NULL),(567,58,'石狮市',3,4,'.1.4.58.567.','2021-03-17 21:14:15',NULL,NULL,NULL,0,0,NULL),(568,58,'晋江市',3,4,'.1.4.58.568.','2021-03-17 21:14:15',NULL,NULL,NULL,0,0,NULL),(569,58,'南安市',3,4,'.1.4.58.569.','2021-03-17 21:14:15',NULL,NULL,NULL,0,0,NULL),(570,58,'惠安县',3,4,'.1.4.58.570.','2021-03-17 21:14:15',NULL,NULL,NULL,0,0,NULL),(571,58,'安溪县',3,4,'.1.4.58.571.','2021-03-17 21:14:15',NULL,NULL,NULL,0,0,NULL),(572,58,'永春县',3,4,'.1.4.58.572.','2021-03-17 21:14:15',NULL,NULL,NULL,0,0,NULL),(573,58,'德化县',3,4,'.1.4.58.573.','2021-03-17 21:14:15',NULL,NULL,NULL,0,0,NULL),(574,58,'金门县',3,4,'.1.4.58.574.','2021-03-17 21:14:16',NULL,NULL,NULL,0,0,NULL),(575,59,'梅列区',3,4,'.1.4.59.575.','2021-03-17 21:14:16',NULL,NULL,NULL,0,0,NULL),(576,59,'三元区',3,4,'.1.4.59.576.','2021-03-17 21:14:16',NULL,NULL,NULL,0,0,NULL),(577,59,'永安市',3,4,'.1.4.59.577.','2021-03-17 21:14:16',NULL,NULL,NULL,0,0,NULL),(578,59,'明溪县',3,4,'.1.4.59.578.','2021-03-17 21:14:16',NULL,NULL,NULL,0,0,NULL),(579,59,'清流县',3,4,'.1.4.59.579.','2021-03-17 21:14:16',NULL,NULL,NULL,0,0,NULL),(580,59,'宁化县',3,4,'.1.4.59.580.','2021-03-17 21:14:16',NULL,NULL,NULL,0,0,NULL),(581,59,'大田县',3,4,'.1.4.59.581.','2021-03-17 21:14:16',NULL,NULL,NULL,0,0,NULL),(582,59,'尤溪县',3,4,'.1.4.59.582.','2021-03-17 21:14:16',NULL,NULL,NULL,0,0,NULL),(583,59,'沙县',3,4,'.1.4.59.583.','2021-03-17 21:14:16',NULL,NULL,NULL,0,0,NULL),(584,59,'将乐县',3,4,'.1.4.59.584.','2021-03-17 21:14:16',NULL,NULL,NULL,0,0,NULL),(585,59,'泰宁县',3,4,'.1.4.59.585.','2021-03-17 21:14:16',NULL,NULL,NULL,0,0,NULL),(586,59,'建宁县',3,4,'.1.4.59.586.','2021-03-17 21:14:16',NULL,NULL,NULL,0,0,NULL),(587,60,'思明区',3,4,'.1.4.60.587.','2021-03-17 21:14:16',NULL,NULL,NULL,0,0,NULL),(588,60,'海沧区',3,4,'.1.4.60.588.','2021-03-17 21:14:16',NULL,NULL,NULL,0,0,NULL),(589,60,'湖里区',3,4,'.1.4.60.589.','2021-03-17 21:14:16',NULL,NULL,NULL,0,0,NULL),(590,60,'集美区',3,4,'.1.4.60.590.','2021-03-17 21:14:16',NULL,NULL,NULL,0,0,NULL),(591,60,'同安区',3,4,'.1.4.60.591.','2021-03-17 21:14:16',NULL,NULL,NULL,0,0,NULL),(592,60,'翔安区',3,4,'.1.4.60.592.','2021-03-17 21:14:17',NULL,NULL,NULL,0,0,NULL),(593,61,'芗城区',3,4,'.1.4.61.593.','2021-03-17 21:14:17',NULL,NULL,NULL,0,0,NULL),(594,61,'龙文区',3,4,'.1.4.61.594.','2021-03-17 21:14:17',NULL,NULL,NULL,0,0,NULL),(595,61,'龙海市',3,4,'.1.4.61.595.','2021-03-17 21:14:17',NULL,NULL,NULL,0,0,NULL),(596,61,'云霄县',3,4,'.1.4.61.596.','2021-03-17 21:14:17',NULL,NULL,NULL,0,0,NULL),(597,61,'漳浦县',3,4,'.1.4.61.597.','2021-03-17 21:14:17',NULL,NULL,NULL,0,0,NULL),(598,61,'诏安县',3,4,'.1.4.61.598.','2021-03-17 21:14:17',NULL,NULL,NULL,0,0,NULL),(599,61,'长泰县',3,4,'.1.4.61.599.','2021-03-17 21:14:17',NULL,NULL,NULL,0,0,NULL),(600,61,'东山县',3,4,'.1.4.61.600.','2021-03-17 21:14:17',NULL,NULL,NULL,0,0,NULL),(601,61,'南靖县',3,4,'.1.4.61.601.','2021-03-17 21:14:17',NULL,NULL,NULL,0,0,NULL),(602,61,'平和县',3,4,'.1.4.61.602.','2021-03-17 21:14:19',NULL,NULL,NULL,0,0,NULL),(603,61,'华安县',3,4,'.1.4.61.603.','2021-03-17 21:14:20',NULL,NULL,NULL,0,0,NULL),(604,62,'皋兰县',3,4,'.1.5.62.604.','2021-03-17 21:14:20',NULL,NULL,NULL,0,0,NULL),(605,62,'城关区',3,4,'.1.5.62.605.','2021-03-17 21:14:20',NULL,NULL,NULL,0,0,NULL),(606,62,'七里河区',3,4,'.1.5.62.606.','2021-03-17 21:14:20',NULL,NULL,NULL,0,0,NULL),(607,62,'西固区',3,4,'.1.5.62.607.','2021-03-17 21:14:20',NULL,NULL,NULL,0,0,NULL),(608,62,'安宁区',3,4,'.1.5.62.608.','2021-03-17 21:14:20',NULL,NULL,NULL,0,0,NULL),(609,62,'红古区',3,4,'.1.5.62.609.','2021-03-17 21:14:20',NULL,NULL,NULL,0,0,NULL),(610,62,'永登县',3,4,'.1.5.62.610.','2021-03-17 21:14:20',NULL,NULL,NULL,0,0,NULL),(611,62,'榆中县',3,4,'.1.5.62.611.','2021-03-17 21:14:20',NULL,NULL,NULL,0,0,NULL),(612,63,'白银区',3,4,'.1.5.63.612.','2021-03-17 21:14:20',NULL,NULL,NULL,0,0,NULL),(613,63,'平川区',3,4,'.1.5.63.613.','2021-03-17 21:14:21',NULL,NULL,NULL,0,0,NULL),(614,63,'会宁县',3,4,'.1.5.63.614.','2021-03-17 21:14:21',NULL,NULL,NULL,0,0,NULL),(615,63,'景泰县',3,4,'.1.5.63.615.','2021-03-17 21:14:21',NULL,NULL,NULL,0,0,NULL),(616,63,'靖远县',3,4,'.1.5.63.616.','2021-03-17 21:14:21',NULL,NULL,NULL,0,0,NULL),(617,64,'临洮县',3,4,'.1.5.64.617.','2021-03-17 21:14:21',NULL,NULL,NULL,0,0,NULL),(618,64,'陇西县',3,4,'.1.5.64.618.','2021-03-17 21:14:21',NULL,NULL,NULL,0,0,NULL),(619,64,'通渭县',3,4,'.1.5.64.619.','2021-03-17 21:14:21',NULL,NULL,NULL,0,0,NULL),(620,64,'渭源县',3,4,'.1.5.64.620.','2021-03-17 21:14:21',NULL,NULL,NULL,0,0,NULL),(621,64,'漳县',3,4,'.1.5.64.621.','2021-03-17 21:14:21',NULL,NULL,NULL,0,0,NULL),(622,64,'岷县',3,4,'.1.5.64.622.','2021-03-17 21:14:21',NULL,NULL,NULL,0,0,NULL),(623,64,'安定区',3,4,'.1.5.64.623.','2021-03-17 21:14:21',NULL,NULL,NULL,0,0,NULL),(624,64,'安定区',3,4,'.1.5.64.624.','2021-03-17 21:14:21',NULL,NULL,NULL,0,0,NULL),(625,65,'合作市',3,4,'.1.5.65.625.','2021-03-17 21:14:21',NULL,NULL,NULL,0,0,NULL),(626,65,'临潭县',3,4,'.1.5.65.626.','2021-03-17 21:14:21',NULL,NULL,NULL,0,0,NULL),(627,65,'卓尼县',3,4,'.1.5.65.627.','2021-03-17 21:14:21',NULL,NULL,NULL,0,0,NULL),(628,65,'舟曲县',3,4,'.1.5.65.628.','2021-03-17 21:14:22',NULL,NULL,NULL,0,0,NULL),(629,65,'迭部县',3,4,'.1.5.65.629.','2021-03-17 21:14:22',NULL,NULL,NULL,0,0,NULL),(630,65,'玛曲县',3,4,'.1.5.65.630.','2021-03-17 21:14:22',NULL,NULL,NULL,0,0,NULL),(631,65,'碌曲县',3,4,'.1.5.65.631.','2021-03-17 21:14:22',NULL,NULL,NULL,0,0,NULL),(632,65,'夏河县',3,4,'.1.5.65.632.','2021-03-17 21:14:22',NULL,NULL,NULL,0,0,NULL),(633,66,'嘉峪关市',3,4,'.1.5.66.633.','2021-03-17 21:14:22',NULL,NULL,NULL,0,0,NULL),(634,67,'金川区',3,4,'.1.5.67.634.','2021-03-17 21:14:23',NULL,NULL,NULL,0,0,NULL),(635,67,'永昌县',3,4,'.1.5.67.635.','2021-03-17 21:14:23',NULL,NULL,NULL,0,0,NULL),(636,68,'肃州区',3,4,'.1.5.68.636.','2021-03-17 21:14:23',NULL,NULL,NULL,0,0,NULL),(637,68,'玉门市',3,4,'.1.5.68.637.','2021-03-17 21:14:23',NULL,NULL,NULL,0,0,NULL),(638,68,'敦煌市',3,4,'.1.5.68.638.','2021-03-17 21:14:23',NULL,NULL,NULL,0,0,NULL),(639,68,'金塔县',3,4,'.1.5.68.639.','2021-03-17 21:14:23',NULL,NULL,NULL,0,0,NULL),(640,68,'瓜州县',3,4,'.1.5.68.640.','2021-03-17 21:14:23',NULL,NULL,NULL,0,0,NULL),(641,68,'肃北',3,4,'.1.5.68.641.','2021-03-17 21:14:23',NULL,NULL,NULL,0,0,NULL),(642,68,'阿克塞',3,4,'.1.5.68.642.','2021-03-17 21:14:23',NULL,NULL,NULL,0,0,NULL),(643,69,'临夏市',3,4,'.1.5.69.643.','2021-03-17 21:14:23',NULL,NULL,NULL,0,0,NULL),(644,69,'临夏县',3,4,'.1.5.69.644.','2021-03-17 21:14:23',NULL,NULL,NULL,0,0,NULL),(645,69,'康乐县',3,4,'.1.5.69.645.','2021-03-17 21:14:23',NULL,NULL,NULL,0,0,NULL),(646,69,'永靖县',3,4,'.1.5.69.646.','2021-03-17 21:14:23',NULL,NULL,NULL,0,0,NULL),(647,69,'广河县',3,4,'.1.5.69.647.','2021-03-17 21:14:23',NULL,NULL,NULL,0,0,NULL),(648,69,'和政县',3,4,'.1.5.69.648.','2021-03-17 21:14:23',NULL,NULL,NULL,0,0,NULL),(649,69,'东乡族自治县',3,4,'.1.5.69.649.','2021-03-17 21:14:23',NULL,NULL,NULL,0,0,NULL),(650,69,'积石山',3,4,'.1.5.69.650.','2021-03-17 21:14:24',NULL,NULL,NULL,0,0,NULL),(651,70,'成县',3,4,'.1.5.70.651.','2021-03-17 21:14:24',NULL,NULL,NULL,0,0,NULL),(652,70,'徽县',3,4,'.1.5.70.652.','2021-03-17 21:14:24',NULL,NULL,NULL,0,0,NULL),(653,70,'康县',3,4,'.1.5.70.653.','2021-03-17 21:14:24',NULL,NULL,NULL,0,0,NULL),(654,70,'礼县',3,4,'.1.5.70.654.','2021-03-17 21:14:24',NULL,NULL,NULL,0,0,NULL),(655,70,'两当县',3,4,'.1.5.70.655.','2021-03-17 21:14:24',NULL,NULL,NULL,0,0,NULL),(656,70,'文县',3,4,'.1.5.70.656.','2021-03-17 21:14:24',NULL,NULL,NULL,0,0,NULL),(657,70,'西和县',3,4,'.1.5.70.657.','2021-03-17 21:14:24',NULL,NULL,NULL,0,0,NULL),(658,70,'宕昌县',3,4,'.1.5.70.658.','2021-03-17 21:14:24',NULL,NULL,NULL,0,0,NULL),(659,70,'武都区',3,4,'.1.5.70.659.','2021-03-17 21:14:24',NULL,NULL,NULL,0,0,NULL),(660,71,'崇信县',3,4,'.1.5.71.660.','2021-03-17 21:14:24',NULL,NULL,NULL,0,0,NULL),(661,71,'华亭县',3,4,'.1.5.71.661.','2021-03-17 21:14:24',NULL,NULL,NULL,0,0,NULL),(662,71,'静宁县',3,4,'.1.5.71.662.','2021-03-17 21:14:24',NULL,NULL,NULL,0,0,NULL),(663,71,'灵台县',3,4,'.1.5.71.663.','2021-03-17 21:14:24',NULL,NULL,NULL,0,0,NULL),(664,71,'崆峒区',3,4,'.1.5.71.664.','2021-03-17 21:14:24',NULL,NULL,NULL,0,0,NULL),(665,71,'庄浪县',3,4,'.1.5.71.665.','2021-03-17 21:14:24',NULL,NULL,NULL,0,0,NULL),(666,71,'泾川县',3,4,'.1.5.71.666.','2021-03-17 21:14:24',NULL,NULL,NULL,0,0,NULL),(667,72,'合水县',3,4,'.1.5.72.667.','2021-03-17 21:14:24',NULL,NULL,NULL,0,0,NULL),(668,72,'华池县',3,4,'.1.5.72.668.','2021-03-17 21:14:24',NULL,NULL,NULL,0,0,NULL),(669,72,'环县',3,4,'.1.5.72.669.','2021-03-17 21:14:25',NULL,NULL,NULL,0,0,NULL),(670,72,'宁县',3,4,'.1.5.72.670.','2021-03-17 21:14:25',NULL,NULL,NULL,0,0,NULL),(671,72,'庆城县',3,4,'.1.5.72.671.','2021-03-17 21:14:25',NULL,NULL,NULL,0,0,NULL),(672,72,'西峰区',3,4,'.1.5.72.672.','2021-03-17 21:14:25',NULL,NULL,NULL,0,0,NULL),(673,72,'镇原县',3,4,'.1.5.72.673.','2021-03-17 21:14:25',NULL,NULL,NULL,0,0,NULL),(674,72,'正宁县',3,4,'.1.5.72.674.','2021-03-17 21:14:25',NULL,NULL,NULL,0,0,NULL),(675,73,'甘谷县',3,4,'.1.5.73.675.','2021-03-17 21:14:25',NULL,NULL,NULL,0,0,NULL),(676,73,'秦安县',3,4,'.1.5.73.676.','2021-03-17 21:14:25',NULL,NULL,NULL,0,0,NULL),(677,73,'清水县',3,4,'.1.5.73.677.','2021-03-17 21:14:25',NULL,NULL,NULL,0,0,NULL),(678,73,'秦州区',3,4,'.1.5.73.678.','2021-03-17 21:14:25',NULL,NULL,NULL,0,0,NULL),(679,73,'麦积区',3,4,'.1.5.73.679.','2021-03-17 21:14:25',NULL,NULL,NULL,0,0,NULL),(680,73,'武山县',3,4,'.1.5.73.680.','2021-03-17 21:14:25',NULL,NULL,NULL,0,0,NULL),(681,73,'张家川',3,4,'.1.5.73.681.','2021-03-17 21:14:25',NULL,NULL,NULL,0,0,NULL),(682,74,'古浪县',3,4,'.1.5.74.682.','2021-03-17 21:14:26',NULL,NULL,NULL,0,0,NULL),(683,74,'民勤县',3,4,'.1.5.74.683.','2021-03-17 21:14:26',NULL,NULL,NULL,0,0,NULL),(684,74,'天祝',3,4,'.1.5.74.684.','2021-03-17 21:14:26',NULL,NULL,NULL,0,0,NULL),(685,74,'凉州区',3,4,'.1.5.74.685.','2021-03-17 21:14:26',NULL,NULL,NULL,0,0,NULL),(686,75,'高台县',3,4,'.1.5.75.686.','2021-03-17 21:14:26',NULL,NULL,NULL,0,0,NULL),(687,75,'临泽县',3,4,'.1.5.75.687.','2021-03-17 21:14:27',NULL,NULL,NULL,0,0,NULL),(688,75,'民乐县',3,4,'.1.5.75.688.','2021-03-17 21:14:27',NULL,NULL,NULL,0,0,NULL),(689,75,'山丹县',3,4,'.1.5.75.689.','2021-03-17 21:14:27',NULL,NULL,NULL,0,0,NULL),(690,75,'肃南',3,4,'.1.5.75.690.','2021-03-17 21:14:27',NULL,NULL,NULL,0,0,NULL),(691,75,'甘州区',3,4,'.1.5.75.691.','2021-03-17 21:14:27',NULL,NULL,NULL,0,0,NULL),(692,76,'从化市',3,4,'.1.6.76.692.','2021-03-17 21:14:27',NULL,NULL,NULL,0,0,NULL),(693,76,'天河区',3,4,'.1.6.76.693.','2021-03-17 21:14:27',NULL,NULL,NULL,0,0,NULL),(694,76,'东山区',3,4,'.1.6.76.694.','2021-03-17 21:14:27',NULL,NULL,NULL,0,0,NULL),(695,76,'白云区',3,4,'.1.6.76.695.','2021-03-17 21:14:27',NULL,NULL,NULL,0,0,NULL),(696,76,'海珠区',3,4,'.1.6.76.696.','2021-03-17 21:14:27',NULL,NULL,NULL,0,0,NULL),(697,76,'荔湾区',3,4,'.1.6.76.697.','2021-03-17 21:14:27',NULL,NULL,NULL,0,0,NULL),(698,76,'越秀区',3,4,'.1.6.76.698.','2021-03-17 21:14:27',NULL,NULL,NULL,0,0,NULL),(699,76,'黄埔区',3,4,'.1.6.76.699.','2021-03-17 21:14:27',NULL,NULL,NULL,0,0,NULL),(700,76,'番禺区',3,4,'.1.6.76.700.','2021-03-17 21:14:27',NULL,NULL,NULL,0,0,NULL),(701,76,'花都区',3,4,'.1.6.76.701.','2021-03-17 21:14:27',NULL,NULL,NULL,0,0,NULL),(702,76,'增城区',3,4,'.1.6.76.702.','2021-03-17 21:14:27',NULL,NULL,NULL,0,0,NULL),(703,76,'从化区',3,4,'.1.6.76.703.','2021-03-17 21:14:27',NULL,NULL,NULL,0,0,NULL),(704,76,'市郊',3,4,'.1.6.76.704.','2021-03-17 21:14:27',NULL,NULL,NULL,0,0,NULL),(705,77,'福田区',3,4,'.1.6.77.705.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(706,77,'罗湖区',3,4,'.1.6.77.706.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(707,77,'南山区',3,4,'.1.6.77.707.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(708,77,'宝安区',3,4,'.1.6.77.708.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(709,77,'龙岗区',3,4,'.1.6.77.709.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(710,77,'盐田区',3,4,'.1.6.77.710.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(711,78,'湘桥区',3,4,'.1.6.78.711.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(712,78,'潮安县',3,4,'.1.6.78.712.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(713,78,'饶平县',3,4,'.1.6.78.713.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(714,79,'南城区',3,4,'.1.6.79.714.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(715,79,'东城区',3,4,'.1.6.79.715.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(716,79,'万江区',3,4,'.1.6.79.716.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(717,79,'莞城区',3,4,'.1.6.79.717.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(718,79,'石龙镇',3,4,'.1.6.79.718.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(719,79,'虎门镇',3,4,'.1.6.79.719.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(720,79,'麻涌镇',3,4,'.1.6.79.720.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(721,79,'道滘镇',3,4,'.1.6.79.721.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(722,79,'石碣镇',3,4,'.1.6.79.722.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(723,79,'沙田镇',3,4,'.1.6.79.723.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(724,79,'望牛墩镇',3,4,'.1.6.79.724.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(725,79,'洪梅镇',3,4,'.1.6.79.725.','2021-03-17 21:14:28',NULL,NULL,NULL,0,0,NULL),(726,79,'茶山镇',3,4,'.1.6.79.726.','2021-03-17 21:14:29',NULL,NULL,NULL,0,0,NULL),(727,79,'寮步镇',3,4,'.1.6.79.727.','2021-03-17 21:14:29',NULL,NULL,NULL,0,0,NULL),(728,79,'大岭山镇',3,4,'.1.6.79.728.','2021-03-17 21:14:29',NULL,NULL,NULL,0,0,NULL),(729,79,'大朗镇',3,4,'.1.6.79.729.','2021-03-17 21:14:29',NULL,NULL,NULL,0,0,NULL),(730,79,'黄江镇',3,4,'.1.6.79.730.','2021-03-17 21:14:29',NULL,NULL,NULL,0,0,NULL),(731,79,'樟木头',3,4,'.1.6.79.731.','2021-03-17 21:14:29',NULL,NULL,NULL,0,0,NULL),(732,79,'凤岗镇',3,4,'.1.6.79.732.','2021-03-17 21:14:29',NULL,NULL,NULL,0,0,NULL),(733,79,'塘厦镇',3,4,'.1.6.79.733.','2021-03-17 21:14:33',NULL,NULL,NULL,0,0,NULL),(734,79,'谢岗镇',3,4,'.1.6.79.734.','2021-03-17 21:14:33',NULL,NULL,NULL,0,0,NULL),(735,79,'厚街镇',3,4,'.1.6.79.735.','2021-03-17 21:14:33',NULL,NULL,NULL,0,0,NULL),(736,79,'清溪镇',3,4,'.1.6.79.736.','2021-03-17 21:14:33',NULL,NULL,NULL,0,0,NULL),(737,79,'常平镇',3,4,'.1.6.79.737.','2021-03-17 21:14:33',NULL,NULL,NULL,0,0,NULL),(738,79,'桥头镇',3,4,'.1.6.79.738.','2021-03-17 21:14:33',NULL,NULL,NULL,0,0,NULL),(739,79,'横沥镇',3,4,'.1.6.79.739.','2021-03-17 21:14:33',NULL,NULL,NULL,0,0,NULL),(740,79,'东坑镇',3,4,'.1.6.79.740.','2021-03-17 21:14:33',NULL,NULL,NULL,0,0,NULL),(741,79,'企石镇',3,4,'.1.6.79.741.','2021-03-17 21:14:33',NULL,NULL,NULL,0,0,NULL),(742,79,'石排镇',3,4,'.1.6.79.742.','2021-03-17 21:14:33',NULL,NULL,NULL,0,0,NULL),(743,79,'长安镇',3,4,'.1.6.79.743.','2021-03-17 21:14:33',NULL,NULL,NULL,0,0,NULL),(744,79,'中堂镇',3,4,'.1.6.79.744.','2021-03-17 21:14:33',NULL,NULL,NULL,0,0,NULL),(745,79,'高埗镇',3,4,'.1.6.79.745.','2021-03-17 21:14:33',NULL,NULL,NULL,0,0,NULL),(746,80,'禅城区',3,4,'.1.6.80.746.','2021-03-17 21:14:33',NULL,NULL,NULL,0,0,NULL),(747,80,'南海区',3,4,'.1.6.80.747.','2021-03-17 21:14:33',NULL,NULL,NULL,0,0,NULL),(748,80,'顺德区',3,4,'.1.6.80.748.','2021-03-17 21:14:33',NULL,NULL,NULL,0,0,NULL),(749,80,'三水区',3,4,'.1.6.80.749.','2021-03-17 21:14:33',NULL,NULL,NULL,0,0,NULL),(750,80,'高明区',3,4,'.1.6.80.750.','2021-03-17 21:14:33',NULL,NULL,NULL,0,0,NULL),(751,81,'东源县',3,4,'.1.6.81.751.','2021-03-17 21:14:34',NULL,NULL,NULL,0,0,NULL),(752,81,'和平县',3,4,'.1.6.81.752.','2021-03-17 21:14:34',NULL,NULL,NULL,0,0,NULL),(753,81,'源城区',3,4,'.1.6.81.753.','2021-03-17 21:14:34',NULL,NULL,NULL,0,0,NULL),(754,81,'连平县',3,4,'.1.6.81.754.','2021-03-17 21:14:34',NULL,NULL,NULL,0,0,NULL),(755,81,'龙川县',3,4,'.1.6.81.755.','2021-03-17 21:14:34',NULL,NULL,NULL,0,0,NULL),(756,81,'紫金县',3,4,'.1.6.81.756.','2021-03-17 21:14:34',NULL,NULL,NULL,0,0,NULL),(757,82,'惠阳区',3,4,'.1.6.82.757.','2021-03-17 21:14:34',NULL,NULL,NULL,0,0,NULL),(758,82,'惠城区',3,4,'.1.6.82.758.','2021-03-17 21:14:34',NULL,NULL,NULL,0,0,NULL),(759,82,'大亚湾',3,4,'.1.6.82.759.','2021-03-17 21:14:34',NULL,NULL,NULL,0,0,NULL),(760,82,'博罗县',3,4,'.1.6.82.760.','2021-03-17 21:14:34',NULL,NULL,NULL,0,0,NULL),(761,82,'惠东县',3,4,'.1.6.82.761.','2021-03-17 21:14:34',NULL,NULL,NULL,0,0,NULL),(762,82,'龙门县',3,4,'.1.6.82.762.','2021-03-17 21:14:35',NULL,NULL,NULL,0,0,NULL),(763,83,'江海区',3,4,'.1.6.83.763.','2021-03-17 21:14:35',NULL,NULL,NULL,0,0,NULL),(764,83,'蓬江区',3,4,'.1.6.83.764.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(765,83,'新会区',3,4,'.1.6.83.765.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(766,83,'台山市',3,4,'.1.6.83.766.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(767,83,'开平市',3,4,'.1.6.83.767.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(768,83,'鹤山市',3,4,'.1.6.83.768.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(769,83,'恩平市',3,4,'.1.6.83.769.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(770,84,'榕城区',3,4,'.1.6.84.770.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(771,84,'普宁市',3,4,'.1.6.84.771.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(772,84,'揭东县',3,4,'.1.6.84.772.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(773,84,'揭西县',3,4,'.1.6.84.773.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(774,84,'惠来县',3,4,'.1.6.84.774.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(775,85,'茂南区',3,4,'.1.6.85.775.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(776,85,'茂港区',3,4,'.1.6.85.776.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(777,85,'高州市',3,4,'.1.6.85.777.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(778,85,'化州市',3,4,'.1.6.85.778.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(779,85,'信宜市',3,4,'.1.6.85.779.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(780,85,'电白县',3,4,'.1.6.85.780.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(781,86,'梅县',3,4,'.1.6.86.781.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(782,86,'梅江区',3,4,'.1.6.86.782.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(783,86,'兴宁市',3,4,'.1.6.86.783.','2021-03-17 21:14:36',NULL,NULL,NULL,0,0,NULL),(784,86,'大埔县',3,4,'.1.6.86.784.','2021-03-17 21:14:37',NULL,NULL,NULL,0,0,NULL),(785,86,'丰顺县',3,4,'.1.6.86.785.','2021-03-17 21:14:37',NULL,NULL,NULL,0,0,NULL),(786,86,'五华县',3,4,'.1.6.86.786.','2021-03-17 21:14:37',NULL,NULL,NULL,0,0,NULL),(787,86,'平远县',3,4,'.1.6.86.787.','2021-03-17 21:14:37',NULL,NULL,NULL,0,0,NULL),(788,86,'蕉岭县',3,4,'.1.6.86.788.','2021-03-17 21:14:37',NULL,NULL,NULL,0,0,NULL),(789,87,'清城区',3,4,'.1.6.87.789.','2021-03-17 21:14:37',NULL,NULL,NULL,0,0,NULL),(790,87,'英德市',3,4,'.1.6.87.790.','2021-03-17 21:14:37',NULL,NULL,NULL,0,0,NULL),(791,87,'连州市',3,4,'.1.6.87.791.','2021-03-17 21:14:37',NULL,NULL,NULL,0,0,NULL),(792,87,'佛冈县',3,4,'.1.6.87.792.','2021-03-17 21:14:37',NULL,NULL,NULL,0,0,NULL),(793,87,'阳山县',3,4,'.1.6.87.793.','2021-03-17 21:14:37',NULL,NULL,NULL,0,0,NULL),(794,87,'清新县',3,4,'.1.6.87.794.','2021-03-17 21:14:37',NULL,NULL,NULL,0,0,NULL),(795,87,'连山',3,4,'.1.6.87.795.','2021-03-17 21:14:37',NULL,NULL,NULL,0,0,NULL),(796,87,'连南',3,4,'.1.6.87.796.','2021-03-17 21:14:37',NULL,NULL,NULL,0,0,NULL),(797,88,'南澳县',3,4,'.1.6.88.797.','2021-03-17 21:14:37',NULL,NULL,NULL,0,0,NULL),(798,88,'潮阳区',3,4,'.1.6.88.798.','2021-03-17 21:14:37',NULL,NULL,NULL,0,0,NULL),(799,88,'澄海区',3,4,'.1.6.88.799.','2021-03-17 21:14:37',NULL,NULL,NULL,0,0,NULL),(800,88,'龙湖区',3,4,'.1.6.88.800.','2021-03-17 21:14:37',NULL,NULL,NULL,0,0,NULL),(801,88,'金平区',3,4,'.1.6.88.801.','2021-03-17 21:14:37',NULL,NULL,NULL,0,0,NULL),(802,88,'濠江区',3,4,'.1.6.88.802.','2021-03-17 21:14:38',NULL,NULL,NULL,0,0,NULL),(803,88,'潮南区',3,4,'.1.6.88.803.','2021-03-17 21:14:38',NULL,NULL,NULL,0,0,NULL),(804,89,'城区',3,4,'.1.6.89.804.','2021-03-17 21:14:38',NULL,NULL,NULL,0,0,NULL),(805,89,'陆丰市',3,4,'.1.6.89.805.','2021-03-17 21:14:38',NULL,NULL,NULL,0,0,NULL),(806,89,'海丰县',3,4,'.1.6.89.806.','2021-03-17 21:14:38',NULL,NULL,NULL,0,0,NULL),(807,89,'陆河县',3,4,'.1.6.89.807.','2021-03-17 21:14:38',NULL,NULL,NULL,0,0,NULL),(808,90,'曲江县',3,4,'.1.6.90.808.','2021-03-17 21:14:38',NULL,NULL,NULL,0,0,NULL),(809,90,'浈江区',3,4,'.1.6.90.809.','2021-03-17 21:14:38',NULL,NULL,NULL,0,0,NULL),(810,90,'武江区',3,4,'.1.6.90.810.','2021-03-17 21:14:39',NULL,NULL,NULL,0,0,NULL),(811,90,'曲江区',3,4,'.1.6.90.811.','2021-03-17 21:14:39',NULL,NULL,NULL,0,0,NULL),(812,90,'乐昌市',3,4,'.1.6.90.812.','2021-03-17 21:14:39',NULL,NULL,NULL,0,0,NULL),(813,90,'南雄市',3,4,'.1.6.90.813.','2021-03-17 21:14:39',NULL,NULL,NULL,0,0,NULL),(814,90,'始兴县',3,4,'.1.6.90.814.','2021-03-17 21:14:39',NULL,NULL,NULL,0,0,NULL),(815,90,'仁化县',3,4,'.1.6.90.815.','2021-03-17 21:14:39',NULL,NULL,NULL,0,0,NULL),(816,90,'翁源县',3,4,'.1.6.90.816.','2021-03-17 21:14:39',NULL,NULL,NULL,0,0,NULL),(817,90,'新丰县',3,4,'.1.6.90.817.','2021-03-17 21:14:39',NULL,NULL,NULL,0,0,NULL),(818,90,'乳源',3,4,'.1.6.90.818.','2021-03-17 21:14:39',NULL,NULL,NULL,0,0,NULL),(819,91,'江城区',3,4,'.1.6.91.819.','2021-03-17 21:14:39',NULL,NULL,NULL,0,0,NULL),(820,91,'阳春市',3,4,'.1.6.91.820.','2021-03-17 21:14:39',NULL,NULL,NULL,0,0,NULL),(821,91,'阳西县',3,4,'.1.6.91.821.','2021-03-17 21:14:39',NULL,NULL,NULL,0,0,NULL),(822,91,'阳东县',3,4,'.1.6.91.822.','2021-03-17 21:14:39',NULL,NULL,NULL,0,0,NULL),(823,92,'云城区',3,4,'.1.6.92.823.','2021-03-17 21:14:39',NULL,NULL,NULL,0,0,NULL),(824,92,'罗定市',3,4,'.1.6.92.824.','2021-03-17 21:14:39',NULL,NULL,NULL,0,0,NULL),(825,92,'新兴县',3,4,'.1.6.92.825.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(826,92,'郁南县',3,4,'.1.6.92.826.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(827,92,'云安县',3,4,'.1.6.92.827.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(828,93,'赤坎区',3,4,'.1.6.93.828.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(829,93,'霞山区',3,4,'.1.6.93.829.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(830,93,'坡头区',3,4,'.1.6.93.830.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(831,93,'麻章区',3,4,'.1.6.93.831.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(832,93,'廉江市',3,4,'.1.6.93.832.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(833,93,'雷州市',3,4,'.1.6.93.833.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(834,93,'吴川市',3,4,'.1.6.93.834.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(835,93,'遂溪县',3,4,'.1.6.93.835.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(836,93,'徐闻县',3,4,'.1.6.93.836.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(837,94,'肇庆市',3,4,'.1.6.94.837.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(838,94,'高要市',3,4,'.1.6.94.838.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(839,94,'四会市',3,4,'.1.6.94.839.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(840,94,'广宁县',3,4,'.1.6.94.840.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(841,94,'怀集县',3,4,'.1.6.94.841.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(842,94,'封开县',3,4,'.1.6.94.842.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(843,94,'德庆县',3,4,'.1.6.94.843.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(844,95,'石岐街道',3,4,'.1.6.95.844.','2021-03-17 21:14:40',NULL,NULL,NULL,0,0,NULL),(845,95,'东区街道',3,4,'.1.6.95.845.','2021-03-17 21:14:41',NULL,NULL,NULL,0,0,NULL),(846,95,'西区街道',3,4,'.1.6.95.846.','2021-03-17 21:14:41',NULL,NULL,NULL,0,0,NULL),(847,95,'环城街道',3,4,'.1.6.95.847.','2021-03-17 21:14:41',NULL,NULL,NULL,0,0,NULL),(848,95,'中山港街道',3,4,'.1.6.95.848.','2021-03-17 21:14:41',NULL,NULL,NULL,0,0,NULL),(849,95,'五桂山街道',3,4,'.1.6.95.849.','2021-03-17 21:14:41',NULL,NULL,NULL,0,0,NULL),(850,96,'香洲区',3,4,'.1.6.96.850.','2021-03-17 21:14:41',NULL,NULL,NULL,0,0,NULL),(851,96,'斗门区',3,4,'.1.6.96.851.','2021-03-17 21:14:42',NULL,NULL,NULL,0,0,NULL),(852,96,'金湾区',3,4,'.1.6.96.852.','2021-03-17 21:14:43',NULL,NULL,NULL,0,0,NULL),(853,97,'邕宁区',3,4,'.1.7.97.853.','2021-03-17 21:14:43',NULL,NULL,NULL,0,0,NULL),(854,97,'青秀区',3,4,'.1.7.97.854.','2021-03-17 21:14:43',NULL,NULL,NULL,0,0,NULL),(855,97,'兴宁区',3,4,'.1.7.97.855.','2021-03-17 21:14:43',NULL,NULL,NULL,0,0,NULL),(856,97,'良庆区',3,4,'.1.7.97.856.','2021-03-17 21:14:43',NULL,NULL,NULL,0,0,NULL),(857,97,'西乡塘区',3,4,'.1.7.97.857.','2021-03-17 21:14:44',NULL,NULL,NULL,0,0,NULL),(858,97,'江南区',3,4,'.1.7.97.858.','2021-03-17 21:14:44',NULL,NULL,NULL,0,0,NULL),(859,97,'武鸣县',3,4,'.1.7.97.859.','2021-03-17 21:14:44',NULL,NULL,NULL,0,0,NULL),(860,97,'隆安县',3,4,'.1.7.97.860.','2021-03-17 21:14:44',NULL,NULL,NULL,0,0,NULL),(861,97,'马山县',3,4,'.1.7.97.861.','2021-03-17 21:14:44',NULL,NULL,NULL,0,0,NULL),(862,97,'上林县',3,4,'.1.7.97.862.','2021-03-17 21:14:44',NULL,NULL,NULL,0,0,NULL),(863,97,'宾阳县',3,4,'.1.7.97.863.','2021-03-17 21:14:44',NULL,NULL,NULL,0,0,NULL),(864,97,'横县',3,4,'.1.7.97.864.','2021-03-17 21:14:44',NULL,NULL,NULL,0,0,NULL),(865,98,'秀峰区',3,4,'.1.7.98.865.','2021-03-17 21:14:44',NULL,NULL,NULL,0,0,NULL),(866,98,'叠彩区',3,4,'.1.7.98.866.','2021-03-17 21:14:44',NULL,NULL,NULL,0,0,NULL),(867,98,'象山区',3,4,'.1.7.98.867.','2021-03-17 21:14:44',NULL,NULL,NULL,0,0,NULL),(868,98,'七星区',3,4,'.1.7.98.868.','2021-03-17 21:14:44',NULL,NULL,NULL,0,0,NULL),(869,98,'雁山区',3,4,'.1.7.98.869.','2021-03-17 21:14:44',NULL,NULL,NULL,0,0,NULL),(870,98,'阳朔县',3,4,'.1.7.98.870.','2021-03-17 21:14:44',NULL,NULL,NULL,0,0,NULL),(871,98,'临桂县',3,4,'.1.7.98.871.','2021-03-17 21:14:44',NULL,NULL,NULL,0,0,NULL),(872,98,'灵川县',3,4,'.1.7.98.872.','2021-03-17 21:14:44',NULL,NULL,NULL,0,0,NULL),(873,98,'全州县',3,4,'.1.7.98.873.','2021-03-17 21:14:44',NULL,NULL,NULL,0,0,NULL),(874,98,'平乐县',3,4,'.1.7.98.874.','2021-03-17 21:14:44',NULL,NULL,NULL,0,0,NULL),(875,98,'兴安县',3,4,'.1.7.98.875.','2021-03-17 21:14:44',NULL,NULL,NULL,0,0,NULL),(876,98,'灌阳县',3,4,'.1.7.98.876.','2021-03-17 21:14:45',NULL,NULL,NULL,0,0,NULL),(877,98,'荔浦县',3,4,'.1.7.98.877.','2021-03-17 21:14:45',NULL,NULL,NULL,0,0,NULL),(878,98,'资源县',3,4,'.1.7.98.878.','2021-03-17 21:14:45',NULL,NULL,NULL,0,0,NULL),(879,98,'永福县',3,4,'.1.7.98.879.','2021-03-17 21:14:45',NULL,NULL,NULL,0,0,NULL),(880,98,'龙胜',3,4,'.1.7.98.880.','2021-03-17 21:14:45',NULL,NULL,NULL,0,0,NULL),(881,98,'恭城',3,4,'.1.7.98.881.','2021-03-17 21:14:45',NULL,NULL,NULL,0,0,NULL),(882,99,'右江区',3,4,'.1.7.99.882.','2021-03-17 21:14:45',NULL,NULL,NULL,0,0,NULL),(883,99,'凌云县',3,4,'.1.7.99.883.','2021-03-17 21:14:45',NULL,NULL,NULL,0,0,NULL),(884,99,'平果县',3,4,'.1.7.99.884.','2021-03-17 21:14:45',NULL,NULL,NULL,0,0,NULL),(885,99,'西林县',3,4,'.1.7.99.885.','2021-03-17 21:14:45',NULL,NULL,NULL,0,0,NULL),(886,99,'乐业县',3,4,'.1.7.99.886.','2021-03-17 21:14:45',NULL,NULL,NULL,0,0,NULL),(887,99,'德保县',3,4,'.1.7.99.887.','2021-03-17 21:14:45',NULL,NULL,NULL,0,0,NULL),(888,99,'田林县',3,4,'.1.7.99.888.','2021-03-17 21:14:45',NULL,NULL,NULL,0,0,NULL),(889,99,'田阳县',3,4,'.1.7.99.889.','2021-03-17 21:14:46',NULL,NULL,NULL,0,0,NULL),(890,99,'靖西县',3,4,'.1.7.99.890.','2021-03-17 21:14:46',NULL,NULL,NULL,0,0,NULL),(891,99,'田东县',3,4,'.1.7.99.891.','2021-03-17 21:14:46',NULL,NULL,NULL,0,0,NULL),(892,99,'那坡县',3,4,'.1.7.99.892.','2021-03-17 21:14:46',NULL,NULL,NULL,0,0,NULL),(893,99,'隆林',3,4,'.1.7.99.893.','2021-03-17 21:14:46',NULL,NULL,NULL,0,0,NULL),(3401,3,'合肥',2,3,'.1.3.3401.','2021-03-17 21:14:57',NULL,NULL,NULL,0,0,NULL);

/*Table structure for table `table_business_fee_item` */

DROP TABLE IF EXISTS `table_business_fee_item`;

CREATE TABLE `table_business_fee_item` (
  `fee_item_id` bigint NOT NULL COMMENT '费用项标识',
  `fee_name` varchar(64) DEFAULT NULL COMMENT '费用名称',
  `fee_code` varchar(4) DEFAULT NULL COMMENT '费用内部编码\n            详见数据字典\n            ',
  `sums` decimal(12,2) DEFAULT NULL COMMENT '费用金额',
  `is_preference` int DEFAULT '0' COMMENT '是否接受优惠结算0否1是',
  `fee_type` varchar(4) DEFAULT NULL COMMENT '费用类型\n            0其他\n            1房租充值\n            2押金\n            3平台服务费',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`fee_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='费用项表。包括月租费、年租费、押金、平台服务费、推广费等。此表暂时不用';

/*Data for the table `table_business_fee_item` */

/*Table structure for table `table_business_house_images_and_video` */

DROP TABLE IF EXISTS `table_business_house_images_and_video`;

CREATE TABLE `table_business_house_images_and_video` (
  `resource_id` bigint NOT NULL AUTO_INCREMENT COMMENT '资源标识',
  `house_id` bigint DEFAULT NULL COMMENT '房屋标识',
  `file_name` varchar(128) DEFAULT NULL COMMENT '文件名称',
  `file_url` varchar(512) DEFAULT NULL COMMENT '资源路径',
  `resource_status` int DEFAULT '1' COMMENT '资源状态0待审核1启用2禁用',
  `resource_type` int DEFAULT NULL COMMENT '资源类型0图片1资源',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`resource_id`),
  UNIQUE KEY `house_id` (`house_id`,`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='房屋图片视频信息表。每个房屋ID最多可以对应5张图片和1个视频。数据库中存储的是url';

/*Data for the table `table_business_house_images_and_video` */

insert  into `table_business_house_images_and_video`(`resource_id`,`house_id`,`file_name`,`file_url`,`resource_status`,`resource_type`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`) values (1,1,'1','http://8.129.77.225:9000/yeju/8469f77f-b29d-43cb-b90c-b5acab2a4bba56eb70e8c716a.jpg',1,0,NULL,NULL,NULL,NULL,NULL,0,0),(2,1,'2','http://8.129.77.225:9000/yeju/03656464-275e-43dd-96a9-04ba0c541ad41.png',1,0,NULL,NULL,NULL,NULL,NULL,0,0),(5,1,'3','http://8.129.77.225:9000/yeju/c1153540-ccef-424a-acf5-2936b7c941975174e72674e52.jpg',1,0,NULL,NULL,NULL,NULL,NULL,0,0);

/*Table structure for table `table_business_house_info` */

DROP TABLE IF EXISTS `table_business_house_info`;

CREATE TABLE `table_business_house_info` (
  `house_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `owner_id` bigint DEFAULT NULL COMMENT '业主',
  `title` varchar(100) DEFAULT NULL COMMENT '房源标题',
  `community_id` bigint DEFAULT NULL COMMENT '所属小区',
  `building_number` varchar(12) DEFAULT NULL COMMENT '楼号（栋）',
  `building_uint` varchar(12) DEFAULT NULL COMMENT '所属单元',
  `building_floor_number` varchar(4) DEFAULT NULL COMMENT '门牌号',
  `rent` decimal(9,2) DEFAULT NULL COMMENT '租金',
  `rental_mode` varchar(4) DEFAULT NULL COMMENT '出租方式（0整租，1合租，2可合租可整租）详见参数表',
  `payment_method` varchar(4) DEFAULT NULL COMMENT '支付方式（1押一付一，2押一付二，3押一付三，4押一付六，5押一付年，6其他）详见参数表',
  `house_type` varchar(4) DEFAULT NULL COMMENT '户型 详见参数表',
  `covered_area` int DEFAULT NULL COMMENT '建筑面积,单位平方米',
  `use_area` int DEFAULT NULL COMMENT '使用面积,单位平方米',
  `floors` varchar(10) DEFAULT NULL COMMENT '楼层,例如8/26',
  `house_orientation` varchar(4) DEFAULT NULL COMMENT '朝向，1坐北朝南，2坐东朝西，3坐南朝北，4坐西朝东  详见参数表',
  `house_decoration_type` varchar(4) DEFAULT NULL COMMENT '房屋装修类型，1简装，2精装，3毛胚',
  `house_facilities` varchar(4) DEFAULT NULL COMMENT '配套设施，详见参数表',
  `descs` varchar(500) DEFAULT NULL COMMENT '描述',
  `house_status` varchar(4) DEFAULT NULL COMMENT '0审核中\n            1审核未通过\n            2待租\n            3预交易\n            4交易生效中\n             详见数据字典\n            ',
  `house_images_address` varchar(500) DEFAULT NULL COMMENT '房屋图片地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `version_number` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  `month_added` int DEFAULT NULL COMMENT '房源信息添加时的月份',
  `month_completed` int DEFAULT NULL COMMENT '房源交易完成时的月份。历史表的分区依据',
  PRIMARY KEY (`house_id`),
  UNIQUE KEY `owner_id` (`owner_id`),
  UNIQUE KEY `community_id` (`community_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='房源信息表，其中按照添加时的月份进行表分区。并且仅保留待审核状态的记录。待交易（审核已完成）的记录搬table_busi';

/*Data for the table `table_business_house_info` */

insert  into `table_business_house_info`(`house_id`,`owner_id`,`title`,`community_id`,`building_number`,`building_uint`,`building_floor_number`,`rent`,`rental_mode`,`payment_method`,`house_type`,`covered_area`,`use_area`,`floors`,`house_orientation`,`house_decoration_type`,`house_facilities`,`descs`,`house_status`,`house_images_address`,`create_time`,`create_by`,`update_time`,`changed_by`,`version_number`,`is_delete`,`month_added`,`month_completed`) values (1,NULL,'海南大学10栋学生公寓楼出租啦',NULL,NULL,NULL,NULL,'1200.00','0','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'海南大学（Hainan University），简称海大，坐落于海南省海口市，是教育部与海南省人民政府“部省合建高校”、海南省人民政府与财政部共建高校，是海南省属综合性重点大学、海南省“国内一流大学建设”重点支持高校，入选世界一流学科建设高校、“211工程”、“卓越工程师教育培养计划”、“卓越法律人才教育培养计划”、“卓越农林人才教育培养计划”、“中西部高校基础能力建设工程”、“中西部高校综合实力提升工程”、国家建设高水平大学公派研究生项目、中国政府奖学金来华留学生接收院校、教育部来华留学示范基地、国家大学生文化素质教育基地、国家级大学生创新创业训练计划，为中西部“一省一校”国家重点建设大学（Z14）联盟、CDIO工程教育联盟成员单位。学校由天津大学对口合作建设。 [1] ','1',NULL,'2021-03-17 17:26:12',NULL,NULL,NULL,0,0,NULL,NULL);

/*Table structure for table `table_business_house_info_tradable` */

DROP TABLE IF EXISTS `table_business_house_info_tradable`;

CREATE TABLE `table_business_house_info_tradable` (
  `house_id` bigint NOT NULL COMMENT '主键',
  `owner_id` bigint DEFAULT NULL COMMENT '业主',
  `title` varchar(100) DEFAULT NULL COMMENT '房源标题',
  `community_id` bigint DEFAULT NULL COMMENT '所属小区',
  `building_number` varchar(12) DEFAULT NULL COMMENT '楼号（栋）',
  `building_uint` varchar(12) DEFAULT NULL COMMENT '所属单元',
  `building_floor_number` varchar(4) DEFAULT NULL COMMENT '门牌号',
  `rent` decimal(9,2) DEFAULT NULL COMMENT '租金',
  `rental_mode` varchar(4) DEFAULT NULL COMMENT '出租方式（0整租，1合租，2可合租可整租）详见参数表',
  `payment_method` varchar(4) DEFAULT NULL COMMENT '支付方式（1押一付一，2押一付二，3押一付三，4押一付六，5押一付年，6其他）详见参数表',
  `house_type` varchar(4) DEFAULT NULL COMMENT '户型 详见参数表',
  `covered_area` int DEFAULT NULL COMMENT '建筑面积,单位平方米',
  `use_area` int DEFAULT NULL COMMENT '使用面积,单位平方米',
  `floors` varchar(10) DEFAULT NULL COMMENT '楼层,例如8/26',
  `house_orientation` varchar(4) DEFAULT NULL COMMENT '朝向，1坐北朝南，2坐东朝西，3坐南朝北，4坐西朝东  详见参数表',
  `house_decoration_type` varchar(4) DEFAULT NULL COMMENT '房屋装修类型，1简装，2精装，3毛胚',
  `house_facilities` varchar(4) DEFAULT NULL COMMENT '配套设施，详见参数表',
  `descs` varchar(500) DEFAULT NULL COMMENT '描述',
  `house_status` varchar(4) DEFAULT NULL COMMENT '2待租\n            3预租\n            4在租\n             详见参数表',
  `house_images_address` varchar(500) DEFAULT NULL COMMENT '房屋图片地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  `month_added` int DEFAULT NULL COMMENT '房源信息添加时的月份',
  `month_completed` int DEFAULT NULL COMMENT '房源交易完成时的月份。历史表的分区依据',
  PRIMARY KEY (`house_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='房源信息表，其中按照添加时的月份进行表分区。并且仅保留待审核状态的记录。待交易（审核已完成）的记录搬table_busi';

/*Data for the table `table_business_house_info_tradable` */

/*Table structure for table `table_business_house_other_attribute` */

DROP TABLE IF EXISTS `table_business_house_other_attribute`;

CREATE TABLE `table_business_house_other_attribute` (
  `house_id` bigint NOT NULL COMMENT '房源标识',
  `is_hot_water` int DEFAULT NULL COMMENT '是否有热水\n            0否1有',
  `is_have_kitchen` int DEFAULT NULL COMMENT '是否有厨房',
  `is_have_living_root` int DEFAULT NULL COMMENT '是否有客厅',
  `is_have_balcony` int DEFAULT NULL COMMENT '是否有阳台',
  `is_have_window` int DEFAULT NULL COMMENT '是否有窗户',
  `is_separate_toilet` int DEFAULT NULL COMMENT '是否是独卫',
  `is_check_in_with_bags` int DEFAULT NULL COMMENT '是否支持拎包入住',
  `is_have_elevator` int DEFAULT NULL COMMENT '是否有电梯\n            3楼以上的房源需要选择\n            -1房间在二楼及以下\n            0没有\n            1有',
  `is_there_a_parking_space` int DEFAULT NULL COMMENT '是否有车位',
  `electricity_price` decimal(9,2) DEFAULT NULL COMMENT '电价',
  `water_price` decimal(9,2) DEFAULT NULL COMMENT '水价',
  `whether_the_heating` int DEFAULT NULL COMMENT '是否供暖',
  `is_gender_restrictions` int DEFAULT NULL COMMENT '性别限制\n            0不限\n            1男\n            2女',
  `is_it_air_conditioned` int DEFAULT NULL COMMENT '是否有空调',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`house_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='房源其他属性表.包括一些可选的属性以及客户自定义属性（<=3）\n。';

/*Data for the table `table_business_house_other_attribute` */

insert  into `table_business_house_other_attribute`(`house_id`,`is_hot_water`,`is_have_kitchen`,`is_have_living_root`,`is_have_balcony`,`is_have_window`,`is_separate_toilet`,`is_check_in_with_bags`,`is_have_elevator`,`is_there_a_parking_space`,`electricity_price`,`water_price`,`whether_the_heating`,`is_gender_restrictions`,`is_it_air_conditioned`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`) values (1,1,1,1,1,1,1,1,1,1,'1.20','0.60',1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `table_business_integration` */

DROP TABLE IF EXISTS `table_business_integration`;

CREATE TABLE `table_business_integration` (
  `id` bigint NOT NULL COMMENT '主键',
  `account_id` bigint DEFAULT NULL COMMENT '客户账户主键',
  `integration` decimal(10,2) DEFAULT '0.00' COMMENT '积分数',
  `integration_type` varchar(4) DEFAULT NULL COMMENT '积分类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_id` (`account_id`),
  KEY `integration_type` (`integration_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='客户积分表';

/*Data for the table `table_business_integration` */

/*Table structure for table `table_business_payment` */

DROP TABLE IF EXISTS `table_business_payment`;

CREATE TABLE `table_business_payment` (
  `payment_id` bigint NOT NULL COMMENT '主键',
  `payment_status` varchar(4) DEFAULT NULL COMMENT '支付状态\n            0支付工单已创建\n            1支付成功\n            2待生效\n            3生效\n            2支付失败',
  `third_party_serial_number` bigint DEFAULT NULL COMMENT '如果是第三方支付的话需要记录第三方流水号\n            ',
  `payment_mode` varchar(4) DEFAULT NULL COMMENT '支付方式0平台账户支付1支付宝支付2微信支付3网银',
  `transfer_out_account_id` bigint DEFAULT NULL COMMENT '转出账户id',
  `ransfer_to_account_id` bigint DEFAULT NULL COMMENT '转入账户id',
  `free` decimal(12,2) DEFAULT NULL COMMENT '最终应支付金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  `payment_type` varchar(4) DEFAULT NULL COMMENT '0其他\n            1支付宝转入\n            2微信转入\n            3工商行转入\n            4提现\n            5平台补贴\n            6交租金\n            7交押金\n            8系统代扣租金',
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='交易支付信息表，记录平台支付信息';

/*Data for the table `table_business_payment` */

/*Table structure for table `table_business_preengage_see_the_apartment` */

DROP TABLE IF EXISTS `table_business_preengage_see_the_apartment`;

CREATE TABLE `table_business_preengage_see_the_apartment` (
  `preengage_id` bigint NOT NULL COMMENT '主键',
  `Prospective_customer_id` bigint DEFAULT NULL COMMENT '意向客户id',
  `landlord_id` bigint DEFAULT NULL COMMENT '房东id',
  `house_id` bigint DEFAULT NULL COMMENT '房源id',
  `status` int DEFAULT NULL COMMENT '0关闭\n            1等待房东确认\n            2等待意向客户确认\n            3完成看房\n            4意向客户爽约\n            5房东爽约\n            6取消',
  `preengage_time` datetime DEFAULT NULL COMMENT '预约看房时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  `cause_of_abnormal_end` varchar(128) DEFAULT NULL COMMENT '异常结束原因',
  PRIMARY KEY (`preengage_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='预约看房信息表，完成看房搬历史，按照创建月份本区';

/*Data for the table `table_business_preengage_see_the_apartment` */

/*Table structure for table `table_business_questions` */

DROP TABLE IF EXISTS `table_business_questions`;

CREATE TABLE `table_business_questions` (
  `questions_id` bigint NOT NULL COMMENT '主键',
  `problem` varchar(256) DEFAULT NULL COMMENT '提问问题',
  `status` int DEFAULT NULL COMMENT '问题状态0关闭1开启',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`questions_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='提问信息表。记录客户在平台【发现】模块的提问。存储于mongodb';

/*Data for the table `table_business_questions` */

/*Table structure for table `table_business_rent_payment_log` */

DROP TABLE IF EXISTS `table_business_rent_payment_log`;

CREATE TABLE `table_business_rent_payment_log` (
  `rent_payment_id` bigint NOT NULL COMMENT '缴纳流水号',
  `house_id` bigint DEFAULT NULL COMMENT '房屋标识',
  `payment_id` bigint NOT NULL COMMENT '支付流水',
  `effective_time` datetime DEFAULT NULL COMMENT '实际转入房东账户的时间',
  `months` int DEFAULT NULL COMMENT '充值了几个月\n            months=充值总金额/月租',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`rent_payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='房租缴纳记录.表';

/*Data for the table `table_business_rent_payment_log` */

/*Table structure for table `table_business_topic` */

DROP TABLE IF EXISTS `table_business_topic`;

CREATE TABLE `table_business_topic` (
  `topic_id` bigint NOT NULL COMMENT '主键',
  `topic_title` varchar(64) DEFAULT NULL COMMENT '话题标题',
  `citation` varchar(512) DEFAULT NULL COMMENT '话题引文（话题正文）',
  `topic_type` varchar(4) DEFAULT NULL COMMENT '话题类型（详见数据字典）',
  `image_url` varchar(512) DEFAULT NULL COMMENT '图片路径list',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='话题(帖子)信息表，记录椰租平台上的各类话题信息，来源包括平台创建类、客户创建类。类型包括上班摸鱼、找舍友、房源推荐、日';

/*Data for the table `table_business_topic` */

/*Table structure for table `table_business_trading_evaluation` */

DROP TABLE IF EXISTS `table_business_trading_evaluation`;

CREATE TABLE `table_business_trading_evaluation` (
  `evaluation_id` bigint DEFAULT NULL COMMENT '评价主键',
  `trading_id` bigint NOT NULL COMMENT '交易记录主键',
  `customer_id` bigint DEFAULT NULL COMMENT '客户主键',
  `customer_name` varchar(32) DEFAULT NULL COMMENT '客户姓名',
  `content` varchar(1024) DEFAULT NULL COMMENT '评价内容',
  `comment_time` datetime DEFAULT NULL COMMENT '评价时间',
  `create_time` datetime DEFAULT NULL COMMENT '字段创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  `descriptive_coincidence` int DEFAULT NULL COMMENT '描述符合度1很不符合2不符合3基本相符4比较相符5非常相符',
  `landlord_service` int DEFAULT NULL COMMENT '房东服务满意度1很不满意2不满意3基本满意4比较满意5非常满意',
  `tenant_behavior` int DEFAULT NULL COMMENT '房客行为评价1非常不满意2不满意3一般4比较满意5非常满意',
  `evaluation_type` int DEFAULT NULL COMMENT '评价类型0其他1房客评价2房东评价',
  `evaluation_status` varchar(4) DEFAULT NULL COMMENT '评价状态0正常1待回访2回访结果已提交3评价可信4评价不可信\n            详情见数据字典',
  `evaluation_like_count` bigint DEFAULT NULL COMMENT '评价点赞数（认同度）',
  PRIMARY KEY (`trading_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='交易评价表，记录房源交易评价信息。实时评价记录在mongodb数据库。每天同步评论到mysql。以访问者IP最后一位作为';

/*Data for the table `table_business_trading_evaluation` */

/*Table structure for table `table_business_trading_information_house` */

DROP TABLE IF EXISTS `table_business_trading_information_house`;

CREATE TABLE `table_business_trading_information_house` (
  `trading_id` bigint NOT NULL COMMENT '主键',
  `house_id` bigint DEFAULT NULL COMMENT '房源id',
  `landlord_id` bigint DEFAULT NULL COMMENT '房东标识',
  `tenant_id` bigint DEFAULT NULL COMMENT '(准)房客标识',
  `trading_status` varchar(4) DEFAULT NULL COMMENT '交易状态\n            0交易创建成功\n            1已支付\n            2已收货（房东已交房、卡卷已到账）\n            3商家已收款（房租已转入房东账户）\n            4交易异常申诉中(此时创建申述工单，并进入状态10)\n            5交易正常结束（货款两清）\n            9交易非正常结束\n            10交易暂停\n            11交易取消\n            \n            详细见数据字典',
  `create_time` datetime DEFAULT NULL COMMENT '交易创建时间',
  `payment_id` bigint DEFAULT NULL COMMENT '支付平台内部流水号',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  `cancel_time` datetime DEFAULT NULL COMMENT '交易取消时间',
  `trading_type` varchar(4) DEFAULT NULL COMMENT '交易类型，详见数据字典\n            0租房\n            1退房\n            3续交押金\n            4退押金\n            \n            \n            ',
  PRIMARY KEY (`trading_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='房屋交易记录表，按照交易月份进行分区';

/*Data for the table `table_business_trading_information_house` */

/*Table structure for table `table_business_warning_account` */

DROP TABLE IF EXISTS `table_business_warning_account`;

CREATE TABLE `table_business_warning_account` (
  `account_id` bigint NOT NULL COMMENT '账户标识',
  `warning_status` char(1) DEFAULT NULL COMMENT '预警状态\n            0待预警\n            1已预警\n            2预警前缴纳\n            3预警后缴纳但未逾期\n            4逾期未缴纳\n            5逾期后缴纳',
  `notice_time` datetime DEFAULT NULL COMMENT '第一次预警通知时间',
  `late_time` datetime DEFAULT NULL COMMENT '最近一次预警通知时间',
  `notice_mode` char(1) DEFAULT NULL COMMENT '最近一次通知方式\n            0 APP推送\n            1短信\n            2邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  `partition_number` char(2) DEFAULT NULL COMMENT '分区标识',
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='租期到期预警表.记录待通知缴纳租金的账号信息，失效搬历史。按照账户id后两位分区';

/*Data for the table `table_business_warning_account` */

/*Table structure for table `table_data_browsing_history` */

DROP TABLE IF EXISTS `table_data_browsing_history`;

CREATE TABLE `table_data_browsing_history` (
  `browsing_id` bigint NOT NULL COMMENT '浏览标识',
  `customer_id` bigint DEFAULT NULL COMMENT '客户标识',
  `house_id` bigint DEFAULT NULL COMMENT '房源标识',
  `count` int DEFAULT NULL COMMENT '浏览计数',
  `latest_time` datetime DEFAULT NULL COMMENT '最近一次浏览时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`browsing_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='房源浏览记录.从客户角度记录其浏览房源记录';

/*Data for the table `table_data_browsing_history` */

/*Table structure for table `table_message` */

DROP TABLE IF EXISTS `table_message`;

CREATE TABLE `table_message` (
  `id` bigint NOT NULL COMMENT '主键',
  `be_from` bigint DEFAULT NULL COMMENT '来自谁',
  `send_to` bigint DEFAULT NULL COMMENT '发给谁',
  `message_type` varchar(4) DEFAULT NULL COMMENT '消息类型1群组消息2私信',
  `content` varchar(256) DEFAULT NULL COMMENT '消息主体',
  `message_status` varchar(4) DEFAULT NULL COMMENT '消息状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_message` */

/*Table structure for table `table_message_group` */

DROP TABLE IF EXISTS `table_message_group`;

CREATE TABLE `table_message_group` (
  `group_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_name` varchar(16) DEFAULT NULL COMMENT '群组名',
  `group_status` varchar(4) DEFAULT NULL COMMENT '状态',
  `group_type` varchar(4) DEFAULT NULL COMMENT '组类型 1系统2自定义',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='消息群组信息表';

/*Data for the table `table_message_group` */

insert  into `table_message_group`(`group_id`,`group_name`,`group_status`,`group_type`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`) values (1,'全部用户','1','1','2021-03-06 13:29:17',NULL,NULL,NULL,NULL,0,0),(2,'全部员工','1','1','2021-03-06 13:32:32',NULL,NULL,NULL,NULL,0,0),(3,'全部客户','1','1','2021-03-06 13:33:21',NULL,NULL,NULL,NULL,0,0);

/*Table structure for table `table_platform_department` */

DROP TABLE IF EXISTS `table_platform_department`;

CREATE TABLE `table_platform_department` (
  `department_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_department_id` bigint DEFAULT NULL COMMENT '父部门主键',
  `name` varchar(32) DEFAULT NULL COMMENT '部门名称',
  `leader_id` bigint DEFAULT NULL COMMENT '负责人id',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号',
  `phone_number_prefix` bigint DEFAULT NULL COMMENT '手机区号，比如中国是+86，详见属性表\n            ',
  `email` varchar(32) DEFAULT NULL COMMENT '部门（团队）邮箱',
  `yeju_department_status` bigint DEFAULT NULL COMMENT '部门状态0未启用1启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `version_number` int DEFAULT '1' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='椰居平台部门表';

/*Data for the table `table_platform_department` */

insert  into `table_platform_department`(`department_id`,`parent_department_id`,`name`,`leader_id`,`phone_number`,`phone_number_prefix`,`email`,`yeju_department_status`,`create_time`,`create_by`,`update_time`,`changed_by`,`version_number`,`is_delete`) values (1,0,'椰居科技有限公司',1,'17330937086',86,'bingfengdev@aliyun.com',1,'2021-02-14 15:58:32',1,NULL,NULL,1,0),(2,1,'产品部',NULL,NULL,NULL,'product@yeju.com',1,'2021-02-16 08:52:19',NULL,NULL,NULL,1,0),(3,1,'研发部',NULL,NULL,NULL,'development@yeju.com',1,'2021-02-16 08:53:26',NULL,NULL,NULL,1,0),(4,1,'运营部',NULL,NULL,NULL,'operation@yeju.com',1,'2021-02-16 08:54:57',NULL,NULL,NULL,1,0),(5,1,'综合管理部',NULL,NULL,NULL,'gm@yeju.com',1,'2021-02-16 08:56:16',NULL,NULL,NULL,1,0);

/*Table structure for table `table_platform_employees` */

DROP TABLE IF EXISTS `table_platform_employees`;

CREATE TABLE `table_platform_employees` (
  `employees_id` bigint NOT NULL COMMENT '主键',
  `name` varchar(32) DEFAULT NULL COMMENT '员工姓名',
  `gender` bigint DEFAULT NULL COMMENT '性别0男1女2未知，详见属性表',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号',
  `employees_number` bigint NOT NULL AUTO_INCREMENT COMMENT '工号',
  `phone_number_prefix` varchar(8) DEFAULT NULL COMMENT '手机区号值',
  `leader_id` bigint DEFAULT NULL COMMENT '所属领导',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像地址',
  `email` varchar(32) DEFAULT NULL COMMENT '公司邮箱',
  `employee_status` bigint DEFAULT '0' COMMENT '员工状态0在职1离职',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `version_number` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  `month_added` int DEFAULT NULL COMMENT '员工信息添加时的月份，分区标识',
  `month_outmoded` int DEFAULT NULL COMMENT '员工离职时的月份。历史表的分区依据',
  `department_id` bigint DEFAULT NULL COMMENT '所属部门id',
  PRIMARY KEY (`employees_id`),
  UNIQUE KEY `table_platform_employees_employees_number_uindex` (`employees_number`),
  UNIQUE KEY `table_platform_employees_phone_number_uindex` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='椰居平台员工表';

/*Data for the table `table_platform_employees` */

insert  into `table_platform_employees`(`employees_id`,`name`,`gender`,`phone_number`,`employees_number`,`phone_number_prefix`,`leader_id`,`avatar`,`email`,`employee_status`,`create_time`,`create_by`,`update_time`,`changed_by`,`version_number`,`is_delete`,`month_added`,`month_outmoded`,`department_id`) values (1,'超级员工',1,'17330937086',969391,'86',1,'http://8.129.77.225:9000/yeju/8469f77f-b29d-43cb-b90c-b5acab2a4bba56eb70e8c716a.jpg','bingfengdev@aliyun.com',1,'2021-02-14 15:29:25',NULL,NULL,NULL,NULL,0,12,NULL,1);

/*Table structure for table `table_platform_post` */

DROP TABLE IF EXISTS `table_platform_post`;

CREATE TABLE `table_platform_post` (
  `post_id` bigint NOT NULL COMMENT '主键',
  `post_name` varchar(32) DEFAULT NULL COMMENT '岗位名称',
  `department_id` bigint DEFAULT NULL COMMENT '岗位所属部门',
  `post_code` varchar(64) DEFAULT NULL COMMENT '岗位编码',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `post_status` varchar(4) DEFAULT NULL COMMENT '岗位状态0未启用1启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='职位信息表';

/*Data for the table `table_platform_post` */

/*Table structure for table `table_relationship_customer_house` */

DROP TABLE IF EXISTS `table_relationship_customer_house`;

CREATE TABLE `table_relationship_customer_house` (
  `customer_id` bigint NOT NULL COMMENT '客户主键',
  `house_id` bigint NOT NULL COMMENT '房源主键',
  `relationship_type` varchar(4) DEFAULT NULL COMMENT '关系类型,主要有\n            0租赁关系、\n            1归属关系，\n            2收藏关系，\n            3推荐关系，\n            4踩 关系，\n            5待交租金关系（预租，抢占资源），\n            6待续交租金关系、\n            7租赁过 关系',
  `start_time` datetime DEFAULT NULL COMMENT '关系开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '关系结束时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  `create_month` int DEFAULT NULL COMMENT '建立关系月份，用于表分区',
  `relationship_status` int DEFAULT NULL COMMENT '0已结束\n            1正常\n            2异常',
  `is_warning` int DEFAULT '0' COMMENT '是否开启关系到期预警0不开启1开启',
  PRIMARY KEY (`customer_id`,`house_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='客户-房源关系表。主要有租赁关系、归属关系，收藏关系，推荐关系，踩 关系，待交租金关系，待需交租金关系、租赁过 关系.关';

/*Data for the table `table_relationship_customer_house` */

/*Table structure for table `table_system_account` */

DROP TABLE IF EXISTS `table_system_account`;

CREATE TABLE `table_system_account` (
  `account_id` bigint NOT NULL COMMENT '主键',
  `account_number` varchar(16) NOT NULL COMMENT '账号',
  `subject_id` bigint DEFAULT NULL COMMENT '账户所属者主键',
  `account_password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '账户密码',
  `last_login_address` varchar(128) DEFAULT NULL COMMENT '最后一次登录地址',
  `last_login_date` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `account_status` varchar(4) DEFAULT NULL COMMENT '账户状态0未启用1启用2冻结，详见属性表',
  `account_level` varchar(4) DEFAULT NULL COMMENT '账户等级，详见数据走到吗',
  `account_type` varchar(4) DEFAULT NULL COMMENT '账户类型，0内部账户1客户账户，详见属性表',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  `phone_number` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `account_number` (`account_number`),
  KEY `phoneNumber` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账户表';

/*Data for the table `table_system_account` */

insert  into `table_system_account`(`account_id`,`account_number`,`subject_id`,`account_password`,`last_login_address`,`last_login_date`,`account_status`,`account_level`,`account_type`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`,`phone_number`) values (1,'969391',1,'$2a$10$TyTu1cSR5Q4Z.a/fDDdj1OPYlHcWxsM8EIm2vz90XidLAALWLHuTC',NULL,NULL,'1','1','0',NULL,1,NULL,NULL,'超级工号',1,0,'17330937086'),(2,'969392',1,'$2a$10$TyTu1cSR5Q4Z.a/fDDdj1OPYlHcWxsM8EIm2vz90XidLAALWLHuTC',NULL,NULL,'1','0','1','2021-03-22 20:09:08',2,NULL,NULL,'测试客户',0,0,'17330937087');

/*Table structure for table `table_system_data_dictionary_info` */

DROP TABLE IF EXISTS `table_system_data_dictionary_info`;

CREATE TABLE `table_system_data_dictionary_info` (
  `data_dictionary_info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_id` bigint DEFAULT NULL COMMENT '数据字典类型标识',
  `sort` int DEFAULT NULL COMMENT '排序（优先级）',
  `dictionary_label` varchar(128) DEFAULT NULL COMMENT '字典标签，实际显示出来的值',
  `dictionary_value` varchar(128) DEFAULT NULL COMMENT '字典键值，内部代码例如gender',
  `css_class` varchar(256) DEFAULT NULL COMMENT '样式属性',
  `list_class` varchar(256) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` int DEFAULT '0' COMMENT '是否为默认属性0否1是',
  `status` int DEFAULT '1' COMMENT '状态0未启用1启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`data_dictionary_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据字典信息表';

/*Data for the table `table_system_data_dictionary_info` */

insert  into `table_system_data_dictionary_info`(`data_dictionary_info_id`,`type_id`,`sort`,`dictionary_label`,`dictionary_value`,`css_class`,`list_class`,`is_default`,`status`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`) values (1,1,1,'男','1',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,0),(2,1,2,'女','2',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,0),(3,2,1,'启用','1',NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL,0),(4,2,2,'未启用','0',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,0),(5,3,1,'在职','1',NULL,NULL,1,1,'2021-02-20 15:46:09',NULL,NULL,NULL,'员工在职状态',0,0),(6,3,2,'离职','0',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(7,4,1,'启用','1',NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,0,0),(8,4,2,'未启用','0',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(9,5,1,'启用','1',NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,0,0),(10,5,0,'未启用','0',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(11,6,1,'系统维护类通知','1',NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,0,0),(12,7,1,'启用','1',NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,0,0),(13,7,0,'未启用','0',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(14,6,2,'系统一般性通知','2',NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,0,0),(15,8,1,'启用','1',NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,0,0),(16,8,2,'未启用','0',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(17,9,1,'已发布','1',NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,0,0),(18,9,2,'已失效','0',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(19,10,1,'运行','1',NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,0,0),(20,10,2,'停止','0',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(21,11,1,'群组','1',NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,0,0),(22,11,2,'个人','2',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(24,12,1,'成功','1',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(25,12,2,'失败','0',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(26,13,1,'成功','1',NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,0,0),(27,13,2,'失败','0',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(28,14,1,'待审核','0',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(29,14,2,'已审核','1',NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,0,0),(30,14,3,'交易中','2',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(31,15,1,'整租','0',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(32,15,2,'合租','1',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(33,15,3,'可合租可整租','2',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(34,16,1,'押一付一','1',NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,0,0),(35,16,2,'押一付二','2',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(36,16,3,'押一付三','3',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(37,16,4,'押一付六','4',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(38,16,5,'押一付年','5',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(39,16,6,'其他','6',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(40,14,0,'审核未通过','3',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(41,1,3,'未知','3',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(42,17,1,'未认证','0',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(43,17,2,'已认证','1',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0),(44,17,3,'已冻结','2',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0);

/*Table structure for table `table_system_data_dictionary_type` */

DROP TABLE IF EXISTS `table_system_data_dictionary_type`;

CREATE TABLE `table_system_data_dictionary_type` (
  `data_dictionary_type_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(128) DEFAULT NULL COMMENT '数据字典名称',
  `type` varchar(128) DEFAULT NULL COMMENT '数据字典类型，与名称对应，与表中字段名一致',
  `status` int DEFAULT '1' COMMENT '状态0未启用1启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`data_dictionary_type_id`),
  KEY `type__index` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据字典类型表,用于简单的动态配置';

/*Data for the table `table_system_data_dictionary_type` */

insert  into `table_system_data_dictionary_type`(`data_dictionary_type_id`,`name`,`type`,`status`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`) values (1,'性别','gender',1,NULL,NULL,NULL,NULL,NULL,NULL,0),(2,'角色状态','role_status',1,NULL,NULL,NULL,NULL,NULL,NULL,0),(3,'员工状态','employee_status',1,NULL,NULL,NULL,NULL,NULL,NULL,0),(4,'部门状态','department_status',1,NULL,NULL,NULL,NULL,NULL,0,0),(5,'数据字典类型状态','dict_status',1,NULL,NULL,NULL,NULL,NULL,0,0),(6,'系统通知类型','notice_type',1,NULL,NULL,NULL,NULL,NULL,0,0),(7,'通用状态','status',1,NULL,NULL,NULL,NULL,NULL,0,0),(8,'资源状态','resource_status',1,NULL,NULL,NULL,NULL,NULL,0,0),(9,'通知状态','notice_status',1,NULL,NULL,NULL,NULL,NULL,0,0),(10,'定时任务状态','job_status',1,NULL,NULL,NULL,NULL,NULL,0,0),(11,'消息接收者类型','receiver_type',1,NULL,NULL,NULL,NULL,NULL,0,0),(12,'操作状态','operation_status',1,NULL,NULL,NULL,NULL,NULL,0,0),(13,'定时任务执行状态','task_status',1,NULL,NULL,NULL,NULL,NULL,0,0),(14,'房源状态','house_status',1,NULL,NULL,NULL,NULL,NULL,0,0),(15,'房屋出租方式','rental_mode',1,NULL,NULL,NULL,NULL,NULL,0,0),(16,'支付方式','payment_method',1,NULL,NULL,NULL,NULL,NULL,0,0),(17,'客户状态','customer_status',1,NULL,NULL,NULL,NULL,NULL,0,0);

/*Table structure for table `table_system_job_trigger` */

DROP TABLE IF EXISTS `table_system_job_trigger`;

CREATE TABLE `table_system_job_trigger` (
  `trigger_id` bigint NOT NULL COMMENT 'id',
  `task_id` bigint DEFAULT NULL COMMENT '所属任务id',
  `trigger_name` varchar(32) DEFAULT NULL COMMENT '名字',
  `trigger_group_name` varchar(64) DEFAULT NULL COMMENT '触发器组名',
  `cron` varchar(32) DEFAULT NULL COMMENT 'cron表达式',
  `trigger_type` varchar(16) DEFAULT NULL COMMENT '触发器类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  `start_time` datetime DEFAULT NULL COMMENT '启动时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `timezone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'Asia/Shanghai' COMMENT '时区',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`trigger_id`),
  UNIQUE KEY `task_id` (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_system_job_trigger` */

insert  into `table_system_job_trigger`(`trigger_id`,`task_id`,`trigger_name`,`trigger_group_name`,`cron`,`trigger_type`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`,`start_time`,`end_time`,`timezone`,`description`) values (1,1,'演示任务触发器','YEJU_DEFAULT_JOB_TRIGGER_GROUP',NULL,NULL,'2021-02-28 15:45:39',NULL,NULL,NULL,NULL,0,0,'2021-02-28 15:45:49','2021-03-12 15:45:53','Asia/Shanghai','默认触发器');

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

/*Data for the table `table_system_login_log` */

insert  into `table_system_login_log`(`login_log_id`,`account`,`subject_name`,`ip`,`login_status`,`message`,`accent_time`,`last_ip_number`) values (1365850605194006529,'969391','','127.0.0.1',1,'登录成功','2021-02-27 15:03:04',NULL),(1365850606607486978,'969391','','127.0.0.1',1,'登录成功','2021-02-27 15:12:42',NULL),(1365850606955614209,'969391','','127.0.0.1',1,'登录成功','2021-02-27 19:27:58',NULL),(1365850607169523713,'969391','','127.0.0.1',1,'登录成功','2021-02-27 20:01:00',NULL),(1365850607379238914,'969391','','127.0.0.1',1,'登录成功','2021-02-27 20:32:49',NULL),(1365850607614119937,'969391','','127.0.0.1',1,'登录成功','2021-02-28 08:50:17',NULL),(1365850607882555394,'969391','','127.0.0.1',1,'登录成功','2021-02-28 09:37:48',NULL),(1365850608167768066,'969391','','127.0.0.1',1,'登录成功','2021-02-28 09:38:25',NULL),(1365858343798104066,'969391','','127.0.0.1',1,'登录成功','2021-02-28 10:28:31',NULL),(1366736154859601921,'969391','','127.0.0.1',1,'登录成功','2021-02-28 16:35:47',NULL),(1366736156264693762,'969391','','127.0.0.1',1,'登录成功','2021-02-28 16:35:53',NULL),(1366736156541517826,'969391','','127.0.0.1',1,'登录成功','2021-02-28 16:37:09',NULL),(1366736156939976705,'969391','','127.0.0.1',1,'登录成功','2021-02-28 16:42:19',NULL),(1366736157153886209,'969391','','127.0.0.1',1,'登录成功','2021-02-28 17:31:53',NULL),(1366736157443293185,'969391','','127.0.0.1',1,'登录成功','2021-02-28 17:32:00',NULL),(1366736157804003330,'969391','','127.0.0.1',1,'登录成功','2021-02-28 17:32:34',NULL),(1366736158089216001,'969391','','127.0.0.1',1,'登录成功','2021-02-28 17:33:27',NULL),(1366736158382817282,'969391','','127.0.0.1',1,'登录成功','2021-02-28 17:41:35',NULL),(1366736158659641346,'969391','','127.0.0.1',1,'登录成功','2021-02-28 17:41:51',NULL),(1366736158940659713,'969391','','127.0.0.1',1,'登录成功','2021-02-28 17:43:07',NULL),(1366736159175540738,'969391','','127.0.0.1',1,'登录成功','2021-02-28 17:46:45',NULL),(1366736159985041410,'969391','','127.0.0.1',1,'登录成功','2021-02-28 17:58:12',NULL),(1366736160253476865,'969391','','127.0.0.1',1,'登录成功','2021-02-28 18:03:55',NULL),(1366736160463192065,'969391','','192.168.1.105',1,'登录成功','2021-02-28 18:06:18',NULL),(1366736160740016130,'969391','','127.0.0.1',1,'登录成功','2021-02-28 18:06:48',NULL),(1366736161029423106,'969391','','127.0.0.1',1,'登录成功','2021-02-28 23:17:16',NULL),(1366736161314635777,'969391','','127.0.0.1',1,'登录成功','2021-03-01 11:05:11',NULL),(1366736161604042753,'969391','','127.0.0.1',1,'登录成功','2021-03-01 11:05:17',NULL),(1366736161889255425,'969391','','127.0.0.1',1,'登录成功','2021-03-01 11:08:12',NULL),(1366736162174468098,'969391','','127.0.0.1',1,'登录成功','2021-03-01 11:08:18',NULL),(1366736162484846594,'969391','','127.0.0.1',1,'登录成功','2021-03-01 11:08:25',NULL),(1366736162824585217,'969391','','127.0.0.1',1,'登录成功','2021-03-01 11:10:06',NULL),(1366736163105603586,'969391','','127.0.0.1',1,'登录成功','2021-03-01 11:11:38',NULL),(1366736163319513089,'969391','','127.0.0.1',1,'登录成功','2021-03-01 22:33:26',NULL),(1366736163537616898,'969391','','127.0.0.1',1,'登录成功','2021-03-01 23:27:22',NULL),(1368008240925450242,'969391','','127.0.0.1',1,'登录成功','2021-03-06 00:12:52',NULL),(1368008245132337154,'969391','','127.0.0.1',1,'登录成功','2021-03-06 01:07:29',NULL),(1368008245660819458,'969391','','127.0.0.1',1,'登录成功','2021-03-06 01:17:18',NULL),(1368010515299377153,'969391','','127.0.0.1',1,'登录成功','2021-03-06 09:28:13',NULL),(1368013579087454209,'969391','','127.0.0.1',1,'登录成功','2021-03-06 09:40:24',NULL),(1368019128030011393,'969391','','127.0.0.1',1,'登录成功','2021-03-06 10:02:27',NULL),(1368019622047719425,'969391','','127.0.0.1',1,'登录成功','2021-03-06 10:04:24',NULL),(1368019862997901313,'969391','','127.0.0.1',1,'登录成功','2021-03-06 10:05:22',NULL),(1368020126194671618,'969391','','127.0.0.1',1,'登录成功','2021-03-06 10:06:25',NULL),(1368022021495791618,'969391','','127.0.0.1',1,'登录成功','2021-03-06 10:13:56',NULL),(1368023402944339969,'969391','','127.0.0.1',1,'登录成功','2021-03-06 10:19:26',NULL),(1368027152270434306,'969391','','127.0.0.1',1,'登录成功','2021-03-06 10:34:20',NULL),(1368084264849711105,'969391','','127.0.0.1',1,'登录成功','2021-03-06 14:21:16',NULL),(1368086566796992514,'969391','','127.0.0.1',1,'登录成功','2021-03-06 14:30:25',NULL),(1368091495594926082,'969391','','192.168.1.105',1,'登录成功','2021-03-06 14:50:00',NULL),(1368091652218626050,'969391','','127.0.0.1',1,'登录成功','2021-03-06 14:50:37',NULL),(1368091723823783938,'969391','','192.168.1.105',1,'登录成功','2021-03-06 14:50:54',NULL),(1368093061999697921,'969391','','127.0.0.1',1,'登录成功','2021-03-06 14:56:14',NULL),(1368093062800809985,'969391','','127.0.0.1',1,'登录成功','2021-03-06 14:56:14',NULL),(1368093084783157250,'969391','','127.0.0.1',1,'登录成功','2021-03-06 14:56:19',NULL),(1368094085229518850,'969391','','192.168.1.105',1,'登录成功','2021-03-06 15:00:17',NULL),(1368094536163336193,'969391','','127.0.0.1',1,'登录成功','2021-03-06 15:02:05',NULL),(1368094566228107266,'969391','','192.168.1.105',1,'登录成功','2021-03-06 15:02:12',NULL),(1368095068063997954,'969391','','127.0.0.1',1,'登录成功','2021-03-06 15:04:12',NULL),(1368095150813421569,'969391','','192.168.1.105',1,'登录成功','2021-03-06 15:04:32',NULL),(1368095580092047362,'969391','','192.168.1.105',1,'登录成功','2021-03-06 15:06:14',NULL),(1368095855389384705,'969391','','192.168.1.105',1,'登录成功','2021-03-06 15:07:19',NULL),(1368096224991453185,'969391','','192.168.1.105',1,'登录成功','2021-03-06 15:08:48',NULL),(1368096786487123970,'969391','','192.168.1.105',1,'登录成功','2021-03-06 15:11:01',NULL),(1368097278407680002,'969391','','192.168.1.105',1,'登录成功','2021-03-06 15:12:59',NULL),(1368097420112240642,'969391','','192.168.1.105',1,'登录成功','2021-03-06 15:13:33',NULL),(1368098406818062338,'969391','','192.168.1.105',1,'登录成功','2021-03-06 15:17:28',NULL),(1368098487365476353,'969391','','127.0.0.1',1,'登录成功','2021-03-06 15:17:47',NULL),(1368101801746440193,'969391','','127.0.0.1',1,'登录成功','2021-03-06 15:30:57',NULL),(1368102676883775489,'969391','','127.0.0.1',1,'登录成功','2021-03-06 15:34:26',NULL),(1368103366909698049,'17330937086','','192.168.1.105',1,'登录成功','2021-03-06 15:37:10',NULL),(1368122102924386305,'969391','','127.0.0.1',1,'登录成功','2021-03-06 16:51:37',NULL),(1368133505970282497,'969391','','127.0.0.1',1,'登录成功','2021-03-06 17:36:56',NULL);

/*Table structure for table `table_system_notice` */

DROP TABLE IF EXISTS `table_system_notice`;

CREATE TABLE `table_system_notice` (
  `notice_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(128) DEFAULT NULL COMMENT '通知标题',
  `content` varchar(512) DEFAULT NULL COMMENT '通知正文',
  `notice_type` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '2' COMMENT '通知类型1维护类通知2一般性通知',
  `status` int(1) unsigned zerofill DEFAULT '1' COMMENT '通知状态0关闭1正常',
  `start_time` datetime DEFAULT NULL COMMENT '公告开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '公告结束时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(16) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  `be_from` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '谁发',
  `send_to` bigint DEFAULT NULL COMMENT '发给谁',
  `receiver_type` varchar(4) DEFAULT NULL COMMENT '接收者类型 1全部2群组3私信',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1369849029866274819 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统通知（推送）公告表,记录系统的公告信息。仅保留公告有效期内的公告，超过有效期的公告搬历史表';

/*Data for the table `table_system_notice` */

insert  into `table_system_notice`(`notice_id`,`title`,`content`,`notice_type`,`status`,`start_time`,`end_time`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`,`be_from`,`send_to`,`receiver_type`) values (1,'系统停服维护通知','椰居平台将于2021年2月28日0时开始停服维护，对此给您带来的不便深表歉意','1',1,'2021-02-17 23:46:18','2021-03-28 23:46:22','2021-02-17 23:46:29','969391',NULL,NULL,'无',0,1,NULL,NULL,NULL),(2,'系统更新通知','椰居平台更新啦','2',1,'2021-02-18 00:32:05','2021-02-21 00:32:08','2021-02-18 00:32:12','969391',NULL,NULL,NULL,0,1,NULL,NULL,NULL),(1368234100675514370,'哈啊哈哈哈','<p>qqq</p>','1',1,'2021-03-06 00:00:00','2021-03-08 00:00:00','2021-03-07 00:16:45','969391',NULL,NULL,NULL,0,1,NULL,1,NULL),(1368236542016294913,'关于停服维护的公告','<p class=\"ql-align-center\">诸位居士：</p><p class=\"ql-align-center\"><br></p><p>椰居平台计划于2021年3月9日0时开始停服维护，届时服务将不可用。对此给您带来的不便我们深表歉意。</p><p><br></p><p>椰居平台 综合管理部	</p><p>2021年3月6日</p>','2',1,'2021-03-06 00:00:00','2021-03-09 00:00:00','2021-03-07 00:26:27','969391',NULL,NULL,NULL,0,0,NULL,1,NULL),(1368417604759658498,'这是标题','<p>这是内容</p>','1',1,'2021-03-06 00:00:00','2021-03-08 00:00:00','2021-03-07 12:25:56','969391',NULL,NULL,NULL,0,1,NULL,1,NULL),(1368481768911925250,'sss','<p>sxsaxsa</p>','1',1,'2021-03-05 00:00:00','2021-03-08 00:00:00','2021-03-07 16:40:53','969391',NULL,NULL,NULL,0,1,NULL,1,NULL),(1368488256996896769,'dfvd','<p>vfdvv</p>','1',1,'2021-03-05 00:00:00','2021-03-08 00:00:00','2021-03-07 17:06:40','969391',NULL,NULL,NULL,0,1,NULL,2,NULL),(1369491002130563073,'测试1','<p>111</p>','1',1,'2021-03-09 00:00:00','2021-03-11 00:00:00','2021-03-10 11:31:13','969391',NULL,NULL,NULL,0,1,NULL,1,NULL),(1369833183194324994,'哇哈哈','<p>sss</p>','1',1,'2021-03-10 00:00:00','2021-03-12 00:00:00','2021-03-11 10:10:56','969391',NULL,NULL,NULL,0,1,NULL,1,NULL),(1369838519892557825,'哇哈','<p>hhgg</p>','1',1,'2021-03-13 00:00:00','2021-03-19 00:00:00','2021-03-11 10:32:08','969391',NULL,NULL,NULL,0,1,NULL,3,NULL),(1369838523856175106,'哇哈','<p>hhgg</p>','1',1,'2021-03-13 00:00:00','2021-03-19 00:00:00','2021-03-11 10:32:09','969391',NULL,NULL,NULL,0,1,NULL,3,NULL),(1369840914768838657,'停服更新啦','<p>停服更新啦?</p>','1',1,'2021-03-10 00:00:00','2021-03-12 00:00:00','2021-03-11 10:41:39','969391',NULL,NULL,NULL,0,1,NULL,1,NULL),(1369843593167486977,'大多数','<p>山地车</p>','2',1,'2021-03-10 00:00:00','2021-03-12 00:00:00','2021-03-11 10:52:18','969391',NULL,NULL,NULL,0,1,NULL,1,NULL),(1369845039749382145,'从','<p>1</p>','1',1,'2021-03-10 00:00:00','2021-03-12 00:00:00','2021-03-11 10:58:03','969391',NULL,NULL,NULL,0,1,NULL,1,NULL),(1369845089632239618,'1','<p>1</p>','2',1,'2021-03-10 00:00:00','2021-03-12 00:00:00','2021-03-11 10:58:15','969391',NULL,NULL,NULL,0,1,NULL,1,NULL),(1369848967291453442,'ss','<p>s</p>','1',1,'2021-03-10 00:00:00','2021-03-12 00:00:00','2021-03-11 11:13:39','969391',NULL,NULL,NULL,0,0,NULL,1,NULL),(1369849029866274818,'ssss','<p>sssshhhhhhdddd</p>','2',1,'2021-03-10 00:00:00','2021-03-12 00:00:00','2021-03-11 11:13:54','969391',NULL,NULL,NULL,0,0,NULL,2,NULL);

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

/*Data for the table `table_system_operation_log` */

insert  into `table_system_operation_log`(`operation_log_id`,`title`,`business_type`,`method`,`request_method`,`operator_type`,`operator_name`,`department_name`,`url`,`ip`,`location`,`param`,`result`,`operation_status`,`error_message`,`operation_time`,`last_ip_number`,`operator_account`,`execute_time`) values (1368008241055473666,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:12:31',NULL,NULL,34),(1368008244863901698,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:12:36',NULL,NULL,3050),(1368008245161697281,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:12:36',NULL,NULL,34),(1368008245774065665,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:12:43',NULL,NULL,3050),(1368008246076055554,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:12:43',NULL,NULL,25),(1368008246344491009,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:12:52',NULL,NULL,430),(1368008246621315074,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:12:53',NULL,NULL,182),(1368008246931693569,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:12:53',NULL,NULL,88),(1368008247128825857,NULL,4,'/yeju-all-rest-api/platform/employee/status/list/','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:13:08',NULL,NULL,90),(1368008247342735361,NULL,4,'/yeju-all-rest-api/platform/department/getDepartmentTree','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:13:08',NULL,NULL,107),(1368008247661502466,NULL,4,'/yeju-all-rest-api/platform/employee/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:13:08',NULL,NULL,111),(1368008247984463874,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:14:32',NULL,NULL,448),(1368008248701689857,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:14:32',NULL,NULL,155),(1368008248970125314,NULL,4,'/yeju-all-rest-api/platform/department/getDepartmentTree','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:14:32',NULL,NULL,74),(1368008249234366465,NULL,4,'/yeju-all-rest-api/platform/employee/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:14:32',NULL,NULL,84),(1368008249439887361,NULL,4,'/yeju-all-rest-api/platform/employee/status/list/','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:14:32',NULL,NULL,82),(1368008249670574081,NULL,4,'/yeju-all-rest-api/platform/dataDictionary/type/status/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:14:38',NULL,NULL,125),(1368008249943203842,NULL,4,'/yeju-all-rest-api/platform/dataDictionary/type/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:14:38',NULL,NULL,259),(1368008250199056385,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:17:32',NULL,NULL,54),(1368008250496851970,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:20:32',NULL,NULL,39),(1368008250836590593,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 00:23:32',NULL,NULL,46),(1368008251125997569,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:07:25',NULL,NULL,16),(1368008251339907073,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:07:25',NULL,NULL,242),(1368008251625119746,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:07:26',NULL,NULL,6),(1368008251851612161,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:07:26',NULL,NULL,55),(1368008252078104578,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:07:29',NULL,NULL,489),(1368008252279431170,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:07:29',NULL,NULL,90),(1368008252493340673,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:07:30',NULL,NULL,170),(1368008252719833090,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:07:30',NULL,NULL,74),(1368008252988268546,NULL,4,'/yeju-all-rest-api/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:07:36',NULL,NULL,81),(1368008253244121089,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:07:36',NULL,NULL,125),(1368008253474807809,NULL,1,'/yeju-all-rest-api/notice/create','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:08:08',NULL,NULL,890),(1368008253747437569,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:08:10',NULL,NULL,2103),(1368008253957152769,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:10:27',NULL,NULL,38),(1368008254175256578,NULL,1,'/yeju-all-rest-api/notice/create','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:12:04',NULL,NULL,1432),(1368008254405943297,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:12:05',NULL,NULL,505),(1368008254607269889,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:13:27',NULL,NULL,40),(1368008254825373697,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:16:27',NULL,NULL,36),(1368008255035088897,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:17:15',NULL,NULL,91),(1368008257400676353,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:17:16',NULL,NULL,49),(1368008259925647362,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:17:19',NULL,NULL,349),(1368008260168916994,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:17:19',NULL,NULL,2),(1368008260412186626,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:17:19',NULL,NULL,230),(1368008260655456257,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:17:19',NULL,NULL,91),(1368008260936474626,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 01:20:16',NULL,NULL,50),(1368008261154578433,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 08:29:05',NULL,NULL,9),(1368008261393653762,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 08:32:05',NULL,NULL,8),(1368008261687255042,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 08:35:25',NULL,NULL,5),(1368008261905358850,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 08:38:05',NULL,NULL,10),(1368008262110879745,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 08:41:05',NULL,NULL,82),(1368008262316400641,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 08:44:25',NULL,NULL,14),(1368008262635167745,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 08:47:25',NULL,NULL,15),(1368008262832300033,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 08:50:05',NULL,NULL,35),(1368008263037820930,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 08:53:05',NULL,NULL,13),(1368008263251730434,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 08:56:05',NULL,NULL,11),(1368008263465639938,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 08:59:05',NULL,NULL,20),(1368008263696326658,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:02:05',NULL,NULL,10),(1368008263889264641,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:05:25',NULL,NULL,18),(1368008264111562754,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:08:05',NULL,NULL,12),(1368008264325472257,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:11:05',NULL,NULL,26),(1368008269073424386,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:14:05',NULL,NULL,8),(1368008296869076993,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:17:05',NULL,NULL,18),(1368008467845685249,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:20:05',NULL,NULL,25),(1368009222753296385,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:23:05',NULL,NULL,13),(1368009977740599298,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:26:05',NULL,NULL,8),(1368010327142899714,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:27:28',NULL,NULL,11),(1368010331471421442,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:27:29',NULL,NULL,9),(1368010375264149506,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:27:40',NULL,NULL,16),(1368010383732449282,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:27:42',NULL,NULL,14),(1368010388748836866,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:27:43',NULL,NULL,11),(1368010393308045314,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:27:44',NULL,NULL,7),(1368010410877984769,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:27:48',NULL,NULL,8),(1368010415747571714,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:27:50',NULL,NULL,11),(1368010419639885826,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:27:50',NULL,NULL,7),(1368010423498645505,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:27:51',NULL,NULL,7),(1368010501034549250,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:28:10',NULL,NULL,800),(1368010516142432258,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:28:13',NULL,NULL,614),(1368010517195202562,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:28:14',NULL,NULL,231),(1368010537617268737,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:28:19',NULL,NULL,182),(1368010548031725570,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:28:21',NULL,NULL,154),(1368010555069767681,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:28:23',NULL,NULL,168),(1368010561499635714,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:28:24',NULL,NULL,171),(1368010573281435650,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:28:27',NULL,NULL,152),(1368010578012610561,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:28:28',NULL,NULL,1101),(1368010609482473473,NULL,4,'/yeju-all-rest-api/log/login/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:28:36',NULL,NULL,670),(1368010781016924162,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:29:17',NULL,NULL,154),(1368010782531067906,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:29:17',NULL,NULL,69),(1368010785278337026,NULL,4,'/yeju-all-rest-api/log/login/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:29:18',NULL,NULL,300),(1368010913783422977,NULL,4,'/yeju-all-rest-api/log/login/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:29:48',NULL,NULL,166),(1368011006192328706,NULL,4,'/yeju-all-rest-api/log/login/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:30:10',NULL,NULL,169),(1368011049024561153,NULL,4,'/yeju-all-rest-api/log/login/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:30:21',NULL,NULL,64),(1368011139265011714,NULL,4,'/yeju-all-rest-api/platform/dataDictionary/type/job_status/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:30:42',NULL,NULL,109),(1368011140858847234,NULL,4,'/yeju-all-rest-api/job/jobGroup/getNameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:30:42',NULL,NULL,517),(1368011141332803585,NULL,4,'/yeju-all-rest-api/job/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:30:42',NULL,NULL,518),(1368011165122895873,NULL,4,'/yeju-all-rest-api/platform/role/status/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:30:48',NULL,NULL,87),(1368011165336805377,NULL,4,'/yeju-all-rest-api/platform/role/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:30:48',NULL,NULL,105),(1368011171842170882,NULL,4,'/yeju-all-rest-api/platform/employee/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:30:50',NULL,NULL,79),(1368011172106412033,NULL,4,'/yeju-all-rest-api/platform/employee/status/list/','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:30:50',NULL,NULL,68),(1368011172324515842,NULL,4,'/yeju-all-rest-api/platform/department/getDepartmentTree','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:30:50',NULL,NULL,88),(1368011190825590785,NULL,4,'/yeju-all-rest-api/platform/menu/status/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:30:54',NULL,NULL,66),(1368011194730487809,NULL,4,'/yeju-all-rest-api/platform/menu/findAll','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:30:55',NULL,NULL,997),(1368011538063630337,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:32:17',NULL,NULL,33),(1368012073621725186,NULL,4,'/yeju-all-rest-api/platform/dataDictionary/type/status/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:34:25',NULL,NULL,79),(1368012074200539137,NULL,4,'/yeju-all-rest-api/platform/dataDictionary/type/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:34:25',NULL,NULL,103),(1368012093183959042,NULL,4,'/yeju-all-rest-api/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:34:29',NULL,NULL,99),(1368012093683081217,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:34:30',NULL,NULL,140),(1368012110028283906,NULL,4,'/yeju-all-rest-api/platform/online/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:34:33',NULL,NULL,185),(1368012118525943809,NULL,4,'/yeju-all-rest-api/job/jobGroup/getNameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:34:36',NULL,NULL,32),(1368012118735659009,NULL,4,'/yeju-all-rest-api/job/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:34:36',NULL,NULL,55),(1368012118941179906,NULL,4,'/yeju-all-rest-api/platform/dataDictionary/type/job_status/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:34:36',NULL,NULL,78),(1368012138486636546,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:34:40',NULL,NULL,147),(1368012138922844161,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:34:40',NULL,NULL,72),(1368012141829496833,NULL,4,'/yeju-all-rest-api/job/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:34:41',NULL,NULL,35),(1368012142030823425,NULL,4,'/yeju-all-rest-api/job/jobGroup/getNameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:34:41',NULL,NULL,46),(1368012142295064578,NULL,4,'/yeju-all-rest-api/platform/dataDictionary/type/job_status/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:34:41',NULL,NULL,66),(1368012152994734081,NULL,4,'/yeju-all-rest-api/platform/dataDictionary/type/gender/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:34:44',NULL,NULL,121),(1368012153275752450,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:34:44',NULL,NULL,137),(1368012292132380674,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:35:17',NULL,NULL,151),(1368012293675884545,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:35:17',NULL,NULL,72),(1368012295265525762,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:35:18',NULL,NULL,77),(1368012295500406785,NULL,4,'/yeju-all-rest-api/platform/dataDictionary/type/gender/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:35:18',NULL,NULL,77),(1368013046238879745,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:38:17',NULL,NULL,44),(1368013545486884865,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:40:16',NULL,NULL,42),(1368013548049604609,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:40:16',NULL,NULL,84),(1368013553758052353,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:40:18',NULL,NULL,10),(1368013561177776129,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:40:19',NULL,NULL,51),(1368013580169584642,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:40:24',NULL,NULL,406),(1368013580891004929,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:40:24',NULL,NULL,140),(1368013581218160642,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:40:24',NULL,NULL,60),(1368013597991182337,NULL,4,'/yeju-all-rest-api/platform/dataDictionary/type/gender/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:40:28',NULL,NULL,70),(1368013598544830466,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:40:28',NULL,NULL,93),(1368014314663518210,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:43:19',NULL,NULL,30),(1368015069705347074,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:46:19',NULL,NULL,41),(1368015824692649986,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 09:49:19',NULL,NULL,44),(1368019106806833153,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:02:22',NULL,NULL,10),(1368019110422323202,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:02:23',NULL,NULL,517),(1368019113702268929,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:02:23',NULL,NULL,9),(1368019118773182466,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:02:24',NULL,NULL,43),(1368019128956952578,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:02:27',NULL,NULL,387),(1368019129892282369,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:02:27',NULL,NULL,169),(1368019130240409601,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:02:27',NULL,NULL,101),(1368019146396868610,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:02:31',NULL,NULL,74),(1368019146648526849,NULL,4,'/yeju-all-rest-api/platform/dataDictionary/type/gender/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:02:31',NULL,NULL,90),(1368019602053472258,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:04:20',NULL,NULL,79),(1368019607191494657,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:04:21',NULL,NULL,38),(1368019622681059329,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:04:25',NULL,NULL,275),(1368019623427645442,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:04:25',NULL,NULL,155),(1368019623767384065,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:04:25',NULL,NULL,66),(1368019795012427777,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:05:06',NULL,NULL,184),(1368019795910008833,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:05:06',NULL,NULL,89),(1368019844991754241,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:05:18',NULL,NULL,116),(1368019849773260802,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:05:19',NULL,NULL,31),(1368019863664795649,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:05:22',NULL,NULL,284),(1368019864436547586,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:05:22',NULL,NULL,160),(1368019864784674817,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:05:22',NULL,NULL,71),(1368020063087173634,NULL,4,'/auth-consumer/v3/api-docs','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:06:10',NULL,NULL,370),(1368020078429937665,NULL,4,'/yeju-notice/v3/api-docs','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:06:13',NULL,NULL,157),(1368020110801575938,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:06:21',NULL,NULL,74),(1368020115734077442,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:06:22',NULL,NULL,35),(1368020126999977986,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:06:25',NULL,NULL,314),(1368020127473934338,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:06:25',NULL,NULL,5),(1368020127973056513,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:06:25',NULL,NULL,153),(1368020128316989441,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:06:25',NULL,NULL,54),(1368020869177876481,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:09:22',NULL,NULL,39),(1368021624190345218,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:12:22',NULL,NULL,41),(1368021975362641921,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:13:46',NULL,NULL,150),(1368021976616738818,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:13:46',NULL,NULL,65),(1368022009835626498,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:13:54',NULL,NULL,36),(1368022022322069506,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:13:57',NULL,NULL,333),(1368022022573727746,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:13:57',NULL,NULL,1),(1368022023018323969,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:13:57',NULL,NULL,152),(1368022023425171458,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:13:57',NULL,NULL,83),(1368022074344022017,NULL,4,'/yeju-all-rest-api/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:14:09',NULL,NULL,78),(1368022075950440450,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:14:09',NULL,NULL,131),(1368022763287814145,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:16:53',NULL,NULL,42),(1368023031853293569,NULL,1,'/yeju-all-rest-api/notice/create','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:17:57',NULL,NULL,718),(1368023033967222785,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:17:58',NULL,NULL,496),(1368023383436632065,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:19:21',NULL,NULL,88),(1368023390583726081,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:19:23',NULL,NULL,39),(1368023403770617858,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:19:26',NULL,NULL,361),(1368023406375280642,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:19:26',NULL,NULL,2),(1368023406618550273,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:19:27',NULL,NULL,611),(1368023406975066113,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:19:27',NULL,NULL,64),(1368023695866142721,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:20:36',NULL,NULL,147),(1368023697233485826,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:20:36',NULL,NULL,65),(1368023746030018562,NULL,4,'/yeju-all-rest-api/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:20:48',NULL,NULL,65),(1368023746759827457,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:20:48',NULL,NULL,239),(1368023812996276226,NULL,1,'/yeju-all-rest-api/notice/create','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:21:04',NULL,NULL,174),(1368023814183264257,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:21:04',NULL,NULL,235),(1368024452422115329,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:23:36',NULL,NULL,33),(1368025207363280897,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:26:36',NULL,NULL,35),(1368025962417692674,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:29:36',NULL,NULL,46),(1368027096314224642,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:34:06',NULL,NULL,795),(1368027097010479106,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:34:07',NULL,NULL,73),(1368027100902793218,NULL,4,'/yeju-all-rest-api/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:34:08',NULL,NULL,101),(1368027101376749570,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:34:08',NULL,NULL,140),(1368027133966491650,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:34:15',NULL,NULL,81),(1368027139813351426,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:34:17',NULL,NULL,138),(1368027153235124226,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:34:20',NULL,NULL,327),(1368027153428062209,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:34:20',NULL,NULL,2),(1368027153939767297,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:34:20',NULL,NULL,168),(1368027154220785665,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:34:20',NULL,NULL,58),(1368027195085889538,NULL,4,'/yeju-all-rest-api/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:34:30',NULL,NULL,71),(1368027195668897794,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:34:30',NULL,NULL,131),(1368027248450019330,NULL,1,'/yeju-all-rest-api/notice/create','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:34:43',NULL,NULL,500),(1368027249523761154,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:34:43',NULL,NULL,255),(1368027895886979074,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:37:17',NULL,NULL,32),(1368028650832338945,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:40:17',NULL,NULL,28),(1368029436102516738,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:43:24',NULL,NULL,144),(1368029437797015554,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:43:24',NULL,NULL,73),(1368029440355540993,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:43:25',NULL,NULL,1),(1368029441760632834,NULL,4,'/yeju-all-rest-api/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:43:26',NULL,NULL,95),(1368029442024873986,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:43:26',NULL,NULL,150),(1368029653719785474,NULL,1,'/yeju-all-rest-api/notice/create','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:44:16',NULL,NULL,449),(1368029655003242497,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:44:17',NULL,NULL,258),(1368030190250958850,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:46:24',NULL,NULL,42),(1368030314209419265,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:46:54',NULL,NULL,160),(1368030315719368706,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:46:54',NULL,NULL,87),(1368030320907722754,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:46:55',NULL,NULL,1),(1368030322157625346,NULL,4,'/yeju-all-rest-api/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:46:56',NULL,NULL,75),(1368030322434449410,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:46:56',NULL,NULL,152),(1368030396669435906,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:47:13',NULL,NULL,129),(1368031071117713410,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:49:54',NULL,NULL,55),(1368031593916735490,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:51:59',NULL,NULL,782),(1368031594411663362,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:51:59',NULL,NULL,98),(1368031597687414785,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:52:00',NULL,NULL,1),(1368031600921223169,NULL,4,'/yeju-all-rest-api/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:52:00',NULL,NULL,93),(1368031601193852930,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:52:00',NULL,NULL,134),(1368031699231514625,NULL,1,'/yeju-all-rest-api/notice/create','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:52:24',NULL,NULL,347),(1368031701064425474,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:52:24',NULL,NULL,396),(1368032094745993218,NULL,1,'/yeju-all-rest-api/notice/create','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:53:58',NULL,NULL,487),(1368032096197222402,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:53:59',NULL,NULL,310),(1368032346110631938,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:54:58',NULL,NULL,37),(1368032359104585730,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:55:01',NULL,NULL,154),(1368032360782307329,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:55:02',NULL,NULL,72),(1368032365672865793,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:55:03',NULL,NULL,2),(1368032367010848769,NULL,4,'/yeju-all-rest-api/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:55:03',NULL,NULL,86),(1368032367212175361,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:55:03',NULL,NULL,126),(1368032470719209474,NULL,1,'/yeju-all-rest-api/notice/create','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:55:28',NULL,NULL,194),(1368032472518565889,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:55:28',NULL,NULL,389),(1368033113672458242,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:58:01',NULL,NULL,38),(1368033503709175809,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:59:34',NULL,NULL,812),(1368033505634361345,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:59:34',NULL,NULL,87),(1368033507286917121,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:59:35',NULL,NULL,1),(1368033508436156418,NULL,4,'/yeju-all-rest-api/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:59:35',NULL,NULL,124),(1368033508624900097,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 10:59:35',NULL,NULL,167),(1368033612979183617,NULL,1,'/yeju-all-rest-api/notice/create','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 11:00:00',NULL,NULL,290),(1368033616053608450,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 11:00:01',NULL,NULL,704),(1368034258730033154,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 11:02:34',NULL,NULL,44),(1368035013683781634,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 11:05:34',NULL,NULL,31),(1368035083577663490,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 11:05:51',NULL,NULL,147),(1368035084328443905,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 11:05:51',NULL,NULL,73),(1368035087881019393,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 11:05:52',NULL,NULL,2),(1368035089365803009,NULL,4,'/yeju-all-rest-api/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 11:05:52',NULL,NULL,214),(1368035089558740994,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 11:05:52',NULL,NULL,264),(1368035210484719617,NULL,1,'/yeju-all-rest-api/notice/create','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 11:06:21',NULL,NULL,175),(1368035212107915266,NULL,4,'/yeju-all-rest-api/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 11:06:21',NULL,NULL,350),(1368035839965863937,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 11:08:51',NULL,NULL,34),(1368036595053830145,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 11:11:51',NULL,NULL,55),(1368037349898526721,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 11:14:51',NULL,NULL,31),(1368070387650994178,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 13:26:08',NULL,NULL,10),(1368070390456983554,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 13:26:08',NULL,NULL,227),(1368070395871830018,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 13:26:10',NULL,NULL,9),(1368070403971031041,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 13:26:12',NULL,NULL,75),(1368070427224252418,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 13:26:17',NULL,NULL,3068),(1368070427454939137,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 13:26:17',NULL,NULL,37),(1368070454915047426,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 13:26:24',NULL,NULL,3050),(1368070455368032258,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 13:26:24',NULL,NULL,30),(1368084254330396673,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:21:14',NULL,NULL,302),(1368084265403359234,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:21:16',NULL,NULL,438),(1368084265642434561,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:21:16',NULL,NULL,2),(1368084266267385857,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:21:17',NULL,NULL,187),(1368084268242903041,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:21:17',NULL,NULL,457),(1368084270428135426,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:21:18',NULL,NULL,1),(1368084371590553601,NULL,4,'/yeju-all-rest-api/message/group/nameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:21:42',NULL,NULL,79),(1368084372194533377,NULL,4,'/yeju-all-rest-api/message/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:21:42',NULL,NULL,86),(1368084372404248577,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:21:42',NULL,NULL,168),(1368084642559369218,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:22:46',NULL,NULL,134),(1368084643154960385,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:22:46',NULL,NULL,59),(1368084645998698497,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:22:47',NULL,NULL,1),(1368084647009525761,NULL,4,'/yeju-all-rest-api/message/group/nameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:22:47',NULL,NULL,70),(1368084647198269442,NULL,4,'/yeju-all-rest-api/message/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:22:47',NULL,NULL,109),(1368084647420567553,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:22:47',NULL,NULL,168),(1368085035968307201,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:24:20',NULL,NULL,180),(1368085036631007233,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:24:20',NULL,NULL,75),(1368085038736547841,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:24:21',NULL,NULL,1),(1368085039759958017,NULL,4,'/yeju-all-rest-api/message/group/nameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:24:21',NULL,NULL,57),(1368085039969673217,NULL,4,'/yeju-all-rest-api/message/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:24:21',NULL,NULL,85),(1368085040191971329,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:24:21',NULL,NULL,141),(1368085181665845250,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:24:55',NULL,NULL,131),(1368085183213543425,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:24:55',NULL,NULL,53),(1368085185235197954,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:24:56',NULL,NULL,2),(1368085186099224577,NULL,4,'/yeju-all-rest-api/message/group/nameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:24:56',NULL,NULL,57),(1368085186308939777,NULL,4,'/yeju-all-rest-api/message/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:24:56',NULL,NULL,70),(1368085186527043585,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:24:56',NULL,NULL,140),(1368085529562390529,NULL,1,'/yeju-all-rest-api/message/notice/create','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:26:18',NULL,NULL,198),(1368085937882079234,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:27:55',NULL,NULL,44),(1368086542927208450,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:30:19',NULL,NULL,84),(1368086547935207425,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:30:21',NULL,NULL,44),(1368086567459692546,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:30:25',NULL,NULL,306),(1368086567677796353,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:30:25',NULL,NULL,1),(1368086568210472961,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:30:25',NULL,NULL,149),(1368086568810258433,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:30:25',NULL,NULL,57),(1368086571737882626,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:30:26',NULL,NULL,2),(1368087040900145153,NULL,4,'/yeju-all-rest-api/message/group/nameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:32:18',NULL,NULL,49),(1368087041525096450,NULL,4,'/yeju-all-rest-api/message/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:32:18',NULL,NULL,102),(1368087041713840129,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:32:18',NULL,NULL,142),(1368087169459757057,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:32:49',NULL,NULL,152),(1368087170986483713,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:32:49',NULL,NULL,57),(1368087173243019265,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:32:50',NULL,NULL,1),(1368087174090268674,NULL,4,'/yeju-all-rest-api/message/group/nameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:32:50',NULL,NULL,49),(1368087174450978817,NULL,4,'/yeju-all-rest-api/message/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:32:50',NULL,NULL,100),(1368087175122067458,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:32:50',NULL,NULL,292),(1368087377463681025,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:33:38',NULL,NULL,99),(1368087408744800257,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:33:46',NULL,NULL,118),(1368087926909116417,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:35:49',NULL,NULL,31),(1368088680801705986,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:38:49',NULL,NULL,32),(1368089435839340546,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:41:49',NULL,NULL,43),(1368091247384403970,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:49:01',NULL,NULL,141),(1368091249355726850,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:49:01',NULL,NULL,72),(1368091249825488898,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:49:02',NULL,NULL,2),(1368091250823733250,NULL,4,'/yeju-all-rest-api/message/group/nameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:49:02',NULL,NULL,56),(1368091251004088321,NULL,4,'/yeju-all-rest-api/message/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:49:02',NULL,NULL,82),(1368091251213803522,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:49:02',NULL,NULL,141),(1368091456411738114,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:49:51',NULL,NULL,40),(1368091496467341313,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:00',NULL,NULL,396),(1368091497880821761,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:01',NULL,NULL,143),(1368091498279280642,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:01',NULL,NULL,60),(1368091536166428673,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:10',NULL,NULL,30),(1368091537949007873,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:10',NULL,NULL,40),(1368091565333618689,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:17',NULL,NULL,7),(1368091568483540994,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:18',NULL,NULL,33),(1368091609034072065,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:27',NULL,NULL,168),(1368091609721937921,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:27',NULL,NULL,61),(1368091653153955842,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:38',NULL,NULL,380),(1368091653414002689,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:38',NULL,NULL,2),(1368091654168977410,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:38',NULL,NULL,209),(1368091654512910337,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:38',NULL,NULL,58),(1368091656475844610,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:39',NULL,NULL,2),(1368091683520716801,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:45',NULL,NULL,31),(1368091686481895425,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:46',NULL,NULL,36),(1368091692853043201,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:47',NULL,NULL,11),(1368091696992821249,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:48',NULL,NULL,30),(1368091724704587778,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:55',NULL,NULL,373),(1368091726424252418,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:55',NULL,NULL,151),(1368091726923374593,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:50:55',NULL,NULL,58),(1368091798637584386,NULL,4,'/yeju-all-rest-api/message/group/nameAndId','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:51:12',NULL,NULL,70),(1368091799346421761,NULL,4,'/yeju-all-rest-api/message/notice/type/list','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:51:12',NULL,NULL,73),(1368091799556136962,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:51:13',NULL,NULL,143),(1368092027143266306,NULL,1,'/yeju-all-rest-api/message/notice/create','POST',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:52:07',NULL,NULL,338),(1368092028690964482,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:52:07',NULL,NULL,217),(1368092113021640705,NULL,4,'/yeju-all-rest-api/platform/online/list/1','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:52:27',NULL,NULL,70),(1368092133573730306,NULL,4,'/yeju-all-rest-api/platform/online/list/1','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:52:32',NULL,NULL,27),(1368092141379330049,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:52:34',NULL,NULL,27),(1368092145699463170,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 14:52:35',NULL,NULL,30),(1368092157447708674,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:52:38',NULL,NULL,27),(1368092158039105538,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:52:38',NULL,NULL,27),(1368092173197320194,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:52:42',NULL,NULL,9),(1368092175021842434,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:52:42',NULL,NULL,20),(1368093062670786561,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:56:14',NULL,NULL,274),(1368093062855335938,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:56:14',NULL,NULL,2),(1368093063685808130,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:56:14',NULL,NULL,219),(1368093063933272065,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:56:14',NULL,NULL,37),(1368093064126210049,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:56:14',NULL,NULL,444),(1368093064411422722,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:56:14',NULL,NULL,0),(1368093065409667074,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:56:14',NULL,NULL,1),(1368093074406449153,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:56:17',NULL,NULL,22),(1368093085408108546,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:56:19',NULL,NULL,257),(1368093085680738305,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:56:19',NULL,NULL,1),(1368093086075002882,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:56:19',NULL,NULL,141),(1368093086381187074,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:56:19',NULL,NULL,54),(1368093087584952322,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:56:20',NULL,NULL,1),(1368093831465738242,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 14:59:17',NULL,NULL,40),(1368094086051602433,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:00:18',NULL,NULL,315),(1368094087448305665,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:00:18',NULL,NULL,163),(1368094087964205057,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:00:18',NULL,NULL,57),(1368094096977764353,NULL,4,'/yeju-all-rest-api/platform/online/list/1','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:00:20',NULL,NULL,72),(1368094463589294082,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:01:48',NULL,NULL,466),(1368094464163913729,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:01:48',NULL,NULL,54),(1368094470212100097,NULL,4,'/yeju-all-rest-api/platform/online/list/1','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:01:49',NULL,NULL,70),(1368094485584224258,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:01:53',NULL,NULL,221),(1368094491393335298,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:01:54',NULL,NULL,26),(1368094509969907714,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:01:59',NULL,NULL,42),(1368094511144312833,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:01:59',NULL,NULL,24),(1368094520908652546,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:02:01',NULL,NULL,8),(1368094523085496322,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:02:02',NULL,NULL,21),(1368094537216106498,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:02:05',NULL,NULL,613),(1368094537929138178,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:02:05',NULL,NULL,1),(1368094538445037570,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:02:06',NULL,NULL,186),(1368094538646364162,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:02:06',NULL,NULL,57),(1368094539736883201,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:02:06',NULL,NULL,1),(1368094567087939586,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:02:12',NULL,NULL,322),(1368094568291704834,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:02:13',NULL,NULL,152),(1368094568904073218,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:02:13',NULL,NULL,63),(1368095051643297794,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:04:08',NULL,NULL,368),(1368095052331163650,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:04:08',NULL,NULL,63),(1368095058370961409,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:04:10',NULL,NULL,14),(1368095060501667842,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:04:10',NULL,NULL,24),(1368095069028687874,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:04:12',NULL,NULL,410),(1368095069234208770,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:04:12',NULL,NULL,1),(1368095069792051202,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:04:12',NULL,NULL,157),(1368095070123401218,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:04:12',NULL,NULL,52),(1368095071339749377,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:04:13',NULL,NULL,1),(1368095105741430786,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:04:21',NULL,NULL,44),(1368095109197537281,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:04:22',NULL,NULL,20),(1368095112787861505,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:04:23',NULL,NULL,6),(1368095120845119489,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:04:25',NULL,NULL,18),(1368095151614533633,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:04:32',NULL,NULL,318),(1368095152692469762,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:04:32',NULL,NULL,155),(1368095153162231810,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:04:32',NULL,NULL,54),(1368095182090346498,NULL,4,'/yeju-all-rest-api/platform/online/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:04:39',NULL,NULL,27),(1368095194354491394,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:04:42',NULL,NULL,68),(1368095196564889602,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:04:43',NULL,NULL,26),(1368095545208020994,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:06:06',NULL,NULL,344),(1368095547196121090,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:06:06',NULL,NULL,46),(1368095557593800706,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:06:09',NULL,NULL,17),(1368095563759427586,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:06:10',NULL,NULL,24),(1368095580859604993,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:06:14',NULL,NULL,344),(1368095582155644930,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:06:14',NULL,NULL,143),(1368095582696710146,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:06:15',NULL,NULL,60),(1368095811865092098,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:07:09',NULL,NULL,45),(1368095856144359425,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:07:20',NULL,NULL,410),(1368095856987414530,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:07:20',NULL,NULL,135),(1368095857402650626,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:07:20',NULL,NULL,52),(1368096183161659393,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:08:38',NULL,NULL,43),(1368096225649958913,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:08:48',NULL,NULL,284),(1368096226685952002,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:08:48',NULL,NULL,133),(1368096227189268481,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:08:48',NULL,NULL,69),(1368096722624651266,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:10:46',NULL,NULL,528),(1368096723379625986,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:10:47',NULL,NULL,57),(1368096748373483521,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:10:53',NULL,NULL,80),(1368096753444397058,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:10:54',NULL,NULL,31),(1368096787309207554,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:11:02',NULL,NULL,367),(1368096788454252546,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:11:02',NULL,NULL,139),(1368096788882071554,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:11:02',NULL,NULL,54),(1368097255947182082,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:12:54',NULL,NULL,65),(1368097262045700097,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:12:55',NULL,NULL,22),(1368097279099740162,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:12:59',NULL,NULL,285),(1368097280542580738,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:12:59',NULL,NULL,169),(1368097280974594050,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:13:00',NULL,NULL,49),(1368097405562200066,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:13:29',NULL,NULL,25),(1368097420762357762,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:13:33',NULL,NULL,262),(1368097421580247042,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:13:33',NULL,NULL,126),(1368097421991288834,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:13:33',NULL,NULL,47),(1368098356998119426,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:17:16',NULL,NULL,484),(1368098357568544770,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:17:16',NULL,NULL,56),(1368098384542113793,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:17:23',NULL,NULL,78),(1368098391248805889,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:17:24',NULL,NULL,37),(1368098407669506049,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:17:28',NULL,NULL,373),(1368098408961351681,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:17:28',NULL,NULL,147),(1368098409229787138,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:17:29',NULL,NULL,58),(1368098487952678913,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:17:47',NULL,NULL,255),(1368098489114501122,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:17:47',NULL,NULL,1),(1368098489340993537,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:17:47',NULL,NULL,148),(1368098489542320129,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:17:48',NULL,NULL,47),(1368098490255351810,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:17:48',NULL,NULL,2),(1368098492172148738,NULL,4,'/yeju-all-rest-api/platform/online/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:17:48',NULL,NULL,80),(1368098508420882434,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:17:52',NULL,NULL,140),(1368098508836118530,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:17:52',NULL,NULL,61),(1368098549164351490,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:18:02',NULL,NULL,65),(1368098551953563649,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:18:03',NULL,NULL,22),(1368098576968392705,NULL,4,'/yeju-all-rest-api/platform/online/list/1','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:18:08',NULL,NULL,51),(1368099265652137986,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:20:53',NULL,NULL,44),(1368100624979927041,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:26:17',NULL,NULL,47),(1368101803440939009,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:30:58',NULL,NULL,1063),(1368101803659042817,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:30:58',NULL,NULL,1),(1368101804455960578,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:30:58',NULL,NULL,218),(1368101807211618305,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:30:59',NULL,NULL,630),(1368101808499269634,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:30:59',NULL,NULL,2),(1368101846310920194,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:31:08',NULL,NULL,151),(1368101846805848065,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:31:08',NULL,NULL,63),(1368101855576137729,NULL,4,'/yeju-all-rest-api/platform/online/list/1','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:31:10',NULL,NULL,87),(1368101987604439042,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:31:42',NULL,NULL,136),(1368102028989636610,NULL,4,'/yeju-all-rest-api/platform/online/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:31:52',NULL,NULL,47),(1368102059897462785,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:31:59',NULL,NULL,87),(1368102062565040130,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:31:59',NULL,NULL,39),(1368102132949655553,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:32:16',NULL,NULL,28),(1368102677554864129,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:34:26',NULL,NULL,279),(1368102678544719873,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:34:26',NULL,NULL,1),(1368102679253557250,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:34:26',NULL,NULL,167),(1368102679446495234,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:34:26',NULL,NULL,53),(1368102679995949057,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:34:27',NULL,NULL,1),(1368102680759312386,NULL,4,'/yeju-all-rest-api/platform/online/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:34:27',NULL,NULL,82),(1368102819901153282,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:35:00',NULL,NULL,45),(1368103368230903810,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:37:11',NULL,NULL,777),(1368103369589858306,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:37:11',NULL,NULL,151),(1368103370151895042,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:37:11',NULL,NULL,65),(1368103493644787713,NULL,4,'/yeju-all-rest-api/platform/online/list/1','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:37:41',NULL,NULL,108),(1368103574972342273,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:38:00',NULL,NULL,45),(1368103664537509889,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:38:21',NULL,NULL,306),(1368103665216987138,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:38:22',NULL,NULL,124),(1368103666424946690,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:38:22',NULL,NULL,1),(1368103668085891073,NULL,4,'/yeju-all-rest-api/platform/online/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:38:22',NULL,NULL,204),(1368103788709879810,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'192.168.1.105',NULL,NULL,NULL,0,NULL,'2021-03-06 15:38:51',NULL,NULL,42),(1368104426424442881,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:41:23',NULL,NULL,1068),(1368105177154527234,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:44:22',NULL,NULL,57),(1368105932057944066,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 15:47:22',NULL,NULL,40),(1368122007864680449,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:15',NULL,NULL,28),(1368122008502214657,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:15',NULL,NULL,34),(1368122013132726273,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:16',NULL,NULL,6),(1368122015166963714,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:17',NULL,NULL,6),(1368122027569520641,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:20',NULL,NULL,7),(1368122034792112129,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:21',NULL,NULL,4),(1368122041633021953,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:23',NULL,NULL,6),(1368122058078887937,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:27',NULL,NULL,9),(1368122062520655874,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:28',NULL,NULL,7),(1368122067771924482,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:29',NULL,NULL,313),(1368122079855714306,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:32',NULL,NULL,238),(1368122080086401025,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:32',NULL,NULL,38),(1368122093348790274,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:35',NULL,NULL,7),(1368122093956964354,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:35',NULL,NULL,29),(1368122097270464513,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:36',NULL,NULL,4),(1368122097501151234,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:36',NULL,NULL,23),(1368122102022610946,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:37',NULL,NULL,457),(1368122103809384449,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:38',NULL,NULL,402),(1368122122750861313,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:42',NULL,NULL,131),(1368122132158685185,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:44',NULL,NULL,138),(1368122158633132033,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:51',NULL,NULL,145),(1368122160394739713,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:51',NULL,NULL,400),(1368122161661419522,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:52',NULL,NULL,2),(1368122191709413378,NULL,4,'/yeju-all-rest-api/message/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:59',NULL,NULL,95),(1368122192128843777,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:59',NULL,NULL,167),(1368122192384696321,NULL,4,'/yeju-all-rest-api/message/group/nameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:51:59',NULL,NULL,242),(1368122268372901889,NULL,1,'/yeju-all-rest-api/message/notice/create','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:52:17',NULL,NULL,476),(1368122270235172866,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:52:17',NULL,NULL,413),(1368122914887114753,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:54:51',NULL,NULL,36),(1368123669937332225,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 16:57:51',NULL,NULL,36),(1368124424874303489,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:00:51',NULL,NULL,42),(1368124955160158209,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:02:58',NULL,NULL,135),(1368124955462148098,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:02:58',NULL,NULL,57),(1368124956762382337,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:02:58',NULL,NULL,1),(1368124957769015298,NULL,4,'/yeju-all-rest-api/message/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:02:58',NULL,NULL,64),(1368124958020673537,NULL,4,'/yeju-all-rest-api/message/group/nameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:02:58',NULL,NULL,70),(1368124958238777345,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:02:58',NULL,NULL,124),(1368125042611396609,NULL,1,'/yeju-all-rest-api/message/notice/create','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:03:18',NULL,NULL,222),(1368125043781607426,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:03:19',NULL,NULL,252),(1368125505805164546,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:05:09',NULL,NULL,165),(1368125507268976642,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:05:09',NULL,NULL,51),(1368125507587743745,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:05:09',NULL,NULL,1),(1368125508338524161,NULL,4,'/yeju-all-rest-api/message/group/nameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:05:09',NULL,NULL,49),(1368125508556627970,NULL,4,'/yeju-all-rest-api/message/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:05:09',NULL,NULL,73),(1368125508766343169,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:05:09',NULL,NULL,115),(1368125572737867778,NULL,1,'/yeju-all-rest-api/message/notice/create','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:05:25',NULL,NULL,163),(1368125573970993153,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:05:25',NULL,NULL,268),(1368126261933318145,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:08:09',NULL,NULL,37),(1368127016966758402,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:11:09',NULL,NULL,33),(1368127771886952449,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:14:09',NULL,NULL,35),(1368133488886882306,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:36:52',NULL,NULL,8),(1368133491139223554,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:36:53',NULL,NULL,257),(1368133495820066817,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:36:54',NULL,NULL,5),(1368133497946578945,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:36:54',NULL,NULL,34),(1368133506989498369,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:36:56',NULL,NULL,407),(1368133507849330689,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:36:57',NULL,NULL,185),(1368133509589966850,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:36:57',NULL,NULL,403),(1368133561318318082,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:37:09',NULL,NULL,139),(1368133561645473793,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:37:09',NULL,NULL,59),(1368133562790518786,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:37:10',NULL,NULL,1),(1368133638401236993,NULL,4,'/yeju-all-rest-api/message/group/nameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:37:28',NULL,NULL,54),(1368133638946496513,NULL,4,'/yeju-all-rest-api/message/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:37:28',NULL,NULL,97),(1368133639168794625,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:37:28',NULL,NULL,190),(1368133698656608257,NULL,1,'/yeju-all-rest-api/message/notice/create','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:37:42',NULL,NULL,364),(1368133700212695042,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:37:42',NULL,NULL,338),(1368134099825008642,NULL,1,'/yeju-all-rest-api/message/notice/create','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:39:18',NULL,NULL,247),(1368134100991025153,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:39:18',NULL,NULL,241),(1368134319304548354,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:40:10',NULL,NULL,52),(1368135071569747970,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:43:09',NULL,NULL,34),(1368135829220433922,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:46:10',NULL,NULL,39),(1368136584190959618,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:49:10',NULL,NULL,39),(1368137339245371393,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:52:10',NULL,NULL,50),(1368138094064902146,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:55:10',NULL,NULL,30),(1368138314513326082,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:56:03',NULL,NULL,555),(1368138316434317314,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:56:03',NULL,NULL,75),(1368138316723724289,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:56:03',NULL,NULL,0),(1368138317155737602,NULL,4,'/yeju-all-rest-api/message/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:56:03',NULL,NULL,87),(1368138317399007234,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:56:03',NULL,NULL,133),(1368138317831020545,NULL,4,'/yeju-all-rest-api/message/group/nameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:56:03',NULL,NULL,239),(1368138402258165762,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:56:24',NULL,NULL,160),(1368138403042500610,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:56:24',NULL,NULL,64),(1368138404049133570,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:56:24',NULL,NULL,1),(1368138404778942466,NULL,4,'/yeju-all-rest-api/message/group/nameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:56:24',NULL,NULL,61),(1368138405013823489,NULL,4,'/yeju-all-rest-api/message/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:56:24',NULL,NULL,75),(1368138405739438082,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:56:24',NULL,NULL,292),(1368138450308112386,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:56:35',NULL,NULL,142),(1368138451256025090,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:56:35',NULL,NULL,53),(1368138454489833474,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:56:36',NULL,NULL,1),(1368138455370637313,NULL,4,'/yeju-all-rest-api/message/group/nameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:56:36',NULL,NULL,51),(1368138455555186689,NULL,4,'/yeju-all-rest-api/message/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:56:36',NULL,NULL,96),(1368138455798456322,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:56:36',NULL,NULL,137),(1368139031437320193,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:58:54',NULL,NULL,657),(1368139031802224642,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:58:54',NULL,NULL,50),(1368139087976538113,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:59:07',NULL,NULL,140),(1368139088832176129,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:59:07',NULL,NULL,77),(1368139091252289537,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:59:08',NULL,NULL,1),(1368139092275699713,NULL,4,'/yeju-all-rest-api/message/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:59:08',NULL,NULL,84),(1368139093265555458,NULL,4,'/yeju-all-rest-api/message/group/nameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:59:08',NULL,NULL,313),(1368139094163136514,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 17:59:09',NULL,NULL,535),(1368139716019036161,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:01:37',NULL,NULL,175),(1368139718690807809,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:01:37',NULL,NULL,466),(1368139721853313026,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:01:38',NULL,NULL,1),(1368139722826391554,NULL,4,'/yeju-all-rest-api/message/group/nameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:01:38',NULL,NULL,88),(1368139723048689666,NULL,4,'/yeju-all-rest-api/message/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:01:38',NULL,NULL,104),(1368139723631697922,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:01:39',NULL,NULL,280),(1368140037030092802,NULL,4,'/auth-consumer/authz/simple/info','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:02:53',NULL,NULL,629),(1368140039836082177,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:02:54',NULL,NULL,650),(1368140042864369665,NULL,4,'/yeju-notice/ws/notice','GET',NULL,NULL,NULL,NULL,'172.17.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:02:55',NULL,NULL,2),(1368140044466593794,NULL,4,'/yeju-all-rest-api/message/notice/type/list','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:02:55',NULL,NULL,99),(1368140044726640641,NULL,4,'/yeju-all-rest-api/message/notice/list/1','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:02:55',NULL,NULL,198),(1368140044982493185,NULL,4,'/yeju-all-rest-api/message/group/nameAndId','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:02:55',NULL,NULL,308),(1368140791065288705,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:05:53',NULL,NULL,6),(1368141545914179585,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:08:53',NULL,NULL,8),(1368143056492765185,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:14:53',NULL,NULL,11),(1368143810825756674,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:17:53',NULL,NULL,5),(1368144565804670978,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:20:53',NULL,NULL,8),(1368145321773441026,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:23:53',NULL,NULL,10),(1368146075817025538,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:26:53',NULL,NULL,13),(1368146830728830977,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:29:53',NULL,NULL,6),(1368147585841963009,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:32:53',NULL,NULL,5),(1368148340699242498,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:35:53',NULL,NULL,8),(1368149095686545410,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:38:53',NULL,NULL,8),(1368149850782900225,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:41:53',NULL,NULL,6),(1368150605640179714,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:44:53',NULL,NULL,7),(1368151360673619969,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:47:53',NULL,NULL,10),(1368152115778363394,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:50:53',NULL,NULL,9),(1368152870618865666,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:53:53',NULL,NULL,5),(1368153625593585666,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:56:53',NULL,NULL,9),(1368154380664774658,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 18:59:53',NULL,NULL,7),(1368155135618523138,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:02:53',NULL,NULL,8),(1368155890513551362,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:05:53',NULL,NULL,6),(1368156645727346689,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:08:53',NULL,NULL,9),(1368157400467185666,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:11:53',NULL,NULL,11),(1368158155601289217,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:14:53',NULL,NULL,7),(1368158910613757954,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:17:53',NULL,NULL,5),(1368159665643003906,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:20:53',NULL,NULL,10),(1368160420487700482,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:23:53',NULL,NULL,9),(1368161175407894529,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:26:53',NULL,NULL,8),(1368161930525220865,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:29:53',NULL,NULL,9),(1368162685474775041,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:32:53',NULL,NULL,16),(1368163440592101378,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:35:53',NULL,NULL,8),(1368164195680067586,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:38:53',NULL,NULL,17),(1368164950545735681,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:41:53',NULL,NULL,13),(1368165705201688577,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:44:53',NULL,NULL,8),(1368166460226740226,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:47:53',NULL,NULL,9),(1368167215457312770,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:50:53',NULL,NULL,11),(1368167970415255553,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:53:53',NULL,NULL,11),(1368168725293506561,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:56:53',NULL,NULL,10),(1368169480532467713,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 19:59:53',NULL,NULL,14),(1368170235402330113,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 20:02:53',NULL,NULL,7),(1368170990389633026,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 20:05:53',NULL,NULL,8),(1368171745469210626,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 20:08:53',NULL,NULL,9),(1368172500456513538,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 20:11:53',NULL,NULL,8),(1368173255431233537,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 20:14:53',NULL,NULL,5),(1368174010456285186,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 20:17:53',NULL,NULL,6),(1368174765426810882,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 20:20:53',NULL,NULL,7),(1368175520414113793,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 20:23:53',NULL,NULL,12),(1368176275380445185,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 20:26:53',NULL,NULL,5),(1368177030376136706,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 20:29:53',NULL,NULL,6),(1368177785468297217,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 20:32:53',NULL,NULL,15),(1368178540388491265,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 20:35:53',NULL,NULL,8),(1368179295321268226,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 20:38:53',NULL,NULL,7),(1368180050405040129,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 20:41:53',NULL,NULL,10),(1368180804998078466,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 20:44:53',NULL,NULL,6),(1368181560178319361,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 20:47:53',NULL,NULL,6),(1368182314918158338,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 20:50:53',NULL,NULL,8),(1368183070241005569,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-03-06 20:53:53',NULL,NULL,6);

/*Table structure for table `table_system_resource_md5` */

DROP TABLE IF EXISTS `table_system_resource_md5`;

CREATE TABLE `table_system_resource_md5` (
  `id` bigint NOT NULL,
  `resource` varchar(500) DEFAULT NULL COMMENT '当资源为一个文件时，此时这里记录的是文件的全路径',
  `resource_type` bigint DEFAULT NULL COMMENT '资源类型，详见数字字典\r\n            主要有：1.文件 2.string',
  `md5` varchar(32) DEFAULT NULL COMMENT '资源md5值',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`),
  UNIQUE KEY `md5__index` (`md5`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源md5表，用于快传';

/*Data for the table `table_system_resource_md5` */

insert  into `table_system_resource_md5`(`id`,`resource`,`resource_type`,`md5`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`) values (1,'http://8.129.77.225:9000/yeju/img/3.jpg',1,'101',NULL,NULL,NULL,NULL,NULL,1,0),(1357213972349526018,'http://8.129.77.225:9000/yeju/f8163e33-a662-4ddc-a8ec-74c0d64507551.png',1,'39e8edf4eba284afe1cb47add95e8543',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1357218276724670466,'http://8.129.77.225:9000/yeju/c2798f51-a07c-4efd-a504-4c8e4a4751f456eb70edc6dd1.jpg',1,'af724a3109d1fda82e001159fff39b04',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1357218950820593665,'http://8.129.77.225:9000/yeju/1fb049af-16ae-437b-8b3c-fd6682c9fe24致一.jpg',1,'5afefce8c948de630e939be476fb5333',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1357222697806311426,'http://8.129.77.225:9000/yeju/8469f77f-b29d-43cb-b90c-b5acab2a4bba56eb70e8c716a.jpg',1,'15e03fee9d1935f31e7953f0b2121f9f',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `table_system_resources` */

DROP TABLE IF EXISTS `table_system_resources`;

CREATE TABLE `table_system_resources` (
  `resource_id` bigint NOT NULL COMMENT '主键',
  `resource_name` varchar(64) DEFAULT NULL COMMENT '资源名',
  `resource_code` varchar(128) DEFAULT NULL COMMENT '资源权限字符串',
  `resource_type` varchar(4) DEFAULT NULL COMMENT '资源类型值0菜单目录1接口2操作（按钮）3菜单项',
  `parent_menu_id` bigint DEFAULT NULL COMMENT '父菜单id,仅当资源类型为0时生效',
  `order_number` int DEFAULT NULL COMMENT '显示顺序，当多个子菜单对应一个父菜单时，需要给定显示顺序',
  `path` varchar(512) DEFAULT NULL COMMENT '路由地址，当资源为菜单操作时需要记录路由地址',
  `componet_path` varchar(256) DEFAULT NULL COMMENT '当为组件时，需要给定组件路径',
  `is_cache` int DEFAULT NULL COMMENT '是否缓存0否1是',
  `resource_status` int DEFAULT NULL COMMENT '资源状态0未启用1启用',
  `visible` int DEFAULT NULL COMMENT '菜单是否显示0不显示1显示',
  `icon` varchar(512) DEFAULT NULL COMMENT '菜单图标地址，当资源类型为菜单时需要指定',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  `is_frame` int DEFAULT '0' COMMENT '是否为外部链接0否1是',
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='(受保护的资源)权限表，包括菜单和API';

/*Data for the table `table_system_resources` */

insert  into `table_system_resources`(`resource_id`,`resource_name`,`resource_code`,`resource_type`,`parent_menu_id`,`order_number`,`path`,`componet_path`,`is_cache`,`resource_status`,`visible`,`icon`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`,`is_frame`) values (1,'超级权限','*:**','1',NULL,NULL,NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,0,0),(2,'运营中心','system','0',0,1,'/system','Layout',1,1,1,'system',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(3,'监控中心','monitor','0',0,2,'/monitor','Layout',1,1,1,'monitor',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(4,'系统工具','tool','0',0,3,'/tool','Layout',1,1,1,'tool',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(5,'产商品中心','product','0',0,0,'/product','Layout',1,1,1,'product',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(6,'日志中心','log','0',0,6,'/log','Layout',1,1,1,'log',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(7,'客户中心','customer','0',0,1,'/customer','Layout',1,1,1,'user','2021-03-23 22:55:43',NULL,NULL,NULL,NULL,NULL,0,0),(201,'员工管理','system:user:list','3',2,1,'user','system/user/index',1,1,1,'user',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(202,'角色管理','system:role:list','3',2,2,'role','system/role/index',1,1,1,'peoples',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(203,'菜单管理','system:menu:list','3',2,3,'menu','system/menu/index',1,1,1,'tree-table',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(204,'部门管理','system:dept:list','3',2,4,'dept','system/dept/index',1,1,1,'tree',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(205,'数据字典','system:dict:list','3',2,5,'dict','system/dict/index',1,1,1,'dict',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(206,'通知公告','system:notice:list','3',2,6,'notice','system/notice/index',1,1,1,'message',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(304,'在线用户','monitor:online:list','3',3,2,'online','monitor/online/index',1,1,1,'monitor','2021-02-20 16:09:46',NULL,NULL,NULL,NULL,NULL,0,0),(305,'定时任务','monitor:job:list','3',3,3,'job','monitor/job/index',1,1,1,'job','2021-02-21 10:43:24',NULL,NULL,NULL,NULL,NULL,0,0),(401,'api文档','tool:api','3',4,1,'swagger','tool/swagger/index',1,1,1,'monitor',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(402,'表单构建','tool:build','3',4,2,'build','tool/build/index',1,1,1,'tool',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(501,'房源审核','product:house:list','3',5,1,'house','product/house/index',1,1,1,'product',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(601,'登录日志','log:login:list','3',6,1,'logininfo','log/logininfo/index',1,1,1,'log',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(602,'操作日志','log:operation:list','3',6,2,'operlog','log/operlog/index',1,1,1,'log',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(603,'任务调度日志','log:job:list','3',6,3,'joblog','log/joblog/index',1,1,1,'log',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(701,'客户管理','customer:list','3',7,0,'customer','customer/index',1,1,1,'user','2021-03-23 22:37:05',NULL,NULL,NULL,NULL,NULL,0,0),(702,'积分管理','integration:list','3',7,1,'integration','customer/integration/index',1,1,1,'product','2021-03-25 17:46:30',NULL,NULL,NULL,NULL,NULL,0,0);

/*Table structure for table `table_system_role` */

DROP TABLE IF EXISTS `table_system_role`;

CREATE TABLE `table_system_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色字符串',
  `role_status` bigint DEFAULT '1' COMMENT '角色状态 0未启用1启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';

/*Data for the table `table_system_role` */

insert  into `table_system_role`(`role_id`,`role_name`,`role_code`,`role_status`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`) values (1,'超级管理员','admin',1,'2021-02-18 21:47:28',NULL,NULL,NULL,NULL,1,0);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_system_task_log` */

/*Table structure for table `table_system_task_properties` */

DROP TABLE IF EXISTS `table_system_task_properties`;

CREATE TABLE `table_system_task_properties` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `task_id` bigint DEFAULT NULL COMMENT '对应的任务id',
  `properties_name` varchar(128) DEFAULT NULL COMMENT '属性名',
  `properties_value` varchar(128) DEFAULT NULL COMMENT '属性值',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时任务参数表';

/*Data for the table `table_system_task_properties` */

insert  into `table_system_task_properties`(`id`,`task_id`,`properties_name`,`properties_value`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`) values (1,1,'name','喜小乐','2021-03-02 21:24:55',NULL,NULL,NULL,'演示任务数据',0,0);

/*Table structure for table `table_system_timing_task_scheduler` */

DROP TABLE IF EXISTS `table_system_timing_task_scheduler`;

CREATE TABLE `table_system_timing_task_scheduler` (
  `task_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_name` varchar(128) DEFAULT NULL COMMENT '任务名称',
  `task_group_id` bigint DEFAULT NULL COMMENT '任务组id',
  `invoke_target` varchar(256) DEFAULT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(256) DEFAULT NULL COMMENT 'cron任务执行表达式',
  `misfire_policy` int DEFAULT NULL COMMENT '当任务出错时的执行策略1立即执行 2执行一次 3放弃执行',
  `concurrent` int DEFAULT NULL COMMENT '是否并发执行0否1是',
  `status` int DEFAULT NULL COMMENT '执行状态0暂停1正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`task_id`),
  UNIQUE KEY `nameAndGroup` (`task_name`,`task_group_id`),
  UNIQUE KEY `task_group_id` (`task_group_id`),
  UNIQUE KEY `task_name` (`task_name`(12))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时任务调度表';

/*Data for the table `table_system_timing_task_scheduler` */

insert  into `table_system_timing_task_scheduler`(`task_id`,`task_name`,`task_group_id`,`invoke_target`,`cron_expression`,`misfire_policy`,`concurrent`,`status`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`) values (1,'演示定时任务',1,'pers.lbf.yeju.provider.job.task.HelloTask','0/30 * * * * ? ',NULL,1,1,'2021-02-27 20:21:24',NULL,NULL,NULL,'演示任务',0,0);

/*Table structure for table `table_system_timing_task_scheduler_group` */

DROP TABLE IF EXISTS `table_system_timing_task_scheduler_group`;

CREATE TABLE `table_system_timing_task_scheduler_group` (
  `task_group_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_name` varchar(64) DEFAULT NULL COMMENT '任务组名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`task_group_id`),
  UNIQUE KEY `group_name` (`group_name`(12))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时任务组信息表';

/*Data for the table `table_system_timing_task_scheduler_group` */

insert  into `table_system_timing_task_scheduler_group`(`task_group_id`,`group_name`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`) values (1,'yeju_job_default_group','2021-02-27 20:15:46',NULL,NULL,NULL,'默认分组',0,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
