/**
 *给操作日志表添加`operator_id` 字段
 *date: 2021-1-1
 *version: 1.0.0
 *
 */ 
ALTER TABLE `yeju_core`.`table_system_operation_log`   
  ADD COLUMN `operator_id` BIGINT NULL   COMMENT '操作者id' AFTER `last_ip_number`;
