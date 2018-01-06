/*
Navicat MySQL Data Transfer

Source Server         : loveting.link_3306
Source Server Version : 50556
Source Host           : loveting.link:3306
Source Database       : eforum

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-01-05 17:54:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `last_update_user_id` bigint(20) DEFAULT NULL,
  `create_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_update_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6o7dhbednte91n1lkgql4em3d` (`create_user_id`),
  KEY `FKmpjvrttlegxir27w44qt7yqn7` (`last_update_user_id`),
  CONSTRAINT `FK6o7dhbednte91n1lkgql4em3d` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKmpjvrttlegxir27w44qt7yqn7` FOREIGN KEY (`last_update_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longtext COLLATE utf8_bin,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `last_update_user_id` bigint(20) DEFAULT NULL,
  `last_update_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `is_essence` bit(1) DEFAULT NULL,
  `last_update_time_for_all` datetime DEFAULT NULL,
  `top_grade` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbc2qerk3l47javnl2yvn51uoi` (`user_id`),
  KEY `FK4hjje83gc1niba7unn4wi56bi` (`create_user_id`),
  KEY `FK4x9nh4sycceeuxdfuqb5om5i6` (`last_update_user_id`),
  CONSTRAINT `FKbc2qerk3l47javnl2yvn51uoi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;


-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `file_extension_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `file_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `file_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `original_file_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `last_update_user_id` bigint(20) DEFAULT NULL,
  `create_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_update_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3655daeqf2lss2dvj2bevlnbk` (`create_user_id`),
  KEY `FKq1ntcrw0h0tqsexvkpkgqmjh5` (`last_update_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for global_param
-- ----------------------------
DROP TABLE IF EXISTS `global_param`;
CREATE TABLE `global_param` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `g_description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `g_key` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `g_value` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `last_update_user_id` bigint(20) DEFAULT NULL,
  `create_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_update_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1s6bqj1iesicqa6xba3sc4rh3` (`g_key`),
  KEY `FKg1vjq4f4l9f9l337vwc4sp735` (`create_user_id`),
  KEY `FKfsmq028cbto9t2iqwp6ah61u7` (`last_update_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of global_param
-- ----------------------------
INSERT INTO `global_param` VALUES ('1', null, null, null, 'IMAGE_DIR', '/appData/eforum/images', null, null, null, null);
INSERT INTO `global_param` VALUES ('2', null, null, null, 'HEAD_PORTRAIT_DIR', '/appData/eforum/headPortrait', null, null, null, null);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `create_user_name` varchar(255) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `last_update_user_id` bigint(20) DEFAULT NULL,
  `last_update_user_name` varchar(255) DEFAULT NULL,
  `be_checked` bit(1) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `from_user_id` bigint(20) DEFAULT NULL,
  `to_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3nju8asf4v72h0d7g6vgtx7p2` (`from_user_id`),
  KEY `FKgm8awic1hpa2cgr7pv7j8yn0` (`to_user_id`),
  CONSTRAINT `FK3nju8asf4v72h0d7g6vgtx7p2` FOREIGN KEY (`from_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKgm8awic1hpa2cgr7pv7j8yn0` FOREIGN KEY (`to_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `content` longtext COLLATE utf8_bin,
  `create_user_id` bigint(20) DEFAULT NULL,
  `last_update_user_id` bigint(20) DEFAULT NULL,
  `article_id` bigint(20) DEFAULT NULL,
  `create_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_update_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8kg8pwp0amq3u10l8rkuonj35` (`create_user_id`),
  KEY `FKdut92sn6p37qwhn4tur31ln5a` (`last_update_user_id`),
  KEY `FK77b1kppqpd0yxs2wl8u8fddma` (`article_id`),
  CONSTRAINT `FK77b1kppqpd0yxs2wl8u8fddma` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;


-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `create_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `last_update_user_id` bigint(20) DEFAULT NULL,
  `last_update_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of resource
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `create_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `last_update_user_id` bigint(20) DEFAULT NULL,
  `last_update_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `role_resource_relation`;
CREATE TABLE `role_resource_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `create_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `last_update_user_id` bigint(20) DEFAULT NULL,
  `last_update_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7fl6n14fhyai191nabpskqfr3` (`resource_id`),
  KEY `FKsruxp5ot37wm07b448m3rsnkn` (`role_id`),
  CONSTRAINT `FK7fl6n14fhyai191nabpskqfr3` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`),
  CONSTRAINT `FKsruxp5ot37wm07b448m3rsnkn` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of role_resource_relation
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `last_update_user_id` bigint(20) DEFAULT NULL,
  `create_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_update_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `head_portrait_file_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `mobile_number` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `personalized_signature` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `real_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `be_shutup` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn1eo3hcou22xdhm9vmll8oivg` (`create_user_id`),
  KEY `FKqdcdhuvbdp4a95f0bxvnugclw` (`last_update_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_role_relation`;
CREATE TABLE `user_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `create_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `last_update_user_id` bigint(20) DEFAULT NULL,
  `last_update_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa08l2fcc2ddfee3m849an7sd3` (`role_id`),
  KEY `FKo7bl1miqkg694yg5jlehsnn24` (`user_id`),
  CONSTRAINT `FKa08l2fcc2ddfee3m849an7sd3` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKo7bl1miqkg694yg5jlehsnn24` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user_role_relation
-- ----------------------------
