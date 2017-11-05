package com.yiban.servlet;

import cn.yiban.open.Authorize;
import com.yiban.service.handle.GetInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

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
        //授权验证
        Authorize authorize = new Authorize(appKey,appSecret);
        String code = request.getParameter("code");
        if (code==null||code.isEmpty()) {
            String url = authorize.forwardurl(callbackUrl, "test", Authorize.DISPLAY_TAG_T.WEB);
            response.sendRedirect(url);
        } else {
            String text = authorize.querytoken(code,callbackUrl);
            String accessToken = text.substring(17,57);
            //获取学号姓名等关键信息
            GetInfo info = new GetInfo();
            Map<String,String> myInfo = info.getMyInfo(accessToken);
            //该用户已经完成校方认证
            if ("success".equals(myInfo.get("status"))) {
                request.setAttribute("info",myInfo);
                if (myInfo.get("yb_studentid")!=null) {
                    //跳转到学生页面，传值
                    request.getRequestDispatcher("/WEB-INF/zqu/student/test.jsp").forward(request, response);

                } else if (myInfo.get("yb_employid")!=null) {
                    //跳转到教师页面
                    request.getRequestDispatcher("WEB-INF/zqu/teacher/teacher.html").forward(request,response);
                }
            } else {
                //提示完成校方认证
                request.getRequestDispatcher("/WEB-INF/zqu/student/false.jsp").forward(request,response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
