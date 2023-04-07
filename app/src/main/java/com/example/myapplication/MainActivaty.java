package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.model.JsonResult;
import com.example.myapplication.service.LoginService;
import com.example.myapplication.utils.HttpClientUtil;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author: ZhangJian
 * @date: 2023/4/4 17:52
 * @description:
 */
public class MainActivaty extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivaty";
    private ImageButton loginButton;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        loginButton = (ImageButton) findViewById(R.id.loginButton);
        //初始化事件
        initEvent();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /** Stops the camera. */
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initEvent() {
        loginButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.loginButton){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userName", "zshz");
            jsonObject.put("password", "Zs1234");
            LoginService.loginApp(jsonObject);
        }
    }
}
