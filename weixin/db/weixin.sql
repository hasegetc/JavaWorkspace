/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : weixin

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2013-04-19 13:19:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `weixin_auto_reply`
-- ----------------------------
DROP TABLE IF EXISTS `weixin_auto_reply`;
CREATE TABLE `weixin_auto_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reply_key` varchar(20) NOT NULL,
  `content` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_auto_reply
-- ----------------------------
INSERT INTO `weixin_auto_reply` VALUES ('1', 'subscribe', '欢迎订阅香香美食,想查询美食吗，直接回复我就可以了。\\n 1.回复时间 20130327 即可查询当天推荐的美食。');
INSERT INTO `weixin_auto_reply` VALUES ('2', 'help', '帮助');
INSERT INTO `weixin_auto_reply` VALUES ('3', '你好', '你也好');

-- ----------------------------
-- Table structure for `weixin_msg`
-- ----------------------------
DROP TABLE IF EXISTS `weixin_msg`;
CREATE TABLE `weixin_msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_user_name` varchar(50) NOT NULL,
  `to_user_name` varchar(50) NOT NULL,
  `create_time` varchar(50) NOT NULL,
  `msg_type` varchar(20) NOT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `location_x` varchar(20) DEFAULT NULL,
  `location_y` varchar(20) DEFAULT NULL,
  `scale` varchar(10) DEFAULT NULL,
  `pic_url` varchar(100) DEFAULT NULL,
  `event` varchar(10) DEFAULT NULL,
  `event_key` varchar(10) DEFAULT NULL,
  `isReply` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_msg
-- ----------------------------
