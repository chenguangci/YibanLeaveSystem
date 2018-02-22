package com.yiban.service.handle;

import com.yiban.dto.YibanURL;
import com.yiban.exception.SendError;
import com.yiban.exception.RequestInfoError;
import com.yiban.exception.SystemRunTimeError;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 获取登陆者的学号姓名等重要信息
 */

public class GetInfo {

    private Logger logger = LoggerFactory.getLogger(GetInfo.class);

    public Map<String, String> getMyInfo(String param) throws SendError, RequestInfoError, SystemRunTimeError {
        SendRequest request = new SendRequest();
        String result = request.sendRequest(YibanURL.VerifyMe, "access_token=" + param, SendRequest.TYPE.GET);
        logger.info("查询个人信息返回的json：{}",result);
        if (!result.contains("success")) {
            logger.error("获取个人信息失败，失败原因：{}",result);
            throw new RequestInfoError("获取个人信息失败");
        }
        JSONObject json = JSONObject.fromObject(result);
        Iterator iterator = json.keys();
        Map<String, String> map = new HashMap<String, String>();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            map.put(key, json.getString(key));
            if ("info".equals(key)) {
                JSONObject json2 = JSONObject.fromObject(map.get("info"));
                Iterator iterator1 = json2.keys();
                while (iterator1.hasNext()) {
                    String key1 = (String) iterator1.next();
                    map.put(key1, json2.getString(key1));
                }
            }
        }
        map.remove("info");
        return map;
    }
}
