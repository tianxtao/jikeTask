SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `t_order_products`;
CREATE TABLE `t_order_products`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `pid` int(10) NULL DEFAULT NULL COMMENT '商品ID',
  `amount` int(8) NULL DEFAULT NULL COMMENT '商品数量',
  `unitPrice` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `discountedPrice` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠价格',
  `totalPrice` decimal(10, 2) NULL DEFAULT NULL COMMENT '总价格',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
