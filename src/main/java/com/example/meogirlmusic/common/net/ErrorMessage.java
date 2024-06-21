package com.example.meogirlmusic.common.net;

import com.alibaba.fastjson2.JSONArray;

import java.util.HashMap;


public class ErrorMessage {

    HashMap<Object,Object> hashMap =  new HashMap<>();


    public ErrorMessage(String message) {
        hashMap.put("code", 200);
        hashMap.put("message", message);
        hashMap.put("success", false);
        hashMap.put("type", "error");
        hashMap.put("data", null);
    }

    public String getMessage() {
        return JSONArray.toJSONString(hashMap);
    }

}
