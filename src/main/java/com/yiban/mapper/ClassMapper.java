package com.yiban.mapper;

import java.util.List;

public interface ClassMapper {
    String searchTeacherByStudentId(String id);
    List<String> searchTeacher(String id);
}
