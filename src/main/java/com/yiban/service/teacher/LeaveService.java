package com.yiban.service.teacher;

import com.yiban.dto.Dictionary;
import com.yiban.dto.Result;
import com.yiban.dto.workbookFactory;
import com.yiban.entity.Information;
import com.yiban.entity.Student;
import com.yiban.exception.ReSetTokenException;
import com.yiban.exception.RequestInfoException;
import com.yiban.exception.SendException;
import com.yiban.exception.SystemRunTimeException;
import com.yiban.mapper.ContentMapper;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LeaveService {

	@Autowired
	private ContentMapper contentMapper;
	@Autowired
	private LeaveHandle leaveHandle;

	/**
	 * 
	 * @Title: selectFilePath
	 * @Description: 查找文件路径
	 */
	public String selectFilePath(long id) {
		return contentMapper.selectFilePath(id);
	}

	/**
	 * 
	 * @Title: selectInformation
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @Params:id Information 2018年4月14日
	 */
	public Information selectInformation(long id) {
		return contentMapper.selectInformation(id);
	}

	/**
	 * 查询请假的学生信息
	 * 
	 * @param id
	 *            辅导员或班主任的易班ID
	 * @return 请假详细信息
	 */
	public List<Information> selectLeaves(String id) {
		return contentMapper.selectLeaves(id);
	}

	/**
	 * 辅导员对请假信息进行操作
	 * 
	 * @param id
	 *            请假信息的id 请假状态：（-1：拒绝，0：待审核，1：已同意未销假，2：已销假）
	 * @param status
	 *            请假状态更新
	 * @return 更新结果
	 */
	public Result updateLeave(long id, int status) {
		if (status < -1 || status > 2) {
			return new Result(Dictionary.ILLEGAL_OPERATION);
		} else {
			try {
				if (status == -1) {
					// 拒绝
					return leaveHandle.disagree(id);
				} else if (status == 0) {
					// 待审核状态，初始状态
					return leaveHandle.waiting(id);
				} else if (status == 1) {
					// 同意请假，发送信息给请假人再转发给班长
					return leaveHandle.agreeLeave(id);
				} else {
					// 销假操作
					return leaveHandle.back(id);
				}
			} catch (SendException | RequestInfoException | ReSetTokenException e1) {
				return new Result(Dictionary.SEND_FAIL);
			} catch (SystemRunTimeException e2) {
				return new Result(Dictionary.SYSTEM_ERROR);
			}
		}
	}

	/**
	 * 查询所有请假人员
	 * 
	 * @param id
	 *            班主任或者辅导员的ID
	 * @return 请假信息的集合
	 */
	public List<Information> selectAll(String id, int limit, int page) {
		return contentMapper.selectAll(id, limit, page);
	}

	/**
	 * 查看班级当天请假人数
	 * 
	 * @param id
	 *            班主任或者辅导员的ID
	 * @return 当天处于请假状态的学生的请假详细信息
	 */
	public List<Information> todayLeaves(String id) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String today = format.format(date);
		return contentMapper.todayLeaves(id, today);
	}

	/**
	 * 根据请假类型查询（事假或病假）
	 * 
	 * @param id
	 *            辅导员或班主任的易班id
	 * @param type
	 *            类型
	 * @return 相应的请假信息
	 */
	public List<Information> selectByType(String id, String type) {
		return contentMapper.selectByType(id, type);
	}

	/**
	 * 页数查找
	 */
	public int totalNumber(String yibanId) {
		return contentMapper.totalNumber(yibanId);
	}

	public Boolean exportInformation (long[] array, String path)
    {
    	List<Information> informationList =new ArrayList<Information>();
    	for (int i =0;i<array.length;i++)
    	{
    		Information  information =new Information();
    	
    		information=contentMapper.selectInformation(array[i]);
    		
    		System.out.println(information.toString());
    		
    		informationList.add(information);
    	}
    	/*
		 * 设置表头：对Excel每列取名(必须根据你取的数据编写)
		 */
    	String[] tableHeader = {"序号","学号", "姓名","学院", "班级","联系方式","开始日期","截止日期","请假节数","请假原因","请假状态"};
    	/*
		 * 下面的都可以拷贝不用编写
		 */
		short  cellNumber =workbookFactory.workBook(tableHeader) ;// 表的列数
		HSSFWorkbook workbook = workbookFactory.getHssfWorkbook();; // 创建一个excel
		HSSFCell cell = null; // Excel的列
		HSSFRow row = null; // Excel的行
		HSSFCellStyle style = workbook.createCellStyle(); // 设置表头的类型
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCellStyle style1 = workbook.createCellStyle(); // 设置数据类型
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = workbook.createFont(); // 设置字体
		HSSFSheet sheet = workbook.createSheet("sheet1"); // 创建一个sheet
		HSSFHeader header = sheet.getHeader();// 设置sheet的头
		try {
			/**
			 * 根据是否取出数据，设置header信息
			 * 
			 */
			if(informationList.size()<1)
			{
				header.setCenter("查无资料");
			}
			else {
				header.setCenter("学生请假信息表");
				row = sheet.createRow(0);
				row.setHeight((short) 400);
				for (int k = 0; k < cellNumber; k++) {
					cell = row.createCell(k);// 创建第0行第k列
					cell.setCellValue(tableHeader[k]);// 设置第0行第k列的值
					sheet.setColumnWidth(k, 8000);// 设置列的宽度
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色.
					font.setFontHeight((short) 350); // 设置单元字体高度
					style1.setFont(font);// 设置字体风格
					cell.setCellStyle(style1);
				}
				
				int index=0;
				 // 给excel填充数据这里需要编写
				for (int i=0;i<informationList.size();i++)
				{
					Information information = new Information();
					information =(Information) informationList.get(i);
					row = sheet.createRow((short) (i + 1));// 创建第i+1行
					row.setHeight((short) 400);// 设置行高					
					cell=row.createCell(0);
					cell.setCellValue(index++);
					cell.setCellStyle(style);// 设置风格
					if(information.getStudentId()!=null)
					{
						cell=row.createCell(1);
						cell.setCellValue(information.getStudentId());
						cell.setCellStyle(style);// 设置风格

					}
					if(information.getStudent().getName()!=null)
					{
						cell=row.createCell(2);
						cell.setCellValue(information.getStudent().getName());
						cell.setCellStyle(style);// 设置风格
					}
					if(information.getStudent().getDepartment()!=null)
					{
						cell =row.createCell(3);
						cell.setCellValue(information.getStudent().getDepartment());
						cell.setCellStyle(style);// 设置风格
					}
					if(information.getStudent().getClassName()!=null)
					{
						cell =row.createCell(4);
						cell.setCellValue(information.getStudent().getClassName());
						cell.setCellStyle(style);// 设置风格
					}
					if(information.getPhone()!=null)
					{
						cell =row.createCell(5);
						cell.setCellValue(information.getPhone());
						cell.setCellStyle(style);// 设置风格
					}
					if(information.getBeginTime()!=null)
					{
						cell =row.createCell(6);
						cell.setCellValue(information.getBeginTime());
						cell.setCellStyle(style);// 设置风格
					}
					if(information.getEndTime()!=null)
					{
						cell =row.createCell(7);
						cell.setCellValue(information.getEndTime());
						cell.setCellStyle(style);// 设置风格
					}
					if(String.valueOf(information.getNumber())!=null)
					{
						cell =row.createCell(8);
						cell.setCellValue(information.getNumber());
						cell.setCellStyle(style);// 设置风格
					}
					if(information.getReason()!=null)
					{
						cell =row.createCell(9);
						cell.setCellValue(information.getReason());
						cell.setCellStyle(style);// 设置风格
					}
					if(String.valueOf(information.getStatus())!=null)
					{
						cell =row.createCell(10);
						cell.setCellStyle(style);
		    			if(information.getStatus()==-1)
		    				cell.setCellValue("拒批准");
		    			else if (information.getStatus()==0)
		    				cell.setCellValue("待审核");
		    			else if (information.getStatus()==1)
		    				cell.setCellValue("未销假");
		    			else {
		    				cell.setCellValue("已销假");
						}
					}
					
					
				}


				/*int index =0;
				for (int i=0;i<informationList.size();i++)
				{
					Information information = new Information();
					information =(Information) informationList.get(i);
					Student student =information.getStudent();
					 @SuppressWarnings("rawtypes")
					Class informationClass=(Class) information.getClass();
					 @SuppressWarnings("rawtypes")
					Class studentClass =(Class) information.getStudent().getClass();
				
				         
					Method[] infoMethod = informationClass.getMethods();
					Method[] stuMethod = studentClass.getMethods();
					 
					row = sheet.createRow((short) (i + 1));// 创建第i+1行
					row.setHeight((short) 400);// 设置行高
				    cell=row.createCell(index++);			
				    cell.setCellValue(i+1);
				    cell.setCellStyle(style);
				    for (int j=0;j<stuMethod.length;j++)
				    {
                            Method m=stuMethod[j];
				    	
				    	if(m.getName().startsWith("get"))
				    	{
				    		if(m.getName()!="getYibanId")
				    		{
				    			cell=row.createCell(index++);
				    			cell.setCellValue(m.invoke(student).toString());
				    			cell.setCellStyle(style);
				    		}
				    	}
				    }
				    for(int j =0;j<infoMethod.length;j++)
				    {
				    	Method m=infoMethod[j];
				    	
				    	if(m.getName().startsWith("get"))
				    	{
				    		if(m.getName()=="getStatus")
				    		{
				    			cell=row.createCell(index++);
				    			cell.setCellStyle(style);
				    			if(m.invoke(information).toString()=="1")
				    				cell.setCellValue("拒批准");
				    			else if (m.invoke(information).toString()=="0")
				    				cell.setCellValue("待审核");
				    			else if (m.invoke(information).toString()=="1")
				    				cell.setCellValue("未销假");
				    			else {
				    				cell.setCellValue("已销假");
								}
				    				
				    				
				    		}
				    		if(m.getName()!="getFilePath"&&m.getName()!="getStudentId" &&m.getName()!="getCode")
				    		{
				    			
				    			cell=row.createCell(index++);
				    			cell.setCellValue(m.invoke(information).toString());
				    			cell.setCellStyle(style);
				    		}
				    	}
				    }
				  
					
					 
					
					
				}*/
					
			}
		} catch (SecurityException e) {  
            e.printStackTrace();  
        } catch (IllegalArgumentException e) {  
            e.printStackTrace();  
        }
		// 创建一个HttpServletResponse对象
		FileOutputStream out = null;
		// 创建一个输出流对象
		try {
			// 初始化HttpServletResponse对象
			out = new FileOutputStream(path);
			workbook.write(out);
			out.flush();
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}				
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
		
    }
}
