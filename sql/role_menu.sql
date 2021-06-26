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

 Date: 26/06/2021 10:50:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_id` int(0) NOT NULL,
  `menu_id` int(0) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 144 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (17, 2, 4, '2021-04-28 17:34:32');
INSERT INTO `role_menu` VALUES (18, 2, 6, '2021-04-28 17:35:31');
INSERT INTO `role_menu` VALUES (19, 2, 7, '2021-04-28 17:35:42');
INSERT INTO `role_menu` VALUES (20, 2, 15, '2021-04-28 17:41:27');
INSERT INTO `role_menu` VALUES (24, 2, 1, '2021-06-11 20:03:33');
INSERT INTO `role_menu` VALUES (31, 3, 7, '2021-06-15 15:14:24');
INSERT INTO `role_menu` VALUES (32, 3, 8, '2021-06-15 15:14:24');
INSERT INTO `role_menu` VALUES (33, 3, 10, '2021-06-15 15:14:24');
INSERT INTO `role_menu` VALUES (47, 4, 2, '2021-06-24 17:18:25');
INSERT INTO `role_menu` VALUES (48, 4, 9, '2021-06-24 17:18:25');
INSERT INTO `role_menu` VALUES (129, 1, 1, '2021-06-25 22:22:57');
INSERT INTO `role_menu` VALUES (130, 1, 2, '2021-06-25 22:22:57');
INSERT INTO `role_menu` VALUES (131, 1, 3, '2021-06-25 22:22:57');
INSERT INTO `role_menu` VALUES (132, 1, 4, '2021-06-25 22:22:57');
INSERT INTO `role_menu` VALUES (133, 1, 6, '2021-06-25 22:22:57');
INSERT INTO `role_menu` VALUES (134, 1, 16, '2021-06-25 22:22:57');
INSERT INTO `role_menu` VALUES (135, 1, 7, '2021-06-25 22:22:57');
INSERT INTO `role_menu` VALUES (136, 1, 8, '2021-06-25 22:22:57');
INSERT INTO `role_menu` VALUES (137, 1, 9, '2021-06-25 22:22:57');
INSERT INTO `role_menu` VALUES (138, 1, 10, '2021-06-25 22:22:57');
INSERT INTO `role_menu` VALUES (139, 1, 11, '2021-06-25 22:22:57');
INSERT INTO `role_menu` VALUES (140, 1, 12, '2021-06-25 22:22:57');
INSERT INTO `role_menu` VALUES (141, 1, 13, '2021-06-25 22:22:57');
INSERT INTO `role_menu` VALUES (142, 1, 14, '2021-06-25 22:22:57');
INSERT INTO `role_menu` VALUES (143, 1, 20, '2021-06-25 22:22:57');

SET FOREIGN_KEY_CHECKS = 1;
