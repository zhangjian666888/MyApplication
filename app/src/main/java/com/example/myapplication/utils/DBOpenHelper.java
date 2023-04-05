package com.example.myapplication.utils;


import com.mysql.jdbc.Connection;

import java.sql.DriverManager;

/**
 * @author: ZhangJian
 * @date: 2023/4/4 18:33
 * @description:
 */
public class DBOpenHelper {
    private static final String TAG = "DBOpenHelper";
    private static String diver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://43.143.181.73:3306/hospital?useUnicode=true&characterEncoding=UTF-8";
    private static String user = "root";
    private static String password = "MySQL%5.7";
    public static Connection getConn(){
        Connection conn = null;
        try {
            Class.forName(diver);
//            Driver driver = new Driver();
//            Properties info = new Properties();
//            info.setProperty("user", user);
//            info.setProperty("password", password);
//            conn = (Connection) driver.connect(url, info);
            conn = (Connection) DriverManager.getConnection(url, user, password);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
