/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : medicalservice

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 17/06/2021 17:00:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for case
-- ----------------------------
DROP TABLE IF EXISTS `case`;
CREATE TABLE `case`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `case_id` int(16) NULL DEFAULT NULL COMMENT '案例id',
  `case_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '案例名',
  `course_id` int(16) NULL DEFAULT NULL COMMENT '课程id',
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名',
  `case_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '案例描述',
  `creat_time` datetime(6) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for case_file
-- ----------------------------
DROP TABLE IF EXISTS `case_file`;
CREATE TABLE `case_file`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `case_id` int(16) NULL DEFAULT NULL COMMENT '案例id',
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件资源',
  `creat_time` datetime(6) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for case_image
-- ----------------------------
DROP TABLE IF EXISTS `case_image`;
CREATE TABLE `case_image`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `case_id` int(16) NULL DEFAULT NULL COMMENT '案例id',
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片资源',
  `creat_time` datetime(6) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment_reply
-- ----------------------------
DROP TABLE IF EXISTS `comment_reply`;
CREATE TABLE `comment_reply`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `comment_id` int(16) NULL DEFAULT NULL COMMENT '评论id',
  `case_id` int(16) NULL DEFAULT NULL COMMENT '案例id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `from_id` int(16) NULL DEFAULT NULL COMMENT '评论者id',
  `creat_time` datetime(6) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `course_id` int(16) NULL DEFAULT NULL COMMENT '课程id',
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名',
  `teacher_id` int(16) NULL DEFAULT NULL COMMENT '创建老师id',
  `course_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程描述',
  `course_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程状态',
  `creat_time` datetime(6) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_student
-- ----------------------------
DROP TABLE IF EXISTS `course_student`;
CREATE TABLE `course_student`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `course_id` int(16) NULL DEFAULT NULL COMMENT '课程id',
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `student_id` int(16) NULL DEFAULT NULL COMMENT '学生id',
  `student_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生名称',
  `team_id` int(16) NULL DEFAULT NULL COMMENT '队伍id',
  `creat_time` datetime(6) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_teacher
-- ----------------------------
DROP TABLE IF EXISTS `course_teacher`;
CREATE TABLE `course_teacher`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `course_id` int(16) NULL DEFAULT NULL COMMENT '课程id',
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `teacher_id` int(16) NULL DEFAULT NULL COMMENT '老师id',
  `teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老师姓名',
  `iscreater` int(1) NULL DEFAULT NULL COMMENT '是否创建者',
  `creat_time` datetime(6) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dictionary_detail
-- ----------------------------
DROP TABLE IF EXISTS `dictionary_detail`;
CREATE TABLE `dictionary_detail`  (
  `id` int(16) NOT NULL,
  `type_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典编码如sex',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典具体名字如男，女',
  `value` int(2) NULL DEFAULT NULL COMMENT '字典值数字如1，2',
  `creat_time` datetime(6) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dictionary_type
-- ----------------------------
DROP TABLE IF EXISTS `dictionary_type`;
CREATE TABLE `dictionary_type`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `type_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典编码如sex',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典名称如性别',
  `creat_time` datetime(6) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `role_id` int(9) NOT NULL,
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `perms` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, 0, '管理员', '/login,/home');
INSERT INTO `roles` VALUES (2, 1, '教师', NULL);
INSERT INTO `roles` VALUES (3, 2, '学生', NULL);

-- ----------------------------
-- Table structure for sub_comment_reply
-- ----------------------------
DROP TABLE IF EXISTS `sub_comment_reply`;
CREATE TABLE `sub_comment_reply`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `parent_id` int(16) NULL DEFAULT NULL COMMENT '父id',
  `child_id` int(16) NULL DEFAULT NULL COMMENT '子id',
  `creat_time` datetime(6) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `user_id` int(16) NULL DEFAULT NULL COMMENT '用户id',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `role_id` int(16) NULL DEFAULT NULL COMMENT '角色id',
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门',
  `grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年级',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称',
  `creat_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'xxx', '123456', 0, '1', '1383838338', '66666@gmail.com', 0, '1', '1', 'xxxxx', '2021-06-16 18:54:43');

SET FOREIGN_KEY_CHECKS = 1;
