package com.yiban.controller;

import com.yiban.entity.Information;
import com.yiban.service.teacher.LeaveHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private LeaveHandle leaveHandle;

    @RequestMapping(value = "/{yibanId}")
    @ResponseBody
    public ResponseEntity<List<Information>> teacher(@PathVariable(value = "yibanId") String yibanId) {
        return new ResponseEntity<>(leaveHandle.selectAll(yibanId), HttpStatus.OK);
    }
}
