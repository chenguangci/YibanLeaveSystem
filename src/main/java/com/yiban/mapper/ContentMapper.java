package com.yiban.mapper;

import com.yiban.entity.Information;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentMapper {
    /*
     * 添加信息
     */
    int addContent(Information content);
    /*
     * 同意请假的情况下更新操作
     */
    int updateLeave(@Param("id")long id, @Param("status")int status, @Param("code")String code);
    /*
     * 不同意请假的情况下更新操作
     */
    int updateLeaveWithoutCode(@Param("id")long id, @Param("status")int status);

    /*
     * 根据请假信息的id查找请假人的易班id
     */
    String selectYibanId(long id);
    /*
     * 根据请假信息查找请假人的学生id
     */
    String selectStudentId(long id);
    /*
     * 页数查询
     */
    int totalNumber(String id);

    /**
     * 
            根据id查询filePath
     */
    public String selectFilePath(long id);
    
    /**
     * 根据id查找Information
   
     */
    Information selectInformation(long id);
    
    String selectMonitor(long id);
    List<Information> selectLeaves(String id);
    List<Information> selectAll(@Param("id") String id, @Param("limit") int limit, @Param("page") int page);
    List<Information> myLeaves(String myId);
    List<Information> todayLeaves(@Param("id") String id, @Param("today") String today);
    List<Information> selectByType(@Param("id") String id, @Param("type") String type);
}
