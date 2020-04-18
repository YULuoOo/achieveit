CREATE TABLE  `device`(
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(50) DEFAULT NULL,
`category` varchar(50) DEFAULT NULL,
`owner_id` int(11) DEFAULT NULL,
`owner` varchar(50) DEFAULT NULL,
`state` varchar(50) DEFAULT NULL,
`condition` varchar(50) DEFAULT NULL,
`borrower` varchar(50) DEFAULT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY(`owner_id`) REFERENCES staff(`id`)
)  ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workinghour
-- ----------------------------
INSERT INTO `device` VALUES (1,'手机1','手机',2,'员工2','可借用','正常','');
INSERT INTO `device` VALUES (2,'手机2','手机',2,'员工2','可借用','轻微损坏','');
INSERT INTO `device` VALUES (3,'电脑1','电脑',2,'员工2','可借用','损坏','');
INSERT INTO `device` VALUES (4,'电脑2','电脑',2,'员工2','借用中','正常','员工1');
