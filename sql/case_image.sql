/*
 Navicat MySQL Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : medicalservice

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 27/06/2021 20:59:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for case_image
-- ----------------------------
DROP TABLE IF EXISTS `case_image`;
CREATE TABLE `case_image`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `case_id` int NULL DEFAULT NULL COMMENT '案例id',
  `image` longblob NULL COMMENT '图片资源',
  `image_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creat_time` datetime(6) NULL DEFAULT CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
