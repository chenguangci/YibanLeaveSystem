package com.yiban.servlet.studentServlet;

import com.yiban.bean.LeaveContent;
import com.yiban.service.student.GetMyLeave;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/MyLeave.action")
public class MyLeave extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GetMyLeave myLeave = new GetMyLeave();
        Map<String,String> info = (Map<String, String>) request.getAttribute("info");
        if (info!=null) {
            List<LeaveContent> contents = myLeave.getMyLeave(info.get("yb_studentid"));
            JSONArray array = JSONArray.fromObject(contents);
            PrintWriter out = response.getWriter();
            out.print(array.toString());
            out.flush();
            out.close();
        }
//        PrintWriter out = response.getWriter();
//        DaoTest test = new DaoTest();
//        String str = test.testContentDao();
//        System.out.println(str);
//        out.write(str);
//        out.flush();
//        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
