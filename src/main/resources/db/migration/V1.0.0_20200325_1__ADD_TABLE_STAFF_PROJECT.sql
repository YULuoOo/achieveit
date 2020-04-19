CREATE TABLE  `staff_project`(
`id` int(11) NOT NULL AUTO_INCREMENT,
`pro_id`  int(11) NOT NULL ,
`staff_id` int(11) NOT NULL,
`staff_role` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY(`pro_id`) REFERENCES ach_project(`id`) on delete cascade on update cascade,
FOREIGN KEY(`staff_id`) REFERENCES staff(`id`) on delete cascade on update cascade
)  ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of staff_project
-- ----------------------------
INSERT INTO `staff_project` VALUES (1,1,1,'项目经理');
INSERT INTO `staff_project` VALUES (2,1,2,'项目上级');
INSERT INTO `staff_project` VALUES (3,1,3,'项目成员');