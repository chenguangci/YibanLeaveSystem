package com.yiban.controller;


import com.yiban.service.handle.IdentityHandle;

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
//        HttpSession session = request.getSession();
//        String yibanId = (String) session.getAttribute("yiban_id");
//        String md5 = (String) session.getAttribute("yiban_id_key");
//        //除了开始以及错误界面外，其他请求应该都包含yibanId以及md5两个值
//        if (request.getRequestURI().contains("index") || request.getRequestURI().contains("error"))
//            return true;
//        if (md5 == null || yibanId == null || !md5.equals(identityHandle.key(yibanId))) {
//            //TODO 跳转到错误提示界面或者授权界面
//            logger.error("出现非法请求，跳转至错误页面");
//            response.sendRedirect("error");
//            return false;
//        } else {
//            return true;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
