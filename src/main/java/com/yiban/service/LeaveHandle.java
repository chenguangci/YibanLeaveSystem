package com.yiban.service;

import com.yiban.bean.LeaveContent;
import com.yiban.dao.ContentDao;

import java.util.List;

public class LeaveHandle {
    private ContentDao contentDao = new ContentDao();
    /**
     * 查询请假的学生信息
     * @param id 辅导员或班主任的易班ID
     * @return 请假详细信息
     */
    public List<LeaveContent> selectLeaves(String id){
        return contentDao.selectLeaves(id);
    }

    public List<LeaveContent> selectAll(String id){
        return contentDao.selectAll(id);
    }

}
