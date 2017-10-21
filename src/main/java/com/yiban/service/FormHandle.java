package com.yiban.service;

import com.yiban.dao.SearchTeacherByStudentId;

public class FormHandle {
    private String teacherId;
    private String studentId;

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTeacherId() {
        String teacherId;
        /*模拟一个学生id*/
        studentId = "201524133114";
        String Id = studentId.substring(0,studentId.length()-2);
        SearchTeacherByStudentId search = new SearchTeacherByStudentId();
        teacherId = search.searchTeacherByStudentId(Id);
        if (teacherId!=null){
            return teacherId;
        } else {
            return "error";
        }
    }

    public void getResult(){
        /*发送信件*/
        SendLetter sendLetter = new SendLetter();
        String str = sendLetter.sendLetter();

    }


}
