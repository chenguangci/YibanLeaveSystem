package com.yiban.mapper;

import com.yiban.bean.LeaveContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContentMapper {
    void addContent(LeaveContent content);
    List<LeaveContent> selectLeaves(String id);
    List<LeaveContent> selectAll(String id);
    List<LeaveContent> myLeaves(String myId);
    List<LeaveContent> todayLeaves(@Param("id") String id, @Param("today") String today);
    List<LeaveContent> selectByType(@Param("id") String id, @Param("type") String type);
}
