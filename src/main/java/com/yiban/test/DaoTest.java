package com.yiban.test;

import com.yiban.bean.LeaveContent;
import com.yiban.service.student.FormHandle;
import net.sf.json.JSONArray;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DaoTest {
    @Test
    public void testContentDao(){
        LeaveContent content = new LeaveContent();
        content.setStudentId("201524133114");
        content.setName("陈光赐");
        content.setTelephone("17876253432");
        content.setDepartment("计算机科学与软件学院、大数据学院");
        content.setMajor("软件工程");
        content.setBeginTime("2017-10-16");
        content.setEndTime("2017-10-17");
        content.setReason("有事回家");
        content.setAgree("no");
        LeaveContent content1 = new LeaveContent();
        content1.setStudentId("201524133101");
        content1.setName("小明");
        content1.setTelephone("17876253432");
        content1.setDepartment("计算机科学与软件学院、大数据学院");
        content1.setMajor("物联网工程");
        content1.setBeginTime("2017-10-20");
        content1.setEndTime("2017-10-21");
        content1.setReason("请病假");
        content.setAgree("no");
        LeaveContent content2 = new LeaveContent();
        content2.setStudentId("201524133101");
        content2.setName("小明");
        content2.setTelephone("17876253432");
        content2.setDepartment("计算机科学与软件学院、大数据学院");
        content2.setMajor("计算机科学与技术");
        content2.setBeginTime("2017-10-26");
        content2.setEndTime("2017-10-28");
        content2.setReason("请病假");
        content.setAgree("no");
        List<LeaveContent> contents = new ArrayList<LeaveContent>();
        contents.add(content);
        contents.add(content1);
        contents.add(content2);
        JSONArray object = JSONArray.fromObject(contents);
        System.out.println(object);
        System.out.println(object.toString());
    }
}
