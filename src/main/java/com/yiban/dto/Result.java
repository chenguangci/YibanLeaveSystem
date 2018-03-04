package com.yiban.dto;

/**
 * 学生提交请假要求后的结果
 */
public class Result<T>{
    //提交是否成功
    private boolean success;
    //错误信息
    private String msg;
    //数据对象
    private T data;

    public Result(Dictionary dictionary) {
        this.success = dictionary.isSuccess();
        this.msg = dictionary.getStateInfo();
    }

    public Result(Dictionary dictionary, T data) {
        this.success = dictionary.isSuccess();
        this.msg = dictionary.getStateInfo();
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
