package com.yiban.dto;

/**
 * 学生提交请假要求后的结果
 */
public class Result{
    //提交是否成功
    private boolean success;
    //错误信息
    private String msg;

    public Result(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
