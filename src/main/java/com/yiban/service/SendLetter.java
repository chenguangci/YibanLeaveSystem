package com.yiban.service;

import com.yiban.bean.Token;
import com.yiban.dao.TokenDao;
import net.sf.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class SendLetter {
    private static final String appKey = "6e5022b516e51935";
    private static final String appSecret = "7eafe47e5c585ef1ad6b3e3bd3aff408";
    private static final String myId = "10849451";
    private static final String CONTENT = "尊敬的老师，您有一学生申请请假，详情请登陆易班请假系统查看";
    private SendRequest request = new SendRequest();
    private String accessToken ;


    /**
     * 解析json字符
     */
    boolean send(String teacherId){
        getAccessToken();
        String str = sendLetter(teacherId);
        if ("error".equals(str)){
            //重新获取授权，再次发送请求
//            resetToken();
//            str = sendLetter(teacherId);
//            return "success".equals(str);
            return false;
        } else {
            return true;
        }
    }
    /**
     * 调用sendPost()发送请求
     * @return 返回json
     */
    private String sendLetter(String teacherId){
        String param="access_token="+accessToken+"&to_yb_uid="+teacherId+"&content="+CONTENT+"&template=user";
        String url = "https://openapi.yiban.cn/msg/letter";
        String str = request.sendPost(url, param);
        System.out.println("送信时返回的json："+str);
        JSONObject object = JSONObject.fromObject(str);
        Iterator iterator = object.keys();
        Map<String,String> map = new HashMap<String, String>();
        while (iterator.hasNext()){
            String key = (String) iterator.next();
            map.put(key,object.getString(key));
        }
        return map.get("status");
    }

    /**
     * 重置授权,返回新的access_token
     * 将新的access_token存入数据库
     */
    private void resetToken(){
        String param = "client_id="+appKey+"&client_secret="+appSecret+"&dev_uid="+myId;
        String url = "https://openapi.yiban.cn/oauth/reset_token";
        String json = request.sendPost(url,param);
        System.out.println("重置授权到返回的json："+json);
        JSONObject jsonObject = JSONObject.fromObject(json);
        Iterator iterator = jsonObject.keys();
        Map<String,String> map = new HashMap<String, String>();
        while (iterator.hasNext()){
            String key = (String) iterator.next();
            map.put(key,jsonObject.getString(key));
        }
        this.accessToken = map.get("access_token");
        //插入新的access_token
        Token token = new Token();
        token.setTokenType("resetToken");
        token.setToken(this.accessToken);
        TokenDao tokenDao = new TokenDao();
        tokenDao.addToken(token);
    }
    /**
     * 需定期获取access_token
     * @return
     */
    private void getAccessToken() {
        TokenDao tokenDao = new TokenDao();
        accessToken = tokenDao.selectToken("resetToken");
    }
}
