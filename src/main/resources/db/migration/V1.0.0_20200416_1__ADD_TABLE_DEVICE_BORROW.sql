CREATE TABLE  `device_borrow`(
`id` int(11) NOT NULL AUTO_INCREMENT,
`device_id` int(11) NOT NULL,
`borrower_id` int(11) NOT NULL,
`state` varchar(50) DEFAULT NULL,
`borrow_date` date DEFAULT NULL,
`return_date` date DEFAULT NULL,
`condition` varchar(50) DEFAULT NULL,
`detail` varchar(50) DEFAULT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (`device_id`) REFERENCES device(`id`) on delete cascade on update cascade,
FOREIGN KEY(`borrower_id`) REFERENCES staff(`id`) on delete cascade on update cascade
)  ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workinghour
-- ----------------------------
INSERT INTO `device_borrow` VALUES (1,4,1,'借用中','2020-04-01',NULL,'','');
