package com.yiban.controller;

import com.yiban.dto.Data;
import com.yiban.dto.Dictionary;
import com.yiban.dto.Result;
import com.yiban.entity.Synchronize;
import com.yiban.service.teacher.LeaveService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

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
                totalPage = total / 10 + 1;
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
    public Result handle(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "status", required = false) Integer status,HttpSession session) {
        if (id == null || status == null) {
            return new Result(Dictionary.DATA_LOSS);
        }
        String yiBanId = (String) session.getAttribute("yiban_id");
        Synchronize synchronize = new Synchronize(yiBanId,id, status);
        return  synchronize.updateStatus();
    }

    @RequestMapping("/downloadExcel")
    public ResponseEntity <byte[]> downloadExcel(@RequestParam("List") long[] List, HttpServletRequest request) throws ServletException, IOException {
        //	long[] List={1,3,4,5,6,7,8};
        String path = request.getRealPath("/temp/");

        String filePath = "学生请假信息表" + System.currentTimeMillis() + ".xls";


        File downFile = new File(path, filePath);

        if (!downFile.getParentFile().exists()) {
            downFile.getParentFile().mkdirs();
        }

        if (leaveHandle.exportInformation(List, path + File.separator + filePath)) {
            File file = new File(path + File.separator + filePath);


            HttpHeaders headers = new HttpHeaders();

            //下载显示的文件名，解决中文名称乱码问题

            String downloadFielName = new String(filePath.getBytes("UTF-8"),
                    "iso-8859-1");

            //通知浏览器以attachment（下载方式）
            headers.setContentDispositionFormData("attachment", filePath);

            //application/octet-stream ： 二进制流数据（最常见的文件下载）。
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);


            return new ResponseEntity <byte[]>(FileUtils.readFileToByteArray(file),
                    headers, HttpStatus.CREATED);

        }
        return null;


    }


    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public ResponseEntity <byte[]> download(@PathVariable("id") long id, HttpServletRequest request) throws ServletException, IOException {

        String filePath = leaveHandle.selectFilePath(id);

        System.err.println(filePath);

        String path = request.getRealPath("/upload/");


        File downFile = new File(path, filePath);

        if (!downFile.getParentFile().exists()) {
            downFile.getParentFile().mkdirs();
        }


        /*	  filePath ="/upload/"+filePath;*/


        File file = new File(path + File.separator + filePath);


        HttpHeaders headers = new HttpHeaders();

        //下载显示的文件名，解决中文名称乱码问题

        String downloadFielName = new String(filePath.getBytes("UTF-8"),
                "iso-8859-1");

        //通知浏览器以attachment（下载方式）
        headers.setContentDispositionFormData("attachment", filePath);

        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);


        return new ResponseEntity <byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);


    }

}
