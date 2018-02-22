package com.yiban.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassMapper {
    String searchTeacherByStudentId(String id);
    List<String> searchTeacher(String id);
}
