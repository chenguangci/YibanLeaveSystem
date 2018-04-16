package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.yiban.dto.Result;
import com.yiban.entity.Information;
import com.yiban.mapper.ContentMapper;
import com.yiban.service.teacher.LeaveService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class teacherTest {

	@Autowired
	private ContentMapper contentMapper;

	@Autowired
	private LeaveService leaveService;

	/*
	 * Information{ id=1002, studentId='201524133112', beginTime='2018-02-25
	 * 00:00:00.0', endTime='2018-02-26 00:00:00.0', number=4, reason='test',
	 * phone='10086', status=0, filePath='null', code='null'}
	 */
	/*
	 * @Test public void selectLeaves() throws Exception { List<Information>
	 * list = leaveService.selectLeaves("10849451"); for (Information
	 * information : list) { System.out.println(information.toString()); } }
	 * 
	 * （1）生成key值 INFO [main] - 生成的key值：ec5f5c7b1 （2）开始事务，更新请假人的请假信息，1为同意请假 JDBC
	 * Connection [com.mysql.jdbc.JDBC4Connection@6cdba6dc] will be managed by
	 * Spring ==> Preparing: UPDATE information SET status = ?, code = ? WHERE
	 * id = ? ==> Parameters: 1(Integer), ec5f5c7b1(String), 1002(Long) <==
	 * Updates: 1 （3）查找请假人的易班id Fetched SqlSession
	 * [org.apache.ibatis.session.defaults.DefaultSqlSession@3e74829] from
	 * current transaction ==> Preparing: SELECT s.yiban_id FROM student s WHERE
	 * s.student_id = (SELECT a.student_id FROM information a WHERE a.id = ?)
	 * ==> Parameters: 1002(Long) <== Total: 1 （4）数据库中获取token，用于发送信息 ==>
	 * Preparing: SELECT token_type, token, add_time, expire_in FROM token WHERE
	 * token_type = ? LIMIT 0,1 ==> Parameters: 0(Integer) <== Total: 1 （5）发送结果
	 * success：成功 送信时返回的json：{"status":"success","info":true} （6）查找对应的班长的易班id
	 * ==> Preparing: SELECT c.monitor FROM class c WHERE c.class_id =
	 * LEFT((SELECT a.student_id FROM information a WHERE a.id = ?),10) ==>
	 * Parameters: 1002(Long) <== Total: 1 （7）发送信息结果：success：成功
	 * 送信时返回的json：{"status":"success","info":true} （8）提交事务 Committing JDBC
	 * transaction on Connection （9）返回结果 Result{success=true, msg='操作成功',
	 * data=null}
	 * 
	 * @Test public void updateLeave() throws Exception { //同意请假 Result result =
	 * leaveService.updateLeave(1002, 1); //拒绝请假 // Result result =
	 * leaveService.updateLeave(1002, -1); //同意销假 // Result result =
	 * leaveService.updateLeave(1002, 2); //等待，一般不会出现 // Result result =
	 * leaveService.updateLeave(1002, 0); System.out.println(result.toString());
	 * }
	 */
	@Test
	public void selectAll() throws Exception {
	}

	@Test
	public void export() {
		
		 Information information=contentMapper.selectInformation(3);
		  
		 System.out.println(information.toString());
		/* ArrayList list = new ArrayList(); */
	/*	long[] list = { 1, 2, 3, 6, 5 };
		String path = request.getRealPath("/temp/");
		List<Information> informationList = new ArrayList<Information>();
		String filePath = "学生请假信息表" + System.currentTimeMillis();*/

	/*	File downFile = new File(path, filePath);

		if (!downFile.getParentFile().exists()) {
			downFile.getParentFile().mkdirs();
		}

		if (leaveService.exportInformation(list, path+File.separator+filePath)) {
			System.err.println("导出成功");
		}*/
	
	/*	  for (int i =0;i<list.length;i++) 
		  {
		  System.err.println("long[i]"+list[i]); Information information =new
		 Information();
		 
		  information=contentMapper.selectInformation(list[i]);
		  
		 System.out.println(information.getStudent().getName());
		  
		 informationList.add(information); 
		 }*/
		
	}

}
