package com.yiban.service.teacher;

import com.yiban.dto.Dictionary;
import com.yiban.dto.Result;
import com.yiban.service.teacher.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>Title:同步方法</p>
 * <p>Description:防止班主任与辅导员同时操作</p>
 * @author 郑达成
 */
@Service
public class Synchronize {
    @Autowired
    private LeaveService leaveService;

    public Result updateStatus(String yiBanId , long id, Integer status){
        if (yiBanId!=null&&!"".equals(yiBanId)){
            synchronized (this){
                return leaveService.updateLeave(id,status,yiBanId);
            }
    }
        return new Result(Dictionary.DATA_LOSS);
    }
}
