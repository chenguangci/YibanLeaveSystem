package com.yiban.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yiban.dto.Dictionary;
import com.yiban.dto.Result;
import com.yiban.entity.Information;
import com.yiban.entity.Student;
import com.yiban.exception.DataLossException;
import com.yiban.exception.ReSetTokenException;
import com.yiban.exception.RequestInfoException;
import com.yiban.exception.SendException;
import com.yiban.exception.UnknownInfoException;
import com.yiban.service.student.FormHandle;
import com.yiban.service.student.GetMyLeave;

/**
 * 测试开发
 * @author chenyihui
   2018年4月11日
 */
@Controller
@RequestMapping("/Toleave")
public class Student_leaveController {

	@Autowired
	private FormHandle formHandle;

	@Autowired
	private GetMyLeave myLeave;

	private Logger logger = LoggerFactory
			.getLogger(Student_leaveController.class);

	 /**
	  * 
	 * @Title: yibanIndex   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @return    设定文件   
	 * @return ModelAndView    返回类型   
	 * @throws
	  */
	 @RequestMapping(value="/studentIndex")
     public ModelAndView yibanIndex()
     {
         Map<String,Object> map=new HashMap<String,Object>();
        // ModelAndView modelAndView =new ModelAndView();
         Student student =new Student("111111", "201524133105", "陈怡慧", "软件工程", "15软件一班");
        // map.put("result",student);
         map.put("result", new Result(Dictionary.SUCCESS, student));
       /*  modelAndView.addObject(map);
         modelAndView.setViewName("/student/index");*/
         return new ModelAndView("/student/index",map);
     }
	

	
	 
	  /**
	   * 跳转页面
	  * @Title: record   
	  * @Description: TODO(这里用一句话描述这个方法的作用)   
	  * @param @param studentId
	  * @param @return    设定文件   
	  * @return ModelAndView    返回类型   
	  * @throws
	   */
	  @RequestMapping(value="/record/{studentId}",method=RequestMethod.GET)
	  public ModelAndView record(@PathVariable(value="studentId") String studentId)
	  {
		  Map<String,Object> map=new HashMap<String,Object>();
		  map.put("studentId", studentId);
		// return "redirect:" + "YibanLeaveSystem/module/record.html?studentId="+studentId;
		  return new ModelAndView("/student/record",map);
		  
	  }
	  
	  
	    //获取个人的请假信息
	    @RequestMapping(value = "/info/{studentId}", method = RequestMethod.GET)
	    @ResponseBody
	    public List<Information> myInfo(@PathVariable(value="studentId") String studentId) {
	        //获取学号
	    	List<Information> lists=myLeave.getMyLeave(studentId);
	    	for(Information i:lists)
	    	{
	    		System.out.println(i.getBeginTime());
	    	}
        return myLeave.getMyLeave(studentId);
	    }

	 

    /**
     *获取请假信息
    * @Title: leave
    * @Description: TODO(这里用一句话描述这个方法的作用)  
      @Return:Result
      2018年4月16日下午5:20:32
     */
	@RequestMapping(value = "/leave", method = RequestMethod.POST)
	@ResponseBody
	public Result leave(Information information,
			@RequestParam(value = "file") MultipartFile file,
			HttpServletRequest request) {

		try {

			if (file != null && !file.isEmpty()) {
				logger.info("上传的文件名：{}", file.getOriginalFilename());
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String day = format.format(date);
				String nameString = file.getOriginalFilename();
			     //防止有些浏览器中文不识别
				nameString = java.net.URLEncoder.encode(nameString,
						"utf-8");
				nameString = nameString.replace("%", "-");
				String originalFilename = System.currentTimeMillis()
						 + "%"  + information.getStudentId()+"%"+ nameString;
				// 构建上传目录及文件对象，不存在则自动创建
				 String filePath =request.getSession().getServletContext()
							.getRealPath( "/upload/" );				

				FileUtils.copyInputStreamToFile(file.getInputStream(),
						new File(filePath,originalFilename));
				 information.setFilePath(originalFilename);
				/*
				 * 重要功能，存储请假信息以及查找学生对应的辅导员并发送请假通知
				 */
				 formHandle.setInfoAndSend(information);
				System.err.println("上传文件成功");
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
