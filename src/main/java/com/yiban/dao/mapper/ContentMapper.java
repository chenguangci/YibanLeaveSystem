package com.yiban.dao.mapper;

import com.yiban.bean.LeaveContent;

import java.util.List;

public interface ContentMapper {
    void addContent(LeaveContent content);
    List<LeaveContent> selectLeaves(String id);
    List<LeaveContent> selectAll(String id);
    List<LeaveContent> myLeaves(String myId);
    List<LeaveContent> todayLeaves(String id,String today);
    List<LeaveContent> selectByType(String id,String type);
}
