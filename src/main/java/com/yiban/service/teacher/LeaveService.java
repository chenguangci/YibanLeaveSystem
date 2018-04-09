package com.yiban.service.teacher;

import com.yiban.dto.Dictionary;
import com.yiban.dto.Result;
import com.yiban.entity.Information;
import com.yiban.exception.ReSetTokenException;
import com.yiban.exception.RequestInfoException;
import com.yiban.exception.SendException;
import com.yiban.exception.SystemRunTimeException;
import com.yiban.mapper.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private LeaveHandle leaveHandle;

    /**
     * 查询请假的学生信息
     *
     * @param id 辅导员或班主任的易班ID
     * @return 请假详细信息
     */
    public List<Information> selectLeaves(String id) {
        return contentMapper.selectLeaves(id);
    }

    /**
     * 辅导员对请假信息进行操作
     *
     * @param id     请假信息的id  请假状态：（-1：拒绝，0：待审核，1：已同意未销假，2：已销假）
     * @param status 请假状态更新
     * @return 更新结果
     */
    public Result updateLeave(long id, int status) {
        if (status < -1 || status > 2) {
            return new Result(Dictionary.ILLEGAL_OPERATION);
        } else {
            try {
                if (status == -1) {
                    //拒绝
                    return leaveHandle.disagree(id);
                } else if (status == 0) {
                    //待审核状态，初始状态
                    return leaveHandle.waiting(id);
                } else if (status == 1) {
                    //同意请假，发送信息给请假人再转发给班长
                    return leaveHandle.agreeLeave(id);
                } else {
                    //销假操作
                    return leaveHandle.back(id);
                }
            } catch (SendException | RequestInfoException | ReSetTokenException e1) {
                return new Result(Dictionary.SEND_FAIL);
            } catch (SystemRunTimeException e2) {
                return new Result(Dictionary.SYSTEM_ERROR);
            }
        }
    }

    /**
     * 查询所有请假人员
     *
     * @param id 班主任或者辅导员的ID
     * @return 请假信息的集合
     */
    public List<Information> selectAll(String id, int limit, int page) {
        return contentMapper.selectAll(id, limit, page);
    }

    /**
     * 查看班级当天请假人数
     *
     * @param id 班主任或者辅导员的ID
     * @return 当天处于请假状态的学生的请假详细信息
     */
    public List<Information> todayLeaves(String id) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String today = format.format(date);
        return contentMapper.todayLeaves(id, today);
    }

    /**
     * 根据请假类型查询（事假或病假）
     *
     * @param id   辅导员或班主任的易班id
     * @param type 类型
     * @return 相应的请假信息
     */
    public List<Information> selectByType(String id, String type) {
        return contentMapper.selectByType(id, type);
    }

    /**
     * 页数查找
     */
    public int totalNumber(String yibanId){
        return contentMapper.totalNumber(yibanId);
    }
}
