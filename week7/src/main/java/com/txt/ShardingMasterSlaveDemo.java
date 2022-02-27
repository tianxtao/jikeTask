package com.txt;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.ReplicaQueryRuleConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.rule.ReplicaQueryDataSourceRuleConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @Description :
 * @Date : 2022-02-27
 */
public class ShardingMasterSlaveDemo {
    public static void main(String[] args) {
        insert();
        select();
    }

    public static void insert() {
        String insertSql = "insert into t_user(username, phone, password) values ('tiannnn', '187777777', '4564113');";
        DataSource d = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            d = getDataSource();
            conn = d.getConnection();
            preparedStatement = conn.prepareStatement(insertSql);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void select() {
        String query = "select username, phone, password from t_user;";
        DataSource d = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet sets = null;
        try {
            d = getDataSource();
            conn = d.getConnection();
            preparedStatement = conn.prepareStatement(query);
            sets = preparedStatement.executeQuery();
            while (sets.next()) {
                System.out.println(sets.getInt(1) + " " +  sets.getString(2) + " " +
                        sets.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                preparedStatement.close();
                sets.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static DataSource getDataSource() throws SQLException {
        //数据源Map
        Map<String, DataSource> dsMap = new HashMap<>();
        //配置主库
        HikariDataSource masterDs = new HikariDataSource();
        masterDs.setDriverClassName("com.mysql.jdbc.Driver");
        masterDs.setJdbcUrl("jdbc:mysql://localhost:3316/db?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true");
        masterDs.setMinimumIdle(4);
        masterDs.setMaximumPoolSize(8);
        masterDs.setConnectionInitSql("SET NAMES utf8mb4");
        masterDs.setUsername("root");
        masterDs.setPassword("root");
        dsMap.put("master_ds", masterDs);
        //配置读库1
        HikariDataSource salveDs = new HikariDataSource();
        salveDs.setDriverClassName("com.mysql.jdbc.Driver");
        salveDs.setJdbcUrl("jdbc:mysql://localhost:3326/db?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true");
        salveDs.setMinimumIdle(4);
        salveDs.setMaximumPoolSize(8);
        salveDs.setConnectionInitSql("SET NAMES utf8mb4");
        salveDs.setUsername("root");
        salveDs.setPassword("root");
        dsMap.put("salve_ds", salveDs);
        //配置读写分离规则
        List<ReplicaQueryDataSourceRuleConfiguration> configurations = new ArrayList<>();
        configurations.add(new ReplicaQueryDataSourceRuleConfiguration("ds", "master_ds", Arrays.asList("salve_ds"), "load_balancer"));
        Map<String, ShardingSphereAlgorithmConfiguration> loadBalancers = new HashMap<>();
        loadBalancers.put("load_balancer", new ShardingSphereAlgorithmConfiguration("ROUND_ROBIN", new Properties()));
        ReplicaQueryRuleConfiguration ruleConfiguration = new ReplicaQueryRuleConfiguration(configurations, loadBalancers);
        //创建DS
        return ShardingSphereDataSourceFactory.createDataSource(dsMap, Arrays.asList(ruleConfiguration), new Properties());
    }
}
