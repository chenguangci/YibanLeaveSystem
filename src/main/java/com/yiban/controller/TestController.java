package com.yiban.controller;

import com.yiban.dto.Dictionary;
import com.yiban.dto.Result;
import com.yiban.entity.Information;
import com.yiban.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于测试
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
    @RequestMapping(value = "/student")
    public ModelAndView studentTest() {
        ModelAndView modelAndView =new ModelAndView();
        Student student = new Student ("101010","201624122332","郑达成","电子与电气工程学院","16电气3班");
        modelAndView.addObject("r", new Result(Dictionary.SUCCESS,student));
        modelAndView.setViewName("/student/index");
        return modelAndView;
    }
    @RequestMapping("/teacher1")
    public String teacherTest1(){ return "/teacher/jqgrid";}
    @RequestMapping("/teacher2")
    public String teacherTest2(){ return "/teacher/jqgrid1";}
}
