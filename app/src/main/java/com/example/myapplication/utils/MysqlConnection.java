package com.example.myapplication.utils;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author: ZhangJian
 * @date: 2023/4/4 18:33
 * @description:
 */
public class MysqlConnection {

    private static final String TAG = "MysqlConnection";
    Connection conn = null;
    public static void connectToMysql(){
        final Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    try {
                        Thread.sleep(100);  // 每隔0.1秒尝试连接
                    } catch (InterruptedException e) {
                        Log.e(TAG, e.toString());
                    }
                    // 1.加载JDBC驱动
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Log.v(TAG, "加载JDBC驱动成功");
                    } catch (ClassNotFoundException e) {
                        Log.e(TAG, "加载JDBC驱动失败");
                        return;
                    }
                    // 2.设置好IP/端口/数据库名/用户名/密码等必要的连接信息
                    String ip = "43.143.181.73";
                    int port = 3306;
                    String dbName = "mysql";
                    String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbName+"?useUnicode=true&characterEncoding=utf-8&useSSL=false";
                    // 构建连接mysql的字符串
                    String user = "root";
                    String password = "lyx123";

                    // 3.连接JDBC
                    try {
                        java.sql.Connection conn = DriverManager.getConnection(url, user, password);
                        Log.d(TAG, "数据库连接成功");
                        conn.close();
                        return;
                    }
                    catch (SQLException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }
        });
        thread.start();

    }


}
