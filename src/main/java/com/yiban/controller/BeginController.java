package com.yiban.controller;

import com.yiban.bean.Student;
import com.yiban.service.handle.GetInfo;
import com.yiban.service.handle.IdentityHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping(value = "/toLeave.action")
public class BeginController {
    private IdentityHandle identityHandle;
    @Autowired
    public BeginController(IdentityHandle identityHandle){
        this.identityHandle = identityHandle;
    }
    @RequestMapping
    public ModelAndView studentIndex(HttpSession session) {
        String yibanId = (String) session.getAttribute("yibanId");
        String accessToken = (String)session.getAttribute("accessToken");
        session.removeAttribute("accessToken");
        Student student = identityHandle.select(yibanId);
        ModelAndView modelAndView = new ModelAndView();
        if (student!=null) {
            modelAndView.addObject("student",student);
            modelAndView.setViewName("student/test");
        } else {
            //数据库中没有该学生信息
            GetInfo info = new GetInfo();
            //发送请求
            Map<String,String> myInfo = info.getMyInfo(accessToken);
            if ("success".equals(myInfo.get("status"))) {
                if (myInfo.get("yb_studentid")!=null || !"".equals(myInfo.get("yb_studentid").trim())) {
                    //跳转到学生页面，传值
                    student = new Student();
                    student.setYibanId(yibanId);
                    student.setStudentid(myInfo.get("yb_studentid"));
                    student.setName(myInfo.get("yb_realname"));
                    student.setDepartment(myInfo.get("yb_collegename"));
                    student.setClassname(myInfo.get("yb_classname"));
                    identityHandle.insert(student);
                    session.setAttribute("student", student);
                    modelAndView.addObject("student",student);
                    modelAndView.setViewName("student/test");
                } else if (myInfo.get("yb_employid")!=null || !"".equals(myInfo.get("yb_employid").trim())) {
                    //跳转到教师页面
                    modelAndView.setViewName("redirect:/teacher.action");
                }
            } else {
                //提示完成校方认证
                modelAndView.setViewName("false");
            }
        }
        return modelAndView;
    }
}
