package com.yiban.service.handle;

import com.yiban.entity.Student;
import com.yiban.mapper.ClassMapper;
import com.yiban.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class IdentityHandle {

    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private StudentMapper studentMapper;

    private Logger logger = LoggerFactory.getLogger(IdentityHandle.class);

    private static final String salt = "ZQUYiBanJiShuBu666666";

    /**
     * 判断授权用户身份
     * @param id 授权后返回的json
     * @return  0：请求失败  1：授权用户为学生  2：授权用户为老师
     */
    public boolean isTeacher(String id) {
        return classMapper.searchTeacher(id).size() > 0;
    }

    public Student select(String id){
        return studentMapper.select(id);
    }

    public void insert(Student student) {
        if (studentMapper.insert(student) == 0)
            logger.error("添加学生信息出现失败，学生信息：{}",student.toString());
    }

    public String key(String yibanId) {
        String key = yibanId+'/'+salt;
        String md5 = DigestUtils.md5DigestAsHex(key.getBytes());
        logger.info("md5的值：{}", md5);
        return md5;
    }
}
