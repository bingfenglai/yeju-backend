/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.21 : Database - yeju_core
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yeju_core` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `yeju_core`;

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
  PRIMARY KEY (`account_id`,`role_id`),
  UNIQUE KEY `account_id_2` (`account_id`,`role_id`),
  UNIQUE KEY `account_number` (`account_number`),
  UNIQUE KEY `phome_number` (`phone_number`),
  KEY `account_id` (`account_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账户-角色关系表 N-1';

/*Data for the table `r_t_account_role` */

insert  into `r_t_account_role`(`account_id`,`role_id`,`status`,`create_time`,`create_by`,`update_time`,`changed_by`,`versions`,`is_delete`,`remark`,`account_number`,`phone_number`,`role_code`) values (1,1,'1',NULL,NULL,NULL,NULL,NULL,0,NULL,'969391','17330937086','admin');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='这是一张冗余的关系表。主要是用来便于权限查询';

/*Data for the table `t_r_account_resources` */

insert  into `t_r_account_resources`(`id`,`account_number`,`phone_number`,`resource_id`,`status`,`create_time`,`create_by`,`update_time`,`changed_by`,`version_number`,`is_delete`,`remark`) values (1,'969391','17330937086',1,'1','2020-12-23 14:22:48',1,'2020-12-23 14:23:06',1,0,0,NULL);

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
  `gender` bigint DEFAULT NULL COMMENT '性别0男1女，详见属性表',
  `gender_value` varchar(4) DEFAULT NULL COMMENT '性别值',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号',
  `phone_number_prefix` bigint DEFAULT NULL COMMENT '手机区号，比如中国是+86，详见属性表\n            ',
  `phone_number_prefix_value` varchar(4) DEFAULT NULL COMMENT '手机区号值',
  `customer_status` varchar(4) DEFAULT NULL COMMENT '客户认证状态0未认证1已认证',
  `province` bigint DEFAULT NULL COMMENT '所在省、自治州',
  `province_value` varchar(24) DEFAULT NULL COMMENT '所在省、自治州值',
  `city` bigint DEFAULT NULL COMMENT '所在城市',
  `city_value` varchar(24) DEFAULT NULL COMMENT '所在城市值',
  `avatar` varchar(500) DEFAULT NULL COMMENT '客户头像地址',
  `email` varchar(32) DEFAULT NULL COMMENT '安全邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  `month_certified` int DEFAULT NULL COMMENT '完成认证时所在月份，table_business_customer_valid分区标识',
  `month_added` int DEFAULT NULL COMMENT '客户信息添加时的月份',
  `month_outmoded` int DEFAULT NULL COMMENT '客户信息过时时的月份。历史表的分区依据',
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='客户信息表，实名认证完成后搬table_business_customer_valid.所属账户超半年未登录系统，客户信';

/*Data for the table `table_business_customer` */

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
  `gender_value` varchar(4) DEFAULT NULL COMMENT '性别值',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号',
  `phone_number_prefix` bigint DEFAULT NULL COMMENT '手机区号，比如中国是+86，详见属性表\n            ',
  `phone_number_prefix_value` varchar(4) DEFAULT NULL COMMENT '手机区号值',
  `customer_status` varchar(4) DEFAULT NULL COMMENT '客户认证状态0未认证1已认证',
  `province` bigint DEFAULT NULL COMMENT '所在省、自治州',
  `province_value` varchar(24) DEFAULT NULL COMMENT '所在省、自治州值',
  `city` bigint DEFAULT NULL COMMENT '所在城市',
  `city_value` varchar(24) DEFAULT NULL COMMENT '所在城市值',
  `avatar` varchar(500) DEFAULT NULL COMMENT '客户头像地址',
  `email` varchar(32) DEFAULT NULL COMMENT '安全邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  `month_certified` int DEFAULT NULL COMMENT '完成认证时所在月份，table_business_customer_valid分区标识',
  `month_added` int DEFAULT NULL COMMENT '客户信息添加时的月份',
  `month_outmoded` int DEFAULT NULL COMMENT '客户信息过时时的月份。历史表的分区依据',
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='客户信息表，实名认证完成后搬table_business_customer_valid.所属账户超半年未登录系统，客户信';

/*Data for the table `table_business_customer_valid` */

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
  `name` varchar(32) DEFAULT NULL COMMENT '城市名称',
  `type` int DEFAULT NULL COMMENT '城市类型0国1省2市3区',
  `hierarchy` int DEFAULT NULL COMMENT '地区所处的层级',
  `district_sequence` varchar(12) DEFAULT NULL COMMENT '层级序列',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`district_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='中国省市表';

/*Data for the table `table_business_district` */

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
  `resource_id` bigint NOT NULL COMMENT '资源标识',
  `house_id` bigint DEFAULT NULL COMMENT '房屋标识',
  `file_name` varchar(128) DEFAULT NULL COMMENT '文件名称',
  `file_url` varchar(512) DEFAULT NULL COMMENT '资源路径',
  `resource_status` int DEFAULT NULL COMMENT '资源状态0待审核1启用2禁用',
  `resource_type` int DEFAULT NULL COMMENT '资源类型0图片1资源',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='房屋图片视频信息表。每个房屋ID最多可以对应5张图片和1个视频。数据库中存储的是url';

/*Data for the table `table_business_house_images_and_video` */

/*Table structure for table `table_business_house_info` */

DROP TABLE IF EXISTS `table_business_house_info`;

CREATE TABLE `table_business_house_info` (
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
  `house_status` varchar(4) DEFAULT NULL COMMENT '0审核中\n            1审核未通过\n            2待租\n            3预交易\n            4交易生效中\n             详见数据字典\n            ',
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

/*Data for the table `table_business_house_info` */

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
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  `is_gender_restrictions` int DEFAULT NULL COMMENT '性别限制\n            0不限\n            1男\n            2女',
  `is_it_air_conditioned` int DEFAULT NULL COMMENT '是否有空调',
  `whether_the_heating` int DEFAULT NULL COMMENT '是否供暖',
  PRIMARY KEY (`house_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='房源其他属性表.包括一些可选的属性以及客户自定义属性（<=3）\n。';

/*Data for the table `table_business_house_other_attribute` */

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

/*Table structure for table `table_platform_department` */

DROP TABLE IF EXISTS `table_platform_department`;

CREATE TABLE `table_platform_department` (
  `department_id` bigint NOT NULL COMMENT '主键',
  `parent_department_id` bigint DEFAULT NULL COMMENT '父部门主键',
  `name` varchar(32) DEFAULT NULL COMMENT '部门名称',
  `leader_id` bigint DEFAULT NULL COMMENT '负责人id',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号',
  `phone_number_prefix` bigint DEFAULT NULL COMMENT '手机区号，比如中国是+86，详见属性表\n            ',
  `phone_number_prefix_value` varchar(4) DEFAULT NULL COMMENT '手机区号值',
  `email` varchar(32) DEFAULT NULL COMMENT '部门（团队）邮箱',
  `yeju_department_status` bigint DEFAULT NULL COMMENT '部门状态0未启用1启用',
  `yeju_department_status_value` varchar(4) DEFAULT NULL COMMENT '部门状态值，详见属性表',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='椰居平台部门表';

/*Data for the table `table_platform_department` */

/*Table structure for table `table_platform_employees` */

DROP TABLE IF EXISTS `table_platform_employees`;

CREATE TABLE `table_platform_employees` (
  `employees_id` bigint NOT NULL COMMENT '主键',
  `name` varchar(32) DEFAULT NULL COMMENT '员工姓名',
  `gender` bigint DEFAULT NULL COMMENT '性别0男1女2未知，详见属性表',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号',
  `employees_number` bigint DEFAULT NULL COMMENT '工号',
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
  PRIMARY KEY (`employees_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='椰居平台员工表';

/*Data for the table `table_platform_employees` */

insert  into `table_platform_employees`(`employees_id`,`name`,`gender`,`phone_number`,`employees_number`,`phone_number_prefix`,`leader_id`,`avatar`,`email`,`employee_status`,`create_time`,`create_by`,`update_time`,`changed_by`,`version_number`,`is_delete`,`month_added`,`month_outmoded`) values (1,'超级员工',1,'17330937086',969391,'86',1,'http://8.129.77.225:9000/yeju/8469f77f-b29d-43cb-b90c-b5acab2a4bba56eb70e8c716a.jpg','bingfengdev@aliyun.com',1,NULL,NULL,NULL,NULL,NULL,0,12,NULL);

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
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  `phone_number` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `account_number` (`account_number`),
  UNIQUE KEY `phone_number` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账户表';

/*Data for the table `table_system_account` */

insert  into `table_system_account`(`account_id`,`account_number`,`subject_id`,`account_password`,`last_login_address`,`last_login_date`,`account_status`,`account_level`,`account_type`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`,`phone_number`) values (1,'969391',1,'$2a$10$TyTu1cSR5Q4Z.a/fDDdj1OPYlHcWxsM8EIm2vz90XidLAALWLHuTC',NULL,NULL,'1','1','0',NULL,1,NULL,NULL,'超级工号',1,0,'17330937086');

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
  `is_default` int DEFAULT NULL COMMENT '是否为默认属性0否1是',
  `status` int DEFAULT '1' COMMENT '状态0未启用1启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT '0' COMMENT '字段版本',
  `is_delete` int DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`data_dictionary_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据字典信息表';

/*Data for the table `table_system_data_dictionary_info` */

insert  into `table_system_data_dictionary_info`(`data_dictionary_info_id`,`type_id`,`sort`,`dictionary_label`,`dictionary_value`,`css_class`,`list_class`,`is_default`,`status`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`) values (1,1,1,'男','1',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,0),(2,1,1,'女','2',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(3,2,1,'启用','1',NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL,0),(4,2,1,'未启用','0',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,0),(5,3,1,'在职','0',NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,0,0),(6,3,2,'离职','1',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,0,0);

/*Table structure for table `table_system_data_dictionary_type` */

DROP TABLE IF EXISTS `table_system_data_dictionary_type`;

CREATE TABLE `table_system_data_dictionary_type` (
  `data_dictionary_type_id` bigint NOT NULL COMMENT '主键',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据字典类型表,用于简单的动态配置';

/*Data for the table `table_system_data_dictionary_type` */

insert  into `table_system_data_dictionary_type`(`data_dictionary_type_id`,`name`,`type`,`status`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`) values (1,'性别','gender',1,NULL,NULL,NULL,NULL,NULL,NULL,0),(2,'角色状态','role_status',1,NULL,NULL,NULL,NULL,NULL,NULL,0),(3,'员工状态','employee_status',1,NULL,NULL,NULL,NULL,NULL,NULL,0);

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

insert  into `table_system_login_log`(`login_log_id`,`account`,`subject_name`,`ip`,`login_status`,`message`,`accent_time`,`last_ip_number`) values (1345201981133139970,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-01-02 10:55:19',NULL),(1345202180438077442,'969391','','0:0:0:0:0:0:0:1',0,'账号或密码错误','2021-01-02 10:56:07',NULL),(1345202856639516673,'969391','','0:0:0:0:0:0:0:1',0,'账号或密码错误','2021-01-02 10:58:48',NULL),(1345402762293604353,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-01-03 00:13:09',NULL),(1345759568790421505,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-01-03 23:50:05',NULL),(1345759569885134849,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-01-03 23:50:06',NULL),(1345759570333925378,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-01-03 23:50:07',NULL),(1345759570786910209,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-01-03 23:50:08',NULL),(1345759571218923521,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-01-03 23:50:09',NULL),(1345762470871396354,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-01-03 23:55:21',NULL),(1345762471945138178,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-01-03 23:55:23',NULL),(1345762472549117953,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-01-03 23:55:24',NULL),(1345762473111154690,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-01-03 23:55:25',NULL),(1345762473585111041,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-01-03 23:55:25',NULL),(1345762474109399041,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-01-03 23:55:26',NULL),(1345762474637881346,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-01-03 23:55:27',NULL),(1345762475153780738,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-01-03 23:55:28',NULL),(1349321962405965825,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-01-13 19:46:37',NULL),(1349322607938711554,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-01-13 19:49:13',NULL),(1349326195419152386,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-01-13 20:03:24',NULL),(1351061373592887298,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-01-18 14:19:19',NULL),(1357284665636331522,'969391','','192.168.0.123',1,'登录成功','2021-02-04 15:51:24',NULL),(1357284666575855617,'969391','','192.168.0.123',1,'登录成功','2021-02-04 16:18:31',NULL),(1357284666793959425,'969391','','192.168.0.123',1,'登录成功','2021-02-04 16:21:59',NULL),(1357284667091755010,'969391','','192.168.0.123',1,'登录成功','2021-02-04 16:27:50',NULL),(1357284667305664514,'969391','','192.168.0.123',1,'登录成功','2021-02-04 16:33:39',NULL),(1357284667544739842,'969391','','192.168.0.123',1,'登录成功','2021-02-04 16:35:45',NULL),(1357284667754455042,'969391','','0:0:0:0:0:0:0:1',1,'登录成功','2021-02-04 18:21:00',NULL),(1358091732144070658,'969391','','192.168.0.148',1,'登录成功','2021-02-06 17:20:03',NULL),(1358091733054234626,'969391','','192.168.0.148',1,'登录成功','2021-02-06 17:21:09',NULL),(1358091733456887809,'969391','','192.168.0.148',1,'登录成功','2021-02-06 17:21:47',NULL),(1358091733884706817,'969391','','192.168.0.148',1,'登录成功','2021-02-06 17:37:19',NULL),(1358091734312525825,'969391','','192.168.0.148',1,'登录成功','2021-02-06 18:35:56',NULL),(1358091734719373314,'969391','','192.168.0.148',1,'登录成功','2021-02-06 18:39:48',NULL),(1358091735109443586,'969391','','192.168.0.148',1,'登录成功','2021-02-06 18:45:37',NULL),(1358091735512096770,'969391','','192.168.0.148',1,'登录成功','2021-02-06 18:47:55',NULL),(1358091735918944258,'969391','','192.168.0.148',1,'登录成功','2021-02-06 18:50:17',NULL),(1358818468615905282,'969391','','192.168.43.183',1,'登录成功','2021-02-09 00:39:13',NULL),(1359090129705574401,'969391','','192.168.0.148',1,'登录成功','2021-02-09 00:50:41',NULL),(1359090131379101698,'969391','','192.168.0.148',1,'登录成功','2021-02-09 00:51:12',NULL),(1359090133010685953,'969391','','192.168.0.148',1,'登录成功','2021-02-09 00:59:09',NULL),(1359090134117982209,'969391','','192.168.0.148',1,'登录成功','2021-02-09 01:04:02',NULL),(1359090137175629825,'969391','','192.168.0.148',1,'登录成功','2021-02-09 01:05:02',NULL),(1359090140774342657,'969391','','192.168.0.148',1,'登录成功','2021-02-09 01:06:40',NULL),(1359090144276586497,'969391','','192.168.0.148',1,'登录成功','2021-02-09 16:14:16',NULL),(1359090145652318209,'969391','','192.168.0.148',1,'登录成功','2021-02-09 16:14:16',NULL),(1359090146877054977,'969391','','192.168.0.148',1,'登录成功','2021-02-09 16:14:16',NULL),(1359090149360082945,'969391','','192.168.0.148',1,'登录成功','2021-02-09 16:14:16',NULL),(1359090150748397569,'969391','','192.168.0.123',1,'登录成功','2021-02-09 16:14:16',NULL),(1359090152241569793,'969391','','192.168.0.148',1,'登录成功','2021-02-09 16:14:16',NULL),(1359090158478499841,'969391','','192.168.0.148',1,'登录成功','2021-02-09 16:14:16',NULL),(1359090159803899905,'969391','','192.168.0.148',1,'登录成功','2021-02-09 16:14:46',NULL),(1359090160558874626,'969391','','192.168.0.148',1,'登录成功','2021-02-09 16:16:07',NULL),(1359090161934606338,'969391','','192.168.0.148',1,'登录成功','2021-02-09 16:16:26',NULL),(1359090163721379842,'969391','','192.168.0.148',1,'登录成功','2021-02-09 16:26:45',NULL),(1359090164908367874,'969391','','192.168.0.148',1,'登录成功','2021-02-09 16:29:47',NULL),(1359090166569312257,'969391','','192.168.0.148',1,'登录成功','2021-02-09 17:10:40',NULL),(1359090167584333826,'969391','','192.168.0.148',1,'登录成功','2021-02-09 17:12:36',NULL),(1359090170134470658,'969391','','192.168.0.148',1,'登录成功','2021-02-09 17:21:28',NULL),(1359090170889445378,'969391','','192.168.0.148',1,'登录成功','2021-02-09 17:31:25',NULL),(1359090172231622657,'969391','','192.168.0.148',1,'登录成功','2021-02-09 17:47:29',NULL),(1359090173712211969,'969391','','192.168.0.148',1,'登录成功','2021-02-09 18:03:32',NULL),(1359090175331213314,'969391','','192.168.0.148',1,'登录成功','2021-02-09 18:04:29',NULL),(1359090176652419074,'969391','','192.168.0.148',1,'登录成功','2021-02-09 18:07:49',NULL),(1359090177864572929,'969391','','192.168.0.148',1,'登录成功','2021-02-09 18:08:39',NULL),(1359090178808291329,'969391','','192.168.0.148',1,'登录成功','2021-02-09 18:09:46',NULL),(1359090179810729986,'969391','','192.168.0.148',1,'登录成功','2021-02-09 18:12:58',NULL),(1359090181140324353,'969391','','192.168.0.148',1,'登录成功','2021-02-09 18:14:40',NULL),(1359090182511861762,'969391','','192.168.0.148',1,'登录成功','2021-02-09 18:18:32',NULL),(1359090183950508033,'969391','','192.168.0.148',1,'登录成功','2021-02-09 18:19:28',NULL),(1359090184806146050,'969391','','192.168.0.148',1,'登录成功','2021-02-09 18:21:24',NULL),(1359106680156549122,'969391','','192.168.0.148',1,'登录成功','2021-02-09 19:47:38',NULL),(1359106877221728258,'969391','','192.168.0.148',1,'登录成功','2021-02-09 19:48:24',NULL),(1359106959727882241,'969391','','192.168.0.148',1,'登录成功','2021-02-09 19:48:45',NULL),(1359107184639045633,'969391','','192.168.0.148',1,'登录成功','2021-02-09 19:49:38',NULL),(1359108634484109314,'969391','','192.168.0.148',1,'登录成功','2021-02-09 19:55:20',NULL),(1359108820304359425,'969391','','192.168.0.148',1,'登录成功','2021-02-09 19:56:06',NULL),(1359109335704629250,'969391','','192.168.0.148',1,'登录成功','2021-02-09 19:58:10',NULL),(1359110676954906626,'969391','','192.168.0.148',1,'登录成功','2021-02-09 20:03:28',NULL),(1359111674092040194,'969391','','192.168.0.148',1,'登录成功','2021-02-09 20:07:27',NULL),(1359112280655507458,'969391','','192.168.0.148',1,'登录成功','2021-02-09 20:09:53',NULL),(1359115582059294722,'969391','','192.168.0.148',1,'登录成功','2021-02-09 20:23:00',NULL),(1359118594622337025,'969391','','192.168.0.148',1,'登录成功','2021-02-09 20:34:59',NULL),(1359121210261614594,'969391','','192.168.0.148',1,'登录成功','2021-02-09 20:45:22',NULL),(1359123894548996098,'969391','','192.168.0.148',1,'登录成功','2021-02-09 20:56:02',NULL),(1359125043347890178,'969391','','192.168.0.148',1,'登录成功','2021-02-09 21:00:36',NULL),(1359125665803575297,'969391','','192.168.0.148',1,'登录成功','2021-02-09 21:03:04',NULL),(1359126296526233602,'969391','','192.168.0.148',1,'登录成功','2021-02-09 21:05:35',NULL),(1359128604345548801,'969391','','192.168.0.148',1,'登录成功','2021-02-09 21:14:45',NULL),(1359129268949794818,'969391','','192.168.0.148',1,'登录成功','2021-02-09 21:17:24',NULL),(1359134880001617921,'969391','','192.168.0.148',1,'登录成功','2021-02-09 21:39:41',NULL),(1359144745495367681,'969391','','192.168.0.148',1,'登录成功','2021-02-09 22:18:52',NULL),(1359145330672078850,'969391','','192.168.0.148',1,'登录成功','2021-02-09 22:21:11',NULL),(1359147151511719938,'969391','','192.168.0.148',1,'登录成功','2021-02-09 22:28:27',NULL),(1359147654391992322,'969391','','192.168.0.148',1,'登录成功','2021-02-09 22:30:26',NULL),(1359147921732734978,'969391','','192.168.0.148',1,'登录成功','2021-02-09 22:31:30',NULL),(1359148835973566466,'969391','','192.168.0.148',1,'登录成功','2021-02-09 22:35:08',NULL),(1359149276241268738,'969391','','192.168.0.148',1,'登录成功','2021-02-09 22:36:53',NULL),(1359151166395977730,'969391','','192.168.0.148',1,'登录成功','2021-02-09 22:44:21',NULL),(1359155116159471619,'969391','','192.168.0.148',1,'登录成功','2021-02-09 23:00:05',NULL),(1359156368440233986,'969391','','192.168.0.148',1,'登录成功','2021-02-09 23:05:03',NULL),(1359156433154150401,'969391','','192.168.0.148',1,'登录成功','2021-02-09 23:05:18',NULL),(1359156956284522498,'969391','','192.168.0.148',1,'登录成功','2021-02-09 23:07:23',NULL),(1359157094763663361,'969391','','192.168.0.148',1,'登录成功','2021-02-09 23:07:56',NULL),(1359157195414376449,'969391','','192.168.0.148',1,'登录成功','2021-02-09 23:08:20',NULL),(1359157421663522817,'969391','','192.168.0.148',1,'登录成功','2021-02-09 23:09:15',NULL),(1359157557441531906,'969391','','192.168.0.148',1,'登录成功','2021-02-09 23:09:46',NULL),(1359158154878193666,'969391','','192.168.0.148',1,'登录成功','2021-02-09 23:12:09',NULL),(1359160409803145217,'969391','','192.168.0.148',1,'登录成功','2021-02-09 23:21:06',NULL),(1359162863693312001,'969391','','192.168.0.148',1,'登录成功','2021-02-09 23:30:53',NULL),(1359163176793911297,'969391','','192.168.0.148',1,'登录成功','2021-02-09 23:32:07',NULL),(1359164575095173121,'969391','','192.168.0.148',1,'登录成功','2021-02-09 23:37:41',NULL),(1359188412352335874,'969391','','192.168.0.148',1,'登录成功','2021-02-10 01:12:24',NULL),(1359191571737047041,'969391','','192.168.0.148',1,'登录成功','2021-02-10 01:24:58',NULL),(1359193551792406530,'969391','','192.168.0.148',1,'登录成功','2021-02-10 01:32:50',NULL),(1359196589168951298,'969391','','192.168.0.148',1,'登录成功','2021-02-10 01:44:54',NULL),(1359198597074604033,'969391','','192.168.0.148',1,'登录成功','2021-02-10 01:52:53',NULL),(1359337684007882754,'969391','','192.168.0.148',1,'登录成功','2021-02-10 11:05:34',NULL),(1359340537455808514,'969391','','192.168.0.148',1,'登录成功','2021-02-10 11:16:55',NULL),(1359346759735132161,'969391','','192.168.0.148',1,'登录成功','2021-02-10 11:41:37',NULL),(1359348515600809986,'969391','','192.168.0.148',1,'登录成功','2021-02-10 11:48:37',NULL),(1359349375458295810,'969391','','192.168.0.148',1,'登录成功','2021-02-10 11:52:02',NULL),(1359351916468322305,'969391','','192.168.0.148',1,'登录成功','2021-02-10 12:02:08',NULL),(1359353269211054082,'969391','','192.168.0.148',1,'登录成功','2021-02-10 12:07:29',NULL),(1359356193534328833,'969391','','192.168.0.148',1,'登录成功','2021-02-10 12:19:07',NULL),(1359389928958935042,'969391','','192.168.0.148',1,'登录成功','2021-02-10 14:33:10',NULL),(1359393023323488257,'969391','','192.168.0.148',1,'登录成功','2021-02-10 14:45:28',NULL),(1359398308406575105,'969391','','192.168.0.148',1,'登录成功','2021-02-10 15:06:28',NULL),(1359403776638140417,'969391','','192.168.0.148',1,'登录成功','2021-02-10 15:28:11',NULL),(1359406432928612353,'969391','','192.168.0.148',1,'登录成功','2021-02-10 15:38:43',NULL),(1359406658124988417,'969391','','192.168.0.148',1,'登录成功','2021-02-10 15:39:37',NULL),(1359421893909061633,'969391','','192.168.0.148',1,'登录成功','2021-02-10 16:40:11',NULL),(1359423832969355265,'969391','','192.168.0.148',1,'登录成功','2021-02-10 16:47:50',NULL),(1359423851088748546,'969391','','192.168.0.148',1,'登录成功','2021-02-10 16:47:56',NULL),(1359424766579482626,'969391','','192.168.0.148',1,'登录成功','2021-02-10 16:51:33',NULL),(1359424991578726402,'969391','','192.168.0.148',1,'登录成功','2021-02-10 16:52:24',NULL),(1359425820146704385,'969391','','192.168.0.148',1,'登录成功','2021-02-10 16:55:46',NULL),(1359425944419737602,'969391','','192.168.0.148',1,'登录成功','2021-02-10 16:56:15',NULL),(1359426208023355394,'969391','','192.168.0.148',1,'登录成功','2021-02-10 16:57:17',NULL),(1359426321470889985,'969391','','192.168.0.148',1,'登录成功','2021-02-10 16:57:45',NULL),(1359426419839901698,'969391','','192.168.0.148',1,'登录成功','2021-02-10 16:58:07',NULL),(1359426548491788289,'969391','','192.168.0.148',1,'登录成功','2021-02-10 16:58:40',NULL),(1359426699016970241,'969391','','192.168.0.148',1,'登录成功','2021-02-10 16:59:14',NULL),(1359427793503821826,'969391','','192.168.0.148',1,'登录成功','2021-02-10 17:03:38',NULL),(1359430566119120897,'969391','','192.168.0.148',1,'登录成功','2021-02-10 17:14:39',NULL),(1359431155561439234,'969391','','192.168.0.148',1,'登录成功','2021-02-10 17:17:00',NULL),(1359433899512864769,'969391','','192.168.0.148',1,'登录成功','2021-02-10 17:27:54',NULL),(1359434145278107650,'969391','','192.168.0.148',1,'登录成功','2021-02-10 17:28:52',NULL),(1359513840803844098,'969391','','192.168.0.148',1,'登录成功','2021-02-10 22:45:33',NULL),(1359520581843095554,'969391','','192.168.0.148',1,'登录成功','2021-02-10 23:12:20',NULL),(1359555616101048321,'969391','','192.168.0.123',1,'登录成功','2021-02-11 01:31:31',NULL),(1359712257018687489,'969391','','192.168.0.148',1,'登录成功','2021-02-11 11:53:58',NULL),(1359732356215730177,'969391','','192.168.0.148',1,'登录成功','2021-02-11 13:13:52',NULL),(1359732629520773121,'969391','','192.168.0.148',1,'登录成功','2021-02-11 13:14:56',NULL),(1359736357808238594,'969391','','192.168.0.148',1,'登录成功','2021-02-11 13:29:46',NULL),(1359746064866299906,'969391','','192.168.0.148',1,'登录成功','2021-02-11 14:08:17',NULL),(1359747388198572033,'969391','','192.168.0.148',1,'登录成功','2021-02-11 14:13:34',NULL),(1359750052110094337,'969391','','192.168.0.148',1,'登录成功','2021-02-11 14:24:09',NULL),(1359753973490733057,'969391','','192.168.0.148',1,'登录成功','2021-02-11 14:39:44',NULL),(1359756779845013506,'969391','','192.168.0.148',1,'登录成功','2021-02-11 14:50:52',NULL),(1359759342094995457,'969391','','192.168.0.148',1,'登录成功','2021-02-11 15:01:06',NULL),(1359762202216058881,'969391','','192.168.0.148',1,'登录成功','2021-02-11 15:12:28',NULL),(1359812741758885889,'969391','','192.168.0.148',1,'登录成功','2021-02-11 18:33:13',NULL),(1359812986051928066,'969391','','192.168.0.148',1,'登录成功','2021-02-11 18:34:09',NULL),(1359813314415599618,'969391','','192.168.0.148',1,'登录成功','2021-02-11 18:35:32',NULL),(1359813602119688195,'969391','','192.168.0.148',1,'登录成功','2021-02-11 18:36:38',NULL),(1359813667555024897,'969391','','192.168.0.148',1,'登录成功','2021-02-11 18:36:55',NULL),(1359813919162933251,'969391','','192.168.0.148',1,'登录成功','2021-02-11 18:37:55',NULL),(1359814055884660739,'969391','','192.168.0.148',1,'登录成功','2021-02-11 18:38:25',NULL),(1359814303054995458,'969391','','192.168.0.148',1,'登录成功','2021-02-11 18:39:27',NULL),(1359821828441636866,'969391','','192.168.0.148',1,'登录成功','2021-02-11 19:09:21',NULL),(1359821921622294529,'969391','','192.168.0.148',1,'登录成功','2021-02-11 19:09:44',NULL),(1359822219795365889,'969391','','192.168.0.148',1,'登录成功','2021-02-11 19:10:54',NULL),(1359822347130241026,'969391','','192.168.0.148',1,'登录成功','2021-02-11 19:11:23',NULL),(1359822763809177602,'969391','','192.168.0.148',1,'登录成功','2021-02-11 19:13:05',NULL),(1359823440400744450,'969391','','192.168.0.148',1,'登录成功','2021-02-11 19:15:46',NULL),(1359823988164263938,'969391','','192.168.0.148',1,'登录成功','2021-02-11 19:17:53',NULL),(1359856838607503361,'969391','','192.168.0.148',1,'登录成功','2021-02-11 21:28:29',NULL),(1359857008007053314,'969391','','192.168.0.148',1,'登录成功','2021-02-11 21:29:10',NULL),(1359857371015675905,'969391','','192.168.0.148',1,'登录成功','2021-02-11 21:30:37',NULL),(1359857784578244609,'969391','','192.168.0.148',1,'登录成功','2021-02-11 21:32:15',NULL),(1359858192184901634,'969391','','192.168.0.148',1,'登录成功','2021-02-11 21:33:52',NULL),(1359858524075982850,'969391','','192.168.0.148',1,'登录成功','2021-02-11 21:35:11',NULL),(1359859262260903937,'969391','','192.168.0.148',1,'登录成功','2021-02-11 21:38:07',NULL),(1359860709887188993,'969391','','192.168.0.148',1,'登录成功','2021-02-11 21:43:53',NULL),(1359861802402783234,'969391','','192.168.0.148',1,'登录成功','2021-02-11 21:48:13',NULL),(1359862030581309441,'969391','','192.168.0.148',1,'登录成功','2021-02-11 21:49:08',NULL),(1359863095162773506,'969391','','192.168.0.148',1,'登录成功','2021-02-11 21:53:21',NULL),(1359863934711439361,'969391','','192.168.0.148',1,'登录成功','2021-02-11 21:56:42',NULL),(1359864580340654082,'969391','','192.168.0.148',1,'登录成功','2021-02-11 21:59:15',NULL),(1359867230406778882,'969391','','192.168.0.148',1,'登录成功','2021-02-11 22:09:47',NULL),(1359868459379793921,'969391','','192.168.0.148',1,'登录成功','2021-02-11 22:14:40',NULL),(1359880606860124162,'969391','','192.168.0.148',1,'登录成功','2021-02-11 23:02:56',NULL),(1359888188593106945,'969391','','192.168.0.148',1,'登录成功','2021-02-11 23:33:04',NULL),(1359896045233336321,'969391','','192.168.0.148',1,'登录成功','2021-02-12 00:04:17',NULL),(1359900735211819009,'969391','','192.168.0.148',1,'登录成功','2021-02-12 00:22:55',NULL),(1360155997902389250,'969391','','192.168.0.148',1,'登录成功','2021-02-12 17:17:07',NULL),(1360156081574559745,'969391','','192.168.0.148',1,'登录成功','2021-02-12 17:17:32',NULL),(1360163148398059521,'969391','','192.168.0.148',1,'登录成功','2021-02-12 17:45:38',NULL),(1360163309362864130,'969391','','192.168.0.148',1,'登录成功','2021-02-12 17:46:15',NULL),(1360169577842466818,'969391','','192.168.0.148',1,'登录成功','2021-02-12 18:11:11',NULL),(1360259905463234561,'969391','','192.168.0.148',1,'登录成功','2021-02-13 00:10:08',NULL),(1360259988971827201,'969391','','192.168.0.148',1,'登录成功','2021-02-13 00:10:27',NULL),(1360264466924023810,'969391','','192.168.0.148',1,'登录成功','2021-02-13 00:28:15',NULL);

/*Table structure for table `table_system_notice` */

DROP TABLE IF EXISTS `table_system_notice`;

CREATE TABLE `table_system_notice` (
  `notice_id` bigint NOT NULL COMMENT '主键',
  `title` varchar(128) DEFAULT NULL COMMENT '通知标题',
  `content` varchar(512) DEFAULT NULL COMMENT '通知正文',
  `notice_type` varchar(4) DEFAULT NULL COMMENT '通知类型0其他1通知2公告',
  `status` int DEFAULT NULL COMMENT '通知状态0关闭1正常',
  `start_time` datetime DEFAULT NULL COMMENT '公告开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '公告结束时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统通知（推送）公告表,记录系统的公告信息。仅保留公告有效期内的公告，超过有效期的公告搬历史表';

/*Data for the table `table_system_notice` */

/*Table structure for table `table_system_operation_log` */

DROP TABLE IF EXISTS `table_system_operation_log`;

CREATE TABLE `table_system_operation_log` (
  `operation_log_id` bigint NOT NULL COMMENT '主键',
  `title` varchar(64) DEFAULT NULL COMMENT '模块标题',
  `business_type` int DEFAULT NULL COMMENT '业务类型0其他1新增2修改3删除',
  `method` varchar(64) DEFAULT NULL COMMENT '方法名称',
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
  `operator_id` bigint DEFAULT NULL COMMENT '操作者id',
  `execute_time` bigint DEFAULT NULL COMMENT '操作耗时 单位 ms',
  PRIMARY KEY (`operation_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统操作日志表，主要用户记录系统后台敏感操作信息，以ip最后一位数字分区。保存最新一个月，过时信息搬历史表保存半年';

/*Data for the table `table_system_operation_log` */

insert  into `table_system_operation_log`(`operation_log_id`,`title`,`business_type`,`method`,`request_method`,`operator_type`,`operator_name`,`department_name`,`url`,`ip`,`location`,`param`,`result`,`operation_status`,`error_message`,`operation_time`,`last_ip_number`,`operator_id`,`execute_time`) values (1359389779666878465,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:32:35',NULL,NULL,49),(1359389785379520514,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:32:35',NULL,NULL,50),(1359389789246668801,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:32:37',NULL,NULL,61),(1359389929080569857,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:33:11',NULL,NULL,341),(1359389930200449025,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:33:11',NULL,NULL,236),(1359389930389192705,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:33:11',NULL,NULL,16),(1359390226704187394,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:34:22',NULL,NULL,164),(1359390226985205762,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:34:22',NULL,NULL,13),(1359390230042853378,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:34:22',NULL,NULL,34),(1359392329573023745,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:42:43',NULL,NULL,37),(1359392333989625857,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:42:44',NULL,NULL,1540),(1359392334211923970,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:42:44',NULL,NULL,18),(1359392989886496770,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:45:20',NULL,NULL,13),(1359392990599528450,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:45:21',NULL,NULL,43),(1359392993350991873,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:45:21',NULL,NULL,33),(1359393023323488258,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:45:28',NULL,NULL,245),(1359393024397230081,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:45:29',NULL,NULL,201),(1359393024606945282,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:45:29',NULL,NULL,19),(1359393999837151234,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:49:21',NULL,NULL,166),(1359394000059449346,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:49:21',NULL,NULL,18),(1359394003532333058,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:49:22',NULL,NULL,38),(1359394095186264066,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:49:44',NULL,NULL,149),(1359394095928655874,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:49:44',NULL,NULL,17),(1359395363329884162,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:54:46',NULL,NULL,2392),(1359395363707371522,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 14:54:46',NULL,NULL,18),(1359398242186903553,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:06:13',NULL,NULL,14),(1359398250852335618,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:06:13',NULL,NULL,75),(1359398308431740930,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:06:28',NULL,NULL,272),(1359398313615900674,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:06:30',NULL,NULL,1188),(1359398313838198786,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:06:30',NULL,NULL,14),(1359402986330603521,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:25:04',NULL,NULL,83),(1359402995121864705,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:25:05',NULL,NULL,433),(1359403714835070978,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:27:57',NULL,NULL,31),(1359403716059807746,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:27:58',NULL,NULL,37),(1359403776684277762,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:28:12',NULL,NULL,1286),(1359403784045281282,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:28:14',NULL,NULL,1670),(1359403784653455361,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:28:14',NULL,NULL,55),(1359404433348706306,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:30:49',NULL,NULL,168),(1359404434053349378,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:30:49',NULL,NULL,20),(1359406273461174274,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:38:07',NULL,NULL,3064),(1359406288887824385,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:38:08',NULL,NULL,43),(1359406432928612354,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:38:45',NULL,NULL,1599),(1359406446371356674,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:38:48',NULL,NULL,3056),(1359406629033295873,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:39:32',NULL,NULL,43),(1359406658099822593,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:39:39',NULL,NULL,2240),(1359406670934392834,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:39:42',NULL,NULL,3079),(1359414016930652162,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 15:58:58',NULL,NULL,8),(1359414076108087298,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:09:08',NULL,NULL,8),(1359414091039809537,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:09:11',NULL,NULL,6),(1359414156965879809,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:09:27',NULL,NULL,12),(1359414188750315522,NULL,4,'/auth-consumer/v3/api-docs','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-10 16:09:35',NULL,NULL,181),(1359414239765635074,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-10 16:09:47',NULL,NULL,6),(1359414301149274113,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-10 16:10:00',NULL,NULL,621),(1359414316752084993,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:10:05',NULL,NULL,59),(1359415368532533250,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:14:16',NULL,NULL,240),(1359415564293283842,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:15:02',NULL,NULL,69),(1359416382459387906,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:18:17',NULL,NULL,82),(1359416402411692034,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:18:22',NULL,NULL,44),(1359416669110706177,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:19:26',NULL,NULL,37),(1359419021888757761,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:28:47',NULL,NULL,35),(1359419301594308609,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:29:53',NULL,NULL,28),(1359419619086344194,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:31:09',NULL,NULL,40),(1359419913560039426,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:32:19',NULL,NULL,30),(1359420213620547586,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:33:31',NULL,NULL,37),(1359420530173059074,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:34:46',NULL,NULL,412),(1359420649400344577,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:35:15',NULL,NULL,31),(1359420771743997954,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:35:44',NULL,NULL,40),(1359420950933053441,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:36:27',NULL,NULL,37),(1359421428542644225,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:38:20',NULL,NULL,182),(1359421441536598018,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:38:24',NULL,NULL,365),(1359421752590376961,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:39:38',NULL,NULL,197),(1359421795372277761,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:39:48',NULL,NULL,259),(1359421893875507202,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:40:11',NULL,NULL,865),(1359421902029234177,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:40:14',NULL,NULL,1841),(1359423028560568322,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:44:42',NULL,NULL,3071),(1359423032696152065,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:44:42',NULL,NULL,42),(1359423796189503489,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:47:45',NULL,NULL,77),(1359423804578111490,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:47:47',NULL,NULL,29),(1359423824144539649,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:47:52',NULL,NULL,34),(1359423830230474753,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:47:53',NULL,NULL,27),(1359423851088748545,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:47:57',NULL,NULL,1594),(1359423864284028930,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:48:01',NULL,NULL,3073),(1359424162016698370,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:49:12',NULL,NULL,26),(1359424191607513090,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:49:18',NULL,NULL,3056),(1359424196053475329,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:49:19',NULL,NULL,27),(1359424289603231745,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:49:42',NULL,NULL,30),(1359424642759434241,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:51:07',NULL,NULL,27),(1359424766512373762,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:51:36',NULL,NULL,3715),(1359424779602796545,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:51:40',NULL,NULL,3082),(1359424952802385922,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:52:21',NULL,NULL,71),(1359424991570337794,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:52:29',NULL,NULL,5525),(1359425002265812994,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:52:32',NULL,NULL,3070),(1359425540315324418,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:54:41',NULL,NULL,77),(1359425799246487554,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:55:42',NULL,NULL,29),(1359425820125732866,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:55:47',NULL,NULL,1285),(1359425834549944322,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:55:50',NULL,NULL,3054),(1359425922462556161,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:56:12',NULL,NULL,42),(1359425944390377474,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:56:16',NULL,NULL,1449),(1359425954985189377,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:56:19',NULL,NULL,3066),(1359426075844059137,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:56:49',NULL,NULL,34),(1359426105103523842,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:56:55',NULL,NULL,3962),(1359426109197164546,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:56:55',NULL,NULL,34),(1359426185680297986,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:57:15',NULL,NULL,29),(1359426207985606657,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:57:19',NULL,NULL,2016),(1359426217364070402,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:57:22',NULL,NULL,3054),(1359426300377735170,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:57:42',NULL,NULL,33),(1359426321462501378,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:57:47',NULL,NULL,1976),(1359426335391784962,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:57:50',NULL,NULL,3080),(1359426394258841602,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:58:05',NULL,NULL,37),(1359426419839901697,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:58:10',NULL,NULL,2727),(1359426431407792130,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:58:13',NULL,NULL,3069),(1359426531232227330,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:58:36',NULL,NULL,32),(1359426548487593986,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:58:41',NULL,NULL,1262),(1359426564572749826,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:58:44',NULL,NULL,3061),(1359426675575005186,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:59:11',NULL,NULL,37),(1359426699000193025,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:59:17',NULL,NULL,3031),(1359426713013362690,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 16:59:20',NULL,NULL,3069),(1359427768790982658,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:03:32',NULL,NULL,29),(1359427793570930690,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:03:38',NULL,NULL,626),(1359427799522648066,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:03:40',NULL,NULL,1265),(1359428028271599618,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:04:34',NULL,NULL,187),(1359428060538380289,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:04:42',NULL,NULL,1837),(1359428179182657537,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:05:10',NULL,NULL,134),(1359428204117794817,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:05:16',NULL,NULL,143),(1359428456958828545,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:06:16',NULL,NULL,401),(1359428473320808449,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:06:20',NULL,NULL,154),(1359428573107494913,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:06:44',NULL,NULL,129),(1359428621602037762,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:06:56',NULL,NULL,135),(1359429758195507201,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:11:27',NULL,NULL,135),(1359430027008450562,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:12:31',NULL,NULL,161),(1359430205039878145,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:13:13',NULL,NULL,133),(1359430463987818497,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:14:15',NULL,NULL,13),(1359430465019617282,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:14:15',NULL,NULL,27),(1359430566140092418,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:14:39',NULL,NULL,244),(1359430566928621569,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:14:39',NULL,NULL,156),(1359430674466381825,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:15:05',NULL,NULL,125),(1359430713599238146,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:15:14',NULL,NULL,153),(1359430929119354882,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:16:06',NULL,NULL,144),(1359430939575754753,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:16:08',NULL,NULL,143),(1359430974581415938,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:16:17',NULL,NULL,141),(1359431041497341953,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:16:33',NULL,NULL,130),(1359431128659173377,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:16:53',NULL,NULL,36),(1359431155582410753,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:17:00',NULL,NULL,233),(1359431156811341825,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:17:00',NULL,NULL,266),(1359431289863053314,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:17:32',NULL,NULL,151),(1359431343176851458,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:17:44',NULL,NULL,131),(1359432096830365698,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:20:44',NULL,NULL,180),(1359432110189223937,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:20:47',NULL,NULL,140),(1359432489601769474,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:22:18',NULL,NULL,172),(1359432507272372225,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:22:22',NULL,NULL,150),(1359432686100717570,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:23:05',NULL,NULL,125),(1359433550362222593,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:26:31',NULL,NULL,151),(1359433674039664642,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:27:00',NULL,NULL,31),(1359433899504476162,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:27:54',NULL,NULL,352),(1359433905095483393,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:27:55',NULL,NULL,1287),(1359434039061553154,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:28:27',NULL,NULL,245),(1359434040793800705,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:28:28',NULL,NULL,276),(1359434126189830145,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:28:48',NULL,NULL,50),(1359434145269719041,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:28:53',NULL,NULL,218),(1359434146125357057,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:28:53',NULL,NULL,181),(1359434266065674241,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:29:21',NULL,NULL,152),(1359434268632588290,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:29:22',NULL,NULL,386),(1359438163475865602,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:44:51',NULL,NULL,10),(1359438172241960961,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:44:51',NULL,NULL,72),(1359438376848498689,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:45:41',NULL,NULL,27),(1359438379524464641,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:45:42',NULL,NULL,34),(1359438730860339202,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:47:06',NULL,NULL,25),(1359438734798790657,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:47:07',NULL,NULL,66),(1359438739257335809,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:47:08',NULL,NULL,65),(1359439802999939074,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:51:21',NULL,NULL,25),(1359439811711508482,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:51:22',NULL,NULL,24),(1359439811925417985,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:51:22',NULL,NULL,20),(1359439812130938882,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:51:23',NULL,NULL,27),(1359439814672687106,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:51:24',NULL,NULL,22),(1359439816337825793,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:51:25',NULL,NULL,32),(1359439819227701249,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 17:51:25',NULL,NULL,26),(1359513810885873665,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 22:45:26',NULL,NULL,462),(1359513840753512449,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 22:45:33',NULL,NULL,417),(1359513841944694785,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 22:45:34',NULL,NULL,228),(1359513845572767746,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 22:45:34',NULL,NULL,544),(1359514332120420353,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 22:47:30',NULL,NULL,213),(1359514332363689985,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 22:47:31',NULL,NULL,43),(1359514608835432450,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 22:48:36',NULL,NULL,168),(1359514609082896385,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 22:48:36',NULL,NULL,41),(1359515171249655809,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 22:50:51',NULL,NULL,164),(1359515172470198273,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 22:50:51',NULL,NULL,269),(1359519189673906178,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 23:06:49',NULL,NULL,12),(1359519198389669889,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 23:06:49',NULL,NULL,70),(1359519865271422977,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 23:09:30',NULL,NULL,54),(1359520569029496834,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 23:12:17',NULL,NULL,52),(1359520581906010113,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 23:12:20',NULL,NULL,242),(1359520586385526785,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 23:12:21',NULL,NULL,223),(1359520586586853378,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-10 23:12:21',NULL,NULL,62),(1359555586745114626,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 01:31:25',NULL,NULL,792),(1359555617422254082,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 01:31:33',NULL,NULL,2459),(1359555619171278849,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 01:31:34',NULL,NULL,440),(1359555623051010050,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 01:31:34',NULL,NULL,582),(1359555839443542017,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 01:32:26',NULL,NULL,1664),(1359555840588587010,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 01:32:26',NULL,NULL,347),(1359711667597340674,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 11:48:37',NULL,NULL,1663),(1359711669317005313,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 11:50:16',NULL,NULL,16),(1359712232565895170,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 11:53:54',NULL,NULL,1195),(1359712257891102722,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 11:54:00',NULL,NULL,1613),(1359712259325554690,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 11:54:00',NULL,NULL,306),(1359712266468454402,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 11:54:02',NULL,NULL,1362),(1359712572270964737,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 11:55:15',NULL,NULL,196),(1359712572564566017,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 11:55:15',NULL,NULL,45),(1359712984675905538,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 11:56:53',NULL,NULL,714),(1359712985967751169,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 11:56:54',NULL,NULL,209),(1359713465309589505,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 11:58:48',NULL,NULL,193),(1359713465666105346,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 11:58:48',NULL,NULL,55),(1359731017146429441,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:08:21',NULL,NULL,27),(1359731022737436673,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:08:21',NULL,NULL,13),(1359732309788979201,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:13:41',NULL,NULL,52),(1359732356433833985,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:13:52',NULL,NULL,343),(1359732369524256769,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:13:55',NULL,NULL,3086),(1359732605751652353,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:14:52',NULL,NULL,42),(1359732629524967425,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:14:57',NULL,NULL,966),(1359732631630508033,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:14:58',NULL,NULL,468),(1359732633392115714,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:14:58',NULL,NULL,96),(1359733761466953730,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:19:27',NULL,NULL,2728),(1359733762200956929,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:19:27',NULL,NULL,144),(1359734664622239745,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:23:02',NULL,NULL,724),(1359734666081857538,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:23:03',NULL,NULL,301),(1359735853904556034,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:27:45',NULL,NULL,22),(1359735862544822274,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:27:46',NULL,NULL,60),(1359736190073827329,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:29:06',NULL,NULL,52),(1359736357908901889,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:29:46',NULL,NULL,241),(1359736358730985474,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:29:46',NULL,NULL,141),(1359736359880224769,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:29:47',NULL,NULL,137),(1359740170954862593,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:44:54',NULL,NULL,3080),(1359740198863761410,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:45:01',NULL,NULL,2020),(1359740254085967874,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:45:14',NULL,NULL,3079),(1359740275187511298,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:45:20',NULL,NULL,913),(1359740317600313346,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:45:29',NULL,NULL,3061),(1359740343047155713,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:45:36',NULL,NULL,2880),(1359740415252099074,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:45:53',NULL,NULL,3014),(1359740572039376898,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:46:30',NULL,NULL,3057),(1359740597494607873,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:46:37',NULL,NULL,2967),(1359740699252617218,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:47:00',NULL,NULL,3057),(1359740724493938690,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:47:06',NULL,NULL,3080),(1359740791091097601,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:47:23',NULL,NULL,3072),(1359740813429960705,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:47:28',NULL,NULL,3069),(1359740932611108865,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:47:56',NULL,NULL,2022),(1359741056666038274,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:48:26',NULL,NULL,3009),(1359741242071052289,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:49:10',NULL,NULL,3066),(1359741267710832642,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:49:17',NULL,NULL,993),(1359741390775906305,NULL,4,'/auth-consumer/v3/api-docs','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-11 13:49:46',NULL,NULL,3052),(1359741445633208322,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-11 13:49:58',NULL,NULL,3065),(1359741971280162817,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-11 13:52:04',NULL,NULL,3064),(1359742172736778242,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-11 13:52:52',NULL,NULL,3070),(1359743167399194625,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-11 13:56:49',NULL,NULL,3060),(1359743326908575746,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-11 13:57:27',NULL,NULL,3065),(1359744537187577857,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:02:14',NULL,NULL,2026),(1359745555510063106,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:06:19',NULL,NULL,969),(1359745991033966593,NULL,4,'/yeju-all-rest-api/v3/api-docs','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-11 13:41:45',NULL,NULL,1970),(1359745992220954625,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 13:44:49',NULL,NULL,10),(1359745992543916034,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:00:13',NULL,NULL,3079),(1359745992736854017,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:00:19',NULL,NULL,3071),(1359745992950763522,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:01:33',NULL,NULL,3059),(1359745993160478721,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:01:42',NULL,NULL,3040),(1359745993386971137,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:01:55',NULL,NULL,3067),(1359745993605074946,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:02:09',NULL,NULL,1016),(1359746064803385345,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:08:20',NULL,NULL,3060),(1359746077474377730,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:08:23',NULL,NULL,1010),(1359746090174730241,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:08:27',NULL,NULL,1017),(1359746227492048897,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:08:58',NULL,NULL,1008),(1359746234362318849,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:09:01',NULL,NULL,1009),(1359747333223829506,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:13:23',NULL,NULL,3073),(1359747350030405633,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:13:27',NULL,NULL,2024),(1359747388261486593,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:13:36',NULL,NULL,2035),(1359747400856981506,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:13:39',NULL,NULL,993),(1359747413649608705,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:13:42',NULL,NULL,1011),(1359747608777019394,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:14:29',NULL,NULL,1014),(1359747634668457985,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:14:35',NULL,NULL,1018),(1359747651659583490,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:14:39',NULL,NULL,2022),(1359748054174355458,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:16:15',NULL,NULL,1008),(1359748071144509441,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:16:19',NULL,NULL,1007),(1359748083790336002,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:16:22',NULL,NULL,983),(1359748279010021378,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:17:08',NULL,NULL,1014),(1359748291706179586,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:17:11',NULL,NULL,1015),(1359748406298759170,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:17:39',NULL,NULL,1021),(1359748418994917377,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:17:42',NULL,NULL,1043),(1359749492229230594,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:21:58',NULL,NULL,3042),(1359749500697530369,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:22:00',NULL,NULL,522),(1359749534654615554,NULL,4,'/yeju-all-rest-api/v3/api-docs','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-11 14:22:08',NULL,NULL,1025),(1359749657904238594,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:22:37',NULL,NULL,996),(1359749666116685826,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:22:39',NULL,NULL,994),(1359749882534383618,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:23:31',NULL,NULL,1027),(1359749903644315650,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:23:36',NULL,NULL,2029),(1359749975857647618,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:23:53',NULL,NULL,13),(1359749988495085570,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:23:56',NULL,NULL,994),(1359750022414422018,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:24:04',NULL,NULL,2024),(1359750052131065858,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:24:11',NULL,NULL,2034),(1359750064894332930,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:24:14',NULL,NULL,1013),(1359750081776406529,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:24:18',NULL,NULL,2022),(1359751880759529473,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:31:27',NULL,NULL,3011),(1359751888841953282,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:31:29',NULL,NULL,987),(1359752465722331138,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:33:47',NULL,NULL,995),(1359752478263300097,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:33:50',NULL,NULL,957),(1359753916557250562,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:39:32',NULL,NULL,8),(1359753929148551170,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:39:35',NULL,NULL,1015),(1359753950275260418,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:39:41',NULL,NULL,990),(1359753973717225473,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:39:46',NULL,NULL,2492),(1359753984303648770,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:39:49',NULL,NULL,1013),(1359753997029167106,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:39:52',NULL,NULL,1010),(1359754777656250369,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:42:58',NULL,NULL,1033),(1359754790276911106,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:43:01',NULL,NULL,993),(1359755757466636290,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:46:51',NULL,NULL,981),(1359755770259263490,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:46:54',NULL,NULL,1024),(1359756720550137858,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:50:41',NULL,NULL,12),(1359756733158215681,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:50:44',NULL,NULL,1008),(1359756750073843713,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:50:48',NULL,NULL,998),(1359756779937288193,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:50:55',NULL,NULL,3066),(1359756792490840066,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:50:58',NULL,NULL,1001),(1359756805283467266,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:51:01',NULL,NULL,1027),(1359757661089255426,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:54:25',NULL,NULL,176),(1359757661902950401,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:54:25',NULL,NULL,44),(1359758095430406145,NULL,4,'/yeju-all-rest-api/v3/api-docs','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:56:09',NULL,NULL,56),(1359758165345259521,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:56:25',NULL,NULL,142),(1359758166758739969,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:56:26',NULL,NULL,286),(1359758237273378818,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:56:43',NULL,NULL,175),(1359758239215341570,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:56:43',NULL,NULL,56),(1359758508816814082,NULL,4,'/auth-consumer/v3/api-docs','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:57:47',NULL,NULL,195),(1359758607290683394,NULL,4,'/yeju-all-rest-api/v3/api-docs','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:58:11',NULL,NULL,33),(1359758690576977922,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:58:31',NULL,NULL,139),(1359758691264843778,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:58:31',NULL,NULL,43),(1359758821569286145,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:59:02',NULL,NULL,177),(1359758821808361473,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:59:02',NULL,NULL,37),(1359758973096906753,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:59:38',NULL,NULL,236),(1359758974262923266,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:59:38',NULL,NULL,153),(1359759002599641089,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:59:45',NULL,NULL,152),(1359759002922602497,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 14:59:45',NULL,NULL,41),(1359759320213311489,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:01:01',NULL,NULL,9),(1359759321425465346,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:01:01',NULL,NULL,36),(1359759333173710849,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:01:04',NULL,NULL,55),(1359759342120161281,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:01:06',NULL,NULL,260),(1359759346704535553,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:01:06',NULL,NULL,174),(1359759346993942529,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:01:07',NULL,NULL,44),(1359759592876625922,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:02:06',NULL,NULL,262),(1359759593203781634,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:02:06',NULL,NULL,62),(1359759671620489218,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:02:25',NULL,NULL,193),(1359759671880536065,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:02:25',NULL,NULL,34),(1359760433939435521,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:05:26',NULL,NULL,306),(1359760434258202626,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:05:26',NULL,NULL,38),(1359760844301750273,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:07:04',NULL,NULL,159),(1359760844565991426,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:07:04',NULL,NULL,37),(1359760977751920642,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:07:36',NULL,NULL,128),(1359760978255237121,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:07:36',NULL,NULL,44),(1359761282585546753,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:08:49',NULL,NULL,163),(1359761282833010690,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:08:49',NULL,NULL,37),(1359762000696528897,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:11:40',NULL,NULL,10),(1359762001308897281,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:11:40',NULL,NULL,31),(1359762189381488642,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:12:25',NULL,NULL,68),(1359762202216058882,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:12:28',NULL,NULL,331),(1359762206754295809,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:12:28',NULL,NULL,362),(1359762207047897090,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 15:12:29',NULL,NULL,63),(1359791724730732546,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 17:09:47',NULL,NULL,286),(1359791902661496833,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 17:10:29',NULL,NULL,38),(1359792023683944449,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 17:10:58',NULL,NULL,27),(1359795547201294337,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 17:24:58',NULL,NULL,388),(1359797343160324097,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 17:32:06',NULL,NULL,44),(1359809708668932098,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 17:07:14',NULL,NULL,7),(1359809713697902594,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 17:07:16',NULL,NULL,1254),(1359812515987890178,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:32:24',NULL,NULL,772),(1359812646183280642,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:32:55',NULL,NULL,1154),(1359812706958745602,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:33:09',NULL,NULL,4543),(1359812717108961282,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:33:10',NULL,NULL,567),(1359812743818289153,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:33:18',NULL,NULL,4229),(1359812760914272258,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:33:22',NULL,NULL,3795),(1359812789234212866,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:33:28',NULL,NULL,220),(1359812881429209089,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:33:50',NULL,NULL,3992),(1359812884033871873,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:33:51',NULL,NULL,279),(1359812914656485377,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:33:58',NULL,NULL,3137),(1359812929110056962,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:34:01',NULL,NULL,1830),(1359812986051928065,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:34:16',NULL,NULL,5306),(1359813002241941505,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:34:19',NULL,NULL,3167),(1359813288905842689,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:35:27',NULL,NULL,162),(1359813314415599617,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:35:34',NULL,NULL,781),(1359813331058597890,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:35:36',NULL,NULL,1861),(1359813525166792706,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:36:24',NULL,NULL,3178),(1359813527503020033,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:36:25',NULL,NULL,493),(1359813572407238657,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:36:35',NULL,NULL,238),(1359813602119688194,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:36:42',NULL,NULL,3088),(1359813620314578946,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:36:46',NULL,NULL,3408),(1359813645908221954,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:36:52',NULL,NULL,718),(1359813667546636289,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:36:58',NULL,NULL,2321),(1359813686295175169,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:37:02',NULL,NULL,3222),(1359813891711213570,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:37:51',NULL,NULL,1182),(1359813919162933250,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:37:58',NULL,NULL,2394),(1359813932727312386,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:38:02',NULL,NULL,3194),(1359813979183423490,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:38:12',NULL,NULL,1689),(1359814015191523329,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:38:21',NULL,NULL,6025),(1359814023802429442,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:38:23',NULL,NULL,1099),(1359814055884660738,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:38:30',NULL,NULL,4016),(1359814070640222210,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:38:34',NULL,NULL,3361),(1359814277662679041,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:39:23',NULL,NULL,998),(1359814303054995457,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:39:30',NULL,NULL,1925),(1359814320272613377,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 18:39:33',NULL,NULL,3429),(1359820240675348482,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:03:04',NULL,NULL,247),(1359820274355609602,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:03:13',NULL,NULL,10),(1359820303703154689,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:03:20',NULL,NULL,8),(1359820315799527425,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:03:23',NULL,NULL,20),(1359820335714082818,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:03:28',NULL,NULL,11),(1359820344056553474,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:03:30',NULL,NULL,9),(1359820471034912770,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:04:00',NULL,NULL,1055),(1359820504610316289,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:04:08',NULL,NULL,3080),(1359820508968198146,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:04:09',NULL,NULL,219),(1359820581403828226,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:04:27',NULL,NULL,5193),(1359820594720743425,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:04:30',NULL,NULL,3071),(1359820655974359042,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:04:44',NULL,NULL,3077),(1359820663201144833,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:04:46',NULL,NULL,1006),(1359821743888662529,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:09:03',NULL,NULL,3052),(1359821754374422529,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:09:04',NULL,NULL,8),(1359821828399693826,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:09:24',NULL,NULL,814),(1359821928349958145,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:09:47',NULL,NULL,472),(1359822191303458817,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:10:50',NULL,NULL,404),(1359822224362962945,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:10:58',NULL,NULL,728),(1359822365123805186,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:11:31',NULL,NULL,3078),(1359822769651843073,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:13:08',NULL,NULL,632),(1359823453591830529,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:15:52',NULL,NULL,3060),(1359823951296331777,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:17:50',NULL,NULL,655),(1359823988164263939,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:17:59',NULL,NULL,6121),(1359824015339159554,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 19:18:03',NULL,NULL,3132),(1359856626350555137,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:27:39',NULL,NULL,1779),(1359856661737897985,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:27:48',NULL,NULL,3204),(1359856662077636609,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:27:48',NULL,NULL,38),(1359856839106625537,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:28:30',NULL,NULL,1008),(1359856840104869889,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:28:30',NULL,NULL,202),(1359856980295286785,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:29:04',NULL,NULL,38),(1359857008187408386,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:29:10',NULL,NULL,258),(1359857009491836929,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:29:11',NULL,NULL,262),(1359857298831704066,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:30:19',NULL,NULL,74),(1359857371292499969,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:30:37',NULL,NULL,231),(1359857372135555074,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:30:37',NULL,NULL,123),(1359857773027131394,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:32:13',NULL,NULL,35),(1359857784607604738,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:32:15',NULL,NULL,183),(1359857785295470594,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:32:16',NULL,NULL,127),(1359858156050972674,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:33:44',NULL,NULL,34),(1359858192268787714,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:33:53',NULL,NULL,179),(1359858193367695361,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:33:53',NULL,NULL,140),(1359858263668424705,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:34:10',NULL,NULL,37),(1359858524164063234,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:35:12',NULL,NULL,222),(1359858524839346177,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:35:12',NULL,NULL,124),(1359859246674870273,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:38:04',NULL,NULL,285),(1359859263611469825,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:38:08',NULL,NULL,483),(1359859264236421121,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:38:08',NULL,NULL,203),(1359860563262709762,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:43:18',NULL,NULL,184),(1359860602127130625,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:43:27',NULL,NULL,13),(1359860620246523906,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:43:31',NULL,NULL,11),(1359860633471164417,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:43:35',NULL,NULL,11),(1359860694179520514,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:43:49',NULL,NULL,514),(1359860710759604225,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:43:53',NULL,NULL,517),(1359860711170646018,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:43:53',NULL,NULL,85),(1359861792818798594,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:48:11',NULL,NULL,638),(1359861803350695938,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:48:14',NULL,NULL,431),(1359861803803680770,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:48:14',NULL,NULL,91),(1359862022851207170,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:49:06',NULL,NULL,50),(1359862031080431617,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:49:08',NULL,NULL,217),(1359862032653295617,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:49:08',NULL,NULL,326),(1359862036491083778,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:49:09',NULL,NULL,585),(1359862819156598786,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:52:16',NULL,NULL,47),(1359862819924156417,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:52:16',NULL,NULL,63),(1359862845702348802,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:52:22',NULL,NULL,56),(1359862868972347393,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:52:28',NULL,NULL,15),(1359862896545701889,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:52:34',NULL,NULL,26),(1359862919069114370,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:52:40',NULL,NULL,12),(1359862945551949826,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:52:46',NULL,NULL,15),(1359862969983770625,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:52:52',NULL,NULL,13),(1359862995850043393,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:52:58',NULL,NULL,10),(1359863019761770497,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:53:04',NULL,NULL,14),(1359863036610289666,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:53:07',NULL,NULL,23),(1359863042431983617,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:53:09',NULL,NULL,62),(1359863095330545666,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:53:22',NULL,NULL,164),(1359863100380487682,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:53:22',NULL,NULL,58),(1359863100724420609,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:53:22',NULL,NULL,115),(1359863116281094146,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:53:26',NULL,NULL,98),(1359863142034120706,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:53:33',NULL,NULL,18),(1359863166361083905,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:53:38',NULL,NULL,77),(1359863174959407105,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:53:40',NULL,NULL,38),(1359863180244230146,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:53:42',NULL,NULL,123),(1359863338742784002,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:54:20',NULL,NULL,50),(1359863358753808386,NULL,4,'/auth-consumer/v3/api-docs','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-11 21:54:24',NULL,NULL,250),(1359863510877020162,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-11 21:55:01',NULL,NULL,70),(1359863906991284225,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:56:35',NULL,NULL,28),(1359863934828879874,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:56:42',NULL,NULL,157),(1359863935726460929,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:56:42',NULL,NULL,42),(1359863936426909698,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:56:42',NULL,NULL,42),(1359864104186486785,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:57:22',NULL,NULL,29),(1359864118111576065,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:57:25',NULL,NULL,10),(1359864130409275394,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:57:28',NULL,NULL,10),(1359864143231262722,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:57:31',NULL,NULL,26),(1359864154467803138,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:57:34',NULL,NULL,13),(1359864168367726594,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:57:37',NULL,NULL,11),(1359864181684641794,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:57:40',NULL,NULL,93),(1359864193634213889,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:57:43',NULL,NULL,10),(1359864204862365698,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:57:46',NULL,NULL,24),(1359864219265605634,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:57:49',NULL,NULL,68),(1359864230367928322,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:57:52',NULL,NULL,42),(1359864242707570690,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:57:55',NULL,NULL,111),(1359864256443916289,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:57:58',NULL,NULL,1060),(1359864562443558913,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:11',NULL,NULL,137),(1359864564494573570,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:11',NULL,NULL,79),(1359864580374208513,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:16',NULL,NULL,231),(1359864581989015554,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:16',NULL,NULL,53),(1359864583100506114,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:16',NULL,NULL,54),(1359864588607627266,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:18',NULL,NULL,177),(1359864610912935937,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:22',NULL,NULL,676),(1359864612682932226,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:23',NULL,NULL,234),(1359864623978192897,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:26',NULL,NULL,13),(1359864635269259266,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:29',NULL,NULL,10),(1359864649227902977,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:32',NULL,NULL,17),(1359864660518969346,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:35',NULL,NULL,22),(1359864674402115586,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:38',NULL,NULL,20),(1359864685688987650,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:41',NULL,NULL,14),(1359864702243905538,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:45',NULL,NULL,10),(1359864713266536449,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:47',NULL,NULL,10),(1359864727548141569,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:51',NULL,NULL,12),(1359864738516246529,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:53',NULL,NULL,15),(1359864752328089602,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:57',NULL,NULL,13),(1359864763635933186,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 21:59:59',NULL,NULL,12),(1359864777451970561,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:03',NULL,NULL,10),(1359864788839505922,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:05',NULL,NULL,19),(1359864802613600257,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:09',NULL,NULL,9),(1359864813913055233,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:11',NULL,NULL,9),(1359864827896864770,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:15',NULL,NULL,17),(1359864839229874177,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:17',NULL,NULL,9),(1359864854304202754,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:21',NULL,NULL,54),(1359864864605413377,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:23',NULL,NULL,60),(1359864878798938113,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:27',NULL,NULL,23),(1359864889569910786,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:29',NULL,NULL,32),(1359864903620829185,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:33',NULL,NULL,8),(1359864914773483522,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:35',NULL,NULL,14),(1359864928799236097,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:39',NULL,NULL,12),(1359864939687649281,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:41',NULL,NULL,17),(1359864951133904897,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:44',NULL,NULL,21),(1359864962403999745,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:47',NULL,NULL,10),(1359864976333283329,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:50',NULL,NULL,10),(1359864987649515522,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:53',NULL,NULL,14),(1359865001465552898,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:56',NULL,NULL,9),(1359865012886642690,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:59',NULL,NULL,17),(1359865015508082689,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:00:59',NULL,NULL,11),(1359865021208141826,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:01:01',NULL,NULL,35),(1359867231363080194,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:09:48',NULL,NULL,630),(1359867239990763522,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:09:48',NULL,NULL,30),(1359868459899887618,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:14:41',NULL,NULL,798),(1359868467252502530,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:14:41',NULL,NULL,176),(1359868467537715201,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:14:41',NULL,NULL,122),(1359868468481433602,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:14:43',NULL,NULL,30),(1359868477721485313,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:14:45',NULL,NULL,26),(1359868493001334786,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:14:48',NULL,NULL,70),(1359868502874726401,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:14:51',NULL,NULL,27),(1359868516808204290,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:14:54',NULL,NULL,29),(1359868528011190274,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:14:57',NULL,NULL,29),(1359868541814644738,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:15:00',NULL,NULL,26),(1359868553122488321,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:15:03',NULL,NULL,27),(1359868566984663041,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:15:06',NULL,NULL,23),(1359868580473544706,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:15:09',NULL,NULL,24),(1359868594398633985,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:15:13',NULL,NULL,27),(1359868605622591489,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:15:15',NULL,NULL,22),(1359868619497349121,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:15:19',NULL,NULL,27),(1359868630813581314,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:15:21',NULL,NULL,23),(1359868644751253506,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:15:25',NULL,NULL,28),(1359868655035686914,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:15:27',NULL,NULL,36),(1359868661494915074,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:15:29',NULL,NULL,80),(1359868662669320193,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:15:29',NULL,NULL,39),(1359868694223069186,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:15:36',NULL,NULL,121),(1359868694575390721,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:15:36',NULL,NULL,245),(1359869950610055170,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:20:36',NULL,NULL,24),(1359871209085804545,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:25:36',NULL,NULL,53),(1359872469914558465,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:30:37',NULL,NULL,33),(1359873728742629378,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:35:37',NULL,NULL,36),(1359874986530512897,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:40:37',NULL,NULL,31),(1359876244796547073,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:45:37',NULL,NULL,30),(1359876554365542401,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:46:50',NULL,NULL,40),(1359876556081012738,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:46:51',NULL,NULL,52),(1359878122984906754,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:53:04',NULL,NULL,40),(1359878131918774273,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:53:05',NULL,NULL,49),(1359878762561740801,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:55:37',NULL,NULL,28),(1359878763710980097,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:55:37',NULL,NULL,34),(1359879757651976194,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 22:59:34',NULL,NULL,33),(1359880561280622594,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:02:46',NULL,NULL,31),(1359880607099199490,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:02:57',NULL,NULL,303),(1359880607485075458,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:02:57',NULL,NULL,42),(1359880607803842561,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:02:57',NULL,NULL,59),(1359880649549750273,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:03:07',NULL,NULL,23),(1359880702301511682,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:03:19',NULL,NULL,23),(1359880943360745474,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:04:17',NULL,NULL,21),(1359880967717068801,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:04:23',NULL,NULL,134),(1359880968153276418,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:04:23',NULL,NULL,51),(1359881131412365313,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:05:02',NULL,NULL,42),(1359881132083453954,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:05:02',NULL,NULL,48),(1359882394497654785,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:10:03',NULL,NULL,38),(1359883377663483906,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:13:57',NULL,NULL,209),(1359883378636562433,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:13:57',NULL,NULL,156),(1359883693477797890,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:15:13',NULL,NULL,34),(1359883694211801089,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:15:13',NULL,NULL,49),(1359886884365889537,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:25:14',NULL,NULL,837),(1359886885536100353,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:26:22',NULL,NULL,49),(1359886885770981378,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:26:24',NULL,NULL,15),(1359886885968113665,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:26:34',NULL,NULL,10),(1359887716201230338,NULL,4,'/auth-consumer/v3/api-docs','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-11 23:31:12',NULL,NULL,348),(1359887781884030977,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-11 23:31:27',NULL,NULL,951),(1359887847881404417,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-11 23:31:43',NULL,NULL,119),(1359888074046664705,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-11 23:32:37',NULL,NULL,298),(1359888172658946049,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:33:00',NULL,NULL,53),(1359888188597301249,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:33:04',NULL,NULL,461),(1359888193127149569,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:33:04',NULL,NULL,87),(1359888391194767362,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:33:53',NULL,NULL,43),(1359888506672345090,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:34:20',NULL,NULL,52),(1359888570190884865,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:34:35',NULL,NULL,38),(1359888624674893826,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:34:48',NULL,NULL,40),(1359888628097445890,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:34:49',NULL,NULL,549),(1359888880003149826,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:35:49',NULL,NULL,23),(1359888892732862465,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:35:52',NULL,NULL,117),(1359888906561482753,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:35:55',NULL,NULL,19),(1359888917588307969,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:35:58',NULL,NULL,27),(1359889065013899265,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:36:33',NULL,NULL,39),(1359889065710153730,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:36:33',NULL,NULL,42),(1359889077382901762,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:36:36',NULL,NULL,23),(1359889091190550529,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:36:39',NULL,NULL,32),(1359889102401925122,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:36:42',NULL,NULL,17),(1359889116436066306,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:36:45',NULL,NULL,23),(1359889263748411394,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:37:21',NULL,NULL,85),(1359889264155258881,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:37:21',NULL,NULL,48),(1359889276016750594,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:37:23',NULL,NULL,24),(1359889288280895490,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:37:26',NULL,NULL,22),(1359889302151458818,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:37:30',NULL,NULL,27),(1359889513808621569,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:38:20',NULL,NULL,43),(1359889515054329858,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:38:20',NULL,NULL,46),(1359889526085349378,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:38:23',NULL,NULL,19),(1359889538831839234,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:38:26',NULL,NULL,17),(1359889552836620290,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:38:29',NULL,NULL,24),(1359890627161452545,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:42:46',NULL,NULL,150),(1359890635826884609,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:42:46',NULL,NULL,36),(1359891355183575041,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:45:39',NULL,NULL,28),(1359891367426748418,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:45:42',NULL,NULL,21),(1359891380462645250,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:45:45',NULL,NULL,19),(1359891394471620609,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:45:49',NULL,NULL,17),(1359891405167095809,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:45:51',NULL,NULL,18),(1359891425962455042,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:45:56',NULL,NULL,23),(1359891438293708802,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:45:59',NULL,NULL,28),(1359891452051025921,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:46:02',NULL,NULL,15),(1359891463434366978,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:46:05',NULL,NULL,32),(1359891477946658817,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:46:08',NULL,NULL,14),(1359891490076585985,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:46:11',NULL,NULL,59),(1359891502709829634,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:46:14',NULL,NULL,18),(1359891513954758658,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:46:17',NULL,NULL,20),(1359891529058447361,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:46:20',NULL,NULL,17),(1359891539691008002,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:46:23',NULL,NULL,20),(1359891552722710530,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:46:26',NULL,NULL,18),(1359891578211495937,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:46:32',NULL,NULL,17),(1359891596058259457,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:46:36',NULL,NULL,19),(1359891609870102530,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:46:40',NULL,NULL,85),(1359891619789631489,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:46:42',NULL,NULL,18),(1359891633656000514,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:46:46',NULL,NULL,17),(1359891644968038401,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:46:48',NULL,NULL,27),(1359891658997985282,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:46:52',NULL,NULL,44),(1359891670368743425,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:46:54',NULL,NULL,22),(1359891683928928257,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:46:58',NULL,NULL,18),(1359891695249354754,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:47:00',NULL,NULL,14),(1359891709111529474,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:47:04',NULL,NULL,24),(1359891720461316097,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:47:06',NULL,NULL,17),(1359891734709366786,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:47:10',NULL,NULL,19),(1359891739360849922,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:47:11',NULL,NULL,50),(1359891739969024002,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:47:11',NULL,NULL,52),(1359892164218679297,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:48:52',NULL,NULL,49),(1359892164994625538,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:48:52',NULL,NULL,79),(1359892393378672641,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:49:47',NULL,NULL,37),(1359892394053955586,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:49:47',NULL,NULL,46),(1359893172416114690,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:52:52',NULL,NULL,57),(1359893173036871681,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:52:53',NULL,NULL,68),(1359894814993641474,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:59:24',NULL,NULL,16),(1359894824409853954,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-11 23:59:24',NULL,NULL,34),(1359895200139800577,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:00:56',NULL,NULL,40),(1359895323464921090,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:01:25',NULL,NULL,35),(1359895335750037505,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:01:28',NULL,NULL,108),(1359895485415387138,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:02:04',NULL,NULL,31),(1359895503736107009,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:02:08',NULL,NULL,36),(1359895590835023873,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:02:29',NULL,NULL,30),(1359895601333366786,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:02:32',NULL,NULL,30),(1359895649358147586,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:02:43',NULL,NULL,25),(1359895671537627138,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:02:48',NULL,NULL,33),(1359896010089263105,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:04:09',NULL,NULL,119),(1359896045296250881,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:04:17',NULL,NULL,638),(1359896045661155329,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:04:17',NULL,NULL,48),(1359896047150133249,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:04:18',NULL,NULL,45),(1359896047380819969,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:04:18',NULL,NULL,13),(1359896058592194561,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:04:21',NULL,NULL,23),(1359896071829417985,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:04:24',NULL,NULL,21),(1359896111679500290,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:04:33',NULL,NULL,106),(1359896121724858370,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:04:36',NULL,NULL,27),(1359896139357712386,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:04:40',NULL,NULL,58),(1359898117878018049,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:12:24',NULL,NULL,124),(1359899916311654402,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:12:23',NULL,NULL,3090),(1359900296256933890,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:21:11',NULL,NULL,7),(1359900605079343105,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:18',NULL,NULL,10),(1359900624633188354,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:29',NULL,NULL,7),(1359900629414694914,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:30',NULL,NULL,8),(1359900630446493697,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:31',NULL,NULL,8),(1359900630777843714,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:31',NULL,NULL,7),(1359900631771893761,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:31',NULL,NULL,6),(1359900632803692545,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:31',NULL,NULL,6),(1359900633478975490,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:31',NULL,NULL,10),(1359900634724683778,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:32',NULL,NULL,9),(1359900635420938242,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:32',NULL,NULL,7),(1359900637400649730,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:32',NULL,NULL,6),(1359900638478585858,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:33',NULL,NULL,7),(1359900639116120065,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:33',NULL,NULL,7),(1359900639917232129,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:33',NULL,NULL,6),(1359900644644212738,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:33',NULL,NULL,6),(1359900644983951361,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:34',NULL,NULL,7),(1359900645218832385,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:33',NULL,NULL,7),(1359900645818617857,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:34',NULL,NULL,7),(1359900646686838785,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:34',NULL,NULL,7),(1359900648234536961,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:35',NULL,NULL,7),(1359900649996144641,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:35',NULL,NULL,7),(1359900652265263106,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:36',NULL,NULL,8),(1359900654077202433,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:36',NULL,NULL,10),(1359900655817838594,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:37',NULL,NULL,8),(1359900658028236802,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:37',NULL,NULL,5),(1359900659970199554,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:37',NULL,NULL,6),(1359900661308182530,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:38',NULL,NULL,11),(1359900712487079937,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:50',NULL,NULL,185),(1359900725153878018,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:53',NULL,NULL,252),(1359900735182458881,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:55',NULL,NULL,968),(1359900743482986498,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:58',NULL,NULL,16),(1359900749103353858,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:22:59',NULL,NULL,2890),(1359900754774052866,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:23:00',NULL,NULL,920),(1359900755784880129,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:23:00',NULL,NULL,13),(1359900767994499073,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 00:23:03',NULL,NULL,15),(1359900903374049281,NULL,4,'/auth-consumer/v3/api-docs','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-12 00:23:36',NULL,NULL,945),(1359901072689713153,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-12 00:24:16',NULL,NULL,92),(1360154859270500354,NULL,4,'/auth-consumer/v3/api-docs','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-12 15:21:56',NULL,NULL,2237),(1360154861464121346,NULL,4,'/auth-consumer/v3/api-docs','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-12 15:22:14',NULL,NULL,145),(1360154867256455169,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 15:26:13',NULL,NULL,463),(1360154868581855233,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 16:18:45',NULL,NULL,20),(1360155954222907393,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:17:04',NULL,NULL,639),(1360155997826891777,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:17:14',NULL,NULL,7177),(1360156013329039362,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:17:18',NULL,NULL,3178),(1360156063262228481,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:17:30',NULL,NULL,72),(1360156081620697089,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:17:34',NULL,NULL,1928),(1360156085861138434,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:17:35',NULL,NULL,999),(1360156094287495169,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:17:37',NULL,NULL,1577),(1360157816422629378,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:22:30',NULL,NULL,27),(1360159230561607682,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:27:30',NULL,NULL,25),(1360159882201210881,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:32:30',NULL,NULL,24),(1360163034141024258,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:45:12',NULL,NULL,14),(1360163046589718529,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:45:12',NULL,NULL,10),(1360163050880491521,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:45:16',NULL,NULL,12),(1360163055037046786,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:45:17',NULL,NULL,9),(1360163131398545410,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:45:35',NULL,NULL,386),(1360163148377088002,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:45:39',NULL,NULL,1075),(1360163160557346818,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:45:42',NULL,NULL,3075),(1360163284129931266,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:46:11',NULL,NULL,48),(1360163309362864129,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:46:17',NULL,NULL,2027),(1360163313917878274,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:46:18',NULL,NULL,917),(1360163318074433538,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:46:19',NULL,NULL,723),(1360164543792668673,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:51:12',NULL,NULL,39),(1360164815214469121,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:52:16',NULL,NULL,374),(1360164820012752897,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:52:17',NULL,NULL,918),(1360166605330833409,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:59:17',NULL,NULL,970),(1360167692578316290,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:03:43',NULL,NULL,985),(1360167708717998081,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:03:46',NULL,NULL,3087),(1360167759615877121,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:03:58',NULL,NULL,272),(1360167768142897153,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:03:59',NULL,NULL,953),(1360167797729517569,NULL,4,'/yeju-all-rest-api/v3/api-docs','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:04:07',NULL,NULL,208),(1360169549925179393,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:11:05',NULL,NULL,13),(1360169558435422210,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:11:06',NULL,NULL,72),(1360169577850855426,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:11:12',NULL,NULL,969),(1360169585912307713,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:11:12',NULL,NULL,542),(1360169592367341569,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:11:14',NULL,NULL,784),(1360170122711916545,NULL,4,'/yeju-all-rest-api/v3/api-docs','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:13:22',NULL,NULL,33),(1360170148074872834,NULL,4,'/auth-consumer/v3/api-docs','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:13:28',NULL,NULL,99),(1360170554666508290,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:15:05',NULL,NULL,316),(1360170559624175617,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:15:05',NULL,NULL,356),(1360170568100864001,NULL,4,'/auth-consumer/v3/api-docs','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:15:06',NULL,NULL,76),(1360171763515252738,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:19:53',NULL,NULL,251),(1360171798344753153,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:19:56',NULL,NULL,3087),(1360171807983263746,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:20:00',NULL,NULL,70),(1360171817009405954,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:20:03',NULL,NULL,3105),(1360171851729854465,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:20:14',NULL,NULL,89),(1360171856213565442,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:20:15',NULL,NULL,477),(1360172014825365505,NULL,4,'/auth-consumer/v3/api-docs','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:20:53',NULL,NULL,100),(1360173113753985025,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:25:15',NULL,NULL,10),(1360238193778614273,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:57:16',NULL,NULL,23),(1360238195246620673,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 17:59:16',NULL,NULL,664),(1360238195452141569,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:03:06',NULL,NULL,296),(1360238195670245378,NULL,4,'/yeju-all-rest-api/platform/menu/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:03:10',NULL,NULL,3193),(1360238195892543489,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:08:58',NULL,NULL,13),(1360238196169367553,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:30:15',NULL,NULL,9),(1360238196353916929,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:35:15',NULL,NULL,17),(1360238196567826434,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:40:15',NULL,NULL,10),(1360238196802707458,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:45:15',NULL,NULL,10),(1360238196995645442,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 18:50:15',NULL,NULL,11),(1360238197184389122,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 22:42:23',NULL,NULL,10),(1360238198392348673,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 22:43:40',NULL,NULL,11),(1360238199084408834,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 22:43:41',NULL,NULL,110),(1360238199294124034,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 22:43:48',NULL,NULL,3076),(1360238199487062017,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-12 22:43:48',NULL,NULL,46),(1360257996941672449,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:00:27',NULL,NULL,22),(1360259545361264641,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:06:09',NULL,NULL,7),(1360259765700636673,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:09:36',NULL,NULL,12),(1360259796067397633,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:09:43',NULL,NULL,7),(1360259883136954369,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:10:04',NULL,NULL,534),(1360259905442263042,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:10:08',NULL,NULL,634),(1360259918448799746,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:10:11',NULL,NULL,3078),(1360259969317318657,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:10:24',NULL,NULL,80),(1360259988984410114,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:10:28',NULL,NULL,1522),(1360259991966560257,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:10:29',NULL,NULL,527),(1360260007581954049,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:10:33',NULL,NULL,3887),(1360260041446764545,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:10:41',NULL,NULL,446),(1360260049969590274,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:10:42',NULL,NULL,280),(1360262052422594561,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:18:38',NULL,NULL,183),(1360262056709173249,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:18:39',NULL,NULL,1053),(1360262553323155457,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:20:39',NULL,NULL,238),(1360262561795649537,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:20:40',NULL,NULL,1026),(1360262588555309058,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:20:48',NULL,NULL,843),(1360262595010342913,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:20:49',NULL,NULL,296),(1360264428692942850,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:28:07',NULL,NULL,10),(1360264434187481090,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:28:08',NULL,NULL,44),(1360264445365301250,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:28:11',NULL,NULL,9),(1360264454164951042,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:28:13',NULL,NULL,52),(1360264466924023811,NULL,1,'/auth-consumer/auth/login','POST',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:28:17',NULL,NULL,1356),(1360264471101550594,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:28:17',NULL,NULL,60),(1360264481876717570,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:28:18',NULL,NULL,710),(1360264918440849410,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:30:04',NULL,NULL,553),(1360264925072044034,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:30:05',NULL,NULL,1063),(1360265799185969154,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:33:33',NULL,NULL,284),(1360265807574577153,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:33:34',NULL,NULL,1059),(1360417052964638722,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:15:42',NULL,NULL,20),(1360417054319398913,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:18:18',NULL,NULL,853),(1360417054550085633,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:18:34',NULL,NULL,177),(1360417054998876161,NULL,4,'/auth-consumer/authz/getAuthzDetailInfo','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:25:20',NULL,NULL,638),(1360417055380557826,NULL,4,'/yeju-all-rest-api/platform/menu/v2/getRouters','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:25:21',NULL,NULL,513),(1360417055745462274,NULL,4,'/auth-consumer/authz/refreshToken','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:38:34',NULL,NULL,11),(1360417056764678145,NULL,3,'/auth-consumer/auth/logout','DELETE',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:42:43',NULL,NULL,13),(1360417057553207298,NULL,4,'/auth-consumer/auth/code/image','GET',NULL,NULL,NULL,NULL,'127.0.0.1',NULL,NULL,NULL,0,NULL,'2021-02-13 00:42:44',NULL,NULL,13),(1360417144966696962,NULL,4,'/auth-consumer/v3/api-docs','GET',NULL,NULL,NULL,NULL,'192.168.0.123',NULL,NULL,NULL,0,NULL,'2021-02-13 10:34:57',NULL,NULL,2621);

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

insert  into `table_system_resources`(`resource_id`,`resource_name`,`resource_code`,`resource_type`,`parent_menu_id`,`order_number`,`path`,`componet_path`,`is_cache`,`resource_status`,`visible`,`icon`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`,`is_frame`) values (1,'超级权限','*:**','1',NULL,NULL,NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,0,0),(2,'运营中心','system','0',0,1,'/system','Layout',1,1,1,'system',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(3,'监控中心','monitor','0',0,2,'/monitor','Layout',1,1,1,'monitor',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(4,'系统工具','tool','0',0,3,'/tool','Layout',1,1,1,'tool',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(5,'产商品中心','product','0',0,4,'/product','Layout',1,1,1,'product',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(201,'用户管理','system:user:list','3',2,1,'user','system/user/index',1,1,1,'user',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(202,'角色管理','system:role:list','3',2,2,'role','system/role/index',1,1,1,'peoples',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(203,'菜单管理','system:menu:list','3',2,3,'menu','system/menu/index',1,1,1,'tree-table',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(204,'部门管理','system:dept:list','3',2,4,'dept','system/dept/index',1,1,1,'tree',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(205,'数据字典','system:dict:list','3',2,5,'dict','system/dict/index',1,1,1,'dict',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(206,'通知公告','system:notice:list','3',2,6,'notice','system/notice/index',1,1,1,'message',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(301,'日志管理','log','0',3,1,'/monitor/log','monitor/log/index',1,1,1,'log',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(302,'登录日志','log:login:list','3',301,1,'logininfo','monitor/log/logininfo/index',1,1,1,'log',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(303,'操作日志','log:operation:list','3',301,2,'operlog','monitor/log/operlog/index',1,1,1,'log',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(401,'api文档','tool:api','3',4,1,'swagger','tool/swagger/index',1,1,1,'monitor',NULL,NULL,NULL,NULL,NULL,NULL,0,0),(402,'表单构建','tool:build','3',4,2,'build','tool/build/index',1,1,1,'tool',NULL,NULL,NULL,NULL,NULL,NULL,0,0);

/*Table structure for table `table_system_role` */

DROP TABLE IF EXISTS `table_system_role`;

CREATE TABLE `table_system_role` (
  `role_id` bigint NOT NULL COMMENT '主键',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色字符串',
  `role_status` bigint DEFAULT NULL COMMENT '角色状态索引',
  `role_status_value` varchar(4) DEFAULT NULL COMMENT '角色状态值0未启用1启用，详见属性表',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';

/*Data for the table `table_system_role` */

insert  into `table_system_role`(`role_id`,`role_name`,`role_code`,`role_status`,`role_status_value`,`create_time`,`create_by`,`update_time`,`changed_by`,`remark`,`version_number`,`is_delete`) values (1,'超级管理员','admin',2,'1',NULL,NULL,NULL,NULL,NULL,1,0);

/*Table structure for table `table_system_timing_task_scheduler` */

DROP TABLE IF EXISTS `table_system_timing_task_scheduler`;

CREATE TABLE `table_system_timing_task_scheduler` (
  `task_id` bigint NOT NULL COMMENT '主键',
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
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时任务调度表';

/*Data for the table `table_system_timing_task_scheduler` */

/*Table structure for table `table_system_timing_task_scheduler_group` */

DROP TABLE IF EXISTS `table_system_timing_task_scheduler_group`;

CREATE TABLE `table_system_timing_task_scheduler_group` (
  `task_group_id` bigint NOT NULL COMMENT '主键',
  `group_name` varchar(64) DEFAULT NULL COMMENT '任务组名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `changed_by` bigint DEFAULT NULL COMMENT '更改者',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `version_number` int DEFAULT NULL COMMENT '字段版本',
  `is_delete` int DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`task_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时任务组信息表';

/*Data for the table `table_system_timing_task_scheduler_group` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
