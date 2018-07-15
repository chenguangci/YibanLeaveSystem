package com.yiban.controller;


import cn.yiban.open.Authorize;
import com.yiban.service.handle.IdentityHandle;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AllInterceptor implements HandlerInterceptor {

    @Autowired
    IdentityHandle identityHandle;

    private Logger logger = LoggerFactory.getLogger(AllInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String yibanId = (String) session.getAttribute("yiban_id");
        String md5 = (String) session.getAttribute("yiban_id_key");
        //除了开始以及错误界面外，其他请求应该都包含yibanId以及md5两个值
        if (request.getRequestURI().contains("index") ||request.getRequestURI().contains("error"))
            return true;
        if (md5 == null || yibanId == null || !md5.equals(identityHandle.key(yibanId))) {
            //TODO 跳转到错误提示界面或者授权界面
            logger.error("出现非法请求，跳转至错误页面");
            request.getRequestDispatcher("/leave/error").forward(request,response);
//            http://localhost:8080/student/record/201624133115
            return false;
        } else {
            return true;
        }
//        return true;
//        HttpSession session = request.getSession();
//        String yiban_id = (String) session.getAttribute("yiban_id");
//        if(yiban_id == null || yiban_id.equals(""))
//        {
//            Authorize authorize = new Authorize(appKey, appSecret);
//            String code = request.getParameter("code");
//            logger.info("获取的code：{}", code);
//            if (code == null || "".equals(code.trim())) {
//                String url = authorize.forwardurl(callbackUrl, "test", Authorize.DISPLAY_TAG_T.WEB);
//                response.sendRedirect(url);
//                return false;
//            } else {
//                JSONObject object = JSONObject.fromObject(authorize.querytoken(code, callbackUrl));
//                logger.info("授权后的toke：{}", object);
//                if (object.has("access_token")) {
//                    String userId = object.getString("userid");
//                    if(userId.equals("8118009")){
//                        String accessToken = object.getString("access_token");
//                        session.setAttribute("yiban_id", userId);
//                        session.setAttribute("accessToken", accessToken);
//                        request.getRequestDispatcher("/views/index").forward(request,response);
//                        return false;
//                    }
//                    return false;
//                } else {
//                    return false;
//                }
//            }
//        }
//
//        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
