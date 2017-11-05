package com.yiban.service.teacher;

import com.yiban.bean.LeaveContent;
import com.yiban.dao.ContentDao;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    /**
     * 查询所有请假人员
     * @param id 班主任或者辅导员的ID
     * @return 请假信息的集合
     */
    public List<LeaveContent> selectAll(String id){
        return contentDao.selectAll(id);
    }

    /**
     * 查看班级当天请假人数
     * @param id 班主任或者辅导员的ID
     * @return 当天处于请假状态的学生的请假详细信息
     */
    public List<LeaveContent> todayLeaves(String id){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String today = format.format(date);
        return contentDao.todayLeaves(id,today);
    }

    /**
     * 根据请假类型查询（事假或病假）
     * @param id 辅导员或班主任的易班id
     * @param type 类型
     * @return 相应的请假信息
     */
    public List<LeaveContent> selectByType(String id,String type){
        return contentDao.selectByType(id,type);
    }
}
