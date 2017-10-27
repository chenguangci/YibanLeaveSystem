package com.yiban.servlet.teacherServlet;

import com.yiban.bean.LeaveContent;
import com.yiban.service.teacher.LeaveHandle;
import net.sf.json.JSONArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/selectAll.action")
public class SelectAllServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (2 == (Integer) session.getAttribute("userType")){
            LeaveHandle handle = new LeaveHandle();
            List<LeaveContent> contents = handle.selectAll((String)session.getAttribute("userid"));
            /*
            * 转为json,返回给前端页面
            * */
            JSONArray array = JSONArray.fromObject(contents);
            PrintWriter out = response.getWriter();
            response.setCharacterEncoding("UTF-8");
            out.write(array.toString());
            out.flush();
            out.close();
        } else {
            request.getRequestDispatcher("/Leave.action").forward(request,response);
        }
    }
}
