package com.yiban.controller;

import com.yiban.dto.Dictionary;
import com.yiban.dto.Result;
import com.yiban.service.teacher.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private LeaveService leaveHandle;

    @RequestMapping(value = "/info")
    @ResponseBody
    public Result teacher(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String yibanId = (String) session.getAttribute("yiban_id");
        //因为有拦截器，所以获取的易班账号不会为空，这里谨慎一步
        if (yibanId != null && !"".equals(yibanId))
            return new Result(Dictionary.SUCCESS, leaveHandle.selectAll(yibanId));
        else
            return new Result(Dictionary.UNKNOWN_INFO);
    }

    /**
     * 处理请假操作
     */
    @RequestMapping(value = "/handle")
    public Result handle() {
        //TODO 如何获取需要的请假id以及请假操作
        return leaveHandle.updateLeave(1,1);
    }
}
