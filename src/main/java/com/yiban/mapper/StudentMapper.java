package com.yiban.mapper;

import com.yiban.bean.Student;

public interface StudentMapper {
    Student select(String id);
    void insert(Student student);
}
