package com.yiban.servlet;

import cn.yiban.open.Authorize;
import com.yiban.service.handle.IdentityHandle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizeServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String appKey  = "6e5022b516e51935";
        String appSecret = "7eafe47e5c585ef1ad6b3e3bd3aff408";
        String callbackUrl = "http://localhost:8080/Leave.action";
        Authorize authorize = new Authorize(appKey,appSecret);
        String code = request.getParameter("code");
        if (code==null||code.isEmpty()) {
            String url = authorize.forwardurl(callbackUrl, "test", Authorize.DISPLAY_TAG_T.WEB);
            response.sendRedirect(url);
        } else {
            String text = authorize.querytoken(code,callbackUrl);
            System.out.println("请求授权后的text:"+text);
            IdentityHandle handle = new IdentityHandle();
            int id = handle.judge(text);
            String userid = handle.getUserid();
            System.out.println(id+" "+userid);
            if (id<1){
                //跳转到授权失败页面
                System.out.println(id);
                request.getRequestDispatcher("/WEB-INF/zqu/index.jsp").forward(request,response);
            }else if(id==1){
                //存好用户userid
                session.setAttribute("userid",userid);
                //跳转到学生页面
                session.setAttribute("userType",1);
                request.getRequestDispatcher("/WEB-INF/zqu/student/test.jsp").forward(request,response);
            } else {
                //存好用户userid
                session.setAttribute("userid",userid);
                //跳转到教师页面
                session.setAttribute("userType",2);
                request.getRequestDispatcher("WEB-INF/zqu/teacher/teacher.html").forward(request,response);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
