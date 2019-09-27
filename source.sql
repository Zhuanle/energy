/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : source

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-09-27 09:16:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '清洁能源');
INSERT INTO `category` VALUES ('2', '不可再生能源');

-- ----------------------------
-- Table structure for energy
-- ----------------------------
DROP TABLE IF EXISTS `energy`;
CREATE TABLE `energy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `updatetime` datetime DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  CONSTRAINT `cid` FOREIGN KEY (`cid`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of energy
-- ----------------------------
INSERT INTO `energy` VALUES ('1', '水', '2019-09-24 10:17:32', '700', '2');
INSERT INTO `energy` VALUES ('2', '电', '2019-09-26 10:06:44', '4522', '1');
INSERT INTO `energy` VALUES ('3', '石油', '2019-09-20 10:07:50', '256', '2');
INSERT INTO `energy` VALUES ('4', '天然气', '2019-09-13 10:08:20', '7524', '2');
INSERT INTO `energy` VALUES ('5', '煤炭', '2019-09-17 10:08:41', '1250', '1');
INSERT INTO `energy` VALUES ('6', '风', '2019-09-20 10:09:17', '456', '2');
INSERT INTO `energy` VALUES ('7', '地热能', '2019-09-21 10:13:14', '2506', '2');
INSERT INTO `energy` VALUES ('8', '潮汐能', '2019-09-26 15:54:12', '20425', '1');

-- ----------------------------
-- Table structure for feed
-- ----------------------------
DROP TABLE IF EXISTS `feed`;
CREATE TABLE `feed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT '0' COMMENT '0    购买    1    消耗',
  `createTime` datetime NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `eid` (`eid`),
  KEY `pid` (`pid`),
  CONSTRAINT `feed_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feed
-- ----------------------------
INSERT INTO `feed` VALUES ('1', '2', null, '1', '2019-09-24 19:14:04', '-17');
INSERT INTO `feed` VALUES ('2', '3', null, '0', '2019-09-24 19:44:58', '1456');
INSERT INTO `feed` VALUES ('3', '8', null, '0', '2019-09-26 15:43:07', '9098');
INSERT INTO `feed` VALUES ('4', '8', null, '0', '2019-09-26 15:43:14', '90');
INSERT INTO `feed` VALUES ('5', '8', null, '0', '2019-09-26 15:48:03', '7777');
INSERT INTO `feed` VALUES ('6', '8', null, '1', '2019-09-26 15:48:25', '-88');
INSERT INTO `feed` VALUES ('7', '8', null, '1', '2019-09-26 15:48:55', '-9');
INSERT INTO `feed` VALUES ('8', '8', null, '1', '2019-09-26 15:49:38', '-1');
INSERT INTO `feed` VALUES ('9', '8', null, '1', '2019-09-26 15:49:59', '-5');
INSERT INTO `feed` VALUES ('10', '8', null, '1', '2019-09-26 15:50:44', '-1');
INSERT INTO `feed` VALUES ('11', '8', null, '1', '2019-09-26 15:52:14', '-1');
INSERT INTO `feed` VALUES ('12', '8', null, '0', '2019-09-26 15:54:12', '1');

-- ----------------------------
-- Table structure for jiancha
-- ----------------------------
DROP TABLE IF EXISTS `jiancha`;
CREATE TABLE `jiancha` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) NOT NULL,
  `createTime` datetime NOT NULL,
  `percent` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `eid` (`eid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jiancha
-- ----------------------------
INSERT INTO `jiancha` VALUES ('6', '1', '2019-09-25 11:41:22', '66');
INSERT INTO `jiancha` VALUES ('7', '1', '2019-09-25 11:41:49', '99');
INSERT INTO `jiancha` VALUES ('10', '1', '2019-09-25 11:43:19', '99');
INSERT INTO `jiancha` VALUES ('11', '2', '2019-09-26 16:10:15', '99');
INSERT INTO `jiancha` VALUES ('12', '3', '2019-09-26 16:18:48', '84');
INSERT INTO `jiancha` VALUES ('13', '5', '2019-09-26 16:18:54', '66');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '商品', '/product');
INSERT INTO `permission` VALUES ('2', '能源', '/energy');
INSERT INTO `permission` VALUES ('3', '用户', '/user');
INSERT INTO `permission` VALUES ('4', '质量检查', '/check');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `cid` int(11) NOT NULL,
  `action` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员');
INSERT INTO `role` VALUES ('2', '销售部经理');
INSERT INTO `role` VALUES ('3', '质检部经理');
INSERT INTO `role` VALUES ('4', '采购部经理');
INSERT INTO `role` VALUES ('6', '销售部');
INSERT INTO `role` VALUES ('7', '质检部');
INSERT INTO `role` VALUES ('8', '采购部');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '1');
INSERT INTO `role_permission` VALUES ('2', '2', '1');
INSERT INTO `role_permission` VALUES ('3', '3', '1');
INSERT INTO `role_permission` VALUES ('4', '4', '1');
INSERT INTO `role_permission` VALUES ('5', '1', '2');
INSERT INTO `role_permission` VALUES ('6', '3', '2');
INSERT INTO `role_permission` VALUES ('7', '3', '3');
INSERT INTO `role_permission` VALUES ('8', '4', '3');
INSERT INTO `role_permission` VALUES ('9', '1', '4');
INSERT INTO `role_permission` VALUES ('10', '3', '4');
INSERT INTO `role_permission` VALUES ('11', '1', '6');
INSERT INTO `role_permission` VALUES ('12', '4', '7');
INSERT INTO `role_permission` VALUES ('13', '2', '8');
INSERT INTO `role_permission` VALUES ('14', '1', '10');
INSERT INTO `role_permission` VALUES ('15', '2', '10');
INSERT INTO `role_permission` VALUES ('16', '3', '10');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `crateTime` datetime NOT NULL,
  `phone` varchar(20) NOT NULL,
  `status` tinyint(4) DEFAULT '1',
  `salt` varchar(255) DEFAULT NULL,
  `realName` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `namePassword` (`phone`,`password`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('6', 'zhl', '7371f9534c70235ac30dee69eb9cab20', '2019-09-19 17:12:15', '13259004192', '1', 'Ap90fbBCRRlZf3PDF8HGIQ==', '张欢乐');
INSERT INTO `user` VALUES ('7', 'lzj', 'a33d773f98c910642f6ba370901e6368', '2019-09-20 11:47:23', '14859654785', null, 'Ovca3hxZ4eYF8zRNc1TOaQ==', '李子杰');
INSERT INTO `user` VALUES ('10', 'yh', '40837f8735658e8648498c7c0625994e', '2019-09-23 16:02:17', '12345678923', '1', '4hg26Xr2plWgvFqnKzaBPA==', '杨昊');
INSERT INTO `user` VALUES ('11', 'zsm', '1497f36404c3059a644e28cd192f86bb', '2019-09-23 16:02:48', '12345678923', '1', 'X+NhLIEzq8HsP2593ZNx2w==', '张思勉');
INSERT INTO `user` VALUES ('12', 'sf', '71814c7e223c0c54e6a3a612b03933e2', '2019-09-24 09:56:20', '13259004192', '1', 'S001sHkKJfkP2EA/9kr3yg==', 'sdf');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `rid` (`rid`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '6', '1');
INSERT INTO `user_role` VALUES ('2', '7', '2');
INSERT INTO `user_role` VALUES ('5', '10', '1');
INSERT INTO `user_role` VALUES ('6', '11', '1');
INSERT INTO `user_role` VALUES ('7', '12', '3');
