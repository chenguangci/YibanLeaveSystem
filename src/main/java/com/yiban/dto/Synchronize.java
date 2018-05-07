package com.yiban.service;

import com.yiban.dto.Dictionary;
import com.yiban.dto.Result;
import com.yiban.service.teacher.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>Title:同步方法</p>
 * <p>Description:防止班主任与辅导员同时操作</p> *
 * @author 郑达成
 *
 */
@Component
public class Synchronize {

    @Autowired
    private LeaveService leaveHandle

    private String yibanId;
    private Integer id;
    private Integer status;

    public Synchronize(String yiBanId, Integer id, Integer status) {
        this.id = id;
        this.status = status;
        this.yibanId = yiBanId;
    }

    public Result updateStatus() {
        if (yibanId != null && !"".equals(yibanId)) {
            synchronized (this) {
                return leaveHandle.updateLeave(id, status,yibanId);
            }
        }
        return new Result(Dictionary.FAIL_OPERATION);
    }

}
