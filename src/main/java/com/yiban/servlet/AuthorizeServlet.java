package com.yiban.servlet;

import cn.yiban.open.Authorize;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appKey  = "6e5022b516e51935";
        String appSecret = "7eafe47e5c585ef1ad6b3e3bd3aff408";
        String callbackUrl = "http://localhost:8080/zqu/begin.jsp";
        Authorize authorize = new Authorize(appKey,appSecret);
        String code = request.getParameter("code");
        if (code==null||code.isEmpty()){
            String url = authorize.forwardurl(callbackUrl,"test",Authorize.DISPLAY_TAG_T.WEB);
            System.out.println(url);
            request.getRequestDispatcher(url);
        } else {
            String test = authorize.querytoken(code,callbackUrl);
            System.out.println(test);
            request.setAttribute("test",test);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
