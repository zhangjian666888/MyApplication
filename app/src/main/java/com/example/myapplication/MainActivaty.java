package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.activaties.LoginActivaty;
import com.example.myapplication.service.LoginService;
import com.example.myapplication.utils.SharedPreferencesUtils;
import okhttp3.MediaType;


/**
 * @author: ZhangJian
 * @date: 2023/4/4 17:52
 * @description:
 */
public class MainActivaty extends AppCompatActivity {
    private static final String TAG = "MainActivaty";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String token = SharedPreferencesUtils.getString(this, "token");
        if(token == null || "".equals(token)){
            setContentView(R.layout.login);
            Intent intent = new Intent(MainActivaty.this, LoginActivaty.class);
            startActivity(intent);
            MainActivaty.this.finish();
        }else {

        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
