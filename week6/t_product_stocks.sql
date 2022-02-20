SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `t_product_stocks`;
CREATE TABLE `t_product_stocks`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `pid` int(10) NULL DEFAULT NULL COMMENT '商品ID',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '成本价格',
  `stock` int(10) NULL DEFAULT NULL COMMENT '存量',
  `attributes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品属性',
  `photo` int(10) NULL DEFAULT NULL COMMENT '图片',
  `isDelete` int(2) NULL DEFAULT 0 COMMENT '是否删除： 0 未删除  1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
