package com.yiban.servlet.studentServlet;

import com.yiban.bean.LeaveContent;
import com.yiban.service.student.GetMyLeave;
import com.yiban.test.DaoTest;
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
@WebServlet(value = "/MyLeave.action")
public class MyLeave extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        String userId = (String)session.getAttribute("userid");
//        if (userId!=null){
//            GetMyLeave myLeave = new GetMyLeave();
//            List<LeaveContent> contents = myLeave.getMyLeave(userId);
//            JSONArray array = JSONArray.fromObject(contents);
//            PrintWriter out = response.getWriter();
//            out.print(array.toString());
//            out.flush();
//            out.close();
//        } else {
//            request.getRequestDispatcher("/Leave.action").forward(request,response);
//        }
        PrintWriter out = response.getWriter();
        DaoTest test = new DaoTest();
        String str = test.testContentDao();
        System.out.println(str);
        out.write(str);
        out.flush();
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
