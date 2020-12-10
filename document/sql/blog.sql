/*
Navicat MySQL Data Transfer

Source Server         : mysql80
Source Server Version : 80020
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2020-12-10 18:23:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog_about_info
-- ----------------------------
DROP TABLE IF EXISTS `blog_about_info`;
CREATE TABLE `blog_about_info` (
  `id` int NOT NULL,
  `about_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '关于我内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_about_info
-- ----------------------------

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '作者',
  `category_id` int DEFAULT NULL COMMENT '文章分类',
  `article_cover` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文章缩略图',
  `article_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `article_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '发表时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_top` tinyint(1) DEFAULT NULL COMMENT '是否置顶',
  `is_draft` tinyint(1) DEFAULT '0' COMMENT '是否为草稿',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_article_user` (`user_id`) USING BTREE,
  KEY `fk_article_category` (`category_id`) USING BTREE,
  FULLTEXT KEY `fk_title_content` (`article_title`,`article_content`) /*!50100 WITH PARSER `ngram` */ 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_article
-- ----------------------------

-- ----------------------------
-- Table structure for blog_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_tag`;
CREATE TABLE `blog_article_tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_id` int NOT NULL COMMENT '文章id',
  `tag_id` int NOT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_article_tag_1` (`article_id`) USING BTREE,
  KEY `fk_article_tag_2` (`tag_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_article_tag
-- ----------------------------

-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_category
-- ----------------------------

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '评论用户Id',
  `article_id` int DEFAULT NULL COMMENT '评论文章id',
  `comment_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `create_time` datetime NOT NULL COMMENT '评论时间',
  `reply_id` int DEFAULT NULL COMMENT '回复用户id',
  `parent_id` int DEFAULT NULL COMMENT '父评论id',
  `is_delete` tinyint DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_comment_user` (`user_id`) USING BTREE,
  KEY `fk_comment_article` (`article_id`) USING BTREE,
  KEY `fk_comment_parent` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_comment
-- ----------------------------

-- ----------------------------
-- Table structure for blog_friend_link
-- ----------------------------
DROP TABLE IF EXISTS `blog_friend_link`;
CREATE TABLE `blog_friend_link` (
  `id` int NOT NULL AUTO_INCREMENT,
  `link_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '链接名',
  `link_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '链接头像',
  `link_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '链接地址',
  `link_intro` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '链接介绍',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_friend_link_user` (`link_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_friend_link
-- ----------------------------

-- ----------------------------
-- Table structure for blog_login_log
-- ----------------------------
DROP TABLE IF EXISTS `blog_login_log`;
CREATE TABLE `blog_login_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户信息id',
  `login_type` tinyint(1) NOT NULL COMMENT '登录类型',
  `ip_addr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户登录ip',
  `ip_source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'ip来源',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for blog_menu
-- ----------------------------
DROP TABLE IF EXISTS `blog_menu`;
CREATE TABLE `blog_menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int DEFAULT NULL COMMENT '父级ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `title` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `level` int DEFAULT NULL COMMENT '菜单级数',
  `sort` int DEFAULT NULL COMMENT '菜单排序',
  `name` varchar(100) DEFAULT NULL COMMENT '前端名称',
  `icon` varchar(200) DEFAULT NULL COMMENT '前端图标',
  `hidden` int DEFAULT NULL COMMENT '前端隐藏',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='后台菜单表';

-- ----------------------------
-- Records of blog_menu
-- ----------------------------

-- ----------------------------
-- Table structure for blog_message
-- ----------------------------
DROP TABLE IF EXISTS `blog_message`;
CREATE TABLE `blog_message` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `ip_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ip',
  `ip_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户地址',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像',
  `message_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '留言内容',
  `time` tinyint(1) NOT NULL COMMENT '弹幕速度',
  `create_time` datetime NOT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_message
-- ----------------------------

-- ----------------------------
-- Table structure for blog_permission
-- ----------------------------
DROP TABLE IF EXISTS `blog_permission`;
CREATE TABLE `blog_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pid` int DEFAULT NULL COMMENT '父级权限id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `value` varchar(200) DEFAULT NULL COMMENT '权限值',
  `icon` varchar(500) DEFAULT NULL COMMENT '图标',
  `type` int DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `uri` varchar(200) DEFAULT NULL COMMENT '前端资源路径',
  `status` int DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `sort` int DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='后台用户权限表';

-- ----------------------------
-- Records of blog_permission
-- ----------------------------

-- ----------------------------
-- Table structure for blog_resource
-- ----------------------------
DROP TABLE IF EXISTS `blog_resource`;
CREATE TABLE `blog_resource` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) DEFAULT NULL COMMENT '资源URL',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `category_id` int DEFAULT NULL COMMENT '资源分类ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='后台资源表';

-- ----------------------------
-- Records of blog_resource
-- ----------------------------

-- ----------------------------
-- Table structure for blog_resource_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_resource_category`;
CREATE TABLE `blog_resource_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) DEFAULT NULL COMMENT '分类名称',
  `sort` int DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='资源分类表';

-- ----------------------------
-- Records of blog_resource_category
-- ----------------------------

-- ----------------------------
-- Table structure for blog_role
-- ----------------------------
DROP TABLE IF EXISTS `blog_role`;
CREATE TABLE `blog_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `admin_count` int DEFAULT NULL COMMENT '后台用户数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `sort` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='后台用户角色表';

-- ----------------------------
-- Records of blog_role
-- ----------------------------
INSERT INTO `blog_role` VALUES ('1', '超级管理员', '超级管理员有所有权限', null, '2020-12-10 18:16:10', '1', '0');

-- ----------------------------
-- Table structure for blog_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `blog_role_menu_relation`;
CREATE TABLE `blog_role_menu_relation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL COMMENT '角色ID',
  `menu_id` int DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='后台角色菜单关系表';

-- ----------------------------
-- Records of blog_role_menu_relation
-- ----------------------------

-- ----------------------------
-- Table structure for blog_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `blog_role_permission_relation`;
CREATE TABLE `blog_role_permission_relation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL COMMENT '角色ID',
  `permission_id` int DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='后台用户角色和权限关系表';

-- ----------------------------
-- Records of blog_role_permission_relation
-- ----------------------------

-- ----------------------------
-- Table structure for blog_role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `blog_role_resource_relation`;
CREATE TABLE `blog_role_resource_relation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL COMMENT '角色ID',
  `resource_id` int DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='后台角色资源关系表';

-- ----------------------------
-- Records of blog_role_resource_relation
-- ----------------------------

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_tag
-- ----------------------------

-- ----------------------------
-- Table structure for blog_user
-- ----------------------------
DROP TABLE IF EXISTS `blog_user`;
CREATE TABLE `blog_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `user_type` int(10) unsigned zerofill DEFAULT '0000000002' COMMENT '用户类型(1前台 2后台 3同时拥有前后台)',
  `avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'https://lauz-oss.oss-cn-hangzhou.aliyuncs.com/blog/user.png' COMMENT '用户头像',
  `intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户简介',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` tinyint(1) DEFAULT '1' COMMENT '用户状态默认1有效',
  `is_silence` tinyint(1) DEFAULT '0' COMMENT '是否禁言',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique_user` (`nickname`) USING BTREE COMMENT '用户名，昵称，邮箱不可重复'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_user
-- ----------------------------
INSERT INTO `blog_user` VALUES ('1', 'admin', '$10$61ogZY7EXsMDWeVGQpDq3OBF1.phaUu7.xrwLyWFTOu8woE08zMIW', 'lauz', '0000000003', 'https://lauz-oss.oss-cn-hangzhou.aliyuncs.com/blog/user.png', null, '2020-12-10 18:14:11', '1', '0', '2020-12-10 18:15:02');

-- ----------------------------
-- Table structure for blog_user_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `blog_user_permission_relation`;
CREATE TABLE `blog_user_permission_relation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `permission_id` int DEFAULT NULL,
  `type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='后台用户和权限关系表(除角色中定义的权限以外的加减权限)';

-- ----------------------------
-- Records of blog_user_permission_relation
-- ----------------------------

-- ----------------------------
-- Table structure for blog_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `blog_user_role_relation`;
CREATE TABLE `blog_user_role_relation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='后台用户和角色关系表';

-- ----------------------------
-- Records of blog_user_role_relation
-- ----------------------------
INSERT INTO `blog_user_role_relation` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for blog_web_view
-- ----------------------------
DROP TABLE IF EXISTS `blog_web_view`;
CREATE TABLE `blog_web_view` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL COMMENT '时间',
  `views_count` int NOT NULL COMMENT '访问量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_web_view
-- ----------------------------
