/*
Navicat MySQL Data Transfer

Source Server         : haojianxiang
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : practise

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-04-28 18:16:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `platform_contact4`
-- ----------------------------
DROP TABLE IF EXISTS `platform_contact4`;
CREATE TABLE `platform_contact4` (
  `taskId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `websiteid` varchar(255) DEFAULT NULL,
  `person` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `mobilephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `product` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `country_region` varchar(255) DEFAULT NULL,
  `province_state` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `operational_address` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `website_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of platform_contact4
-- ----------------------------
