package com.hongchao.coolweathertest.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

//采用OkHttp与服务器进行通信
public class HttpUtil {
    //与服务器进行交互发起一条http请求只需要调用  sendOkHttpRequest()即可
    //传入要请求的地址，注册一个回调处理服务器的响应
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(address).build();
            client.newCall(request).enqueue(callback);
        }
}
