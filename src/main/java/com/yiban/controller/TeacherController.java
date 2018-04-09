package com.yiban.controller;

import com.yiban.dto.Data;
import com.yiban.dto.Dictionary;
import com.yiban.dto.Result;
import com.yiban.service.teacher.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    //总条数
    private Integer total;
    //总页数
    private Integer totalPage;
    //每页的页数
    private static final int PAGE = 10;

    @Autowired
    private LeaveService leaveHandle;

    @RequestMapping(value = "/info")
    @ResponseBody
    public Data teacher(HttpServletRequest request, @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
        HttpSession session = request.getSession();
        String yibanId = (String) session.getAttribute("yiban_id");
        //因为有拦截器，所以获取的易班账号不会为空，这里谨慎一步
        if (yibanId != null && !"".equals(yibanId)) {
            //初始化
            if (pageNumber == null)
                pageNumber = 1;
            if (total == null || totalPage == null) {
                //查找总页数
                total = leaveHandle.totalNumber(yibanId);
                totalPage = total/10 + 1;
            }
            int limit = PAGE * (pageNumber - 1);
            return new Data(true, total, totalPage, leaveHandle.selectAll(yibanId, limit, PAGE));
        } else {
            return new Data(Dictionary.UNKNOWN_INFO);
        }

    }

    /**
     * 处理请假操作
     */
    @RequestMapping(value = "/handle")
    public Result handle(@RequestParam(value = "id", required = false)Integer id, @RequestParam(value = "status", required = false)Integer status) {
        if (id == null || status == null){
            return new Result(Dictionary.DATA_LOSS);
        }
        return leaveHandle.updateLeave(id,status);
    }

}
