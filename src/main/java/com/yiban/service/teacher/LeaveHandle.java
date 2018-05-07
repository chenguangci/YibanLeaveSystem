package com.yiban.service.teacher;

import com.yiban.dto.Dictionary;
import com.yiban.dto.Result;
import com.yiban.entity.Student;
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
import org.springframework.util.DigestUtils;

/**
 * 具体处理请假操作
 */
@Service
public class LeaveHandle {

    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private SendLetter sendLetter;

    private Logger logger = LoggerFactory.getLogger(LeaveHandle.class);

    /**
     * 同意请假操作，发送信息给请假人再转发给班长,生成验证码，
     *
     * @param id 请假信息的id
     * @return 操作结果
     */
    @Transactional
    public Result agreeLeave(long id, String yibanId) throws SendException, RequestInfoException, ReSetTokenException, SystemRunTimeException {
        String studentId = contentMapper.selectStudentId(id);
        //获取辅导员Id
//        String teacherYibanId ="123";
        String teacherYibanId = classMapper.searchTeacherByStudentId(studentId.substring(0, studentId.length() - 2));
        //生成验证码,存入数据库
        String deanYibanId = classMapper.searchDeanByStudentId(studentId.substring(0, studentId.length() - 2));
        if ((contentMapper.updateLeave(id, 1, getCode(id)) > 0) && (yibanId.equals(teacherYibanId))) {
            //发送信息给请假人
            sendLetter.send(contentMapper.selectYibanId(id), "辅导员已同意你的请假，详情请登录肇院请假系统查看");
            //转发信息给班长
            //TODO 具体发送给班长的请假信息还需要考虑
            sendLetter.send(contentMapper.selectMonitor(id), "辅导员向你发送了一条信息，详情请通过肇院请假系统查看");
            return new Result(Dictionary.SUCCESS);
        } else if ((contentMapper.updateLeave(id, 1, getCode(id)) > 0) && (yibanId.equals(deanYibanId))) {
            //发送信息给请假人
            sendLetter.send(contentMapper.selectYibanId(id), "班主任已同意你的请假，详情请登录肇院请假系统查看");
            //转发信息给班长
            //TODO 具体发送给班长的请假信息还需要考虑
            sendLetter.send(contentMapper.selectMonitor(id), "班主任向你发送了一条信息，详情请通过肇院请假系统查看");
            return new Result(Dictionary.SUCCESS);
        } else {
            return new Result(Dictionary.FAIL_OPERATION);
        }

    }

    /**
     * 生成验证码的方法
     *
     * @param id 请假信息的id
     * @return 验证码
     */
    private String getCode(long id) {
        String salt = String.valueOf(id) + "/zqu_jsb";
        String key = DigestUtils.md5DigestAsHex(salt.getBytes()).substring(0, 9);
        logger.info("生成的key值：{}", key);
        return key;
    }

    /**
     * 不同意请假
     *
     * @param id 请假信息的id
     * @return 操作结果
     */
    @Transactional
    public Result disagree(long id, String yibanId) throws SendException, RequestInfoException, ReSetTokenException, SystemRunTimeException {
        String studentId = contentMapper.selectStudentId(id);
        //获取辅导员Id
        String teacherYibanId = classMapper.searchTeacherByStudentId(studentId.substring(0, studentId.length() - 2));
        //生成验证码,存入数据库
        String deanYibanId = classMapper.searchDeanByStudentId(studentId.substring(0, studentId.length() - 2));
        //更新数据库
        if ((contentMapper.updateLeaveWithoutCode(id, -1) > 0) && yibanId.equals(teacherYibanId)) {
            //发送信息给请假人
            sendLetter.send(contentMapper.selectYibanId(id), "抱歉，你的辅导员不同意你的请假，详情请登录肇院请假系统查看");
            return new Result(Dictionary.SUCCESS);
        } else if ((contentMapper.updateLeaveWithoutCode(id, -1) > 0) && yibanId.equals(deanYibanId)) {
            //发送信息给请假人
            sendLetter.send(contentMapper.selectYibanId(id), "抱歉，你的班主任不同意你的请假，详情请登录肇院请假系统查看");
            return new Result(Dictionary.SUCCESS);
        } else {
            return new Result(Dictionary.FAIL_OPERATION);
        }
    }

    /**
     * 待审核操作，初始操作，但一般不会出现这种情况
     *
     * @param id 请假信息的id
     * @return 操作结果
     */
    @Transactional
    public Result waiting(long id) {
        if (contentMapper.updateLeaveWithoutCode(id, 0) > 0)
            return new Result(Dictionary.SUCCESS);
        else
            return new Result(Dictionary.FAIL_OPERATION);
    }

    /**
     * 销假操作
     *
     * @param id 请假信息的id
     * @return 操作结果
     */
    @Transactional
    public Result back(long id) throws SendException, RequestInfoException, ReSetTokenException, SystemRunTimeException {
        if (contentMapper.updateLeaveWithoutCode(id, 2) > 0) {
            //提示销假成功
            sendLetter.send(contentMapper.selectYibanId(id), "销假成功");
            return new Result(Dictionary.SUCCESS);
        } else {
            return new Result(Dictionary.FAIL_OPERATION);
        }
    }

}
