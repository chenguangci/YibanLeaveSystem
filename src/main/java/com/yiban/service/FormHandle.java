package com.yiban.service;

import com.yiban.bean.LeaveContent;
import com.yiban.dao.ContentDao;
import com.yiban.dao.SearchDao;

public class FormHandle {
    private LeaveContent content;

    private String getTeacherId() {
        String teacherId;
        String Id = content.getStudentId().substring(0,content.getStudentId().length()-2);
        SearchDao search = new SearchDao();
        teacherId = search.searchTeacherByStudentId(Id);
        return teacherId;
    }

    private String getResult(){
        String teacherId = getTeacherId();
        if (teacherId.length()>1) {
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

    public String setInformation(String[] information) {
        content = new LeaveContent();
        content.setStudentId(information[0]);
        content.setName(information[1]);
        content.setDepartment(information[2]);
        content.setMajor(information[3]);
        content.setGrade(Integer.parseInt(information[4]));
        content.setClassNo(Integer.parseInt(information[5]));
        content.setBeginTime(information[6]);
        content.setEndTime(information[7]);
        content.setReason(information[8]);
        return getResult();
    }

}
