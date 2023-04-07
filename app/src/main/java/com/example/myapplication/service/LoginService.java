package com.example.myapplication.service;

import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.model.JsonResult;
import com.example.myapplication.utils.HttpClientUtil;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.myapplication.MainActivaty.JSON;

/**
 * @author: ZhangJian
 * @date: 2023/4/7 18:26
 * @description:
 */
public class LoginService {
    private static final String TAG = "LoginService";
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static void loginApp(JSONObject jsonObject){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                RequestBody requestBody = RequestBody.create(JSON, jsonObject.toJSONString());
                Map<String, Object> headersMap = new HashMap<>();
                ResponseBody responseBody = null;
                try {
                    responseBody = new HttpClientUtil().postMethod(requestBody, "/appLogin", headersMap);
                    String responseData = responseBody.string();
                    JSONObject jsonObject1 = JSONObject.parseObject(responseData);
                    System.out.println(jsonObject1.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
