package com.yiban.controller;

import com.yiban.dto.Dictionary;
import com.yiban.dto.Result;
import com.yiban.entity.Information;
import com.yiban.exception.*;
import com.yiban.service.student.FormHandle;
import com.yiban.service.student.GetMyLeave;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private FormHandle formHandle;
    @Autowired
    private GetMyLeave myLeave;

    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    //获取个人的请假信息
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public List<Information> myInfo(HttpSession session) {
        //获取学号
        String id = (String) session.getAttribute("student_id");
        logger.info("学号：{}",id);
        return myLeave.getMyLeave(id);
    }

    @RequestMapping(value = "/leave", method = RequestMethod.POST)
    @ResponseBody
    public Result leave(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        try {
            Information information = new Information();
            /*
             * 原本前段已经有非空校验了，但为了安全起见还是在后台加一次校验
             * 将属性添加到information对象中
             * 学号通过session中的值获取，因为session中的值都有安全校验，所以从中获取的studentId比较安全
             * 其他属性被篡改的意义不大，所以可以直接从request中获取
             */
            HttpSession session = request.getSession();
            String studentId = (String) session.getAttribute("student_id");
//            String studentId = request.getParameter("studentId");
            String phone = request.getParameter("phone");
            String beginTime = request.getParameter("beginTime");
            String endTime = request.getParameter("endTime");
            String number = request.getParameter("number");
            String reason = request.getParameter("reason");
            //非空校验
            if (studentId != null && !"".equals(studentId)
                    && phone != null && !"".equals(phone)
                    && beginTime != null && !"".equals(beginTime)
                    && endTime != null && !"".equals(endTime)
                    && number != null && !"".equals(number)
                    && reason != null && !"".equals(reason)) {
                information.setStudentId(studentId);
                information.setPhone(phone);
                information.setBeginTime(beginTime);
                information.setEndTime(endTime);
                information.setNumber(Integer.parseInt(number));
                information.setReason(reason);
            } else {
                logger.error("数据校验时发生恶意篡改");
                throw new DataLossException("缺少必要信息");
            }
            logger.info("表单信息：{}",information.toString());
            //文件以及请假信息
            if (file != null && !file.isEmpty()) {
                logger.info("上传的文件名：{}", file.getOriginalFilename());
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String day = format.format(date);
                String filePath = "/home/beiyi/MyFile/log/yiban_file/" + day + "/" + studentId + file.getOriginalFilename();
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath));
                information.setFilePath(filePath);
                /*
                 * 重要功能，存储请假信息以及查找学生对应的辅导员并发送请假通知
                 */
                formHandle.setInfoAndSend(information);
                return new Result(Dictionary.SUCCESS, "发送成功，等待辅导员的回复");
            } else {
                logger.error("确少必要信息或文件");
                throw new DataLossException("确少必要信息或文件");
            }

        } catch (SendException | RequestInfoException | ReSetTokenException e1) {
            logger.error("异常信息：发送信息失败，{}", e1.getMessage());
            return new Result(Dictionary.SEND_FAIL);
        } catch (DataLossException e2) {
            logger.error("异常信息：缺少必要信息，{}", e2.getMessage());
            return new Result(Dictionary.DATA_LOSS);
        } catch (UnknownInfoException e3) {
            logger.error("异常信息：获取信息失败，{}", e3.getMessage());
            return new Result(Dictionary.FIND_TEACHER_FAIL);
        } catch (Exception e4) {
            logger.error("异常信息：系统异常，{}", e4.getMessage());
            return new Result(Dictionary.SYSTEM_ERROR);
        }
    }
}
