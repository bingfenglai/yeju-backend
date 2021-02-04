/**
 *给操作日志表添加`operator_id` 字段
 *date: 2021-1-1
 *version: 1.0.0
 *
 */ 
ALTER TABLE `yeju_core`.`table_system_operation_log`   
  ADD COLUMN `operator_id` BIGINT NULL   COMMENT '操作者id' AFTER `last_ip_number`;


alter table tabel_system_resource_md5
drop primary key;

drop table if exists tabel_system_resource_md5;



/**
  添加新表 资源md5表，用于快传
 */
/*==============================================================*/
/* Table: table_system_resource_md5                             */
/*==============================================================*/
create table table_system_resource_md5
(
    id             bigint       not null
        primary key,
    resource       varchar(500) null comment '当资源为一个文件时，此时这里记录的是文件的全路径',
    resource_ype   bigint       null comment '资源类型，详见数字字典
            主要有：1.文件 2.string',
    md5            varchar(32)  null comment '资源md5值',
    create_time    datetime     null comment '创建时间',
    create_by      bigint       null comment '创建者',
    update_time    datetime     null comment '更新时间',
    changed_by     bigint       null comment '更改者',
    remark         varchar(512) null comment '备注',
    version_number int          null comment '字段版本',
    is_delete      int          null comment '删除标识',
    constraint md5__index
        unique (md5)
)
    comment '资源md5表，用于快传';

alter table tabel_system_resource_md5
    add primary key (id);
/*====================================================================*/


/*====================================================================*/
添加操作耗时字段
alter table table_system_operation_log
    add execute_time bigint null comment '操作耗时 单位 ms';
