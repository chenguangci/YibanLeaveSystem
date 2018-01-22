package com.yiban.controller;

import com.yiban.bean.LeaveContent;
import com.yiban.bean.Student;
import com.yiban.service.student.FormHandle;
import com.yiban.service.student.GetMyLeave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    private FormHandle formHandle;
    private GetMyLeave myLeave;
    //学生信息的数量
    private static final int INFO_NUMBER = 9;
    @Autowired
    public StudentController(FormHandle formHandle, GetMyLeave myLeave) {
        this.formHandle = formHandle;
        this.myLeave = myLeave;
    }

    //获取个人的请假信息
    @RequestMapping(value = "/myInfo")
    public ResponseEntity<List<LeaveContent>> myInfo(HttpSession session) {
        //获取学号
        String id = (String) session.getAttribute("studentId");
        return new ResponseEntity<List<LeaveContent>>(myLeave.getMyLeave(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/leave")
    public String leave(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Student student = (Student)session.getAttribute("student");
        String[] info = new String[INFO_NUMBER];
        info[0] = student.getStudentid();
        info[1] = student.getName();
        info[2] = request.getParameter("telephone");
        info[3] = student.getDepartment();
        info[4] = student.getClassname();
        info[5] = request.getParameter("begin_time");
        info[6] = request.getParameter("end_time");
        info[7] = request.getParameter("num");
        info[8] = request.getParameter("reason");
        formHandle.setInformation(info);
        return null;
    }
}
