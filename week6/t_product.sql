SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product`  (
  `pid` int(10) NOT NULL AUTO_INCREMENT,
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品编码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `category` int(8) NULL DEFAULT NULL COMMENT '商品类别',
  `description` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品简要描述',
  `keyword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '可搜索关键词',
  `status` int(2) NULL DEFAULT NULL COMMENT '	状态：-1 已删除 0 已下架 1 热卖中',
  `showPhoto` int(8) NULL DEFAULT NULL COMMENT '展示图片',
  `addTime` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `isDelete` int(2) NULL DEFAULT 0 COMMENT '是否删除： 0 未删除  1 已删除',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
