package com.txt;

import java.sql.*;

/**
 * @Description :
 * @Date : 2022-02-27
 */
public class InsertDemo {
    public static void main(String[] args) {
        System.out.println("Statement，无事务 + 一条一条插入，总花费时间: " + insertTest0());
        System.out.println("PreparedStatement，无事务 + 一条一条插入，总花费时间: " + insertTest1());
        System.out.println("PreparedStatement，事务 + 一条一条插入，总花费时间: " + insertTest2());
        System.out.println("PreparedStatement，无事务 + addBatch，总花费时间: " + insertTest3());
        System.out.println("PreparedStatement，事务 + addBatch，总花费时间: " + insertTest4());
    }

    // 通过jdbc，Statement每次插入一条数据执行100万次。
    public static long insertTest0() {
        String insertSql = "INSERT INTO `t_order` (products, totalCost, orderStatus, statusRemark, createTime, receive, fare, realPay, remark, isDelete) VALUES ('1', 6899.00, '09', '交易完成', '2022-02-27 08:44:54', 1, 0.00, 6899.00, '早点发货哦', 0)";
        Connection conn = null;
        Statement statement = null;
        long costTime = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/testdb?useSSL=false", "root",
                    "123456");
            statement = conn.createStatement();

            long start = System.currentTimeMillis();
            for(int i = 0; i < 1000000; i++) {
                statement.execute(insertSql);
            }
            long end = System.currentTimeMillis();
            costTime = end - start;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return costTime;
    }

    // 通过jdbc，每次插入一条数据执行100万次。
    public static long insertTest1() {
        String insertSql = "INSERT INTO `t_order` (products, totalCost, orderStatus, statusRemark, createTime, receive, fare, realPay, remark, isDelete) VALUES (?, 6899.00, '09', '交易完成', '2022-02-27 08:44:54', 1, 0.00, 6899.00, '早点发货哦', 0)";
        Connection conn = null;
        PreparedStatement ps = null;
        long costTime = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/testdb?useSSL=false", "root",
                    "123456");
            ps = conn.prepareStatement(insertSql);

            long start = System.currentTimeMillis();
            for(int i = 0; i < 1000000; i++) {
                ps.setString(1, "1");
                ps.execute();
            }
            long end = System.currentTimeMillis();
            costTime = end - start;
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

    // 通过jdbc，使用事务，每次插入一条数据执行100万次。
    public static long insertTest2() {
        String insertSql = "INSERT INTO `t_order` (products, totalCost, orderStatus, statusRemark, createTime, receive, fare, realPay, remark, isDelete) VALUES (?, 6899.00, '09', '交易完成', '2022-02-27 08:44:54', 1, 0.00, 6899.00, '早点发货哦', 0)";
        Connection conn = null;
        PreparedStatement ps = null;
        long costTime = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/testdb?useSSL=false", "root",
                    "123456");
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(insertSql);

            long start = System.currentTimeMillis();
            for(int i = 0; i < 1000000; i++) {
                ps.setString(1, "1");
                ps.execute();
            }
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

    // 通过jdbc，不使用事务提交，使用addBatch()插入100w数据。
    public static long insertTest3() {
        String insertSql = "INSERT INTO `t_order` (products, totalCost, orderStatus, statusRemark, createTime, receive, fare, realPay, remark, isDelete) VALUES (?, 6899.00, '09', '交易完成', '2022-02-27 08:44:54', 1, 0.00, 6899.00, '早点发货哦', 0)";
        Connection conn = null;
        PreparedStatement ps = null;
        long costTime = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/testdb?useSSL=false", "root",
                    "123456");
            ps = conn.prepareStatement(insertSql);

            long start = System.currentTimeMillis();
            for(int i = 0; i < 1000000; i++) {
                ps.setString(1, "1");
                ps.addBatch();
            }
            ps.executeBatch();
            long end = System.currentTimeMillis();
            costTime = end - start;
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

    // 通过jdbc，使用事务提交，使用addBatch()插入100w数据。
    public static long insertTest4() {
        String insertSql = "INSERT INTO `t_order` (products, totalCost, orderStatus, statusRemark, createTime, receive, fare, realPay, remark, isDelete) VALUES (?, 6899.00, '09', '交易完成', '2022-02-27 08:44:54', 1, 0.00, 6899.00, '早点发货哦', 0)";
        Connection conn = null;
        PreparedStatement ps = null;
        long costTime = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/testdb?useSSL=false", "root",
                    "123456");
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(insertSql);

            long start = System.currentTimeMillis();
            for(int i = 0; i < 1000000; i++) {
                ps.setString(1, "1");
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
