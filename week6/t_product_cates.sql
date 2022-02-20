SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `t_product_cates`;
CREATE TABLE `t_product_cates`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `cateLevel` int(2) NULL DEFAULT NULL COMMENT '分类级别，如1级分类，2级分类，3级分类',
  `cateTitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态： 0 正常 1 禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
