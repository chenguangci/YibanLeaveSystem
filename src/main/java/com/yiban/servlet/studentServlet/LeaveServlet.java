package com.yiban.servlet.studentServlet;

import com.yiban.service.student.FormHandle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 处理学生业务
 */
@WebServlet(value = "/toLeave.action")
public class LeaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String[] information = new String[8];
        information[0] = (String) session.getAttribute("studentId");
        information[1] = (String) session.getAttribute("studentName");
        information[2] = request.getParameter("telephone");
        information[3] = (String) session.getAttribute("department");
        information[4] = (String) session.getAttribute("major");
        information[5] = request.getParameter("beginTime");
        information[6] = request.getParameter("endTime");
        information[7] = request.getParameter("reason");
        boolean legal = true;
        for (String a:information){
            if (a==null)
                legal = false;
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (legal) {
            /**
             * 表单处理
             * */
            FormHandle handle = new FormHandle();
            String json = "{\"status\":" + "\"" + handle.setInformation(information) + "\"}";
            System.out.println(json);
            out.print(json);
            out.flush();
            out.close();
        } else {
            String json = "{\"status\":" + "\"" + "请将您的信息填写完整" + "\"}";
            out.print(json);
            out.flush();
            out.close();
        }
    }
}
