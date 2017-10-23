package com.yiban.service;

import com.yiban.bean.LeaveContent;
import com.yiban.dao.ContentDao;
import com.yiban.dao.SearchDao;

public class FormHandle {
    private LeaveContent content;

    private String getTeacherId() {
        String teacherId;
//        测试，模拟一个学生id
        String Id = "201524133114".substring(0,"201524133114".length()-2);
//        String Id = content.getStudentId().substring(0,content.getStudentId().length()-2);
        SearchDao search = new SearchDao();
        teacherId = search.searchTeacherByStudentId(Id);
        return teacherId;
    }

    public String getResult(){
        String teacherId = getTeacherId();
        if (!"error".equals(teacherId)) {
            /*发送信件前先将数据加到数据库*/
            ContentDao contentDao = new ContentDao();
            if (!contentDao.addContent(content)){
                return "插入数据失败";
            }
            SendLetter sendLetter = new SendLetter();
            if (sendLetter.send(teacherId)){
                return "发送成功";
            } else {
                return "发送信件失败，请联系易班工作人员";
            }

        } else {
            return "找不到您的班主任或辅导员，请检查您的学号";
        }

    }

    public void setInformation(String[] information) {
        content = new LeaveContent();
//        模拟插入学号
//        content.setStudentId("201524133114");
        content.setStudentId(information[0]);
        content.setName(information[1]);
        content.setDepartment(information[2]);
        content.setMajor(information[3]);
        content.setGrade(Integer.parseInt(information[4]));
        content.setClassNo(Integer.parseInt(information[5]));
        content.setBeginTime(information[6]);
        content.setEndTime(information[7]);
        content.setReason(information[8]);
    }

    /**
     * 测试成功
     */
    public static void main(String[] args){
        FormHandle handle = new FormHandle();
        String[] information = new String[9];
        information[0] = "201524133102";
        information[1] = "小明";
        information[2] = "音乐学院";
        information[3] = "音乐";
        information[4] = "2016";
        information[5] = "1";
        information[6] = "2017-10-24 00:00:00";
        information[7] = "2017-10-26 00:00:00";
        information[8] = "测试";
        handle.setInformation(information);
        System.out.println(handle.getResult());
    }

}
