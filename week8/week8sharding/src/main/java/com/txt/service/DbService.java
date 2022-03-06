package com.txt.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Description :
 * @Date : 2022-03-06
 */
public class DbService {
    public static void main(String[] args) {
        //插入数据
        System.out.println("插入数据耗时：" + insertTest());
    }

    // 通过jdbc，使用事务提交，使用addBatch()插入100w数据。
    public static long insertTest() {
        String insertSql = "INSERT INTO `t_order` (products,user_id,totalCost, orderStatus, statusRemark, createTime, receive, fare, realPay, remark, isDelete) VALUES (1, ?,6899.00, '09', '交易完成', 1646549418, 1, 0.00, 6899.00, '早点发货哦', 0);";
        Connection conn = null;
        PreparedStatement ps = null;
        long costTime = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/sharding_db?useSSL=false&serverTimezone=UTC", "root",
                    "123456");
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(insertSql);

            long start = System.currentTimeMillis();
            for(int i = 0; i < 10000; i++) {
                ps.setInt(1, i % 2);
                ps.addBatch();
            }
            ps.executeBatch();
            long end = System.currentTimeMillis();
            costTime = end - start;
            conn.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return costTime;
    }
}
