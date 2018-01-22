package com.yiban.service.handle;

import com.yiban.bean.Student;
import com.yiban.mapper.ClassMapper;
import com.yiban.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdentityHandle {

    private ClassMapper classMapper;
    private StudentMapper studentMapper;
    @Autowired
    public IdentityHandle(ClassMapper classMapper, StudentMapper studentMapper){
        this.classMapper = classMapper;
        this.studentMapper = studentMapper;
    }

    /**
     * 判断授权用户身份
     *
     * @param id 授权后返回的json
     * @return  0：请求失败  1：授权用户为学生  2：授权用户为老师
     */
    public int judge(String id) {
        if (id == null)
            return 0;
        //判断身份
        List<String> teachers = classMapper.searchTeacher(id);
        if (teachers.size() == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    public Student select(String id){
        return studentMapper.select(id);
    }

    public void insert(Student student) {
        studentMapper.insert(student);
    }

}
