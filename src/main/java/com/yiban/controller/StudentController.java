package com.yiban.controller;

import com.yiban.dto.Result;
import com.yiban.entity.Information;
import com.yiban.entity.Student;
import com.yiban.exception.ReSetTokenException;
import com.yiban.exception.RequestInfoException;
import com.yiban.exception.SendException;
import com.yiban.exception.SystemRunTimeException;
import com.yiban.service.student.FormHandle;
import com.yiban.service.student.GetMyLeave;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private FormHandle formHandle;
    @Autowired
    private GetMyLeave myLeave;

    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    //获取个人的请假信息
    @RequestMapping(value = "/{yibanId}/Info")
    @ResponseBody
    public ResponseEntity<List<Information>> myInfo(HttpSession session, @PathVariable(value = "yibanId")String yibanId) {
        //获取学号
        String id = (String) session.getAttribute("studentId");
        return new ResponseEntity<>(myLeave.getMyLeave(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/leave", method = RequestMethod.POST)
    @ResponseBody
    public Result leave(@RequestParam(value = "file")MultipartFile file, Information information) {
        try {
            //文件处理
            if (!file.isEmpty()) {
                logger.info("上传的文件名：{}", file.getName());
                try {
                    //TODO 将文件保存到指定路径上
                    FileUtils.copyInputStreamToFile(file.getInputStream(),new File("",""));
                } catch (IOException e) {
                    throw new SystemRunTimeException("文件上传异常");
                }
            }
            formHandle.setInfoAndSend(information);
            return new Result(true, "发送成功，等待辅导员的回复");
        } catch (SendException | RequestInfoException | ReSetTokenException e1) {
            return new Result(false, "发送消息失败，请稍后重试");
        } catch (UnknownError e2) {
            return new Result(false, "找不到您的辅导员或者班主任，请联系管理员");
        } catch (SystemRunTimeException e3) {
            return new Result(false, "系统发生异常，请稍后重试");
        }

    }
}
