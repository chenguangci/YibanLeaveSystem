package com.yiban.service.student;

import com.yiban.bean.LeaveContent;
import com.yiban.dao.ContentDao;
import java.util.List;

public class GetMyLeave {
    public List<LeaveContent> getMyLeave(String myId){
        ContentDao contentDao = new ContentDao();
        return contentDao.myLeave(myId);
    }
}
