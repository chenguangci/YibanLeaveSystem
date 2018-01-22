package com.yiban.controller;

import cn.yiban.open.Authorize;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(value = "/Leave.action")
public class IndexServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appKey  = "6e5022b516e51935";
        String appSecret = "7eafe47e5c585ef1ad6b3e3bd3aff408";
        String callbackUrl = "http://localhost:8080/Leave.action";
        //授权验证
        Authorize authorize = new Authorize(appKey,appSecret);
        String code = request.getParameter("code");
        if (code==null||code.isEmpty()) {
            String url = authorize.forwardurl(callbackUrl, "test", Authorize.DISPLAY_TAG_T.WEB);
            response.sendRedirect(url);
        } else {
//          跳转到StudentController
            JSONObject object = JSONObject.fromObject(authorize.querytoken(code,callbackUrl));
            String accessToken = object.getString("access_token");
            String userId = object.getString("userid");
            HttpSession session = request.getSession();
            session.setAttribute("yibanId", userId);
            session.setAttribute("accessToken", accessToken);
            request.getRequestDispatcher("/toLeave.action").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
