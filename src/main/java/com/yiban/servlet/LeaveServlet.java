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
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        String major = request.getParameter("major");
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        String reason = request.getParameter("reason");
        System.out.println(name+" "+department+" "+major+" "+beginTime+" "+endTime+" "+reason);
        /*表单处理*/
        FormHandle handle = new FormHandle();
        /*
        模拟发送学生id
         */
        handle.setStudentId(id);

        request.getRequestDispatcher("/zqu/leave/successful.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
