package com.yiban.controller;

import com.yiban.dto.Dictionary;
import com.yiban.dto.Result;
import com.yiban.entity.Information;
import com.yiban.entity.Student;
import com.yiban.exception.*;
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
import java.util.Calendar;
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
    @RequestMapping(value = "/{studentId}/Info", method = RequestMethod.POST)
    @ResponseBody
    public List<Information> myInfo(HttpSession session, @PathVariable(value = "studentId") String studentId) {
        //获取学号
        String id = (String) session.getAttribute("studentId");
        System.out.println("学号：" + studentId);
        return myLeave.getMyLeave(studentId);
    }

    @RequestMapping(value = "/leave", method = RequestMethod.POST)
    @ResponseBody
    public Result leave(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(required = false) Information information) {
        try {
            //文件以及请假信息
            if (!file.isEmpty() && information != null) {
                logger.info("上传的文件名：{}", file.getName());
                try {
                    String filePath = "/home/beiyi/MyFile/log/yiban_file" + String.valueOf(Calendar.YEAR) + (Calendar.MONTH + 1) + Calendar.DAY_OF_MONTH + file.getName();
                    FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath));
                    information.setFilePath(filePath);
                    /*
                     * 重要功能，存储请假信息以及查找学生对应的辅导员并发送请假通知
                     */
                    formHandle.setInfoAndSend(information);
                    return new Result(Dictionary.SUCCESS, "发送成功，等待辅导员的回复");
                } catch (IOException e) {
                    logger.error("异常信息：{}", e.getMessage());
                    throw new SystemRunTimeException("文件上传异常");
                }
            } else {
                logger.error("确少必要信息或文件");
                throw new DataLossException("确少必要信息或文件");
            }

        } catch (SendException | RequestInfoException | ReSetTokenException e1) {
            return new Result(Dictionary.SEND_FAIL);
        } catch (DataLossException e2) {
            return new Result(Dictionary.DATA_LOSS);
        } catch (UnknownError e3) {
            return new Result(Dictionary.FIND_TEACHER_FAIL);
        } catch (SystemRunTimeException e4) {
            return new Result(Dictionary.SYSTEM_ERROR);
        }

    }
}
