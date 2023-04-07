package com.example.myapplication.utils;


import android.util.Log;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author: ZhangJian
 * @date: 2023/4/6 12:47
 * @description:
 */
public class HttpClientUtil {
    private static final String TAG = "HttpClient";
    String url = "http://192.168.105.253:8080";
    public OkHttpClient client;

    public HttpClientUtil(){
        client = new OkHttpClient.Builder()
                // 设置连接超时时间，这里是一分钟
                .connectTimeout(60, TimeUnit.SECONDS)
                // 设置读取超时时间， 这里是一分钟
                .readTimeout(60, TimeUnit.SECONDS)
                // 设置写入超时时间， 这里是一分钟
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .followRedirects(false)
                .build();
    }

    public ResponseBody postMethod(RequestBody formBody, String serviceName, Map<String, Object> headersMap) throws IOException {
        Request.Builder builder = new Request.Builder().url(url + serviceName);
        if(formBody != null){
            builder.post(formBody);
        }
        if(headersMap != null){
            for (Map.Entry<String, Object> entry : headersMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                builder.addHeader(key, String.valueOf(value));
            }
        }
        return client.newCall(builder.build()).execute().body();
    }

    public ResponseBody getMethod(String serviceName, Map<String, Object> headersMap) throws IOException {
        Request.Builder builder = new Request.Builder().url(url + serviceName);
        if(headersMap != null){
            for (Map.Entry<String, Object> entry : headersMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                builder.addHeader(key, String.valueOf(value));
            }
        }
        return client.newCall(builder.build()).execute().body();
    }

}
