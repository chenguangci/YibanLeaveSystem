package com.yiban.controller;

import com.yiban.bean.LeaveContent;
import com.yiban.service.teacher.LeaveHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/leave/teacher")
public class TeacherController {
    private LeaveHandle leaveHandle;
    @Autowired
    public TeacherController(LeaveHandle leaveHandle){
        this.leaveHandle = leaveHandle;
    }
    @RequestMapping
    public ResponseEntity<List<LeaveContent>> teacher(@RequestParam(value = "teacherId")String teacherId) {
        return new ResponseEntity<List<LeaveContent>>(leaveHandle.selectAll(teacherId), HttpStatus.OK);
    }
}
