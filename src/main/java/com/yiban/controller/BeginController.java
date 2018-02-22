package com.yiban.controller;

import cn.yiban.open.Authorize;
import com.yiban.entity.Student;
import com.yiban.service.handle.GetInfo;
import com.yiban.service.handle.IdentityHandle;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping(value = "/leave")
public class BeginController {

    private static final String appKey  = "6e5022b516e51935";
    private static final String appSecret = "7eafe47e5c585ef1ad6b3e3bd3aff408";
    private static final String callbackUrl = "http://localhost:8080/leave/index";

    private IdentityHandle identityHandle;
    @Autowired
    public BeginController(IdentityHandle identityHandle){
        this.identityHandle = identityHandle;
    }

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //授权验证
        Authorize authorize = new Authorize(appKey,appSecret);
        HttpSession session = request.getSession();
        String code = request.getParameter("code");
        System.out.println(code);
        if (code == null || "".equals(code.trim())) {
            System.out.println("*********************jump*****************");
            String url = authorize.forwardurl(callbackUrl, "test", Authorize.DISPLAY_TAG_T.WEB);
            return "redirect:"+url;
        } else {
//          跳转到StudentController
            JSONObject object = JSONObject.fromObject(authorize.querytoken(code,callbackUrl));
            String accessToken = object.getString("access_token");
            String userId = object.getString("userid");
            session.setAttribute("yibanId", userId);
            session.setAttribute("accessToken", accessToken);
            return "redirect:/leave/toLeave";
        }
    }

    @RequestMapping(value = "toLeave")
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
                    student.setStudentId(myInfo.get("yb_studentid"));
                    student.setName(myInfo.get("yb_realname"));
                    student.setDepartment(myInfo.get("yb_collegename"));
                    student.setClassName(myInfo.get("yb_classname"));
                    //identityHandle.insert(student);
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
