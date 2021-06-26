/*
 Navicat Premium Data Transfer

 Source Server         : presentcloud
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : 159.75.51.228:3306
 Source Schema         : present_cloud

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 26/06/2021 09:47:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------

CREATE TABLE `menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `parent_id` int(0) NOT NULL DEFAULT 0,
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sort` int(0) NULL DEFAULT NULL,
  `component` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `hidden` tinyint(1) NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `role` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 0, 'DashBoard', 'system-home', 'el-icon-house', 'manageBoard', 1, 'manageBoard', 0, '2021-04-16 20:40:01', NULL);
INSERT INTO `menu` VALUES (2, 0, '角色管理', 'system-role', 'el-icon-user-solid', 'roleManage', 2, 'roleManage', 0, '2021-04-16 20:40:01', NULL);
INSERT INTO `menu` VALUES (3, 0, '菜单管理', 'menu', 'el-icon-menu', 'menuManage', 3, 'menuManage', 0, '2021-04-28 15:57:03', NULL);
INSERT INTO `menu` VALUES (4, 0, '用户管理', 'user', 'el-icon-user', '2', 4, NULL, 0, '2021-04-28 15:58:23', NULL);
INSERT INTO `menu` VALUES (5, 4, '教师管理', 'teacher', 'el-icon-s-custom', 'teacherManage', 4, 'teacherManage', 0, '2021-04-28 15:59:12', NULL);
INSERT INTO `menu` VALUES (6, 4, '学生管理', 'student', 'el-icon-s-custom', 'studentManage', 4, 'studentManage', 0, '2021-04-28 16:00:18', NULL);
INSERT INTO `menu` VALUES (7, 0, '班课管理', 'classes', 'el-icon-tickets', 'lessonManage', 5, 'lessonManage', 0, '2021-04-28 16:01:16', NULL);
INSERT INTO `menu` VALUES (8, 0, '数据字典', 'datadic', 'el-icon-s-data', 'dataDictionary', 6, 'dataDictionary', 0, '2021-04-28 16:01:49', NULL);
INSERT INTO `menu` VALUES (9, 0, '参数管理', 'var', 'el-icon-s-data', 'sysParaManage', 7, 'sysParaManage', 0, '2021-04-28 16:02:31', NULL);
INSERT INTO `menu` VALUES (10, 0, '测试页面', 'test', 'el-icon-tickets', 'test', 8, 'test', 1, '2021-04-28 16:03:19', NULL);
INSERT INTO `menu` VALUES (11, 0, '表单相关', 'form', 'el-icon-files', '3', 9, NULL, 1, '2021-04-28 16:04:17', NULL);
INSERT INTO `menu` VALUES (12, 11, '基础列表', 'basetable', 'el-icons-files', 'baseTable', 9, 'baseTable', 0, '2021-04-28 16:04:52', NULL);
INSERT INTO `menu` VALUES (13, 11, '基础表单', 'baseform', 'el-icon-files', 'baseForm', 9, 'baseForm', 0, '2021-04-28 16:05:51', NULL);
INSERT INTO `menu` VALUES (14, 11, '异常页面', 'errorpage', 'el-icon-files', 'errorPage', 9, 'errorPage', 0, '2021-04-28 16:08:31', NULL);
INSERT INTO `menu` VALUES (15, 0, '签到管理', 'sign', 'el-icon-s-claim', 'signManage', 10, 'signManage', 0, '2021-04-28 17:39:53', NULL);
INSERT INTO `menu` VALUES (16, 4, '用户管理', 'users', 'el-icon-user', 'usersManage', 4, 'usersManage', 0, '2021-05-11 13:50:18', NULL);
INSERT INTO `menu` VALUES (19, 0, '1sdfsdfsdfssdf', '2', 'el-icon-s-help', '3', NULL, '6', 0, '2021-06-25 22:41:06', NULL);
INSERT INTO `menu` VALUES (20, 0, '测试呀呀呀', 'string', 'fa fa-video-camera', 'string', NULL, 'sdfsdf', NULL, '2021-06-25 22:20:51', NULL);

SET FOREIGN_KEY_CHECKS = 1;
