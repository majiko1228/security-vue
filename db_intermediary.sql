/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50527
 Source Host           : localhost:3306
 Source Schema         : db_intermediary

 Target Server Type    : MySQL
 Target Server Version : 50527
 File Encoding         : 65001

 Date: 23/03/2021 16:14:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_bonor
-- ----------------------------
DROP TABLE IF EXISTS `tb_bonor`;
CREATE TABLE `tb_bonor`  (
  `honor_id` int(255) NOT NULL,
  `honor_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_bonor
-- ----------------------------
INSERT INTO `tb_bonor` VALUES (1, '三好学生');
INSERT INTO `tb_bonor` VALUES (2, '优秀学生干部');
INSERT INTO `tb_bonor` VALUES (3, '学习标兵');
INSERT INTO `tb_bonor` VALUES (4, '实践公益标兵');
INSERT INTO `tb_bonor` VALUES (5, '体育运动标兵');
INSERT INTO `tb_bonor` VALUES (6, '文艺活动标兵');

-- ----------------------------
-- Table structure for tb_classes
-- ----------------------------
DROP TABLE IF EXISTS `tb_classes`;
CREATE TABLE `tb_classes`  (
  `class_id` int(64) NOT NULL AUTO_INCREMENT COMMENT '班级id',
  `class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '班级名称',
  PRIMARY KEY (`class_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_classes
-- ----------------------------
INSERT INTO `tb_classes` VALUES (1, '计算机科学与技术');
INSERT INTO `tb_classes` VALUES (2, '信息管理');
INSERT INTO `tb_classes` VALUES (3, '软件工程');
INSERT INTO `tb_classes` VALUES (4, '电子');

-- ----------------------------
-- Table structure for tb_exhibition
-- ----------------------------
DROP TABLE IF EXISTS `tb_exhibition`;
CREATE TABLE `tb_exhibition`  (
  `exhibition_id` int(64) NOT NULL AUTO_INCREMENT,
  `exhibition_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`exhibition_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_grant
-- ----------------------------
DROP TABLE IF EXISTS `tb_grant`;
CREATE TABLE `tb_grant`  (
  `grant_id` int(255) NOT NULL,
  `grant_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`grant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu`  (
  `menu_id` tinyint(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限名称',
  `menu_pid` int(11) DEFAULT NULL COMMENT '父节点id',
  `menu_pids` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所有父节点id',
  `is_leaf` tinyint(11) DEFAULT NULL COMMENT '是否是叶子节点，0不是，1是',
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '路径或权限',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `level` int(11) DEFAULT NULL COMMENT '层级',
  `sort` int(11) DEFAULT 1 COMMENT '排序',
  `status` int(11) DEFAULT 0 COMMENT '状态',
  `visible` int(11) DEFAULT 0 COMMENT '0显示，1不显示',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES (1, '首页', 0, NULL, 1, '/dashboard', 'el-icon-lx-home', 1, 1, 0, 0);
INSERT INTO `tb_menu` VALUES (2, '用户管理', 0, NULL, 0, '', 'el-icon-user', 1, 1, 0, 0);
INSERT INTO `tb_menu` VALUES (3, '个人信息', 2, NULL, 1, '/info', '', 2, 1, 0, 0);
INSERT INTO `tb_menu` VALUES (4, '学生管理', 2, NULL, 1, '/user', '', 2, 1, 0, 0);
INSERT INTO `tb_menu` VALUES (5, '奖学金', 0, NULL, 1, '/scholarship', 'el-icon-money', 1, 1, 0, 0);
INSERT INTO `tb_menu` VALUES (6, '助学金', 0, NULL, 1, '/student', 'el-icon-medal', 1, 1, 0, 0);
INSERT INTO `tb_menu` VALUES (7, '勤工俭学', 0, '', 1, '/teacher', 'el-icon-wallet', 1, 1, 0, 0);
INSERT INTO `tb_menu` VALUES (8, '荣誉奖', 0, NULL, 1, '/test', 'el-icon-trophy', 1, 1, 0, 0);
INSERT INTO `tb_menu` VALUES (9, '奖学金明细', 5, NULL, 0, '/sholarship', NULL, 2, 1, 0, 0);
INSERT INTO `tb_menu` VALUES (10, '奖学金管理', 5, NULL, 0, '/sholarship_categoty', NULL, 2, 1, 0, 0);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `role_id` tinyint(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色code，如admin',
  `role_decs` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  `sort` tinyint(11) DEFAULT 1 COMMENT '排序，越小越靠前',
  `status` tinyint(11) DEFAULT 0 COMMENT '状态，0表示正常，1表示禁用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, '管理员', 'admin', '管理员', 1, 0, '2020-11-13 16:13:42');
INSERT INTO `tb_role` VALUES (2, '买方', 'purchaser', '买方', 1, 0, '2020-11-13 16:13:46');
INSERT INTO `tb_role` VALUES (3, '卖方', 'vendor', '卖方', 1, 0, '2020-11-13 16:13:49');
INSERT INTO `tb_role` VALUES (4, '普通用户', 'common', '普通用户', 1, 0, '2020-11-21 14:15:15');
INSERT INTO `tb_role` VALUES (5, '超级管理员', 'super', '超级管理员', 1, 0, '2020-12-16 11:21:27');

-- ----------------------------
-- Table structure for tb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu`  (
  `id` tinyint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` tinyint(11) DEFAULT NULL COMMENT '角色id',
  `menu_id` tinyint(11) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_role_menu
-- ----------------------------
INSERT INTO `tb_role_menu` VALUES (1, 1, 1);
INSERT INTO `tb_role_menu` VALUES (2, 1, 2);
INSERT INTO `tb_role_menu` VALUES (3, 1, 3);
INSERT INTO `tb_role_menu` VALUES (4, 1, 4);
INSERT INTO `tb_role_menu` VALUES (5, 1, 5);
INSERT INTO `tb_role_menu` VALUES (6, 1, 6);
INSERT INTO `tb_role_menu` VALUES (7, 1, 7);
INSERT INTO `tb_role_menu` VALUES (8, 1, 8);
INSERT INTO `tb_role_menu` VALUES (9, 4, 1);
INSERT INTO `tb_role_menu` VALUES (10, 4, 2);
INSERT INTO `tb_role_menu` VALUES (11, 4, 3);
INSERT INTO `tb_role_menu` VALUES (12, 1, 9);
INSERT INTO `tb_role_menu` VALUES (13, 1, 10);

-- ----------------------------
-- Table structure for tb_sholarship
-- ----------------------------
DROP TABLE IF EXISTS `tb_sholarship`;
CREATE TABLE `tb_sholarship`  (
  `scholar_id` int(11) NOT NULL COMMENT '奖学金id',
  `scholar_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '奖学金',
  PRIMARY KEY (`scholar_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_sholarship
-- ----------------------------
INSERT INTO `tb_sholarship` VALUES (1, '一等奖学金');
INSERT INTO `tb_sholarship` VALUES (2, '二等奖学金');
INSERT INTO `tb_sholarship` VALUES (3, '三等奖学金');
INSERT INTO `tb_sholarship` VALUES (4, '特等奖学金');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` tinyint(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名(不能重复)',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `gender` int(11) DEFAULT 0 COMMENT '0：女，1：男',
  `number` int(255) DEFAULT NULL COMMENT '学号',
  `enable` int(11) DEFAULT 0 COMMENT '状态，0：正常，1：禁用',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系方式',
  `status` int(11) DEFAULT 0 COMMENT '状态，0：正常，1：禁用',
  `update_time` datetime DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL COMMENT '班级id',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, '陈雨鹏', 'admin', '$2a$10$nkqe.fiJEP5FWD4cYavGZuY8jZ6tAJgp35Bf9.0LeWADKMMvHMp4.', 1, 1741701, 0, '2020-11-13 16:52:36', '13252265210', 0, NULL, 1);
INSERT INTO `tb_user` VALUES (2, '李佳岭', 'ljl', '$2a$10$ovrhGjlhxmYoEAEAp04jPeQeD4i9y7V5WB92wHyONblsjPIyfSNz', 0, 1741702, 0, '2020-11-24 20:35:39', '13252265210', 0, NULL, 1);
INSERT INTO `tb_user` VALUES (3, '雷霆嘎巴', 'cyp', '$2a$10$0QJKt5AjwAm47yAD8ql26eKgQLwFYskaOHteHDYYfnwHANmJM.gj.', 1, 1741703, 0, '2020-11-24 20:35:43', '13252265210', 0, NULL, 1);
INSERT INTO `tb_user` VALUES (4, '欧青辣少', 'user1', '$2a$10$gh.udq4VpQFWeCML.UljB.4B7KF8jg7IBnLAJCHJeXzAqHhaGal.G', 1, 22, 0, '2020-11-24 21:17:46', '13252265210', 1, NULL, 1);
INSERT INTO `tb_user` VALUES (5, '恩杰爸爸', 'enjie', '$2a$10$8UThkRhq.NvyZqWYEePaW.57Sf6r6QaLqMzp.lJwjffifLEZWOGay', 0, 11, 0, '2020-12-01 08:30:35', '321', 0, NULL, 2);
INSERT INTO `tb_user` VALUES (6, '吧', 'user3', '$2a$10$gh.udq4VpQFWeCML.UljB.4B7KF8jg7IBnLAJCHJeXzAqHhaGal.G', 0, 2, 0, '2020-12-01 08:30:51', '122', 0, NULL, 2);
INSERT INTO `tb_user` VALUES (7, '从', 'user4', '$2a$10$gh.udq4VpQFWeCML.UljB.4B7KF8jg7IBnLAJCHJeXzAqHhaGal.G', 0, 3, 0, '2020-12-02 08:30:55', '1213', 0, NULL, 2);
INSERT INTO `tb_user` VALUES (11, 'qwe', '1', NULL, 0, NULL, 0, NULL, NULL, 1, NULL, 3);
INSERT INTO `tb_user` VALUES (12, 'sdf', 'ls', NULL, 0, NULL, 0, NULL, NULL, 1, NULL, 3);
INSERT INTO `tb_user` VALUES (13, 'ggg', 'test1', '$2a$10$Y7lifQ9QzcrU6XtZ0i2tIelzWur.EcmZLKzAfr5joKO7oif4dk6ES', 1, 333, 0, '2021-03-23 14:10:10', '1111', 0, '2021-03-23 14:10:10', 4);
INSERT INTO `tb_user` VALUES (14, '请求', 'test2', '$2a$10$pFOLwGpCCJECsI/6mcRbcuO5DsmxUC647oXlSp7LDLz3M4i5Xy2VC', 1, 174173331, 0, '2021-03-23 09:37:03', '1232152', 1, NULL, 4);
INSERT INTO `tb_user` VALUES (15, '二手电', 'test3', '$2a$10$iRmoNomI7jKYezoZtRFUneXPL56HOfZXJW6QJhLu8mMp9ELa8NE.a', 0, 123123, 0, '2021-03-23 10:50:31', '1231231', 1, NULL, 2);

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role`  (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `user_id` int(64) DEFAULT NULL,
  `role_id` int(64) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES (1, 1, 1);
INSERT INTO `tb_user_role` VALUES (2, 2, 4);
INSERT INTO `tb_user_role` VALUES (3, 3, 4);
INSERT INTO `tb_user_role` VALUES (4, 11, 4);
INSERT INTO `tb_user_role` VALUES (5, 5, 1);
INSERT INTO `tb_user_role` VALUES (6, 12, 4);
INSERT INTO `tb_user_role` VALUES (7, 13, 4);
INSERT INTO `tb_user_role` VALUES (8, 4, 4);
INSERT INTO `tb_user_role` VALUES (9, 6, 4);
INSERT INTO `tb_user_role` VALUES (10, 7, 1);
INSERT INTO `tb_user_role` VALUES (11, 14, 4);
INSERT INTO `tb_user_role` VALUES (12, 15, 4);

SET FOREIGN_KEY_CHECKS = 1;
