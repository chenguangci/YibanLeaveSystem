package com.yiban.servlet;

import cn.yiban.open.Authorize;
import com.yiban.service.IdentityHandle;

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
//            session.setAttribute("test",test);
//            String text = (String) session.getAttribute("text");
            IdentityHandle handle = new IdentityHandle();
            int id = handle.judge(text);
            request.getRequestDispatcher("/WEB-INF/zqu/begin.jsp").forward(request,response);
//            if (id<1){
//                //跳转到授权失败页面
//            }else if(id==1){
//                //跳转到学生页面
//            } else {
//                //跳转到教师页面
//            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
