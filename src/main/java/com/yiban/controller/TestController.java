package com.yiban.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于测试
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
    @RequestMapping(value = "/student")
    public String studentTest() {
        return "/WEB-INF/zqu/student/student.jsp";
    }
}
