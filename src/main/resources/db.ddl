-- triple.place definition

CREATE TABLE `place` (
  `id` binary(16) NOT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `modified_datetime` datetime DEFAULT NULL,
  `place_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- triple.`user` definition

CREATE TABLE `user` (
  `id` binary(16) NOT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `modified_datetime` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- triple.`point` definition

CREATE TABLE `point` (
  `id` binary(16) NOT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `modified_datetime` datetime DEFAULT NULL,
  `action_type` varchar(255) DEFAULT NULL,
  `applied_point` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `point` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx__user` (`user_id`),
  KEY `idx__user__createDatetime` (`user_id`,`created_datetime`),
  CONSTRAINT `FKh4qxmn9mig6kith0ish2r67ka` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- triple.review definition

CREATE TABLE `review` (
  `id` binary(16) NOT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `modified_datetime` datetime DEFAULT NULL,
  `content` longtext DEFAULT NULL,
  `place_id` binary(16) DEFAULT NULL,
  `user_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn429agmmvh298piqrnnd4gbfg` (`place_id`),
  KEY `FKiyf57dy48lyiftdrf7y87rnxi` (`user_id`),
  CONSTRAINT `FKiyf57dy48lyiftdrf7y87rnxi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKn429agmmvh298piqrnnd4gbfg` FOREIGN KEY (`place_id`) REFERENCES `place` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- triple.photo definition

CREATE TABLE `photo` (
  `id` binary(16) NOT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `modified_datetime` datetime DEFAULT NULL,
  `photo_name` varchar(255) DEFAULT NULL,
  `review_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnx36dfpxbxmxifyiq7yvhiohn` (`review_id`),
  CONSTRAINT `FKnx36dfpxbxmxifyiq7yvhiohn` FOREIGN KEY (`review_id`) REFERENCES `review` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;