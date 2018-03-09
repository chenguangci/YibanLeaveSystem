package com.yiban.dto;

/**
 * 字典类，封装所有信息，表示所有常量信息
 */
public enum Dictionary {
    SUCCESS(true,0,"操作成功"),
    SYSTEM_ERROR(false,-1,"系统异常"),
    REQUEST_INFO_FAIL(false,-2,"授权后获取信息失败"),
    RESET_TOKEN_FAIL(false,-3,"重置授权失败"),
    SEND_FAIL(false,-4,"发送信息失败"),
    UNKNOWN_INFO(false,-5,"查找不到需要的信息"),
    DATA_LOSS(false,-6,"确少必要信息或文件"),
    FIND_TEACHER_FAIL(false, -7, "找不到您的辅导员或者班主任，请联系管理员"),
    AUTHENTICATION(false,-8,"未完成校方认证"),
    ILLEGAL_OPERATION(false,-9,"非法操作"),
    FAIL_OPERATION(false,-10,"操作失败"),
    ;
    //是否成功
    private boolean success;
    //消息代码
    private int state;
    //消息内容
    private String stateInfo;

    Dictionary(boolean success, int state, String stateInfo) {
        this.success = success;
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
