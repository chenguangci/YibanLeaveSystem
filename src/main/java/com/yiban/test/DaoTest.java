package com.yiban.test;

import com.yiban.bean.LeaveContent;
import com.yiban.dao.ContentDao;
import org.junit.Test;

public class DaoTest {
    @Test
    public void testContentDao(){
        LeaveContent content = new LeaveContent();
        content.setStudentId("201524133114");
        content.setName("陈光赐");
        content.setDepartment("计算机科学与软件学院、大数据学院");
        content.setMajor("软件工程");
        content.setGrade(15);
        content.setClassNo(1);
        content.setBeginTime("2017-10-16");
        content.setEndTime("2017-10-17");
        content.setReason("测试");
        ContentDao contentDao = new ContentDao();
        System.out.println(contentDao.addContent(content));
    }
}
