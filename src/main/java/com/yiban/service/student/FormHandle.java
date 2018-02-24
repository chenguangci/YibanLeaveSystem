package com.yiban.service.student;

import com.yiban.entity.Information;
import com.yiban.exception.ReSetTokenException;
import com.yiban.exception.RequestInfoException;
import com.yiban.exception.SendException;
import com.yiban.exception.SystemRunTimeException;
import com.yiban.mapper.ClassMapper;
import com.yiban.mapper.ContentMapper;
import com.yiban.service.handle.SendLetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 处理学生页面传过来的表单信息
 */
@Service
public class FormHandle {

    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private ClassMapper classMapper;

    private Logger logger = LoggerFactory.getLogger(FormHandle.class);

    private SendLetter sendLetter = new SendLetter();

    /**
     * @return 获取该学生的班主任或者辅导员的id
     */
    private String getTeacherId(String studentId) {
        return classMapper.searchTeacherByStudentId(studentId.substring(0,studentId.length()-2));
    }

    /**
     * 获取表单信息，封装进information对象中，发送请求
     * @param information 请假信息
     */
    public void setInfoAndSend(Information information)  throws SendException, RequestInfoException, ReSetTokenException, UnknownError, SystemRunTimeException {
        //TODO 如何通过学号获取辅导员或班主任的易班ID
        String teacherId = getTeacherId(information.getStudentId());
        if (teacherId != null && !"".equals(teacherId)) {
            //发送信件前先将数据加到数据库
            contentMapper.addContent(information);
            sendLetter.send(teacherId);
        } else {
            logger.error("找不到对应的教师id");
            throw new UnknownError("找不到对应的教师id");
        }
    }
}
