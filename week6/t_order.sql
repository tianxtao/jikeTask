SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `oid` int(10) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `products` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '已选商品列表',
  `totalCost` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品总价',
  `orderStatus` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态',
  `statusRemark` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态备注',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `receive` int(10) NULL DEFAULT NULL COMMENT '收货地址',
  `fare` decimal(10, 2) NULL DEFAULT NULL COMMENT '运费',
  `realPay` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际支付',
  `remark` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `isDelete` int(2) NULL DEFAULT 0 COMMENT '是否删除： 0 未删除  1 已删除',
  PRIMARY KEY (`oid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
