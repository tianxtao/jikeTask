package com.txt.homework10;

import java.sql.*;

/**
 * @Description :
 * @Date : 2022-02-13
 */
public class Main01 {
    public static void main(String[] args){
        // 加载mysql数据库驱动
        String insertSql = "insert into student values (1103, '小晓', 18, 1);";
        String updateSql = "update student set age = 19 where cn = 1101;";
        String deleteSql = "delete from student where cn = 1111;";
        String querySql = "select cn, name, age, sex from student;";

        Statement ps = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://192.168.1.4/test?useSSL=false", "root",
                   "root");
            ps = connection.createStatement();

            System.out.println(ps.execute(insertSql));
            System.out.println(ps.executeUpdate(updateSql));
            System.out.println(ps.execute(deleteSql));
            resultSet = ps.executeQuery(querySql);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " +  resultSet.getString(2) + " " +
                        resultSet.getInt(3) + " " +  resultSet.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
