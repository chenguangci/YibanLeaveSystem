package com.yiban.mapper;

import com.yiban.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {
    Student select(String id);
    void insert(Student student);
}
