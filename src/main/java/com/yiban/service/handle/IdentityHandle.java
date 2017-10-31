package com.yiban.service.handle;

import com.yiban.dao.SearchDao;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class IdentityHandle {

    /**
     * 判断授权用户身份
     *
     * @param id 授权后返回的json
     * @return  0：请求失败  1：授权用户为学生  2：授权用户为老师
     */
    public int judge(String id) {
        if (id == null)
            return 0;
        //判断身份
        SearchDao searchDao = new SearchDao();
        List<String> teachers = searchDao.searchTeacher(id);
        if (teachers.size() == 0) {
            return 1;
        } else {
            return 2;
        }
    }

}
