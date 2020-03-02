-- ----------------------------
-- Table structure for product
-- ----------------------------
CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `uuid` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_24bc4yyyk3fj3h7ku64i3yuog` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_attributes
-- ----------------------------
CREATE TABLE IF NOT EXISTS `product_attributes` (
  `product_id` bigint(20) NOT NULL,
  `attributes` varchar(255) DEFAULT NULL,
  `attributes_key` varchar(255) NOT NULL,
  PRIMARY KEY (`product_id`,`attributes_key`),
  CONSTRAINT `FK95nqsy6pr19opyu092fbwdr74` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------
-- Index on uuid column for product table
-- --------------------------------------
ALTER TABLE `product` ADD INDEX `uuid_index` (`uuid`)


