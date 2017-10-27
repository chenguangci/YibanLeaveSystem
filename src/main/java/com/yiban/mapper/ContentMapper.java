package com.yiban.mapper;

import com.yiban.bean.LeaveContent;

import java.util.List;

public interface ContentMapper {
    void addContent(LeaveContent content);
    List<LeaveContent> selectLeaves(String id);
    List<LeaveContent> selectAll(String id);
    List<LeaveContent> myLeaves(String myId);
}
