package com.example.myapplication.service;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.model.JsonResult;
import com.example.myapplication.utils.HttpClientUtil;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

import static com.example.myapplication.utils.CommonUtil.JSON;


/**
 * @author: ZhangJian
 * @date: 2023/4/7 18:26
 * @description:
 */
public class LoginService {
    private static final String TAG = "LoginService";
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static void loginApp(JSONObject jsonObject, Handler handler){
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
                    Message message = new Message();
                    message.what = 1;
                    message.obj = jsonObject1;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
