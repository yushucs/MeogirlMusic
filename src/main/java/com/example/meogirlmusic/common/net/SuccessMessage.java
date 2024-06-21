package com.example.meogirlmusic.common.net;

import com.alibaba.fastjson2.JSONArray;

import java.util.HashMap;


public class SuccessMessage<T> {

    private final HashMap<Object,Object> hashMap =  new HashMap<>();


    public SuccessMessage(String message) {
        hashMap.put("code", 200);
        hashMap.put("message", message);
        hashMap.put("success", true);
        hashMap.put("type", "success");
        hashMap.put("data", null);
    }

    public SuccessMessage(String message, T data) {
        hashMap.put("code", 200);
        hashMap.put("message", message);
        hashMap.put("success", true);
        hashMap.put("type", "success");
        hashMap.put("data", data);
    }

    public String getMessage() {
        return JSONArray.toJSONString(hashMap);
    }
}
