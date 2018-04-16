package com.yiban.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yiban.dto.Data;
import com.yiban.dto.Dictionary;
import com.yiban.entity.ClassTable;
import com.yiban.entity.Information;
import com.yiban.mapper.ContentMapper;
import com.yiban.service.student.GetMyLeave;
import com.yiban.service.teacher.LeaveService;
import com.yiban.dto.Result;

/**
 * 教师操作
 * @author chenyihui
   2018年4月13日
 */
@Controller
@RequestMapping("/Toteacher")
public class Teacher_leaveController {
	
	 //总条数
    private Integer total;
    //总页数
    private Integer totalPage;
    //每页的页数
    private static final int PAGE = 10;

    @Autowired
    private LeaveService leaveHandle;
    
	@Autowired
	private ContentMapper contentMapper;
    
    @RequestMapping("/toSystem")    
    public ModelAndView toSystem()
    {
    	  Map<String,Object> map=new HashMap<String,Object>();
    	  ClassTable classTable  =new ClassTable("2015241331", "22222", "33333");
    	  map.put("teacherYibanId", classTable.getTeacherYibanId());
    	  return new  ModelAndView("/teacher/jqgrid",map);
    }
	
    /**
     * 浏览请假情况
    * @Title: list   
    * @Description: TODO(这里用一句话描述这个方法的作用)   
    * @param @param params
    * @param @return    设定文件   
    * @return Data    返回类型   
    * @throws
     */
	@RequestMapping("/list")
	@ResponseBody
	public Data list(@RequestParam Map<String, Object> params)
	{

		//
		String yibanId  =params.get("yibanId").toString();
		
		Integer pageNumber =Integer.valueOf(params.get("pageNumber").toString());
		
		
		System.err.println("yiban:"+yibanId+" "+"pageNumber:"+pageNumber);
        //因为有拦截器，所以获取的易班账号不会为空，这里谨慎一步
        if(yibanId !=null && ! "".equals(yibanId))
        {
        	  //初始化
            if (pageNumber == null)
                pageNumber = 1;
            if (total == null || totalPage == null) {
                //查找总页数
                total = leaveHandle.totalNumber(yibanId);
                totalPage = total/10 + 1;
            }
            int limit = PAGE * (pageNumber - 1);
            Data data =new Data(true, total, totalPage, leaveHandle.selectAll(yibanId, limit, PAGE));
            List<Information> lists =data.getRows();
            for(Information inf:lists)
            	System.err.println(inf.getStudent().toString());
            return data;
        }
        else {
            return new Data(Dictionary.UNKNOWN_INFO);
        }

		
	}
	
	/**
	 * 审核请假情况
	* @Title: handle   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param information
	* @param @return    设定文件   
	* @return Result    返回类型   
	* @throws
	 */
	@RequestMapping("/handle")
	@ResponseBody
	public Result handle(@RequestBody Information information)
	{
		
		long  id =information.getId();
		
		Integer status =information.getStatus();
		
		
	    if (Long.toString(id)==null ||status ==null){
            return new Result(Dictionary.DATA_LOSS);
        }
        return leaveHandle.updateLeave(id,status);
		
	}
	
	@RequestMapping(value="/downloadExcel")
	public ResponseEntity<byte[]> downloadExcel(@RequestParam("List") long []List, HttpServletRequest request ) throws ServletException, IOException
	{
		
		
		
	//	long[] List={1,3,4,5,6,7,8};
		String path=request.getRealPath("/temp/");	
		
		String filePath ="学生请假信息表" +System.currentTimeMillis();
		

		File downFile =new File(path,filePath);
		
		if (!downFile.getParentFile().exists()) {
			downFile.getParentFile().mkdirs();
		}
		
		if(leaveHandle.exportInformation(List, path+File.separator+filePath))
		{
			File file =new File(path+File.separator+filePath);
			 
			
			 HttpHeaders headers =new HttpHeaders();
			 
			 //下载显示的文件名，解决中文名称乱码问题
		
			String downloadFielName = new String(filePath.getBytes("UTF-8"),
					"iso-8859-1");
		 
		 //通知浏览器以attachment（下载方式）
		 headers.setContentDispositionFormData("attachment", filePath);
		
		 //application/octet-stream ： 二进制流数据（最常见的文件下载）。
		 headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		 
		
		 return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
					headers, HttpStatus.CREATED);
			
		}
		return null;
		
	
	
	
		 
	}

	
	@RequestMapping(value="/download/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(@PathVariable("id") long id,HttpServletRequest request ) throws ServletException, IOException
	{
		
		String  filePath =leaveHandle.selectFilePath(id);
		
		System.err.println(filePath);
	  
		String path=request.getRealPath("/upload/");
	
		

		File downFile =new File(path,filePath);
		
		if (!downFile.getParentFile().exists()) {
			downFile.getParentFile().mkdirs();
		}
		
		
	/*	  filePath ="/upload/"+filePath;*/
		  
	
			File file =new File(path+File.separator+filePath);
			 
			
			 HttpHeaders headers =new HttpHeaders();
			 
			 //下载显示的文件名，解决中文名称乱码问题
		
			String downloadFielName = new String(filePath.getBytes("UTF-8"),
					"iso-8859-1");
		 
		 //通知浏览器以attachment（下载方式）
		 headers.setContentDispositionFormData("attachment", filePath);
		
		 //application/octet-stream ： 二进制流数据（最常见的文件下载）。
		 headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		 
		
		 return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
					headers, HttpStatus.CREATED);
	
	
		 
	}

}
