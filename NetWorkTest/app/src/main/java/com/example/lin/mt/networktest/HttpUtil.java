package com.example.lin.mt.networktest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by MT-Lin on 2017/12/19.
 */

public class HttpUtil {

    interface HttpCallbackListener{
        // 根据返回的内容执行具体逻辑
        void onSuccess(String response);
        void onError(Exception errorException);
    }

    public static void sendHttpRequest(final String address, final HttpCallbackListener httpCallbackListener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null){
                        stringBuilder.append(line);
                    }
                    if (null != httpCallbackListener){
                        httpCallbackListener.onSuccess(stringBuilder.toString());
                    }
                } catch (Exception e) {
                    if (null != httpCallbackListener) {
                        httpCallbackListener.onError(e);
                    }
                } finally {
                    if ( null != connection){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    public static void sendOkHttpRequest(String address, Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
