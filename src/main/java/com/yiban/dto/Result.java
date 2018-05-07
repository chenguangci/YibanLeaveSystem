package com.yiban.dto;

import com.yiban.entity.Information;
import com.yiban.entity.Student;

/**
 * 学生提交请假要求后的结果
 */
public class Result{
    //提交是否成功
    private boolean success;
    //错误信息
    private String msg;
    //数据对象
    private Student student=null;
    private Information information=null;
    private String string=null;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Result(Dictionary dictionary) {
        this.success = dictionary.isSuccess();
        this.msg = dictionary.getStateInfo();
    }

    public Result(Dictionary dictionary, Student student) {
        this.success = dictionary.isSuccess();
        this.msg = dictionary.getStateInfo();
        this.student=student;

    }
    public Result(Dictionary dictionary, Information information) {
        this.success = dictionary.isSuccess();
        this.msg = dictionary.getStateInfo();
        this.information=information;

    }
    public Result(Dictionary dictionary, String string) {
        this.success = dictionary.isSuccess();
        this.msg = dictionary.getStateInfo();
        this.string=string;

    }
    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", string=" + string +
                ",student=" + student+
                ",information="+ information +
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


}
