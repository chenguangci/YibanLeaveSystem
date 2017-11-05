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
import java.util.Map;

@WebServlet(value = "/selectAll.action")
public class SelectAllServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String> info = (Map<String, String>) request.getAttribute("info");
        if (info!=null && info.get("yb_employid")!=null && !"".equals(info.get("yb_employid").trim())){
            LeaveHandle handle = new LeaveHandle();
            List<LeaveContent> contents = handle.selectAll(info.get("yb_userid"));
            /*
            * 转为json,返回给前端页面
            * */
            JSONArray array = JSONArray.fromObject(contents);
            PrintWriter out = response.getWriter();
            response.setCharacterEncoding("UTF-8");
            out.write(array.toString());
            out.flush();
            out.close();
        }
    }
}
