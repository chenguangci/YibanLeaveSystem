package com.yiban.service.handle;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 获取登陆者的学号姓名等重要信息
 */
public class GetInfo {
    public Map<String,String> getMyInfo(String param){
        SendRequest request = new SendRequest();
        String result = request.sendRequest("https://openapi.yiban.cn/user/verify_me","access_token="+param,SendRequest.TYPE.GET);
        System.out.println(result);
        JSONObject json = JSONObject.fromObject(result);
        Iterator iterator = json.keys();
        Map<String ,String > map = new HashMap<String, String>();
        while (iterator.hasNext()){
            String key = (String) iterator.next();
            map.put(key,json.getString(key));
            if ("info".equals(key)){
                JSONObject json2 = JSONObject.fromObject(map.get("info"));
                Iterator iterator1 = json2.keys();
                while (iterator1.hasNext()){
                    String key1 = (String) iterator1.next();
                    map.put(key1,json2.getString(key1));
                }
            }
        }
        map.remove("info");
        return map;
    }
}
