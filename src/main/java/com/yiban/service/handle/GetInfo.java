package com.yiban.service.handle;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 获取重要信息
 */
public class GetInfo {
    public Map<String,String> getMyInfo(String param){
        SendRequest request = new SendRequest();
        String result = request.sendGet("https://openapi.yiban.cn/user/verify_me",param);
        JSONObject json = JSONObject.fromObject(result);
        Iterator iterator = json.keys();
        Map<String ,String > map = new HashMap<String, String>();
        while (iterator.hasNext()){
            String key = (String) iterator.next();
            map.put(key,json.getString(key));
        }
        return map;
    }
}
