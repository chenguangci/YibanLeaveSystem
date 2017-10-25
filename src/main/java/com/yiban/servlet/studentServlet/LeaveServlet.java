package com.yiban.servlet.studentServlet;

import com.yiban.service.FormHandle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LeaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String[] information = new String[8];
        information[0] = request.getParameter("id");
        information[1] = request.getParameter("name");
        information[2] = request.getParameter("telephone");
        information[3] = request.getParameter("department");
        information[4] = request.getParameter("major");
        information[5] = request.getParameter("beginTime");
        information[6] = request.getParameter("endTime");
        information[7] = request.getParameter("reason");
        /**
         * 表单处理
         * */
        FormHandle handle = new FormHandle();
        String result = handle.setInformation(information);
        System.out.println(result);
        //request.getRequestDispatcher("/WEB-INF/zqu/leave/successful.jsp").forward(request,response);
    }
}
