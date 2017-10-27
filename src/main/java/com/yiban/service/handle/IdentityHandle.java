package com.yiban.service.handle;

import com.yiban.dao.SearchDao;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class IdentityHandle {
    private String userid;
    /**
     * 判断授权用户身份
     * @param text 授权后返回的json
     * @return -1：返回的json为空  0：请求失败  1：授权用户为学生  2：授权用户为老师
     */
    public int judge(String text){
        if (text==null){
            return -1;
        }
        JSONObject json = JSONObject.fromObject(text);
        Iterator iterator = json.keys();
        Map<String,String> map = new HashMap<String, String>();
        while (iterator.hasNext()){
            String str = (String) iterator.next();
            map.put(str,json.getString(str));
        }
        String id = map.get("userid");
        if (id!=null){
            //判断身份
            userid = id;
            SearchDao searchDao = new SearchDao();
            List<String> teachers = searchDao.searchTeacher(id);
            if (teachers.size()==0){
                return 1;
            } else {
                return 2;
            }
        } else {
            return 0;
        }
    }
    public String getUserid(){
        return userid;
    }
}
