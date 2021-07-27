/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80022
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 80022
File Encoding         : 65001

Date: 2021-07-27 16:06:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menuid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `details` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`menuid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('2', '炒河粉', '著名的南方小吃', '15.00');
INSERT INTO `menu` VALUES ('3', '过桥米线', '米线', '18.00');
INSERT INTO `menu` VALUES ('5', '黑暗料理', '黑暗', '10.00');
INSERT INTO `menu` VALUES ('6', '回锅肉', '传统小吃', '10.00');
INSERT INTO `menu` VALUES ('7', '辣椒炖肉', '辣椒', '18.00');
INSERT INTO `menu` VALUES ('8', '波兰水饺', '肉蛋菜', '25.00');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `orderid` int NOT NULL,
  `menuid` int DEFAULT NULL,
  `random` int DEFAULT NULL,
  KEY `menuid` (`menuid`),
  KEY `orderid` (`orderid`),
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`menuid`) REFERENCES `menu` (`menuid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `orderitem_ibfk_3` FOREIGN KEY (`orderid`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('9', '2', '343');
INSERT INTO `orderitem` VALUES ('9', '3', '43');
INSERT INTO `orderitem` VALUES ('9', '5', '4354');
INSERT INTO `orderitem` VALUES ('10', null, '54');
INSERT INTO `orderitem` VALUES ('10', '3', '235');
INSERT INTO `orderitem` VALUES ('10', '5', '98');
INSERT INTO `orderitem` VALUES ('10', '6', '65876');
INSERT INTO `orderitem` VALUES ('11', '2', '9');
INSERT INTO `orderitem` VALUES ('11', '3', '986');
INSERT INTO `orderitem` VALUES ('11', '5', '789');
INSERT INTO `orderitem` VALUES ('12', '3', '4689');
INSERT INTO `orderitem` VALUES ('12', '3', '9759');
INSERT INTO `orderitem` VALUES ('12', '6', '5657');
INSERT INTO `orderitem` VALUES ('14', '7', '5');
INSERT INTO `orderitem` VALUES ('14', '7', '78');
INSERT INTO `orderitem` VALUES ('14', '7', '0');
INSERT INTO `orderitem` VALUES ('15', '2', '29682');
INSERT INTO `orderitem` VALUES ('15', '2', '55577');
INSERT INTO `orderitem` VALUES ('16', '8', '9295');
INSERT INTO `orderitem` VALUES ('16', '7', '47573');
INSERT INTO `orderitem` VALUES ('16', '6', '72208');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `cook` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `isdone` int DEFAULT '0',
  `total` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customer` (`customer`) USING BTREE,
  KEY `cook` (`cook`) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer`) REFERENCES `user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`cook`) REFERENCES `user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('9', 'admin', 'test1', '1', '43.00');
INSERT INTO `orders` VALUES ('10', 'admin', '李师傅', '1', '48.00');
INSERT INTO `orders` VALUES ('11', 'admin', null, '1', '43.00');
INSERT INTO `orders` VALUES ('12', 'admin', null, '1', '46.00');
INSERT INTO `orders` VALUES ('14', 'admin', null, '0', '54.00');
INSERT INTO `orders` VALUES ('15', 'admin', null, '0', '30.00');
INSERT INTO `orders` VALUES ('16', 'admin', null, '0', '53.00');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `rid` int NOT NULL,
  `active` int NOT NULL DEFAULT '1',
  `phone` varchar(30) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', '123', '1', '1', '13134325356', '32432@gmai.com');
INSERT INTO `user` VALUES ('test1', '123', '2', '1', '15724561235', 'r3r324@qq.com');
INSERT INTO `user` VALUES ('test2', '123', '3', '1', '14564366663', '43243@163.com');
INSERT INTO `user` VALUES ('刘师傅', '123', '2', '1', '19843563256', '34354sfs@qq.com');
INSERT INTO `user` VALUES ('李师傅', '123', '2', '1', '18645625633', '423rwf@gmail.com');
