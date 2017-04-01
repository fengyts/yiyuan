CREATE TABLE `base_front_category` (
	`id` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT '类目id',
	`name` VARCHAR(20) NOT NULL COMMENT '类目名称',
	`code` VARCHAR(50) NOT NULL COMMENT '类目编码',
	`level` TINYINT(1) NOT NULL COMMENT '类目级别:1-一级类目,2-二级类目,3-三级类目',
	`status` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '状态:0-有效，1-无效',
	`is_highlight` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否突出展示：0-是，1-否',
	`logo_url` VARCHAR(1000) NULL DEFAULT NULL COMMENT '类目url地址',
	`parent_id` BIGINT(11) NOT NULL COMMENT '父类目ID',
	`sort` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '顺序',
	`is_publish` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '是否发布:0-发布，1-否',
	`create_user_id` BIGINT(11) NOT NULL COMMENT '创建人',
	`create_time` DATETIME NOT NULL COMMENT '创建时间',
	`modify_user_id` BIGINT(11) NOT NULL COMMENT '修改人',
	`modify_time` DATETIME NOT NULL COMMENT '修改时间',
	PRIMARY KEY (`id`)
)
COMMENT='前台类目表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
CREATE TABLE `base_front_category_link` (
	`id` BIGINT(11) NOT NULL AUTO_INCREMENT,
	`category_id` BIGINT(11) NOT NULL COMMENT '类目id',
	`link_type` TINYINT(2) NOT NULL COMMENT '跳转方式:1-后台分类,2-固定页面,3-商品,4-品牌,5-搜索词',
	`link_content` VARCHAR(4000) NOT NULL,
	`large_category_ids` VARCHAR(1000) NOT NULL COMMENT '后台大类ids',
	`small_category_ids` VARCHAR(1000) NOT NULL COMMENT '后台小类ids',
	`link_url_pc` VARCHAR(128) NOT NULL,
	`link_url_app` VARCHAR(128) NOT NULL,
	`link_url_wap` VARCHAR(128) NOT NULL,
	`status` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '状态:0-正常，1-删除',
	`create_user_id` BIGINT(11) NOT NULL,
	`create_time` DATETIME NOT NULL,
	`modify_user_id` BIGINT(11) NOT NULL,
	`modify_time` DATETIME NOT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='前台类目跳转链接表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
