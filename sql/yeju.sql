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
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yeju_core` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `yeju_core`;

/*Table structure for table `join_rent_invitation` */

DROP TABLE IF EXISTS `join_rent_invitation`;

CREATE TABLE `join_rent_invitation` (
  `invitation_id` BIGINT NOT NULL COMMENT '邀请标识',
  `initiator_id` BIGINT DEFAULT NULL COMMENT '邀请人标识',
  `house_id` BIGINT DEFAULT NULL COMMENT '房源标识',
  `status` CHAR(1) DEFAULT '1' COMMENT '邀请状态0失效1生效2已邀请到',
  `number_of_people` INT DEFAULT NULL COMMENT '预邀请人数',
  `invitation_type` CHAR(1) DEFAULT NULL COMMENT '0发帖1邀请码',
  `is_recommended` CHAR(1) DEFAULT '1' COMMENT '是否支持系统推荐',
  `roommates_gender` CHAR(1) DEFAULT NULL COMMENT '舍友性别限制0不限1男2女',
  `is_pet` CHAR(1) DEFAULT '0' COMMENT '是否接受宠物',
  `min_age` INT DEFAULT '18' COMMENT '舍友最小年龄',
  `max_age` INT DEFAULT NULL COMMENT '舍友最大年龄',
  `other` VARCHAR(128) DEFAULT NULL COMMENT '其他要求',
  `email` VARCHAR(32) DEFAULT NULL COMMENT '安全邮箱',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`invitation_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='合租申请表。当意向客户邀请合租后，创建合租申请工单。这张表用于存储合租邀请流程信息';

/*Data for the table `join_rent_invitation` */

/*Table structure for table `r_t_account_role` */

DROP TABLE IF EXISTS `r_t_account_role`;

CREATE TABLE `r_t_account_role` (
  `account_id` BIGINT NOT NULL COMMENT '账号主键',
  `role_id` BIGINT NOT NULL COMMENT '角色主键',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `versions` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`account_id`,`role_id`),
  UNIQUE KEY `account_id_2` (`account_id`,`role_id`),
  KEY `account_id` (`account_id`),
  KEY `role_id` (`role_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账户-角色关系表 N-1';

/*Data for the table `r_t_account_role` */

/*Table structure for table `r_t_department_role` */

DROP TABLE IF EXISTS `r_t_department_role`;

CREATE TABLE `r_t_department_role` (
  `department_id` BIGINT NOT NULL COMMENT '部门主键',
  `role_id` BIGINT NOT NULL COMMENT '角色主键',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `versions` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`department_id`,`role_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门-角色表 N-1. 这个表的作用在于：员工分配岗位后，即关联了部门，获得部门的角色。例如财务部的员工可以登录平台获取';

/*Data for the table `r_t_department_role` */

/*Table structure for table `r_t_role_resource` */

DROP TABLE IF EXISTS `r_t_role_resource`;

CREATE TABLE `r_t_role_resource` (
  `role_id` BIGINT NOT NULL COMMENT '角色主键',
  `resource_id` BIGINT NOT NULL COMMENT '资源主键',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `versions` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色-资源关系表 N-N';

/*Data for the table `r_t_role_resource` */

/*Table structure for table `table_business_account_assets` */

DROP TABLE IF EXISTS `table_business_account_assets`;

CREATE TABLE `table_business_account_assets` (
  `assets_id` BIGINT NOT NULL COMMENT '资产标识',
  `account_id` BIGINT DEFAULT NULL COMMENT '账户主键',
  `customer_id` BIGINT DEFAULT NULL COMMENT '客户主键。\n            当账户未系统账户时，这个客户标识是哪个客户托管到平台的押金',
  `balance` DECIMAL(12,2) DEFAULT NULL COMMENT '余额',
  `unit` BIGINT DEFAULT NULL COMMENT '资产计量单位\n            0，元（现金和平台补贴金的计量单位）\n            1，个（积分的计量单位）',
  `assets_type` INT DEFAULT NULL COMMENT '0现金\n            1平台补贴金\n            2积分\n            3押金\n            ',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  PRIMARY KEY (`assets_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账户资产表。记录账户资产。包括现金、积分等';

/*Data for the table `table_business_account_assets` */

/*Table structure for table `table_business_account_withholding` */

DROP TABLE IF EXISTS `table_business_account_withholding`;

CREATE TABLE `table_business_account_withholding` (
  `withholding_id` BIGINT NOT NULL COMMENT '主键',
  `transfer_out_account_id` BIGINT DEFAULT NULL COMMENT '转出账户id',
  `ransfer_to_account_id` BIGINT DEFAULT NULL COMMENT '目标账户id',
  `payment_id` BIGINT NOT NULL COMMENT '支付流水号',
  `sums` DECIMAL(12,2) DEFAULT NULL COMMENT '最终应扣除金额',
  `status` INT DEFAULT NULL COMMENT '0待扣1已扣2已返',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识'
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账户交易预扣款信息表.一个场景是，意向客户支付房租后，相应现金进入预扣状态，当房东交房后，相应现金转入房东账户';

/*Data for the table `table_business_account_withholding` */

/*Table structure for table `table_business_check_out_to_apply_for` */

DROP TABLE IF EXISTS `table_business_check_out_to_apply_for`;

CREATE TABLE `table_business_check_out_to_apply_for` (
  `check_out_apply_for_id` BIGINT NOT NULL COMMENT '主键',
  `tenant_id` BIGINT DEFAULT NULL COMMENT '房客id',
  `landlord_id` BIGINT DEFAULT NULL COMMENT '房东id',
  `type` VARCHAR(4) DEFAULT NULL COMMENT '0正常到期结算申请\n            1房客提前退房\n            2房客欠交租金退房\n            3其他',
  `status` VARCHAR(4) DEFAULT NULL COMMENT '申请状态，详见数据字典\n            0申请发起中\n            1平台预受理\n            3平台受理中\n            4费用结算中\n            5待补交费用\n            6费用清算完毕\n            7待平台下发退款\n            8待第三方支付下发退款\n            9退款金额到账\n            10退房成功\n            11退房失败\n            12申请取消',
  `trading_id` BIGINT DEFAULT NULL COMMENT '交易记录主键',
  `house_id` BIGINT DEFAULT NULL COMMENT '房源id',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`check_out_apply_for_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='退房申请信息表,完成搬历史表，以创建者IP最后两位数字%12作为表分区';

/*Data for the table `table_business_check_out_to_apply_for` */

/*Table structure for table `table_business_collection` */

DROP TABLE IF EXISTS `table_business_collection`;

CREATE TABLE `table_business_collection` (
  `collection_id` BIGINT NOT NULL COMMENT '主键',
  `customer_id` BIGINT DEFAULT NULL COMMENT '客户主键',
  `house_id` BIGINT DEFAULT NULL COMMENT '房源标识',
  `status` INT DEFAULT NULL COMMENT '收藏状态0取消收藏1收藏',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`collection_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='客户收藏信息表,用于记录用户的收藏页信息。收藏的内容可以是图文、视频和房源此表存于mongodb。本表主要用来记录用户收';

/*Data for the table `table_business_collection` */

/*Table structure for table `table_business_comment` */

DROP TABLE IF EXISTS `table_business_comment`;

CREATE TABLE `table_business_comment` (
  `answer_id` BIGINT DEFAULT NULL COMMENT '主键',
  `reviewers_name` VARCHAR(32) DEFAULT NULL COMMENT '评论者名字',
  `commented_object_id` BIGINT NOT NULL COMMENT '被评论对象的主键（针对评论的评论时，则为评论的主键）',
  `content` VARCHAR(512) DEFAULT NULL COMMENT '内容',
  `comment_type` VARCHAR(4) DEFAULT NULL COMMENT '评论类型0图文评论1提问评论2评论的评论.详情见数据字典',
  `comment_images_url` VARCHAR(1024) DEFAULT NULL COMMENT '图片评论路径',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`commented_object_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论信息表,可以是针对【提问】的回答，亦可以是针对图文的评论。此表存于mongodb';

/*Data for the table `table_business_comment` */

/*Table structure for table `table_business_community` */

DROP TABLE IF EXISTS `table_business_community`;

CREATE TABLE `table_business_community` (
  `community_id` BIGINT NOT NULL COMMENT '主键',
  `name` VARCHAR(64) DEFAULT NULL COMMENT '名称',
  `province_id` BIGINT DEFAULT NULL COMMENT '所在省',
  `city_id` BIGINT DEFAULT NULL COMMENT '所在城市',
  `area_id` BIGINT DEFAULT NULL COMMENT '所在区（镇）',
  `detailed_address` VARCHAR(256) DEFAULT NULL COMMENT '详细地址',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`community_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='小区信息表';

/*Data for the table `table_business_community` */

/*Table structure for table `table_business_customer` */

DROP TABLE IF EXISTS `table_business_customer`;

CREATE TABLE `table_business_customer` (
  `customer_id` BIGINT NOT NULL COMMENT '主键',
  `account_id` BIGINT DEFAULT NULL COMMENT '账户id',
  `customer_name` VARCHAR(32) DEFAULT NULL COMMENT '客户姓名',
  `gender` VARCHAR(4) DEFAULT NULL COMMENT '性别0男1女，详见属性表',
  `gender_value` VARCHAR(4) DEFAULT NULL COMMENT '性别值',
  `phone_number` VARCHAR(11) DEFAULT NULL COMMENT '手机号',
  `phone_number_prefix` BIGINT DEFAULT NULL COMMENT '手机区号，比如中国是+86，详见属性表\n            ',
  `phone_number_prefix_value` VARCHAR(4) DEFAULT NULL COMMENT '手机区号值',
  `customer_status` VARCHAR(4) DEFAULT NULL COMMENT '客户认证状态0未认证1已认证',
  `province` BIGINT DEFAULT NULL COMMENT '所在省、自治州',
  `province_value` VARCHAR(24) DEFAULT NULL COMMENT '所在省、自治州值',
  `city` BIGINT DEFAULT NULL COMMENT '所在城市',
  `city_value` VARCHAR(24) DEFAULT NULL COMMENT '所在城市值',
  `avatar` VARCHAR(500) DEFAULT NULL COMMENT '客户头像地址',
  `email` VARCHAR(32) DEFAULT NULL COMMENT '安全邮箱',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  `month_certified` INT DEFAULT NULL COMMENT '完成认证时所在月份，table_business_customer_valid分区标识',
  `month_added` INT DEFAULT NULL COMMENT '客户信息添加时的月份',
  `month_outmoded` INT DEFAULT NULL COMMENT '客户信息过时时的月份。历史表的分区依据',
  PRIMARY KEY (`customer_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='客户信息表，实名认证完成后搬table_business_customer_valid.所属账户超半年未登录系统，客户信';

/*Data for the table `table_business_customer` */

/*Table structure for table `table_business_customer_head_portrait` */

DROP TABLE IF EXISTS `table_business_customer_head_portrait`;

CREATE TABLE `table_business_customer_head_portrait` (
  `customer_id` BIGINT NOT NULL COMMENT '客户主键',
  `customer_name` VARCHAR(16) DEFAULT NULL COMMENT '客户名字',
  `image_url` VARCHAR(128) DEFAULT NULL COMMENT '头像路径',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`customer_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='客户头像信息表.这张表主要是用于资讯业务客户信息的展示。客户名字的显示策略采取仅保留姓氏的脱敏策略';

/*Data for the table `table_business_customer_head_portrait` */

/*Table structure for table `table_business_customer_valid` */

DROP TABLE IF EXISTS `table_business_customer_valid`;

CREATE TABLE `table_business_customer_valid` (
  `customer_id` BIGINT NOT NULL COMMENT '主键',
  `account_id` BIGINT DEFAULT NULL COMMENT '账户id',
  `customer_name` VARCHAR(32) DEFAULT NULL COMMENT '客户姓名',
  `gender` VARCHAR(4) DEFAULT NULL COMMENT '性别0男1女，详见属性表',
  `gender_value` VARCHAR(4) DEFAULT NULL COMMENT '性别值',
  `phone_number` VARCHAR(11) DEFAULT NULL COMMENT '手机号',
  `phone_number_prefix` BIGINT DEFAULT NULL COMMENT '手机区号，比如中国是+86，详见属性表\n            ',
  `phone_number_prefix_value` VARCHAR(4) DEFAULT NULL COMMENT '手机区号值',
  `customer_status` VARCHAR(4) DEFAULT NULL COMMENT '客户认证状态0未认证1已认证',
  `province` BIGINT DEFAULT NULL COMMENT '所在省、自治州',
  `province_value` VARCHAR(24) DEFAULT NULL COMMENT '所在省、自治州值',
  `city` BIGINT DEFAULT NULL COMMENT '所在城市',
  `city_value` VARCHAR(24) DEFAULT NULL COMMENT '所在城市值',
  `avatar` VARCHAR(500) DEFAULT NULL COMMENT '客户头像地址',
  `email` VARCHAR(32) DEFAULT NULL COMMENT '安全邮箱',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  `month_certified` INT DEFAULT NULL COMMENT '完成认证时所在月份，table_business_customer_valid分区标识',
  `month_added` INT DEFAULT NULL COMMENT '客户信息添加时的月份',
  `month_outmoded` INT DEFAULT NULL COMMENT '客户信息过时时的月份。历史表的分区依据',
  PRIMARY KEY (`customer_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='客户信息表，实名认证完成后搬table_business_customer_valid.所属账户超半年未登录系统，客户信';

/*Data for the table `table_business_customer_valid` */

/*Table structure for table `table_business_deposit_withdrawal_log` */

DROP TABLE IF EXISTS `table_business_deposit_withdrawal_log`;

CREATE TABLE `table_business_deposit_withdrawal_log` (
  `log_id` BIGINT NOT NULL COMMENT '主键',
  `account_id` BIGINT DEFAULT NULL COMMENT '账户标识',
  `type` INT DEFAULT NULL COMMENT '0充值\n            1提现\n            2付押金\n            3付房租\n            4收到房租\n            ',
  `source` INT DEFAULT NULL COMMENT '0银行卡充值\n            1支付宝账户充值\n            2微信账户充值\n            3房租收入',
  `sums` DECIMAL(12,2) DEFAULT NULL COMMENT '转入转出金额',
  `third_party_serial_number` BIGINT DEFAULT NULL COMMENT '如果是第三方支付的话需要记录第三方流水号\n            ',
  `original_balance` DECIMAL(12,2) DEFAULT NULL COMMENT '余额变动前总数',
  `after_balance` DECIMAL(12,2) DEFAULT NULL COMMENT '变动之后的金额',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`log_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='存取款记录表。对于平台内部充值账户的余额转入转出记录,以月份作为分区标识\n账户余额变动记录表';

/*Data for the table `table_business_deposit_withdrawal_log` */

/*Table structure for table `table_business_district` */

DROP TABLE IF EXISTS `table_business_district`;

CREATE TABLE `table_business_district` (
  `district_id` BIGINT NOT NULL COMMENT '主键',
  `parent_id` BIGINT DEFAULT NULL COMMENT '所属上一级城市id',
  `name` VARCHAR(32) DEFAULT NULL COMMENT '城市名称',
  `type` INT DEFAULT NULL COMMENT '城市类型0国1省2市3区',
  `hierarchy` INT DEFAULT NULL COMMENT '地区所处的层级',
  `district_sequence` VARCHAR(12) DEFAULT NULL COMMENT '层级序列',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`district_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='中国省市表';

/*Data for the table `table_business_district` */

/*Table structure for table `table_business_fee_item` */

DROP TABLE IF EXISTS `table_business_fee_item`;

CREATE TABLE `table_business_fee_item` (
  `fee_item_id` BIGINT NOT NULL COMMENT '费用项标识',
  `fee_name` VARCHAR(64) DEFAULT NULL COMMENT '费用名称',
  `fee_code` VARCHAR(4) DEFAULT NULL COMMENT '费用内部编码\n            详见数据字典\n            ',
  `sums` DECIMAL(12,2) DEFAULT NULL COMMENT '费用金额',
  `is_preference` INT DEFAULT '0' COMMENT '是否接受优惠结算0否1是',
  `fee_type` VARCHAR(4) DEFAULT NULL COMMENT '费用类型\n            0其他\n            1房租充值\n            2押金\n            3平台服务费',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`fee_item_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='费用项表。包括月租费、年租费、押金、平台服务费、推广费等。此表暂时不用';

/*Data for the table `table_business_fee_item` */

/*Table structure for table `table_business_house_images_and_video` */

DROP TABLE IF EXISTS `table_business_house_images_and_video`;

CREATE TABLE `table_business_house_images_and_video` (
  `resource_id` BIGINT NOT NULL COMMENT '资源标识',
  `house_id` BIGINT DEFAULT NULL COMMENT '房屋标识',
  `file_name` VARCHAR(128) DEFAULT NULL COMMENT '文件名称',
  `file_url` VARCHAR(512) DEFAULT NULL COMMENT '资源路径',
  `resource_status` INT DEFAULT NULL COMMENT '资源状态0待审核1启用2禁用',
  `resource_type` INT DEFAULT NULL COMMENT '资源类型0图片1资源',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`resource_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='房屋图片视频信息表。每个房屋ID最多可以对应5张图片和1个视频。数据库中存储的是url';

/*Data for the table `table_business_house_images_and_video` */

/*Table structure for table `table_business_house_info` */

DROP TABLE IF EXISTS `table_business_house_info`;

CREATE TABLE `table_business_house_info` (
  `house_id` BIGINT NOT NULL COMMENT '主键',
  `owner_id` BIGINT DEFAULT NULL COMMENT '业主',
  `title` VARCHAR(100) DEFAULT NULL COMMENT '房源标题',
  `community_id` BIGINT DEFAULT NULL COMMENT '所属小区',
  `building_number` VARCHAR(12) DEFAULT NULL COMMENT '楼号（栋）',
  `building_uint` VARCHAR(12) DEFAULT NULL COMMENT '所属单元',
  `building_floor_number` VARCHAR(4) DEFAULT NULL COMMENT '门牌号',
  `rent` DECIMAL(9,2) DEFAULT NULL COMMENT '租金',
  `rental_mode` VARCHAR(4) DEFAULT NULL COMMENT '出租方式（0整租，1合租，2可合租可整租）详见参数表',
  `payment_method` VARCHAR(4) DEFAULT NULL COMMENT '支付方式（1押一付一，2押一付二，3押一付三，4押一付六，5押一付年，6其他）详见参数表',
  `house_type` VARCHAR(4) DEFAULT NULL COMMENT '户型 详见参数表',
  `covered_area` INT DEFAULT NULL COMMENT '建筑面积,单位平方米',
  `use_area` INT DEFAULT NULL COMMENT '使用面积,单位平方米',
  `floors` VARCHAR(10) DEFAULT NULL COMMENT '楼层,例如8/26',
  `house_orientation` VARCHAR(4) DEFAULT NULL COMMENT '朝向，1坐北朝南，2坐东朝西，3坐南朝北，4坐西朝东  详见参数表',
  `house_decoration_type` VARCHAR(4) DEFAULT NULL COMMENT '房屋装修类型，1简装，2精装，3毛胚',
  `house_facilities` VARCHAR(4) DEFAULT NULL COMMENT '配套设施，详见参数表',
  `descs` VARCHAR(500) DEFAULT NULL COMMENT '描述',
  `house_status` VARCHAR(4) DEFAULT NULL COMMENT '0审核中\n            1审核未通过\n            2待租\n            3预交易\n            4交易生效中\n             详见数据字典\n            ',
  `house_images_address` VARCHAR(500) DEFAULT NULL COMMENT '房屋图片地址',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  `month_added` INT DEFAULT NULL COMMENT '房源信息添加时的月份',
  `month_completed` INT DEFAULT NULL COMMENT '房源交易完成时的月份。历史表的分区依据',
  PRIMARY KEY (`house_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='房源信息表，其中按照添加时的月份进行表分区。并且仅保留待审核状态的记录。待交易（审核已完成）的记录搬table_busi';

/*Data for the table `table_business_house_info` */

/*Table structure for table `table_business_house_info_tradable` */

DROP TABLE IF EXISTS `table_business_house_info_tradable`;

CREATE TABLE `table_business_house_info_tradable` (
  `house_id` BIGINT NOT NULL COMMENT '主键',
  `owner_id` BIGINT DEFAULT NULL COMMENT '业主',
  `title` VARCHAR(100) DEFAULT NULL COMMENT '房源标题',
  `community_id` BIGINT DEFAULT NULL COMMENT '所属小区',
  `building_number` VARCHAR(12) DEFAULT NULL COMMENT '楼号（栋）',
  `building_uint` VARCHAR(12) DEFAULT NULL COMMENT '所属单元',
  `building_floor_number` VARCHAR(4) DEFAULT NULL COMMENT '门牌号',
  `rent` DECIMAL(9,2) DEFAULT NULL COMMENT '租金',
  `rental_mode` VARCHAR(4) DEFAULT NULL COMMENT '出租方式（0整租，1合租，2可合租可整租）详见参数表',
  `payment_method` VARCHAR(4) DEFAULT NULL COMMENT '支付方式（1押一付一，2押一付二，3押一付三，4押一付六，5押一付年，6其他）详见参数表',
  `house_type` VARCHAR(4) DEFAULT NULL COMMENT '户型 详见参数表',
  `covered_area` INT DEFAULT NULL COMMENT '建筑面积,单位平方米',
  `use_area` INT DEFAULT NULL COMMENT '使用面积,单位平方米',
  `floors` VARCHAR(10) DEFAULT NULL COMMENT '楼层,例如8/26',
  `house_orientation` VARCHAR(4) DEFAULT NULL COMMENT '朝向，1坐北朝南，2坐东朝西，3坐南朝北，4坐西朝东  详见参数表',
  `house_decoration_type` VARCHAR(4) DEFAULT NULL COMMENT '房屋装修类型，1简装，2精装，3毛胚',
  `house_facilities` VARCHAR(4) DEFAULT NULL COMMENT '配套设施，详见参数表',
  `descs` VARCHAR(500) DEFAULT NULL COMMENT '描述',
  `house_status` VARCHAR(4) DEFAULT NULL COMMENT '2待租\n            3预租\n            4在租\n             详见参数表',
  `house_images_address` VARCHAR(500) DEFAULT NULL COMMENT '房屋图片地址',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  `month_added` INT DEFAULT NULL COMMENT '房源信息添加时的月份',
  `month_completed` INT DEFAULT NULL COMMENT '房源交易完成时的月份。历史表的分区依据',
  PRIMARY KEY (`house_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='房源信息表，其中按照添加时的月份进行表分区。并且仅保留待审核状态的记录。待交易（审核已完成）的记录搬table_busi';

/*Data for the table `table_business_house_info_tradable` */

/*Table structure for table `table_business_house_other_attribute` */

DROP TABLE IF EXISTS `table_business_house_other_attribute`;

CREATE TABLE `table_business_house_other_attribute` (
  `house_id` BIGINT NOT NULL COMMENT '房源标识',
  `is_hot_water` INT DEFAULT NULL COMMENT '是否有热水\n            0否1有',
  `is_have_kitchen` INT DEFAULT NULL COMMENT '是否有厨房',
  `is_have_living_root` INT DEFAULT NULL COMMENT '是否有客厅',
  `is_have_balcony` INT DEFAULT NULL COMMENT '是否有阳台',
  `is_have_window` INT DEFAULT NULL COMMENT '是否有窗户',
  `is_separate_toilet` INT DEFAULT NULL COMMENT '是否是独卫',
  `is_check_in_with_bags` INT DEFAULT NULL COMMENT '是否支持拎包入住',
  `is_have_elevator` INT DEFAULT NULL COMMENT '是否有电梯\n            3楼以上的房源需要选择\n            -1房间在二楼及以下\n            0没有\n            1有',
  `is_there_a_parking_space` INT DEFAULT NULL COMMENT '是否有车位',
  `electricity_price` DECIMAL(9,2) DEFAULT NULL COMMENT '电价',
  `water_price` DECIMAL(9,2) DEFAULT NULL COMMENT '水价',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  `is_gender_restrictions` INT DEFAULT NULL COMMENT '性别限制\n            0不限\n            1男\n            2女',
  `is_it_air_conditioned` INT DEFAULT NULL COMMENT '是否有空调',
  `whether_the_heating` INT DEFAULT NULL COMMENT '是否供暖',
  PRIMARY KEY (`house_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='房源其他属性表.包括一些可选的属性以及客户自定义属性（<=3）\n。';

/*Data for the table `table_business_house_other_attribute` */

/*Table structure for table `table_business_payment` */

DROP TABLE IF EXISTS `table_business_payment`;

CREATE TABLE `table_business_payment` (
  `payment_id` BIGINT NOT NULL COMMENT '主键',
  `payment_status` VARCHAR(4) DEFAULT NULL COMMENT '支付状态\n            0支付工单已创建\n            1支付成功\n            2待生效\n            3生效\n            2支付失败',
  `third_party_serial_number` BIGINT DEFAULT NULL COMMENT '如果是第三方支付的话需要记录第三方流水号\n            ',
  `payment_mode` VARCHAR(4) DEFAULT NULL COMMENT '支付方式0平台账户支付1支付宝支付2微信支付3网银',
  `transfer_out_account_id` BIGINT DEFAULT NULL COMMENT '转出账户id',
  `ransfer_to_account_id` BIGINT DEFAULT NULL COMMENT '转入账户id',
  `free` DECIMAL(12,2) DEFAULT NULL COMMENT '最终应支付金额',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  `payment_type` VARCHAR(4) DEFAULT NULL COMMENT '0其他\n            1支付宝转入\n            2微信转入\n            3工商行转入\n            4提现\n            5平台补贴\n            6交租金\n            7交押金\n            8系统代扣租金',
  PRIMARY KEY (`payment_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='交易支付信息表，记录平台支付信息';

/*Data for the table `table_business_payment` */

/*Table structure for table `table_business_preengage_see_the_apartment` */

DROP TABLE IF EXISTS `table_business_preengage_see_the_apartment`;

CREATE TABLE `table_business_preengage_see_the_apartment` (
  `preengage_id` BIGINT NOT NULL COMMENT '主键',
  `Prospective_customer_id` BIGINT DEFAULT NULL COMMENT '意向客户id',
  `landlord_id` BIGINT DEFAULT NULL COMMENT '房东id',
  `house_id` BIGINT DEFAULT NULL COMMENT '房源id',
  `status` INT DEFAULT NULL COMMENT '0关闭\n            1等待房东确认\n            2等待意向客户确认\n            3完成看房\n            4意向客户爽约\n            5房东爽约\n            6取消',
  `preengage_time` DATETIME DEFAULT NULL COMMENT '预约看房时间',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  `cause_of_abnormal_end` VARCHAR(128) DEFAULT NULL COMMENT '异常结束原因',
  PRIMARY KEY (`preengage_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='预约看房信息表，完成看房搬历史，按照创建月份本区';

/*Data for the table `table_business_preengage_see_the_apartment` */

/*Table structure for table `table_business_questions` */

DROP TABLE IF EXISTS `table_business_questions`;

CREATE TABLE `table_business_questions` (
  `questions_id` BIGINT NOT NULL COMMENT '主键',
  `problem` VARCHAR(256) DEFAULT NULL COMMENT '提问问题',
  `status` INT DEFAULT NULL COMMENT '问题状态0关闭1开启',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`questions_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='提问信息表。记录客户在平台【发现】模块的提问。存储于mongodb';

/*Data for the table `table_business_questions` */

/*Table structure for table `table_business_rent_payment_log` */

DROP TABLE IF EXISTS `table_business_rent_payment_log`;

CREATE TABLE `table_business_rent_payment_log` (
  `rent_payment_id` BIGINT NOT NULL COMMENT '缴纳流水号',
  `house_id` BIGINT DEFAULT NULL COMMENT '房屋标识',
  `payment_id` BIGINT NOT NULL COMMENT '支付流水',
  `effective_time` DATETIME DEFAULT NULL COMMENT '实际转入房东账户的时间',
  `months` INT DEFAULT NULL COMMENT '充值了几个月\n            months=充值总金额/月租',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`rent_payment_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='房租缴纳记录.表';

/*Data for the table `table_business_rent_payment_log` */

/*Table structure for table `table_business_topic` */

DROP TABLE IF EXISTS `table_business_topic`;

CREATE TABLE `table_business_topic` (
  `topic_id` BIGINT NOT NULL COMMENT '主键',
  `topic_title` VARCHAR(64) DEFAULT NULL COMMENT '话题标题',
  `citation` VARCHAR(512) DEFAULT NULL COMMENT '话题引文（话题正文）',
  `topic_type` VARCHAR(4) DEFAULT NULL COMMENT '话题类型（详见数据字典）',
  `image_url` VARCHAR(512) DEFAULT NULL COMMENT '图片路径list',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`topic_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='话题(帖子)信息表，记录椰租平台上的各类话题信息，来源包括平台创建类、客户创建类。类型包括上班摸鱼、找舍友、房源推荐、日';

/*Data for the table `table_business_topic` */

/*Table structure for table `table_business_trading_evaluation` */

DROP TABLE IF EXISTS `table_business_trading_evaluation`;

CREATE TABLE `table_business_trading_evaluation` (
  `evaluation_id` BIGINT DEFAULT NULL COMMENT '评价主键',
  `trading_id` BIGINT NOT NULL COMMENT '交易记录主键',
  `customer_id` BIGINT DEFAULT NULL COMMENT '客户主键',
  `customer_name` VARCHAR(32) DEFAULT NULL COMMENT '客户姓名',
  `content` VARCHAR(1024) DEFAULT NULL COMMENT '评价内容',
  `comment_time` DATETIME DEFAULT NULL COMMENT '评价时间',
  `create_time` DATETIME DEFAULT NULL COMMENT '字段创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  `descriptive_coincidence` INT DEFAULT NULL COMMENT '描述符合度1很不符合2不符合3基本相符4比较相符5非常相符',
  `landlord_service` INT DEFAULT NULL COMMENT '房东服务满意度1很不满意2不满意3基本满意4比较满意5非常满意',
  `tenant_behavior` INT DEFAULT NULL COMMENT '房客行为评价1非常不满意2不满意3一般4比较满意5非常满意',
  `evaluation_type` INT DEFAULT NULL COMMENT '评价类型0其他1房客评价2房东评价',
  `evaluation_status` VARCHAR(4) DEFAULT NULL COMMENT '评价状态0正常1待回访2回访结果已提交3评价可信4评价不可信\n            详情见数据字典',
  `evaluation_like_count` BIGINT DEFAULT NULL COMMENT '评价点赞数（认同度）',
  PRIMARY KEY (`trading_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='交易评价表，记录房源交易评价信息。实时评价记录在mongodb数据库。每天同步评论到mysql。以访问者IP最后一位作为';

/*Data for the table `table_business_trading_evaluation` */

/*Table structure for table `table_business_trading_information_house` */

DROP TABLE IF EXISTS `table_business_trading_information_house`;

CREATE TABLE `table_business_trading_information_house` (
  `trading_id` BIGINT NOT NULL COMMENT '主键',
  `house_id` BIGINT DEFAULT NULL COMMENT '房源id',
  `landlord_id` BIGINT DEFAULT NULL COMMENT '房东标识',
  `tenant_id` BIGINT DEFAULT NULL COMMENT '(准)房客标识',
  `trading_status` VARCHAR(4) DEFAULT NULL COMMENT '交易状态\n            0交易创建成功\n            1已支付\n            2已收货（房东已交房、卡卷已到账）\n            3商家已收款（房租已转入房东账户）\n            4交易异常申诉中(此时创建申述工单，并进入状态10)\n            5交易正常结束（货款两清）\n            9交易非正常结束\n            10交易暂停\n            11交易取消\n            \n            详细见数据字典',
  `create_time` DATETIME DEFAULT NULL COMMENT '交易创建时间',
  `payment_id` BIGINT DEFAULT NULL COMMENT '支付平台内部流水号',
  `payment_time` DATETIME DEFAULT NULL COMMENT '支付时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  `cancel_time` DATETIME DEFAULT NULL COMMENT '交易取消时间',
  `trading_type` VARCHAR(4) DEFAULT NULL COMMENT '交易类型，详见数据字典\n            0租房\n            1退房\n            3续交押金\n            4退押金\n            \n            \n            ',
  PRIMARY KEY (`trading_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='房屋交易记录表，按照交易月份进行分区';

/*Data for the table `table_business_trading_information_house` */

/*Table structure for table `table_business_warning_account` */

DROP TABLE IF EXISTS `table_business_warning_account`;

CREATE TABLE `table_business_warning_account` (
  `account_id` BIGINT NOT NULL COMMENT '账户标识',
  `warning_status` CHAR(1) DEFAULT NULL COMMENT '预警状态\n            0待预警\n            1已预警\n            2预警前缴纳\n            3预警后缴纳但未逾期\n            4逾期未缴纳\n            5逾期后缴纳',
  `notice_time` DATETIME DEFAULT NULL COMMENT '第一次预警通知时间',
  `late_time` DATETIME DEFAULT NULL COMMENT '最近一次预警通知时间',
  `notice_mode` CHAR(1) DEFAULT NULL COMMENT '最近一次通知方式\n            0 APP推送\n            1短信\n            2邮箱',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  `partition_number` CHAR(2) DEFAULT NULL COMMENT '分区标识',
  PRIMARY KEY (`account_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='租期到期预警表.记录待通知缴纳租金的账号信息，失效搬历史。按照账户id后两位分区';

/*Data for the table `table_business_warning_account` */

/*Table structure for table `table_data_browsing_history` */

DROP TABLE IF EXISTS `table_data_browsing_history`;

CREATE TABLE `table_data_browsing_history` (
  `browsing_id` BIGINT NOT NULL COMMENT '浏览标识',
  `customer_id` BIGINT DEFAULT NULL COMMENT '客户标识',
  `house_id` BIGINT DEFAULT NULL COMMENT '房源标识',
  `count` INT DEFAULT NULL COMMENT '浏览计数',
  `latest_time` DATETIME DEFAULT NULL COMMENT '最近一次浏览时间',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`browsing_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='房源浏览记录.从客户角度记录其浏览房源记录';

/*Data for the table `table_data_browsing_history` */

/*Table structure for table `table_platform_department` */

DROP TABLE IF EXISTS `table_platform_department`;

CREATE TABLE `table_platform_department` (
  `department_id` BIGINT NOT NULL COMMENT '主键',
  `parent_department_id` BIGINT DEFAULT NULL COMMENT '父部门主键',
  `name` VARCHAR(32) DEFAULT NULL COMMENT '部门名称',
  `leader_id` BIGINT DEFAULT NULL COMMENT '负责人id',
  `phone_number` VARCHAR(11) DEFAULT NULL COMMENT '手机号',
  `phone_number_prefix` BIGINT DEFAULT NULL COMMENT '手机区号，比如中国是+86，详见属性表\n            ',
  `phone_number_prefix_value` VARCHAR(4) DEFAULT NULL COMMENT '手机区号值',
  `email` VARCHAR(32) DEFAULT NULL COMMENT '部门（团队）邮箱',
  `yeju_department_status` BIGINT DEFAULT NULL COMMENT '部门状态0未启用1启用',
  `yeju_department_status_value` VARCHAR(4) DEFAULT NULL COMMENT '部门状态值，详见属性表',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`department_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='椰居平台部门表';

/*Data for the table `table_platform_department` */

/*Table structure for table `table_platform_employees` */

DROP TABLE IF EXISTS `table_platform_employees`;

CREATE TABLE `table_platform_employees` (
  `employees_id` BIGINT NOT NULL COMMENT '主键',
  `name` VARCHAR(32) DEFAULT NULL COMMENT '员工姓名',
  `gender` BIGINT DEFAULT NULL COMMENT '性别0男1女，详见属性表',
  `gender_value` VARCHAR(4) DEFAULT NULL COMMENT '性别值',
  `phone_number` VARCHAR(11) DEFAULT NULL COMMENT '手机号',
  `phone_number_prefix` BIGINT DEFAULT NULL COMMENT '手机区号，比如中国是+86，详见属性表\n            ',
  `phone_number_prefix_value` VARCHAR(4) DEFAULT NULL COMMENT '手机区号值',
  `leader_id` BIGINT DEFAULT NULL COMMENT '所属领导',
  `avatar` VARCHAR(500) DEFAULT NULL COMMENT '头像地址',
  `email` VARCHAR(32) DEFAULT NULL COMMENT '公司邮箱',
  `employees_status` BIGINT DEFAULT NULL COMMENT '员工状态0在职1离职',
  `employees_status_value` VARCHAR(4) DEFAULT NULL COMMENT '员工状态值,见属性表',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  `month_added` INT DEFAULT NULL COMMENT '员工信息添加时的月份，分区标识',
  `month_outmoded` INT DEFAULT NULL COMMENT '员工离职时的月份。历史表的分区依据',
  PRIMARY KEY (`employees_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='椰居平台员工表';

/*Data for the table `table_platform_employees` */

/*Table structure for table `table_platform_post` */

DROP TABLE IF EXISTS `table_platform_post`;

CREATE TABLE `table_platform_post` (
  `post_id` BIGINT NOT NULL COMMENT '主键',
  `post_name` VARCHAR(32) DEFAULT NULL COMMENT '岗位名称',
  `department_id` BIGINT DEFAULT NULL COMMENT '岗位所属部门',
  `post_code` VARCHAR(64) DEFAULT NULL COMMENT '岗位编码',
  `remark` VARCHAR(256) DEFAULT NULL COMMENT '备注',
  `post_status` VARCHAR(4) DEFAULT NULL COMMENT '岗位状态0未启用1启用',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`post_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='职位信息表';

/*Data for the table `table_platform_post` */

/*Table structure for table `table_relationship_customer_house` */

DROP TABLE IF EXISTS `table_relationship_customer_house`;

CREATE TABLE `table_relationship_customer_house` (
  `customer_id` BIGINT NOT NULL COMMENT '客户主键',
  `house_id` BIGINT NOT NULL COMMENT '房源主键',
  `relationship_type` VARCHAR(4) DEFAULT NULL COMMENT '关系类型,主要有\n            0租赁关系、\n            1归属关系，\n            2收藏关系，\n            3推荐关系，\n            4踩 关系，\n            5待交租金关系（预租，抢占资源），\n            6待续交租金关系、\n            7租赁过 关系',
  `start_time` DATETIME DEFAULT NULL COMMENT '关系开始时间',
  `end_time` DATETIME DEFAULT NULL COMMENT '关系结束时间',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  `create_month` INT DEFAULT NULL COMMENT '建立关系月份，用于表分区',
  `relationship_status` INT DEFAULT NULL COMMENT '0已结束\n            1正常\n            2异常',
  `is_warning` INT DEFAULT '0' COMMENT '是否开启关系到期预警0不开启1开启',
  PRIMARY KEY (`customer_id`,`house_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='客户-房源关系表。主要有租赁关系、归属关系，收藏关系，推荐关系，踩 关系，待交租金关系，待需交租金关系、租赁过 关系.关';

/*Data for the table `table_relationship_customer_house` */

/*Table structure for table `table_system_account` */

DROP TABLE IF EXISTS `table_system_account`;

CREATE TABLE `table_system_account` (
  `account_id` BIGINT NOT NULL COMMENT '主键',
  `account_number` VARCHAR(16) NOT NULL COMMENT '账号',
  `subject_id` BIGINT DEFAULT NULL COMMENT '账户所属者主键',
  `account_password` VARCHAR(48) DEFAULT NULL COMMENT '账户密码',
  `last_login_address` VARCHAR(128) DEFAULT NULL COMMENT '最后一次登录地址',
  `last_login_date` DATETIME DEFAULT NULL COMMENT '最后一次登录时间',
  `account_status` VARCHAR(4) DEFAULT NULL COMMENT '账户状态0未启用1启用2冻结，详见属性表',
  `account_level` VARCHAR(4) DEFAULT NULL COMMENT '账户等级，详见数据走到吗',
  `account_type` VARCHAR(4) DEFAULT NULL COMMENT '账户类型，0内部账户1客户账户，详见属性表',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`account_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账户表';

/*Data for the table `table_system_account` */

/*Table structure for table `table_system_data_dictionary_info` */

DROP TABLE IF EXISTS `table_system_data_dictionary_info`;

CREATE TABLE `table_system_data_dictionary_info` (
  `data_dictionary_info_id` BIGINT NOT NULL COMMENT '主键',
  `type_id` BIGINT DEFAULT NULL COMMENT '数据字典类型标识',
  `sort` INT DEFAULT NULL COMMENT '排序（优先级）',
  `dictionary_label` VARCHAR(128) DEFAULT NULL COMMENT '字典标签，实际显示出来的值',
  `dictionary_value` VARCHAR(128) DEFAULT NULL COMMENT '字典键值，内部代码例如gender',
  `css_class` VARCHAR(256) DEFAULT NULL COMMENT '样式属性',
  `list_class` VARCHAR(256) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` INT DEFAULT NULL COMMENT '是否为默认属性0否1是',
  `status` INT DEFAULT NULL COMMENT '状态0未启用1启用',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`data_dictionary_info_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据字典信息表';

/*Data for the table `table_system_data_dictionary_info` */

/*Table structure for table `table_system_data_dictionary_type` */

DROP TABLE IF EXISTS `table_system_data_dictionary_type`;

CREATE TABLE `table_system_data_dictionary_type` (
  `data_dictionary_type_id` BIGINT NOT NULL COMMENT '主键',
  `name` VARCHAR(128) DEFAULT NULL COMMENT '数据字典名称',
  `type` VARCHAR(128) DEFAULT NULL COMMENT '数据字典类型，与名称对应，与表中字段名一致',
  `status` INT DEFAULT NULL COMMENT '状态0未启用1启用',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`data_dictionary_type_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据字典类型表,用于简单的动态配置';

/*Data for the table `table_system_data_dictionary_type` */

/*Table structure for table `table_system_login_log` */

DROP TABLE IF EXISTS `table_system_login_log`;

CREATE TABLE `table_system_login_log` (
  `login_log_id` BIGINT NOT NULL COMMENT '主键',
  `account` VARCHAR(64) DEFAULT NULL COMMENT '访问账户',
  `subject_name` VARCHAR(32) DEFAULT NULL COMMENT '访问者姓名',
  `ip` VARCHAR(32) DEFAULT NULL COMMENT '访问ip地址',
  `login_status` INT DEFAULT NULL COMMENT '访问状态0失败1成功',
  `message` VARCHAR(128) DEFAULT NULL COMMENT '访问失败时记录原因',
  `accent_time` DATETIME DEFAULT NULL COMMENT '访问时间',
  `last_ip_number` INT DEFAULT NULL COMMENT 'ip地址最后一位，表分区依据',
  PRIMARY KEY (`login_log_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='登录日志表，仅保存最新三个月的登录日志';

/*Data for the table `table_system_login_log` */

/*Table structure for table `table_system_notice` */

DROP TABLE IF EXISTS `table_system_notice`;

CREATE TABLE `table_system_notice` (
  `notice_id` BIGINT NOT NULL COMMENT '主键',
  `title` VARCHAR(128) DEFAULT NULL COMMENT '通知标题',
  `content` VARCHAR(512) DEFAULT NULL COMMENT '通知正文',
  `notice_type` VARCHAR(4) DEFAULT NULL COMMENT '通知类型0其他1通知2公告',
  `status` INT DEFAULT NULL COMMENT '通知状态0关闭1正常',
  `start_time` DATETIME DEFAULT NULL COMMENT '公告开始时间',
  `end_time` DATETIME DEFAULT NULL COMMENT '公告结束时间',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`notice_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统通知（推送）公告表,记录系统的公告信息。仅保留公告有效期内的公告，超过有效期的公告搬历史表';

/*Data for the table `table_system_notice` */

/*Table structure for table `table_system_operation_log` */

DROP TABLE IF EXISTS `table_system_operation_log`;

CREATE TABLE `table_system_operation_log` (
  `operation_log_id` BIGINT NOT NULL COMMENT '主键',
  `title` VARCHAR(64) DEFAULT NULL COMMENT '模块标题',
  `business_type` INT DEFAULT NULL COMMENT '业务类型0其他1新增2修改3删除',
  `method` VARCHAR(64) DEFAULT NULL COMMENT '方法名称',
  `request_method` VARCHAR(12) DEFAULT NULL COMMENT '请求方式',
  `operator_type` VARCHAR(4) DEFAULT NULL COMMENT '操作类型0其他1平台业务员2客户3移动端客户',
  `operator_name` VARCHAR(32) DEFAULT NULL COMMENT '操作者名字',
  `department_name` VARCHAR(64) DEFAULT NULL COMMENT '部门名称',
  `url` VARCHAR(128) DEFAULT NULL COMMENT '请求路径',
  `ip` VARCHAR(32) DEFAULT NULL COMMENT '请求ip',
  `location` VARCHAR(256) DEFAULT NULL COMMENT '地点',
  `param` VARCHAR(256) DEFAULT NULL COMMENT '请求参数',
  `result` VARCHAR(512) DEFAULT NULL COMMENT '返回的结果',
  `operation_status` INT DEFAULT NULL COMMENT '操作状态0正常1异常',
  `error_message` VARCHAR(512) DEFAULT NULL COMMENT '错误消息',
  `operation_time` DATETIME DEFAULT NULL COMMENT '操作时间',
  `last_ip_number` INT DEFAULT NULL COMMENT '访问ip最后一位数字，用作分区标识',
  PRIMARY KEY (`operation_log_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统操作日志表，主要用户记录系统后台敏感操作信息，以ip最后一位数字分区。保存最新一个月，过时信息搬历史表保存半年';

/*Data for the table `table_system_operation_log` */

/*Table structure for table `table_system_resouces` */

DROP TABLE IF EXISTS `table_system_resouces`;

CREATE TABLE `table_system_resouces` (
  `resource_id` BIGINT NOT NULL COMMENT '主键',
  `resource_name` VARCHAR(64) DEFAULT NULL COMMENT '资源名',
  `resource_code` VARCHAR(128) DEFAULT NULL COMMENT '资源权限字符串',
  `resource_type` VARCHAR(4) DEFAULT NULL COMMENT '资源类型值0菜单1接口2操作（按钮）',
  `parent_menu_id` BIGINT DEFAULT NULL COMMENT '父菜单id,仅当资源类型为0时生效',
  `order_number` INT DEFAULT NULL COMMENT '显示顺序，当多个子菜单对应一个父菜单时，需要给定显示顺序',
  `path` VARCHAR(512) DEFAULT NULL COMMENT '路由地址，当资源为菜单操作时需要记录路由地址',
  `componet_path` VARCHAR(256) DEFAULT NULL COMMENT '当为组件时，需要给定组件路径',
  `is_cache` INT DEFAULT NULL COMMENT '是否缓存0否1是',
  `resource_status` INT DEFAULT NULL COMMENT '资源状态0未启用1启用',
  `visible` INT DEFAULT NULL COMMENT '菜单是否显示0不显示1显示',
  `icon` VARCHAR(512) DEFAULT NULL COMMENT '菜单图标地址，当资源类型为菜单时需要指定',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`resource_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='(受保护的资源)权限表，包括菜单和API';

/*Data for the table `table_system_resouces` */

/*Table structure for table `table_system_role` */

DROP TABLE IF EXISTS `table_system_role`;

CREATE TABLE `table_system_role` (
  `role_id` BIGINT NOT NULL COMMENT '主键',
  `role_name` VARCHAR(32) DEFAULT NULL COMMENT '角色名称',
  `role_code` VARCHAR(32) DEFAULT NULL COMMENT '角色字符串',
  `role_status` BIGINT DEFAULT NULL COMMENT '角色状态索引',
  `role_status_value` VARCHAR(4) DEFAULT NULL COMMENT '角色状态值0未启用1启用，详见属性表',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`role_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';

/*Data for the table `table_system_role` */

/*Table structure for table `table_system_timing_task_scheduler` */

DROP TABLE IF EXISTS `table_system_timing_task_scheduler`;

CREATE TABLE `table_system_timing_task_scheduler` (
  `task_id` BIGINT NOT NULL COMMENT '主键',
  `task_name` VARCHAR(128) DEFAULT NULL COMMENT '任务名称',
  `task_group_id` BIGINT DEFAULT NULL COMMENT '任务组id',
  `invoke_target` VARCHAR(256) DEFAULT NULL COMMENT '调用目标字符串',
  `cron_expression` VARCHAR(256) DEFAULT NULL COMMENT 'cron任务执行表达式',
  `misfire_policy` INT DEFAULT NULL COMMENT '当任务出错时的执行策略1立即执行 2执行一次 3放弃执行',
  `concurrent` INT DEFAULT NULL COMMENT '是否并发执行0否1是',
  `status` INT DEFAULT NULL COMMENT '执行状态0暂停1正常',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`task_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时任务调度表';

/*Data for the table `table_system_timing_task_scheduler` */

/*Table structure for table `table_system_timing_task_scheduler_group` */

DROP TABLE IF EXISTS `table_system_timing_task_scheduler_group`;

CREATE TABLE `table_system_timing_task_scheduler_group` (
  `task_group_id` BIGINT NOT NULL COMMENT '主键',
  `group_name` VARCHAR(64) DEFAULT NULL COMMENT '任务组名称',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `create_by` BIGINT DEFAULT NULL COMMENT '创建者',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `changed_by` BIGINT DEFAULT NULL COMMENT '更改者',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  `version_number` INT DEFAULT NULL COMMENT '字段版本',
  `is_delete` INT DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`task_group_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时任务组信息表';

/*Data for the table `table_system_timing_task_scheduler_group` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;















DROP TABLE IF EXISTS r_t_account_resources;

/*==============================================================*/
/* Table: r_t_account_resources                                 */
/*==============================================================*/
CREATE TABLE r_t_account_resources
(
   id                   BIGINT NOT NULL COMMENT '主键',
   account_number       VARCHAR(16) COMMENT '账号',
   phone_number         CHAR(11) COMMENT '账号关联的手机号',
   resource_id          BIGINT COMMENT '资源主键',
   statu               VARCHAR(4) COMMENT '状态 0未启用1启用',
   create_time          DATETIME COMMENT '创建时间',
   create_by            BIGINT(64) COMMENT '创建者',
   update_time          DATETIME COMMENT '更新时间',
   changed_by           BIGINT(64) COMMENT '更改者',
   version_number              INT COMMENT '字段版本',
   is_delete               INT COMMENT '删除标识',
   remark               VARCHAR(512) COMMENT '备注'
);

ALTER TABLE r_t_account_resources COMMENT '这是一张冗余的关系表。主要是用来便于权限查询';

ALTER TABLE r_t_account_resources
   ADD PRIMARY KEY (id);

