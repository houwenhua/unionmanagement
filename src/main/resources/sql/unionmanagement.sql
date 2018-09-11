/*
Navicat MySQL Data Transfer

Source Server         : yujing
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : unionmanagement

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-06-02 13:42:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL,
  `end_time` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `remark` text,
  `user_id` int(11) DEFAULT NULL,
  `state` varchar(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnf8p4hfjgitf2sq7xltg92kor` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('1', '2018-05-06', '羽毛球比赛', '要参加的人，请尽快报名,在公告中下载附件', '1', '1');
INSERT INTO `activity` VALUES ('196', '2018-05-05 02:03', '排球比赛', '', '97', '1');
INSERT INTO `activity` VALUES ('195', '2018-10-30 00:00', '趣味运动', '请大家积极参加学校组织的趣味运动', '1', '0');
INSERT INTO `activity` VALUES ('194', '2018-05-31 09:00', '排球比赛', '学院举行排球比赛，请要参加的人员尽快报名！', '95', '0');
INSERT INTO `activity` VALUES ('207', '2018-05-14 02:03', '篮球比赛', '', '95', '0');
INSERT INTO `activity` VALUES ('210', '2018-06-20 09:00', '乒乓球比赛', '数学与计算机学院应学校要求，积极举行工会活动，忘大家踊跃参加', '95', '0');
INSERT INTO `activity` VALUES ('219', '2018-05-31 02:03', '足球比赛', '比赛', '1', '0');

-- ----------------------------
-- Table structure for family_member
-- ----------------------------
DROP TABLE IF EXISTS `family_member`;
CREATE TABLE `family_member` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `policy` varchar(20) NOT NULL,
  `position` varchar(20) DEFAULT NULL,
  `relationship` varchar(20) NOT NULL,
  `unit` varchar(50) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbxltcr568ers5sigeadogc52j` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of family_member
-- ----------------------------
INSERT INTO `family_member` VALUES ('216', '李云龙', '党员', '职员', '夫妻关系', '攀枝花市电力公司', '1');
INSERT INTO `family_member` VALUES ('178', '刘备', '皇亲国戚', '皇帝', '结拜大哥', '蜀国', '103');
INSERT INTO `family_member` VALUES ('179', '关羽', '蜀国大将', '大将军', '结拜二哥', '蜀国', '103');
INSERT INTO `family_member` VALUES ('214', '赵云龙', '党员', '职员', '夫妻关系', '攀枝花市电力公司', '1');

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('223');

-- ----------------------------
-- Table structure for resume
-- ----------------------------
DROP TABLE IF EXISTS `resume`;
CREATE TABLE `resume` (
  `id` int(11) NOT NULL,
  `description` text,
  `enddate` varchar(20) NOT NULL,
  `startdate` varchar(20) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp8rjl6bfbns2y67xsuo1v42xl` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resume
-- ----------------------------
INSERT INTO `resume` VALUES ('125', '四川大学就读', '2011-08-10', '2008-09-01', '1');
INSERT INTO `resume` VALUES ('176', '打酱油', '2018-05-08', '2018-05-01', '103');
INSERT INTO `resume` VALUES ('177', '还在打酱油', '2018-05-23', '2018-05-14', '103');
INSERT INTO `resume` VALUES ('215', '成都联想集团就职', '2015-10-01', '2011-09-01', '1');

-- ----------------------------
-- Table structure for signupactivity
-- ----------------------------
DROP TABLE IF EXISTS `signupactivity`;
CREATE TABLE `signupactivity` (
  `id` int(11) NOT NULL,
  `checkactivity` varchar(1) DEFAULT NULL,
  `signupdate` varchar(50) DEFAULT NULL,
  `activity_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs3wiunc9c3166bif9wj5el6y4` (`activity_id`),
  KEY `FK4jwq3tpyu5xhprnq2w4w9t7y3` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of signupactivity
-- ----------------------------
INSERT INTO `signupactivity` VALUES ('133', '2', '2018-05-01 15:19:44', '195', '103');
INSERT INTO `signupactivity` VALUES ('156', '2', '2018-05-02 09:24:49', '1', '127');
INSERT INTO `signupactivity` VALUES ('158', '2', '2018-05-02 09:58:56', '1', '157');
INSERT INTO `signupactivity` VALUES ('203', '0', '2018-05-06 17:17:18', '1', '103');
INSERT INTO `signupactivity` VALUES ('220', '0', '2018-05-30 10:11:25', '219', '218');

-- ----------------------------
-- Table structure for statistics_activity
-- ----------------------------
DROP TABLE IF EXISTS `statistics_activity`;
CREATE TABLE `statistics_activity` (
  `id` int(11) NOT NULL,
  `actionname` varchar(255) NOT NULL,
  `institute` varchar(255) NOT NULL,
  `number` varchar(255) NOT NULL,
  `statisticsname` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of statistics_activity
-- ----------------------------
INSERT INTO `statistics_activity` VALUES ('163', '羽毛球比赛', '数学与计算机学院', '10', '数学与计算机学院');
INSERT INTO `statistics_activity` VALUES ('174', '羽毛球比赛', '艺术学院', '1', '艺术学院');

-- ----------------------------
-- Table structure for tree_jurisdiction
-- ----------------------------
DROP TABLE IF EXISTS `tree_jurisdiction`;
CREATE TABLE `tree_jurisdiction` (
  `tree_id` int(11) NOT NULL,
  `jurisdiction_id` int(11) NOT NULL,
  PRIMARY KEY (`tree_id`,`jurisdiction_id`),
  KEY `FK6t1ahhae1t1a2i3s00m1ho8lo` (`jurisdiction_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tree_jurisdiction
-- ----------------------------
INSERT INTO `tree_jurisdiction` VALUES ('1', '1');
INSERT INTO `tree_jurisdiction` VALUES ('2', '1');
INSERT INTO `tree_jurisdiction` VALUES ('3', '1');
INSERT INTO `tree_jurisdiction` VALUES ('3', '2');
INSERT INTO `tree_jurisdiction` VALUES ('3', '3');
INSERT INTO `tree_jurisdiction` VALUES ('4', '1');
INSERT INTO `tree_jurisdiction` VALUES ('4', '2');
INSERT INTO `tree_jurisdiction` VALUES ('4', '3');
INSERT INTO `tree_jurisdiction` VALUES ('5', '1');
INSERT INTO `tree_jurisdiction` VALUES ('5', '2');
INSERT INTO `tree_jurisdiction` VALUES ('5', '3');
INSERT INTO `tree_jurisdiction` VALUES ('6', '1');
INSERT INTO `tree_jurisdiction` VALUES ('6', '2');
INSERT INTO `tree_jurisdiction` VALUES ('6', '3');
INSERT INTO `tree_jurisdiction` VALUES ('7', '1');
INSERT INTO `tree_jurisdiction` VALUES ('7', '2');
INSERT INTO `tree_jurisdiction` VALUES ('7', '3');
INSERT INTO `tree_jurisdiction` VALUES ('8', '1');
INSERT INTO `tree_jurisdiction` VALUES ('8', '2');
INSERT INTO `tree_jurisdiction` VALUES ('9', '1');
INSERT INTO `tree_jurisdiction` VALUES ('9', '2');
INSERT INTO `tree_jurisdiction` VALUES ('9', '3');
INSERT INTO `tree_jurisdiction` VALUES ('10', '1');
INSERT INTO `tree_jurisdiction` VALUES ('10', '2');
INSERT INTO `tree_jurisdiction` VALUES ('10', '3');
INSERT INTO `tree_jurisdiction` VALUES ('11', '1');
INSERT INTO `tree_jurisdiction` VALUES ('11', '2');
INSERT INTO `tree_jurisdiction` VALUES ('12', '3');
INSERT INTO `tree_jurisdiction` VALUES ('13', '2');
INSERT INTO `tree_jurisdiction` VALUES ('14', '2');
INSERT INTO `tree_jurisdiction` VALUES ('15', '1');
INSERT INTO `tree_jurisdiction` VALUES ('16', '1');
INSERT INTO `tree_jurisdiction` VALUES ('16', '2');
INSERT INTO `tree_jurisdiction` VALUES ('16', '3');

-- ----------------------------
-- Table structure for t_attachment
-- ----------------------------
DROP TABLE IF EXISTS `t_attachment`;
CREATE TABLE `t_attachment` (
  `id` int(11) NOT NULL,
  `originallyname` varchar(255) NOT NULL,
  `servername` varchar(255) NOT NULL,
  `notice_id` int(11) DEFAULT NULL,
  `signup_id` int(11) DEFAULT NULL,
  `statistics_activity_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfkyk6r0w55qbd1gqe7k2kdiwp` (`notice_id`),
  KEY `FK6b661cxag56lndd08e2gus203` (`signup_id`),
  KEY `FKtbjfqm42mymj5254c27ncff3f` (`statistics_activity_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_attachment
-- ----------------------------
INSERT INTO `t_attachment` VALUES ('108', '余静.docx', '4fb0f038-8ed4-461f-8d48-672f9f2333ca余静.docx', '105', null, null);
INSERT INTO `t_attachment` VALUES ('113', '关于举办第十届教职工排球锦标赛的通知.doc', '233cdefb-f3a7-4b11-b891-436752471f4b关于举办第十届教职工排球锦标赛的通知.doc', '112', null, null);
INSERT INTO `t_attachment` VALUES ('115', '第十届教工羽毛球比赛秩序册.pdf', '43a87657-fb90-4323-8579-1f2bac2eee5f第十届教工羽毛球比赛秩序册.pdf', '114', null, null);
INSERT INTO `t_attachment` VALUES ('118', '攀枝花学院2017年度“文明职工”“文明家庭”评选结果.pdf', '105f9425-dcc0-4d78-952c-25506a50e0e7攀枝花学院2017年度“文明职工”“文明家庭”评选结果.pdf', '117', null, null);
INSERT INTO `t_attachment` VALUES ('122', '攀枝花学院第十届教职工排球锦标赛秩序册.doc', 'd0b2091c-6620-43cd-a45f-21f5fcde637e攀枝花学院第十届教职工排球锦标赛秩序册.doc', '121', null, null);
INSERT INTO `t_attachment` VALUES ('139', '第十届教工羽毛球比赛秩序册.pdf', '8d920661-00e0-43c8-b1e0-840f17801c7c第十届教工羽毛球比赛秩序册.pdf', null, '133', null);
INSERT INTO `t_attachment` VALUES ('154', '第十届教工羽毛球比赛秩序册.pdf', '91c0bd75-ffc0-4eb7-9253-1bdaf14556fa第十届教工羽毛球比赛秩序册.pdf', null, '153', null);
INSERT INTO `t_attachment` VALUES ('159', '关于举办第十届教职工排球锦标赛的通知.doc', 'c370738f-603e-40f0-9c55-bf3458b1bcaa关于举办第十届教职工排球锦标赛的通知.doc', null, '158', null);
INSERT INTO `t_attachment` VALUES ('175', '工会资料.docx', '115bae72-eef8-45f9-bd8f-c11af217a815工会资料.docx', null, null, '174');
INSERT INTO `t_attachment` VALUES ('197', '羽毛球比赛统计.docx', '81dcce9c-e7b6-4cf8-b123-22b3d149a054羽毛球比赛统计.docx', null, null, '163');
INSERT INTO `t_attachment` VALUES ('201', '攀枝花学院2017年度“文明职工”“文明家庭”评选结果.pdf', '7b9f7218-749c-4662-9dd0-b16a71b99291攀枝花学院2017年度“文明职工”“文明家庭”评选结果.pdf', '198', null, null);
INSERT INTO `t_attachment` VALUES ('200', '攀枝花学院2017年度“文明职工”“文明家庭”评选结果.pdf', '8c700851-6422-4185-96fa-b051a6db3f86攀枝花学院2017年度“文明职工”“文明家庭”评选结果.pdf', '198', null, null);
INSERT INTO `t_attachment` VALUES ('202', '攀枝花学院关于开展2018年“三八”妇女节活动的通知.pdf', '95bb132a-8f34-4b9d-a2c2-530fb4c9c7de攀枝花学院关于开展2018年“三八”妇女节活动的通知.pdf', '123', null, null);
INSERT INTO `t_attachment` VALUES ('209', '东区邮政车险团购会.pdf', 'bff927de-a485-4e4c-a914-93eed3af9f32东区邮政车险团购会.pdf', '208', null, null);
INSERT INTO `t_attachment` VALUES ('222', '开题答辩内容.docx', 'a434b907-c27c-456c-894c-43732cd38030开题答辩内容.docx', '221', null, null);

-- ----------------------------
-- Table structure for t_institute
-- ----------------------------
DROP TABLE IF EXISTS `t_institute`;
CREATE TABLE `t_institute` (
  `id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_institute
-- ----------------------------
INSERT INTO `t_institute` VALUES ('1', '据于道，精与业，成于业', '数学与计算机学院');
INSERT INTO `t_institute` VALUES ('2', '以人文之美丽，塑美丽之人文', '人文社科学院');
INSERT INTO `t_institute` VALUES ('3', '志于道、励学躬行；游与艺、和而不同', '艺术学院');
INSERT INTO `t_institute` VALUES ('4', '经济与管理', '经济与管理学院');
INSERT INTO `t_institute` VALUES ('5', '外国语', '外国语学院');
INSERT INTO `t_institute` VALUES ('6', '厚土秀木 建人筑心 微工精管 规划未来', '土木与建筑工程学院');

-- ----------------------------
-- Table structure for t_jurisdiction
-- ----------------------------
DROP TABLE IF EXISTS `t_jurisdiction`;
CREATE TABLE `t_jurisdiction` (
  `id` int(11) NOT NULL,
  `description` text,
  `grade` varchar(1) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_jurisdiction
-- ----------------------------
INSERT INTO `t_jurisdiction` VALUES ('1', '最高权限', '1', '系统管理员');
INSERT INTO `t_jurisdiction` VALUES ('2', '二级管理员', '2', '学院管理员');
INSERT INTO `t_jurisdiction` VALUES ('3', '普通用户', '3', '工会会员');

-- ----------------------------
-- Table structure for t_nav
-- ----------------------------
DROP TABLE IF EXISTS `t_nav`;
CREATE TABLE `t_nav` (
  `id` int(11) NOT NULL,
  `icon_cls` varchar(20) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  `text` varchar(20) DEFAULT NULL,
  `tid` int(11) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_nav
-- ----------------------------
INSERT INTO `t_nav` VALUES ('1', 'icon-group', 'closed', '用户信息', '0', '');
INSERT INTO `t_nav` VALUES ('2', 'icon-user', 'open', '系统用户', '1', '/pages/loginanduser/user.html');
INSERT INTO `t_nav` VALUES ('3', 'icon-group', 'closed', '个人信息', '0', '/pages/imformation/imformation.html');
INSERT INTO `t_nav` VALUES ('4', 'icon-user', 'open', '个人简历', '3', '/pages/resume/resume.html');
INSERT INTO `t_nav` VALUES ('5', 'icon-user', 'open', '家庭成员', '3', '/pages/familyMember/familyMember.html');
INSERT INTO `t_nav` VALUES ('6', 'icon-group', 'closed', '工会公告', '0', '');
INSERT INTO `t_nav` VALUES ('8', 'icon-use', 'open', '公告发布', '6', '/pages/notice/uploadNotice.html');
INSERT INTO `t_nav` VALUES ('7', 'icon-use', 'open', '公告查看', '6', '/pages/notice/notice.html');
INSERT INTO `t_nav` VALUES ('9', 'icon-group', 'closed', '活动', '0', null);
INSERT INTO `t_nav` VALUES ('10', 'icon-user', 'open', '活动查看', '9', '/pages/activity/activity.html');
INSERT INTO `t_nav` VALUES ('11', 'icon-user', 'open', '活动编辑', '9', '/pages/activity/editActivity.html');
INSERT INTO `t_nav` VALUES ('12', 'icon-user', 'open', '活动报名', '9', '/pages/activity/signup.html');
INSERT INTO `t_nav` VALUES ('13', 'icon-user', 'open', '查看活动报名', '9', '/pages/activity/lookactivity.html');
INSERT INTO `t_nav` VALUES ('14', 'icon-user', 'open', '活动统计', '9', '/pages/activity/statisticsactivity.html');
INSERT INTO `t_nav` VALUES ('15', 'icon-user', 'open', '活动统计(系统管理员)', '9', '/pages/activity/lookstatistics.html');
INSERT INTO `t_nav` VALUES ('16', 'icon-user', 'open', '修改个人信息', '10000', '/pages/imformation/imformation.html');

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `date` varchar(20) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1vfhk0eaoi88wb40lsmh5bcvx` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES ('112', '关于举办第十届教职工排球锦标赛的通知', '97', '比赛活动', '2018-04-26 23:44:30', '举办第十届教职工排球锦标赛');
INSERT INTO `t_notice` VALUES ('114', '第十届教工羽毛球比赛秩序册', '97', '比赛活动', '2018-04-26 23:46:24', '第十届教工羽毛球比赛秩序册');
INSERT INTO `t_notice` VALUES ('121', '攀枝花学院第十届教职工排球锦标赛秩序册', '1', '比赛活动', '2018-04-27 09:55:35', '攀枝花学院第十届教职工排球锦标赛秩序册');
INSERT INTO `t_notice` VALUES ('123', '攀枝花学院关于开展2018年“三八”妇女节活动的通知', '95', '工会活动', '2018-04-27 09:58:09', '攀枝花学院关于开展2018年“三八”妇女节活动');
INSERT INTO `t_notice` VALUES ('198', '攀枝花学院2017年度“文明职工”“文明家庭”评选结果', '1', '工会活动', '2018-05-05 20:03:59', '攀枝花学院2017年度“文明职工”“文明家庭”评选结果');
INSERT INTO `t_notice` VALUES ('208', '东区邮政车险团购会', '1', '工会活动', '2018-05-11 16:35:06', '东区邮政车险团购会');
INSERT INTO `t_notice` VALUES ('221', '足球比赛', '95', '比赛活动', '2018-05-30 10:13:32', '比赛');

-- ----------------------------
-- Table structure for xt_user
-- ----------------------------
DROP TABLE IF EXISTS `xt_user`;
CREATE TABLE `xt_user` (
  `id` int(11) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `loginname` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `state` varchar(1) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `jurisdiction_id` int(11) DEFAULT NULL,
  `institute_id` int(11) DEFAULT NULL,
  `birthday` varchar(10) DEFAULT NULL,
  `policy` varchar(20) DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL,
  `sex` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6bes4hw0bq19bs8jy1rpwkwfp` (`institute_id`),
  KEY `FKjk6u1rgo8dbmk3hgq23sxuu8g` (`jurisdiction_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xt_user
-- ----------------------------
INSERT INTO `xt_user` VALUES ('1', '17121323@qq.com', '系统管理员', '赵冬梅', '123456', '', '攀枝花', '15082363011', '1', '1', '1982-01-04', '党员', '教授', '女');
INSERT INTO `xt_user` VALUES ('97', '1248990@160.com', '艺术学院', '陶丁', '123456', null, '中国', '15087234790', '2', '3', '23123', '党员', '讲师', '男');
INSERT INTO `xt_user` VALUES ('96', '234452@qq.com', '人文社科学院', '李丽丽', '18234578734', null, '中国', '18234578734', '2', '2', '23123', '党员', '讲师', '男');
INSERT INTO `xt_user` VALUES ('95', '2432434@qq.com', '数学与计算机学院', '李坊', '123456', null, '中国', '13567823487', '2', '1', '23123', '党员', '讲师', '男');
INSERT INTO `xt_user` VALUES ('98', '889890@160.com', '经济与管理学院', '张亚', '15089897822', null, '中国', '15089897822', '2', '4', '23123', '党员', '讲师', '男');
INSERT INTO `xt_user` VALUES ('99', '39861@qq.com', '外国语学院', '张景志', '13678902340', null, '中国', '13678902340', '2', '5', '23123', '党员', '讲师', '男');
INSERT INTO `xt_user` VALUES ('103', '17121323@qq.com', '张飞', '张飞', '123456', null, '攀枝花', '13545678901', '3', '1', '2018-05-14', '预备党员', '教授', '男');
INSERT INTO `xt_user` VALUES ('111', '24433@qq.com', '朱敏', '朱敏', '123456', null, '中国', '15089982345', '3', '3', '23123', '党员', '讲师', '男');
INSERT INTO `xt_user` VALUES ('127', '1234@qq.com', '王阳', '王阳', '123456', null, '中国', '15089234216', '3', '3', '23123', '党员', '讲师', '男');
INSERT INTO `xt_user` VALUES ('157', '1231232@qq.com', '关羽', '关羽', '123456', null, '中国', '2131312', '3', '1', '23123', '党员', '讲师', '男');
INSERT INTO `xt_user` VALUES ('217', null, '张三', '张三', '123456', null, null, null, '3', '1', null, null, null, null);
INSERT INTO `xt_user` VALUES ('218', null, '李四', '李四', '123456', null, null, null, '3', '1', null, null, null, null);
