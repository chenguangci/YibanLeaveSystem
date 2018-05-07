package com.yiban.dto;

import com.yiban.service.teacher.LeaveService;


/**
 * <p>Title:同步方法</p>
 * <p>Description:防止班主任与辅导员同时操作</p> *
 *
 * @author 郑达成
 */

public class Synchronize {

    private LeaveService leaveService;
    private String yibanId;
    private Integer id;
    private Integer status;

    public Synchronize(String yiBanId, Integer id, Integer status) {
        this.id = id;
        this.status = status;
        this.yibanId = yiBanId;
    }

    public String getYibanId() {
        return yibanId;
    }

    public void setYibanId(String yibanId) {
        this.yibanId = yibanId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Result updateStatus(){
        if (yibanId!=null&&!"".equals(yibanId)){
            leaveService=new LeaveService();
            synchronized (this){
                return leaveService.updateLeave(id,status,yibanId);
            }
        }
        return new Result(Dictionary.DATA_LOSS);
    }
}
