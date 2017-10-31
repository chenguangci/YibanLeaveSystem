package com.yiban.service.student;

import com.yiban.bean.LeaveContent;
import com.yiban.dao.ContentDao;
import java.util.List;

public class GetMyLeave {
    /**
     * 查看个人的请假信息
     * @param myId 登陆者的学号
     * @return 个人的请假信息
     */
    public List<LeaveContent> getMyLeave(String myId){
        ContentDao contentDao = new ContentDao();
        return contentDao.myLeave(myId);
    }
}
