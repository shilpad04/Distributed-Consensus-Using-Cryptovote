/*
MySQL Data Transfer
Source Host: localhost
Source Database: crypto_vote
Target Host: localhost
Target Database: crypto_vote
Date: 31-03-2021 16:40:19
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `user_id` varchar(40) NOT NULL,
  `password` varchar(20) NOT NULL,
  `utype` varchar(20) NOT NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` int(5) NOT NULL auto_increment,
  `qus` varchar(100) NOT NULL,
  `post_date` varchar(40) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for user_post
-- ----------------------------
DROP TABLE IF EXISTS `user_post`;
CREATE TABLE `user_post` (
  `userid` varchar(40) default NULL,
  `id` int(5) default NULL,
  `ans` varchar(100) default NULL,
  `post_date` varchar(40) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for user_registration
-- ----------------------------
DROP TABLE IF EXISTS `user_registration`;
CREATE TABLE `user_registration` (
  `name` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `mobile` varchar(11) NOT NULL,
  PRIMARY KEY  (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `login` VALUES ('admin@gmail.com', '123456', 'admin');
INSERT INTO `login` VALUES ('priyankauh3@gmail.com', '123456', 'user');
INSERT INTO `login` VALUES ('sam@gmail.com', '123456', 'user');
INSERT INTO `login` VALUES ('sumit.ansp07@gmail.com', '123456', 'user');
INSERT INTO `post` VALUES ('1', 'q1', '26-03-2021', 'on');
INSERT INTO `post` VALUES ('2', 'sadfdsfdsfdsf', '31-03-2021', 'on');
INSERT INTO `user_post` VALUES ('sam@gmail.com', '1', 'op1', '27-03-2021');
INSERT INTO `user_post` VALUES ('priyankauh3@gmail.com', '1', 'Yes', '31-03-2021');
INSERT INTO `user_post` VALUES ('priyankauh3@gmail.com', '2', 'No', '31-03-2021');
INSERT INTO `user_registration` VALUES ('Priyanka', 'priyankauh3@gmail.com', '9019070970');
INSERT INTO `user_registration` VALUES ('sam', 'sam@gmail.com', '1122334422');
INSERT INTO `user_registration` VALUES ('sumit', 'sumit.ansp07@gmail.com', '07892044926');
