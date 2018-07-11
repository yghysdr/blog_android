package com.github.yghysdr.http;

import com.google.gson.Gson;

import okhttp3.RequestBody;

/**
 * Json转换请求
 */

public class JsonHelper {


    public static RequestBody getPostBody(Object object){
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return RequestBody.create(
                okhttp3.MediaType.parse("application/json;charset=UTF-8"),
                json);
    }
}
