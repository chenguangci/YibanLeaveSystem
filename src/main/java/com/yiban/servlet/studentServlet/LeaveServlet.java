package com.yiban.servlet.studentServlet;

import com.yiban.service.student.FormHandle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String,String> info = (Map<String, String>) request.getAttribute("info");
        if (info!=null) {
            String[] information = new String[9];
            information[0] = info.get("yb_studentid");
            information[1] = info.get("yb_realname");
            information[2] = request.getParameter("telephone");
            information[3] = info.get("yb_collegename");
            information[4] = info.get("yb_classname");
            information[5] = request.getParameter("beginTime");
            information[6] = request.getParameter("endTime");
            information[7] = request.getParameter("type");
            information[8] = request.getParameter("reason");

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            for (String anInformation : information) {
                if (anInformation == null || "".equals(anInformation)) {
                    String json = "{\"status\":" + "\"" + "请将您的信息填写完整" + "\"}";
                    out.print(json);
                    out.flush();
                    out.close();
                }
            }

            FormHandle handle = new FormHandle();
            String json = "{\"status\":" + "\"" + handle.setInformation(information) + "\"}";
            System.out.println(json);
            out.print(json);
            out.flush();
            out.close();
        }
    }
}
