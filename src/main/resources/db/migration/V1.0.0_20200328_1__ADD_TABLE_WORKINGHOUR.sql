CREATE TABLE  `workinghour`(
`id` int(11) NOT NULL AUTO_INCREMENT,
`staff_id` int(11) NOT NULL,
`work_content` varchar(255) DEFAULT NULL,
`work_date` date DEFAULT NULL,
`work_length` float(3,1) DEFAULT NULL,
`state` varchar(50) DEFAULT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY(`staff_id`) REFERENCES staff(`id`) on delete cascade on update cascade
)  ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workinghour
-- ----------------------------
INSERT INTO `workinghour` VALUES (1,1,'前端开发','2020-04-04',8,'未审批');
