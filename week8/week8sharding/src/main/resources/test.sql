CREATE TABLE `t_order`  (
  `o_id` bigint(30) NOT NULL COMMENT '订单ID',
  `user_id` int(8) NOT NULL COMMENT '用户ID',
  `products` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '已选商品列表',
  `totalCost` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品总价',
  `orderStatus` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态',
  `statusRemark` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态备注',
  `createTime` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `receive` int(10) NULL DEFAULT NULL COMMENT '收货地址',
  `fare` decimal(10, 2) NULL DEFAULT NULL COMMENT '运费',
  `realPay` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际支付',
  `remark` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `isDelete` int(2) NULL DEFAULT 0 COMMENT '是否删除： 0 未删除  1 已删除',
  PRIMARY KEY (`o_id`) USING BTREE
);

drop table t_order;

INSERT INTO `t_order` (products,user_id,totalCost, orderStatus, statusRemark, createTime, receive, fare, realPay, remark, isDelete) VALUES (1, 2,6899.00, '09', '交易完成', 1646549418, 1, 0.00, 6899.00, '早点发货哦', 0);
INSERT INTO `t_order` (products,user_id,totalCost, orderStatus, statusRemark, createTime, receive, fare, realPay, remark, isDelete) VALUES (1, 1,6899.00, '09', '交易完成', 1646549418, 1, 0.00, 6899.00, '早点发货哦', 0);

select * from t_order;

delete from t_order where o_id = 707248688644730880;

delete from t_order;

update t_order set remark = '还不发货？' where user_id = 1; 