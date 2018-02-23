package com.yiban.controller;

import com.yiban.dto.Result;
import com.yiban.entity.Information;
import com.yiban.entity.Student;
import com.yiban.exception.ReSetTokenError;
import com.yiban.exception.RequestInfoError;
import com.yiban.exception.SendError;
import com.yiban.exception.SystemRunTimeError;
import com.yiban.service.student.FormHandle;
import com.yiban.service.student.GetMyLeave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
//TODO 封装返回结果
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private FormHandle formHandle;
    @Autowired
    private GetMyLeave myLeave;

    //获取个人的请假信息
    @RequestMapping(value = "/myInfo")
    @ResponseBody
    public ResponseEntity<List<Information>> myInfo(HttpSession session) {
        //获取学号
        String id = (String) session.getAttribute("studentId");
        return new ResponseEntity<>(myLeave.getMyLeave(id), HttpStatus.OK);
    }

    //TODO 封装所有返回信息
    @RequestMapping(value = "/{yibanId}")
    @ResponseBody
    public Result leave(HttpServletRequest request, @PathVariable(value = "yibanId")String yibanId) {
        try {
            HttpSession session = request.getSession();
            Student student = (Student)session.getAttribute("student");
            Information information = new Information();
            information.setStudentId(student.getStudentId());
            information.setBeginTime(request.getParameter("begin_time"));
            information.setEndTime(request.getParameter("end_time"));
            information.setNumber(Integer.parseInt(request.getParameter("num")));
            information.setReason(request.getParameter("reason"));
            information.setStudentId(student.getStudentId());
            formHandle.setInfoAndSend(information);
            return new Result(true, "发送成功，等待辅导员的回复");
        } catch (SendError | RequestInfoError | ReSetTokenError e1) {
            return new Result(false, "发送消息失败，请稍后重试");
        } catch (UnknownError e2) {
            return new Result(false, "找不到您的辅导员或者班主任，请联系管理员");
        } catch (SystemRunTimeError e3) {
            return new Result(false, "系统发生异常，请稍后重试");
        }

    }
}
