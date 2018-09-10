package com.ysc.baiduapp.service;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostExample {
    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();

    public String post(String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://wgyd.wuminmin.top/api")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private String bowlingJson() {
        return "{\"id\":1,"
                + "\"test\":\"wmm\""
                + "}";
    }

    public static void main(String[] args) throws IOException {
        PostExample example = new PostExample();
        String json = example.bowlingJson();
        String response = example.post(json);
        System.out.println(response);
    }
}