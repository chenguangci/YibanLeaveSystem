package com.yiban.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassMapper {
    //查找学生对应的班主任或辅导员的易班ID
    String searchTeacherByStudentId(String id);
    //查找教师对应的班级
    List<String> searchTeacher(String id);
}
