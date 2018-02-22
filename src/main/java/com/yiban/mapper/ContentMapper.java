package com.yiban.mapper;

import com.yiban.entity.Information;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentMapper {
    void addContent(Information content);
    List<Information> selectLeaves(String id);
    List<Information> selectAll(String id);
    List<Information> myLeaves(String myId);
    List<Information> todayLeaves(@Param("id") String id, @Param("today") String today);
    List<Information> selectByType(@Param("id") String id, @Param("type") String type);
}
