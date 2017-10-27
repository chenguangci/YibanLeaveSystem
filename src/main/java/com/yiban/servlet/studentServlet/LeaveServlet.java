package com.yiban.servlet.studentServlet;

import com.yiban.service.student.FormHandle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 处理学生业务
 */
public class LeaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String[] information = new String[9];
        information[0] = request.getParameter("id");
        information[1] = request.getParameter("name");
        information[2] = request.getParameter("telephone");
        information[3] = request.getParameter("department");
        information[4] = request.getParameter("major");
        information[5] = request.getParameter("beginTime");
        information[6] = request.getParameter("endTime");
        information[7] = request.getParameter("reason");
        information[8] = (String)session.getAttribute("userid");
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
