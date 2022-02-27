package com.txt.datasource;

/**
 * @Description :
 * @Date : 2022-02-27
 */
public class DataSourceContextHolder {
    private DataSourceContextHolder(){}

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSource(String dbType){
        contextHolder.set(dbType);
    }

    public static String getDataSource(){
        return contextHolder.get();
    }

    public static void clearDataSource(){
        contextHolder.remove();
    }
}
