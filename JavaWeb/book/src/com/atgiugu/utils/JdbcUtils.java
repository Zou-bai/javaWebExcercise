package com.atgiugu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
//获取数据库连接池的连接


public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {
        try {
           Properties properties=new Properties();
           //读取jdbc.properties属性配置文件
           InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
           //从流中加载数据
           properties.load(inputStream);
            //创建数据库连接池
           dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @return 如果返回null，说明获取连接异常 有值就是连接成功
     */
    public static Connection getConnection(){

        Connection coon=conns.get();
        if (coon==null){
            try {
                coon=dataSource.getConnection();//从数据库连接池中获取连接

                conns.set(coon);//保存到ThreadLocal对象中，供后面的jdbc操作使用

                coon.setAutoCommit(false);//设置为手动管理事务

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return coon;
    }

    /**
     * 提交事务，并关闭
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if (connection!=null){//如果不等于null，说明之前使用过连接，操作过数据库

            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();//关闭连接，资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            conns.remove();
            //一定要执行remove操作，否则就会出错
        }
    }

    /**
     * 回滚事务，并关闭
     */
    public static void rollbackAndClose(){

    }


    /**
     *
     * @param conn

    public static void close(Connection conn){
        if (conn !=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }     */
}
