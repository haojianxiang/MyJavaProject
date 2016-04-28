/*
Navicat MySQL Data Transfer

Source Server         : haojianxiang
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : practise

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-04-28 18:38:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `platform_contact`
-- ----------------------------
DROP TABLE IF EXISTS `platform_contact`;
CREATE TABLE `platform_contact` (
  `taskId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `person` varchar(255) DEFAULT NULL,
  `mobilephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of platform_contact
-- ----------------------------
INSERT INTO `platform_contact` VALUES ('00000000135', '陈小姐', '13590293055', '1458483582@qq.com', 'http://33702.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000136', '网销部', '', 'wuhuisong@126.com', 'http://12043.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000137', '傅辉', '13971566353', 'rick_fu@yahoo.cn', 'http://28397.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000138', '廖生', '', 'ldq9980@126.com', 'http://30619.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000139', '销售部', '18602381119', '77364165@qq.com', 'http://50291.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000140', '销售部', '', 'info@3r.com.cn', 'http://11839.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000141', '杨亚林', '', 'qhongmei163@163.com', 'http://42546.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000142', '陈小姐', '13590293055', '1458483582@qq.com', 'http://33702.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000143', '傅辉', '13971566353', 'rick_fu@yahoo.cn', 'http://28397.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000144', '网销部', '', 'wuhuisong@126.com', 'http://12043.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000145', '廖生', '', 'ldq9980@126.com', 'http://30619.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000146', '销售部', '', 'info@3r.com.cn', 'http://11839.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000147', '杨亚林', '', 'qhongmei163@163.com', 'http://42546.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000148', '销售部', '18602381119', '77364165@qq.com', 'http://50291.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000149', '李新艳', '18966714195', 'hyq216@163.com', 'http://35650.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000150', '小冯', '13611067210', '179312623@qq.com', 'http://26246.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000151', '陈茹 陈燕', '', 'songben.chan@163.com', 'http://31383.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000152', '赵倩倩', '', 'sywzhaoqianqian@sina.com', 'http://32937.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000153', '王经理', '13770337786', '1196151383@qq.com', 'http://16122.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000154', '苗树军', '18611626887', '13699146755@126.com', 'http://41884.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000155', '陈晶(1) 孙晓媛(2) 商务江薇(3)', '', '7991818@qq.com', 'http://29768.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000156', '', '', 'xiny2003@263.net', 'http://55730.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000157', '贾小姐 QQ：2982335092', '13296126456', 'admin@crmclick.com', 'http://116735.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000158', '王军强', '18310530881', 'wf109@163.com', 'http://13908.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000159', '陈经理', '13971695544', 'whhtsm@163.com', 'http://112987.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000160', '姚小姐', '', '79345597@qq.com', 'http://35806.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000161', '辛星', '13520896223', '', 'http://98247.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000162', '销售部', '', 'yangrui@tianzhekeji.com', 'http://56241.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000163', '罗雪', '', 'luoxue19862000@yahoo.com.cn', 'http://20249.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000164', '李加祥 洪伟', '13681618106', '', 'http://128267.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000165', '孙乾坤', '13810790371', 'hanzhi512@sian.com', 'http://41882.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000166', '林先生', '13061679268', 'seagulee@hotmail.com', 'http://27449.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000167', '王先生，陈先生', '13916934998', 'hill.guo@inovare.com.cn', 'http://126601.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000168', '罗海 13638617367', '', '250036590@QQ.COM', 'http://38422.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000169', '刘先生', '', 'jobmanagement@qq.com', 'http://26687.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000170', '孟小姐', '18930100007', 'valiant@shareoa.com', 'http://56150.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000171', '销售部', '13141388735', 'dongweishiji@126.com', 'http://12216.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000172', '销售部', '13717897009', 'bjlht010@163.com', 'http://23281.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000173', '李经理', '15510551005', '', 'http://128382.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000174', '辛星', '13520896223', '36014471@qq.com', 'http://98898.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000175', '张梅', '', '2880323889@qq.com', 'http://97593.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000176', '朱栋阳', '15873163440', '', 'http://126260.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000177', '朱栋阳', '15873163440', '', 'http://126260.shop.pcpop.com/IncInfo.html');
INSERT INTO `platform_contact` VALUES ('00000000178', '朱栋阳', '15873163440', '', 'http://126260.shop.pcpop.com/Comment.html');
INSERT INTO `platform_contact` VALUES ('00000000179', '朱栋阳', '15873163440', '', 'http://126260.shop.pcpop.com/ML_26_0_VD_1_H.html');
INSERT INTO `platform_contact` VALUES ('00000000180', '亚赛手机客服', '', '360033076@QQ.com', 'http://68803.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000181', '刘经理', '13903794439', '909678899@qq.com', 'http://124661.shop.pcpop.com/IncInfo.html');
INSERT INTO `platform_contact` VALUES ('00000000182', '刘经理', '13903794439', '909678899@qq.com', 'http://124661.shop.pcpop.com/Comment.html');
INSERT INTO `platform_contact` VALUES ('00000000183', '销售部', '15810439117', '127073562@qq.com', 'http://24656.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000184', '刘经理', '13903794439', '909678899@qq.com', 'http://124661.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000185', '刘经理', '13903794439', '909678899@qq.com', 'http://124661.shop.pcpop.com/ML_36_98_VD_1_H.html');
INSERT INTO `platform_contact` VALUES ('00000000186', '王经理', '13466818492', '', 'http://124743.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000187', '苏经理', '18831198456', '18831198456@163.com', 'http://107348.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000188', '吴华', '13981774477', 'cdddtxkj@163.com', 'http://37122.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000189', '石晓晨', '18012982398', '617603845@qq.com', 'http://119476.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000190', '范谦', '13473355355', '402264914@qq.com', 'http://126890.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000191', '陈小燕', '', '565278728@qq.com', 'http://43702.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000192', '陈涛', '13880033770', '706307854@qq.com', 'http://37334.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000193', '蒋经理', '15256578330', '', 'http://125371.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000194', '张经理', '', '1809886000@qq.com', 'http://120508.shop.pcpop.com/');
INSERT INTO `platform_contact` VALUES ('00000000195', '赵先生', '13658809498', 'kmtsxsm@126.com', 'http://100301.shop.pcpop.com/');
