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
import org.springframework.transaction.annotation.Transactional;

/**
 * 处理学生页面传过来的表单信息
 */
@Service
public class FormHandle {

    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private SendLetter sendLetter;

    private Logger logger = LoggerFactory.getLogger(FormHandle.class);


    /**
     * @return 获取该学生所对应辅导员的id
     */
    private String getTeacherId(String studentId) {
        return classMapper.searchTeacherByStudentId(studentId.substring(0, studentId.length() - 2));
    }

    /**
     * @return 获取该学生对应班主任的易班的id
     */
    private String getDeanId(String studentId){
        return classMapper.searchDeanByStudentId(studentId.substring(0,studentId.length() -2));
    }

    /**
     * 获取表单信息，封装进information对象中，发送请求
     *
     * @param information 请假信息
     */
    @Transactional
    public void setInfoAndSend(Information information) throws SendException, RequestInfoException, ReSetTokenException, UnknownError, SystemRunTimeException {
        String teacherId = getTeacherId(information.getStudentId());
        String deanId =getDeanId(information.getStudentId());
//        String teacherId = "10849451";
//        String deanId="12020202";
        if ((teacherId != null && !"".equals(teacherId))&&(deanId != null && !"".equals(deanId))) {
            //发送信件前先将数据加到数据库
            if (contentMapper.addContent(information) > 0) {
                sendLetter.send(teacherId);
                sendLetter.send(deanId);
            } else {
                logger.error("添加请假信息发生错误，需要添加的请假信息为：{}", information.toString());
                throw new SystemRunTimeException("系统发生异常，请稍后重试");
            }
        } else {
            logger.error("找不到对应的教师id");
            throw new UnknownError("找不到对应的教师id");
        }

    }
}
