CREATE TABLE  `config`(
`id` int(11) NOT NULL AUTO_INCREMENT,
`project_id` int(11) NOT NULL,
`giturl` varchar(50) DEFAULT NULL,
`root` varchar(50) DEFAULT NULL,
`disk_size` varchar(50) DEFAULT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY(`project_id`) REFERENCES ach_project(`id`) on delete cascade on update cascade
)  ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workinghour
-- ----------------------------
INSERT INTO `config` VALUES (1,1,'www.aaa.com','/home','8gb');
