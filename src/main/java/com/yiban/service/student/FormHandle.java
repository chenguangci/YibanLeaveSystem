package com.yiban.service.student;

import com.yiban.bean.LeaveContent;
import com.yiban.dao.ContentDao;
import com.yiban.dao.SearchDao;
import com.yiban.service.handle.SendLetter;

/**
 * 处理学生页面传过来的表单信息
 */
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
                return "请检查您填写的信息是否有误";
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
        content.setTelephone(information[2]);
        content.setDepartment(information[3]);
        content.setMajor(information[4]);
        content.setBeginTime(information[5]);
        content.setEndTime(information[6]);
        content.setReason(information[7]);
        content.setUserid(information[8]);
        return getResult();
    }

}
