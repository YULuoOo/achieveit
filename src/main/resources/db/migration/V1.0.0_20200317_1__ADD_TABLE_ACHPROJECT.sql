CREATE TABLE `ach_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pro_name` varchar(255) DEFAULT NULL,
  `pro_desc` varchar(255) DEFAULT NULL,
  `pro_tech` varchar(255) DEFAULT NULL,
  `pro_area` varchar(255) DEFAULT NULL,
  `pro_func` varchar(255) DEFAULT NULL,
  `pro_status` int(11) DEFAULT NULL,
  `pro_enddate` date DEFAULT NULL,
  `pro_startdate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ach_project
-- ----------------------------
INSERT INTO `ach_project` VALUES ('1', '1', 'AchieveIt是一款软件项目管理平台，面向于企业和团队提供数字化协同办公工具', '前端：JavaS vue.js 后端：Java Springboot', '涉及项目管理，团队协作以及数据存储', '项目申请，项目审批，QA管理', '0', '2020-04-22', '2020-02-20');
INSERT INTO `ach_project` VALUES ('2', '2', 'AchieveIt是一款软件项目管理平台，面向于企业和团队提供数字化协同办公工具', '前端：JavaS vue.js 后端：Java Springboot', '涉及项目管理，团队协作以及数据存储', '项目申请，项目审批，QA管理', '1', '2020-04-22', '2020-02-20');
INSERT INTO `ach_project` VALUES ('3', '3', 'AchieveIt是一款软件项目管理平台，面向于企业和团队提供数字化协同办公工具', '前端：JavaS vue.js 后端：Java Springboot', '涉及项目管理，团队协作以及数据存储', '项目申请，项目审批，QA管理', '2', '2020-04-22', '2020-02-20');
INSERT INTO `ach_project` VALUES ('4', '4', 'AchieveIt是一款软件项目管理平台，面向于企业和团队提供数字化协同办公工具', '前端：JavaS vue.js 后端：Java Springboot', '涉及项目管理，团队协作以及数据存储', '项目申请，项目审批，QA管理', '3', '2020-04-22', '2020-02-20');
INSERT INTO `ach_project` VALUES ('5', '5', 'AchieveIt是一款软件项目管理平台，面向于企业和团队提供数字化协同办公工具', '前端：JavaS vue.js 后端：Java Springboot', '涉及项目管理，团队协作以及数据存储', '项目申请，项目审批，QA管理', '4', '2020-04-22', '2020-02-20');
INSERT INTO `ach_project` VALUES ('6', '6', 'AchieveIt是一款软件项目管理平台，面向于企业和团队提供数字化协同办公工具', '前端：JavaS vue.js 后端：Java Springboot', '涉及项目管理，团队协作以及数据存储', '项目申请，项目审批，QA管理', '-1', '2020-04-22', '2020-02-20');
