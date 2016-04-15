CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(128) NOT NULL,
  `mail` varchar(64) DEFAULT NULL,
  `phone` varchar(64) DEFAULT NULL,
  `avatar` varchar(128) DEFAULT NULL COMMENT '头像照片',
  `balance` double DEFAULT NULL COMMENT '虚拟币余额',
  `idnumber` varchar(32) DEFAULT NULL,
  `idphoto` varchar(128) DEFAULT NULL COMMENT '身份证照片',
  `black_status` int(11) DEFAULT NULL COMMENT '0-不是黑名单  1-是黑名单',
  `certifie` int(11) DEFAULT NULL COMMENT '实名认证状态 0-未实名 1-认证中 2-认证成功',
  `baifubao_account` varchar(64) DEFAULT NULL COMMENT '百度钱包账号',
  `register_time` datetime NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `password` varchar(64) NOT NULL,
  `role` int(11) DEFAULT '0' COMMENT '0-普通管理员 1-超级管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `description` varchar(64) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_key_idx` (`parent_id`),
  CONSTRAINT `parent_key_foreign` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8CREATE TABLE `channel` (
  `id` bigint(20) NOT NULL,
  `name` varchar(32) NOT NULL,
  `order` int(11) DEFAULT '0',
  `parent` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_key_idx` (`parent`),
  CONSTRAINT `parent_key` FOREIGN KEY (`parent`) REFERENCES `channel` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(128) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `isread` int(11) DEFAULT '0' COMMENT '0-未读 1-读',
  PRIMARY KEY (`id`),
  KEY `message_user_idx` (`user_id`),
  CONSTRAINT `message_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8