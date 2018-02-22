package com.yiban.service.teacher;

import com.yiban.entity.Information;
import com.yiban.mapper.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LeaveHandle {
    private ContentMapper contentMapper;
    @Autowired
    public LeaveHandle(ContentMapper contentMapper){
        this.contentMapper = contentMapper;
    }
    /**
     * 查询请假的学生信息
     * @param id 辅导员或班主任的易班ID
     * @return 请假详细信息
     */
    public List<Information> selectLeaves(String id){
        return contentMapper.selectLeaves(id);
    }

    /**
     * 查询所有请假人员
     * @param id 班主任或者辅导员的ID
     * @return 请假信息的集合
     */
    public List<Information> selectAll(String id){
        return contentMapper.selectAll(id);
    }

    /**
     * 查看班级当天请假人数
     * @param id 班主任或者辅导员的ID
     * @return 当天处于请假状态的学生的请假详细信息
     */
    public List<Information> todayLeaves(String id){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String today = format.format(date);
        return contentMapper.todayLeaves(id,today);
    }

    /**
     * 根据请假类型查询（事假或病假）
     * @param id 辅导员或班主任的易班id
     * @param type 类型
     * @return 相应的请假信息
     */
    public List<Information> selectByType(String id,String type){
        return contentMapper.selectByType(id,type);
    }
}
