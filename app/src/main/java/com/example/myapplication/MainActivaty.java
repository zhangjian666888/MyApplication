package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.*;


/**
 * @author: ZhangJian
 * @date: 2023/4/4 17:52
 * @description:
 */
public class MainActivaty extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivaty";
    private ImageButton loginButton;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        loginButton = (ImageButton) findViewById(R.id.loginButton);
        //初始化事件
        initEvent();
    }

    private void initEvent() {
        loginButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.loginButton){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String diver = "com.mysql.jdbc.Driver";
                    String url = "jdbc:mysql://43.143.181.73:3306/hospital?useUnicode=true&characterEncoding=UTF-8";
                    String user = "root";
                    String password = "MySQL%5.7";
                    try {
                        Class.forName(diver).newInstance();
                        Connection conn = (Connection) DriverManager.getConnection(url, user, password);
                        String sql = "select * from b_coupe";
                        PreparedStatement statement = conn.prepareStatement(sql);
                        ResultSet resultSet = statement.executeQuery();
                        Log.i(TAG, "结果：" + resultSet.toString());
                        Log.i(TAG, "登录");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
