package com.alpha.pandemic.structor.tools;


import okhttp3.*;

import java.util.Objects;

@SuppressWarnings("unused")
public class HttpUtil
{
    public static final MediaType HEAD = MediaType.parse("application/json;charset=utf-8");


    public static String httpPost(String url, String body) throws Exception
    {
        String postData;
        OkHttpClient httpClient = new OkHttpClient();
        @SuppressWarnings("")
        RequestBody requestBody = RequestBody.create(HEAD, body);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = httpClient.newCall(request).execute();
        postData = Objects.requireNonNull(response.body()).string();
        return postData;
    }


    public static String httpGet(String url) throws Exception
    {
        String getData;
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = httpClient.newCall(request).execute();
        getData = Objects.requireNonNull(response.body()).string();
        return getData;
    }
}
