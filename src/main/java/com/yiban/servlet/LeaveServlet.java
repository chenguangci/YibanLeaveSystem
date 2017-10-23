package com.yiban.servlet;

import com.yiban.service.FormHandle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LeaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String[] information = new String[9];
        information[0] = request.getParameter("id");
        information[1] = request.getParameter("name");
        information[2] = request.getParameter("department");
        information[3] = request.getParameter("major");
        information[4] = "2015";
        information[5] = "1";
        information[6] = request.getParameter("beginTime");
        information[7] = request.getParameter("endTime");
        information[8] = request.getParameter("reason");
        /**
         * 表单处理
         * */
        FormHandle handle = new FormHandle();
        handle.setInformation(information);
        System.out.println(handle.getResult());
        request.getRequestDispatcher("/zqu/leave/successful.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
