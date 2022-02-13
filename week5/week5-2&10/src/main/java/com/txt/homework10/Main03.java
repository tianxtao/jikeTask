package com.txt.homework10;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.*;

/**
 * @Description :
 * @Date : 2022-02-13
 */
public class Main03 {
    public static void main(String[] args) {
        // 加载mysql数据库驱动
        String insertSql = "insert into student values (?, ?, ?, ?);";
        String updateSql = "update student set age = 22 where cn = 1101;";
        String deleteSql = "delete from student where cn = 1111;";
        String querySql = "select cn, name, age, sex from student;";

        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            DataSource dataSource = (DataSource) context.getBean("dataSource");
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(insertSql);
            for(int i = 1103; i < 1200; i++ ) {
                ps.setInt(1, i+1);
                ps.setString(2, "name" + i);
                ps.setInt(3, 18);
                ps.setInt(4, 0);
                ps.addBatch();
            }

            System.out.println(ps.executeBatch());

            ps = connection.prepareStatement(updateSql);
            System.out.println(ps.execute());
            ps = connection.prepareStatement(deleteSql);
            System.out.println(ps.execute());
            ps = connection.prepareStatement(querySql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " +  resultSet.getString(2) + " " +
                        resultSet.getInt(3) + " " +  resultSet.getInt(4));
            }
            connection.commit();
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
