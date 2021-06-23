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

 Date: 23/06/2021 16:15:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for approve_request
-- ----------------------------
DROP TABLE IF EXISTS `approve_request`;
CREATE TABLE `approve_request`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `course_id` int(16) NOT NULL,
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `student_id` int(16) NOT NULL,
  `student_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_approved` int(1) UNSIGNED ZEROFILL NOT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `ar_id` int(16) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of approve_request
-- ----------------------------
INSERT INTO `approve_request` VALUES (1, 10000, '数据结构', 999999999, '申请者', 0, '2021-06-22 15:21:35', 10000);
INSERT INTO `approve_request` VALUES (2, 10001, '操作系统', 999999999, '申请者', 0, '2021-06-22 15:22:45', 10001);

-- ----------------------------
-- Table structure for case_file
-- ----------------------------
DROP TABLE IF EXISTS `case_file`;
CREATE TABLE `case_file`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `case_id` int(16) NULL DEFAULT NULL COMMENT '案例id',
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件资源',
  `creat_time` datetime(6) NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of case_file
-- ----------------------------
INSERT INTO `case_file` VALUES (1, 1000, './image/file.pdf', '2021-06-22 14:45:14.000000');

-- ----------------------------
-- Table structure for case_image
-- ----------------------------
DROP TABLE IF EXISTS `case_image`;
CREATE TABLE `case_image`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `case_id` int(16) NULL DEFAULT NULL COMMENT '案例id',
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片资源',
  `creat_time` datetime(6) NULL DEFAULT CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of case_image
-- ----------------------------
INSERT INTO `case_image` VALUES (1, 1000, './image/img.png', '2021-06-22 15:23:15.094094');

-- ----------------------------
-- Table structure for cases
-- ----------------------------
DROP TABLE IF EXISTS `cases`;
CREATE TABLE `cases`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `case_id` int(16) NOT NULL COMMENT '案例id',
  `case_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '案例名',
  `course_id` int(16) NOT NULL COMMENT '课程id',
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名',
  `case_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '案例描述',
  `creat_time` datetime(6) NULL DEFAULT CURRENT_TIMESTAMP(6),
  `creat_trecher` int(16) NOT NULL,
  `med_history` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `thinking` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `isPublish` int(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cases
-- ----------------------------
INSERT INTO `cases` VALUES (1, 1000, '跳表', 10000, '数据结构', '跳表数据结构', '2021-06-22 14:44:16.000000', 888888888, '数据结构', '链表的一种', 1);

-- ----------------------------
-- Table structure for comment_reply
-- ----------------------------
DROP TABLE IF EXISTS `comment_reply`;
CREATE TABLE `comment_reply`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `case_id` int(16) NOT NULL COMMENT '所属案例id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `from_id` int(16) NOT NULL COMMENT '评论者id',
  `creat_time` datetime(6) NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `from_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论者名称',
  `from_avatar` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '评论者头像',
  `have_reply` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '是否有回复内容',
  `parent_id` int(16) NULL DEFAULT NULL COMMENT '父评论id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment_reply
-- ----------------------------
INSERT INTO `comment_reply` VALUES (1, 1, '< 该评论已被作者删除！>', 222222222, '2021-06-22 14:46:40.101945', 'ewertewr', NULL, 1, NULL);
INSERT INTO `comment_reply` VALUES (2, 1, 'ewfwefwefwf', 333333333, '2021-06-22 14:46:47.025916', 'sdgr', NULL, 0, 1);
INSERT INTO `comment_reply` VALUES (3, 1, 'fasfrggera', 222222222, '2021-06-22 14:46:50.604389', 'sdfsdf', NULL, 1, NULL);
INSERT INTO `comment_reply` VALUES (5, 1, 'sdfsdlfjsdlfsdofjdsoif', 222222222, '2021-06-22 14:46:53.708152', 'yuhang', NULL, 0, 3);
INSERT INTO `comment_reply` VALUES (6, 1, 'sdfsdlfjsdlfsdofjdsoif', 333333333, '2021-06-22 14:47:12.402283', 'yuhang', NULL, 0, 3);

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
  `course_state` int(1) NULL DEFAULT NULL COMMENT '课程状态',
  `creat_time` datetime(6) NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 10000, '数据结构', 888888888, '就是数据结构', 1, '2021-06-23 10:29:17.386988');
INSERT INTO `course` VALUES (2, 10001, '操作系统', 888888888, '操作系统呀', 1, '2021-06-23 10:29:24.125607');
INSERT INTO `course` VALUES (3, 10002, '计算机网络', 888888888, '计网呀', 0, '2021-06-23 10:29:30.769428');

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
  `creat_time` datetime(6) NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_student
-- ----------------------------
INSERT INTO `course_student` VALUES (1, 10000, '数据结构', 222222222, '林同学', 1, '2021-06-22 14:38:50.000000');
INSERT INTO `course_student` VALUES (2, 10000, '数据结构', 333333333, '陈同学', 1, '2021-06-22 14:39:29.000000');
INSERT INTO `course_student` VALUES (3, 10000, '数据结构', 100000000, 'H同学', 2, '2021-06-22 15:15:06.660025');
INSERT INTO `course_student` VALUES (4, 10000, '数据结构', 666666666, '没有组同学', -1, '2021-06-22 14:49:06.000000');

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
  `creat_time` datetime(6) NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_teacher
-- ----------------------------
INSERT INTO `course_teacher` VALUES (1, 10000, '数据结构', 888888888, 'admin', 1, '2021-06-23 10:28:56.151774');

-- ----------------------------
-- Table structure for dictionary_detail
-- ----------------------------
DROP TABLE IF EXISTS `dictionary_detail`;
CREATE TABLE `dictionary_detail`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `type_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典编码如sex',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典具体名字如男，女',
  `value` int(16) NULL DEFAULT NULL COMMENT '字典值数字如1，2',
  `creat_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `is_default` int(1) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dictionary_detail
-- ----------------------------
INSERT INTO `dictionary_detail` VALUES (1, 'sex', '男', 1, '2021-06-22 15:25:09', 1);
INSERT INTO `dictionary_detail` VALUES (2, 'sex', '女', 0, '2021-06-22 15:26:06', 0);

-- ----------------------------
-- Table structure for dictionary_type
-- ----------------------------
DROP TABLE IF EXISTS `dictionary_type`;
CREATE TABLE `dictionary_type`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `type_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典编码如sex',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典名称如性别',
  `creat_time` datetime(6) NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dictionary_type
-- ----------------------------
INSERT INTO `dictionary_type` VALUES (1, 'sex', '性别', '2021-06-22 15:23:48.517211');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `role_id` int(9) NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `perms` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, 0, 'admin', '/login,/home');
INSERT INTO `roles` VALUES (2, 1, 'teacher', '/login');
INSERT INTO `roles` VALUES (3, 2, 'student', '123');
INSERT INTO `roles` VALUES (4, 9, 'sdsdsdfsfsf', 'sdfsdf,dsf');

-- ----------------------------
-- Table structure for student_file
-- ----------------------------
DROP TABLE IF EXISTS `student_file`;
CREATE TABLE `student_file`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `case_id` int(16) NOT NULL,
  `student_id` int(16) NOT NULL,
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `upload_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `file_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_file
-- ----------------------------
INSERT INTO `student_file` VALUES (1, 10000, 222222222, './helloword/hello.png', '2021-06-22 14:42:13', 'png');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `user_id` int(16) NOT NULL COMMENT '用户id',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `role_id` int(16) NULL DEFAULT NULL COMMENT '角色id',
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门',
  `grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年级',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称',
  `creat_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真名',
  `avatar` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '头像',
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (4, 'lyh', '12345678', 333333333, '1', NULL, NULL, 1, '1', '1', 'xxxx', '2021-06-20 09:30:13', NULL, NULL);
INSERT INTO `users` VALUES (5, 'requester', '123456', 777777777, '1', '3242353', '3453@qq.com', 1, '1', '1', 'sd', '2021-06-22 14:49:36', NULL, NULL);
INSERT INTO `users` VALUES (11, 'admin', '123456', 888888888, '1', '1', 'sdfasfasdf', 0, '1', '1', '管理员', '2021-06-22 15:11:18', '林同学', 'data:image/jpeg;base64,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');
INSERT INTO `users` VALUES (12, 'chen', '123456', 222222222, '1', '1', NULL, 2, '1', '1', '学生', '2021-06-22 15:12:12', '陈同学', NULL);
INSERT INTO `users` VALUES (14, 'hello', '123456', 100000000, '1', '1', '1', 2, '1', '1', '班长', '2021-06-22 15:14:46', 'H同学', NULL);
INSERT INTO `users` VALUES (15, 'meiyouzu', '123456', 666666666, '1', '1', '1', 2, '1', '1', '1', '2021-06-22 15:15:46', '没有组同学', NULL);
INSERT INTO `users` VALUES (16, 'shenqinzhe', '123456', 999999999, '1', '1', '1', 2, '1', '1', '1', '2021-06-22 15:21:09', '申请者', NULL);

SET FOREIGN_KEY_CHECKS = 1;
