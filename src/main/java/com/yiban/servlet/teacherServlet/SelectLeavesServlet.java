package com.yiban.servlet.teacherServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * 查询请假学生信息
 */
public class SelectLeavesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        /*从session中获取教师的易班id*/
        String json = (String)session.getAttribute("test");

        System.out.println("json:"+json);
        req.getRequestDispatcher("/zqu/leave/successful.jsp").forward(req,resp);
        //LeaveHandle handle = new LeaveHandle();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
