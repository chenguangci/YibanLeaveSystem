package com.yiban.servlet;

import cn.yiban.open.Authorize;
import com.yiban.service.handle.GetInfo;
import com.yiban.service.handle.IdentityHandle;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AuthorizeServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String appKey  = "6e5022b516e51935";
        String appSecret = "7eafe47e5c585ef1ad6b3e3bd3aff408";
        String callbackUrl = "http://localhost:8080/Leave.action";
        Authorize authorize = new Authorize(appKey,appSecret);
        String code = request.getParameter("code");
        if (code==null||code.isEmpty()) {
            String url = authorize.forwardurl(callbackUrl, "test", Authorize.DISPLAY_TAG_T.WEB);
            response.sendRedirect(url);
        } else {
            String text = authorize.querytoken(code,callbackUrl);
            JSONObject object = JSONObject.fromObject(text);
            Iterator iterator = object.keys();
            Map<String,String> map = new HashMap<String, String>();
            while (iterator.hasNext()){
                String key = (String) iterator.next();
                map.put(key,object.getString(key));
            }
            String userid = map.get("userid");
            String accessToken = map.get("access_token");
            //判断身份
            IdentityHandle handle = new IdentityHandle();
            int id = handle.judge(userid);

            if (id<1){
                //跳转到授权失败页面
                System.out.println(id);
                request.getRequestDispatcher("/WEB-INF/zqu/index.jsp").forward(request,response);
            }else if(id==1){
                //获取学号姓名等关键信息
                GetInfo info = new GetInfo();
                map = info.getMyInfo(accessToken);
                //该用户已经完成校方认证
                if ("success".equals(map.get("status"))) {
                    session.setAttribute("studentId", map.get("yb_studentid"));
                    session.setAttribute("studentName",map.get("yb_realname"));
                    session.setAttribute("department",map.get("yb_collegename"));
                    session.setAttribute("major",map.get("yb_classname"));
                    //跳转到学生页面
                    session.setAttribute("userType", id);
                    request.getRequestDispatcher("/WEB-INF/zqu/student/test.jsp").forward(request, response);
                }else {
                    //提示完成校方认证
                }
            } else {
                //存好用户userid
                session.setAttribute("userid",userid);
                //跳转到教师页面
                session.setAttribute("userType",id);
                request.getRequestDispatcher("WEB-INF/zqu/teacher/teacher.html").forward(request,response);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
