create database open_store charset utf8;
use open_store;

-- alter table tb_user add column isAdmin tinyint not null  default 0 comment '是否是管理员 1-是,0-不是' after staus;
-- alter table tb_user add column status tinyint not null default 1 comment '1-激活,2-注销' after email;
-- alter table tb_user add column is_admin tinyint not null  default 0 comment '是否是管理员 1-是,0-不是' after staus;

create table tb_user(
    -- id 一律采用 bigint
    id bigint primary key  comment '用户ID',
    username varchar(20) unique not null comment '用户名',
    password varchar(255) not null comment '用户密码',
    tel varchar(20) not null unique comment '用户手机号码',
    email varchar(20) unique comment '用户邮箱',
    status tinyint not null default 1 comment '1-激活,2-注销',
    is_admin tinyint not null  default 0 comment '是否是管理员 1-是,0-不是',
    deleted tinyint not null default 0 comment '删除标记 0-未删除,1-已删除',
    create_user bigint not null comment '创建人ID',
    update_user bigint not null comment '更新者ID',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间'
)comment '用户表' charset utf8;


create table sys_menu(
    id int primary key auto_increment comment '系统菜单ID',
    parent_id int not null default 0 comment '父菜单ID',
    name varchar(20) not null comment '菜单名',
    type  tinyint  not null comment '1-导航,2-菜单,3-按钮',
    sort  smallint default null comment '排序字段',
    icon varchar(100) default null comment '菜单图标',
    deleted tinyint not null default 0 comment '删除标记 0-未删除,1-已删除',
    create_user bigint not null comment '创建人ID',
    update_user bigint not null comment '更新者ID',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间'
)comment  '菜单表' charset utf8;

create table sys_role(
    id int primary key auto_increment comment '角色ID',
    name varchar(20) not null comment '角色名',
    remark varchar(500) default null comment '角色备注说民',
    deleted tinyint not null default 0 comment '删除标记 0-未删除,1-已删除',
    create_user bigint not null comment '创建人ID',
    update_user bigint not null comment '更新者ID',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间'
)charset utf8 comment '角色表';

create table role_menu_rel(
    id int primary key auto_increment comment '角色菜单关系表',
    role_id int not null comment '角色ID',
    menu_id int not null comment '菜单ID',
    deleted tinyint not null default 0 comment '删除标记 0-未删除,1-已删除',
    create_user bigint not null comment '创建人ID',
    update_user bigint not null comment '更新者ID',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间'
)charset utf8 comment '角色菜单关系表';



create table user_role_rel(
    id bigint primary key auto_increment comment '用户角色关系ID',
    user_id bigint not null  comment '用户ID',
    role_id int not null comment '角色ID',
    deleted tinyint not null default 0 comment '删除标记 0-未删除,1-已删除',
    create_user bigint not null comment '创建人ID',
    update_user bigint not null comment '更新者ID',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间'
)charset utf8 comment '用户角色关系表';

