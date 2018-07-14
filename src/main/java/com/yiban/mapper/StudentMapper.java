package com.yiban.mapper;

import com.yiban.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {
    Student select(String id);
    int insert(Student student);
    Student selectStudentNameAndClass(String studentId);
    int updateStudentBaseInfo(Student student);
}
