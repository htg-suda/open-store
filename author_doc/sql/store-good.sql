create database db_store_goods  charset 'utf8';
/*商品分类*/
create table tb_goods_category(
    id int primary key auto_increment comment '分类id',
    parent_id int not null default 0 comment '分类父id, 0 为根分类',
    name varchar(100) not null comment '分类名',
    icon varchar(255) default null comment '分类图标',

    is_leaf tinyint not null comment '是否是叶子节点,表示是否是最终分类,0-不是,1-是',
    need_brand tinyint not null default 0 comment '是否需要品牌 0-不需要,1-需要',
    -- 附带信息
    deleted tinyint not null default 0 comment '删除标记,0 表示存在,-1 表示删除',
    sort bigint  comment '排序字段',
    create_user bigint  comment '创建人ID',
    update_user bigint  comment '更新者ID',
    create_time datetime comment '创建时间',
    update_time datetime comment '更新时间'
) comment '商品分类' charset utf8;

/* 商品品牌表 */
create table tb_brand(
    id int primary key auto_increment comment '品牌ID',
    name varchar(20) unique key comment '品牌名',
    logo varchar(255) default null  comment '品牌logo',
    -- 附带信息
    deleted tinyint not null default 0 comment '删除标记,0 表示存在,-1 表示删除',
    sort bigint  comment '排序字段',
    create_user bigint  comment '创建人ID',
    update_user bigint  comment '更新者ID',
    create_time datetime  comment '创建时间',
    update_time datetime  comment '更新时间'
)charset utf8 comment '品牌表';

/* 品牌和分类的关系表 ,是多对多的关系*/
create table tb_brand_category_rel(
   id int primary key auto_increment comment '品牌表id',
   category_id int not  null  comment '分类表id',
   brand_id int not null  comment '品牌表id',
    -- 附带信息
   deleted tinyint not null default 0 comment '删除标记,0 表示存在,-1 表示删除',
   sort bigint  comment '排序字段',
   create_user bigint  comment '创建人ID',
   update_user bigint  comment '更新者ID',
   create_time datetime  comment '创建时间',
   update_time datetime  comment '更新时间'
)comment '品牌和分类的关系表' charset utf8;

/* 商品通用属性 表*/
create table tb_gen_goods(
     id bigint  primary key auto_increment  comment '商品通用属性主键',
     sn varchar(50) unique key comment '商品通用属性序列号',
     category_id int not null comment '产品分类ID,参考产品分类表',
     brand_id int  comment '品牌ID',
     title varchar(100) not null comment '商品主标题',
     sub_title varchar(100) default null comment '商品副标题',
     main_img varchar(255) not null comment '商品主图',
     sub_imgs varchar(1000) not null comment '商品子图,以逗号间隔',
     detail text not null comment '商品详情页面',
     status tinyint default 0  comment '商品状态 1-在售,2-下架',
    -- 附带信息
     deleted tinyint not null default 0 comment '删除标记,0 表示存在,-1 表示删除',
     sort bigint  default null comment '排序字段',
     create_user bigint  comment '创建人ID',
     update_user bigint  comment '更新者ID',
     create_time datetime  comment '创建时间',
     update_time datetime  comment '更新时间'
)charset utf8 comment '商品通用属性表';

/* 具体商品属性表,这张表只有商品的 价格,库存  */
create table tb_def_goods(
    id bigint primary key auto_increment   comment '具体商品主键',
    gen_id bigint not null comment '商品通用属性id ,通用属性下有多个具体商品',
    sn varchar(50) unique key not null comment '具体商品 序列号码',
    title varchar(100) comment '具体商品主标题',
    sub_title varchar(100)  comment '具体商品副标题',
    main_img varchar(255)  comment '具体商品主图',
    sub_imgs varchar(1000) comment '具体商品子图,以逗号间隔',
    detail text  comment '具体商品详情页面',
    price decimal(10,2) not null comment '商品价格',
    stock int not null  default 0 comment '商品的库存总量',
    stock_alarm int not null  default 0 comment '库存警戒值',
    status tinyint default 0  comment '商品状态 1-在售,2-下架',
    -- 附带信息
    deleted tinyint not null default 0 comment '删除标记,0 表示存在,-1 表示删除',
    sort bigint  default null comment '排序字段',
    create_user bigint  comment '创建人ID',
    update_user bigint  comment '更新者ID',
    create_time datetime  comment '创建时间',
    update_time datetime  comment '更新时间'
)charset utf8 comment '具体商品属性表';

/* 规格组 */
create table spec_group(
    id int primary key auto_increment comment '规格主键',
    name varchar(20) not null comment '规格组名',
    category_id  int not null comment '商品分类id',
    type tinyint not null  default 0 comment '0-通用规格组,1-具体商品规格组',

    -- 附带信息
    deleted tinyint not null default 0 comment '删除标记,0 表示存在,-1 表示删除',
    sort bigint  default null comment '排序字段',
    create_user bigint  comment '创建人ID',
    update_user bigint  comment '更新者ID',
    create_time datetime  comment '创建时间',
    update_time datetime  comment '更新时间'
)charset utf8 comment '规格组表';

/* 规格组 key 表*/
create table spec_key(
    id int primary key auto_increment comment 'spec id 主键',
    group_id int  not null comment '规格组id ',
    spec_name varchar(20) not null comment '商品spec 名',
    spec_type varchar(20) not null comment 'text-文本类型,num-数值类型,many-枚举类型...',
    spec_unit varchar(20)  comment '单位值',
    spec_enum varchar(200)  comment 'spec 的可选值,逗号间隔',
    is_search tinyint not null default 0 comment '是否参与检索 0-不参与,1-参与',
    is_essential tinyint not null default 0 comment '是否必须 0-不是必须,1-必须',
    -- 附带信息
    deleted tinyint not null default 0 comment '删除标记,0 表示存在,-1 表示删除',
    sort bigint  default null comment '排序字段',
    create_user bigint  comment '创建人ID',
    update_user bigint  comment '更新者ID',
    create_time datetime  comment '创建时间',
    update_time datetime  comment '更新时间'
)charset utf8 comment '商品规格名';

/* 商品通用规格value表 存商品通用规格值表*/
create table gen_spec_value(
    id bigint  primary key auto_increment  comment '商品通用规格值主键',
    key_id int not  null  comment 'spec key id',
    spec_value varchar(100) not null comment 'spec value 的值',
    gen_id bigint not null comment '商品通用属性id',
    -- 附带信息
    deleted tinyint not null default 0 comment '删除标记,0 表示存在,-1 表示删除',
    sort bigint  default null comment '排序字段',
    create_user bigint  comment '创建人ID',
    update_user bigint  comment '更新者ID',
    create_time datetime  comment '创建时间',
    update_time datetime  comment '更新时间'
)charset utf8 comment '商品通用规格值表';

/* 商品通用规格value表 存商品通用规格值表*/
create table def_spec_value(
    id bigint  primary key auto_increment  comment '商品通用规格值主键',
    key_id int not  null  comment 'spec key id',
    spec_value varchar(100) not null comment 'spec value 的值',
    def_id bigint not null comment '具体商品属性id',
    -- 附带信息
    deleted tinyint not null default 0 comment '删除标记,0 表示存在,-1 表示删除',
    sort bigint  default null comment '排序字段',
    create_user bigint  comment '创建人ID',
    update_user bigint  comment '更新者ID',
    create_time datetime  comment '创建时间',
    update_time datetime  comment '更新时间'
)charset utf8 comment '商品通用规格值表';


