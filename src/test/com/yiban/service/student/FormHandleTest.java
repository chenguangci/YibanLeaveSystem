package com.yiban.service.student;

import com.yiban.entity.Information;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class FormHandleTest {
    @Resource
    FormHandle formHandle;
    @Test
    public void setInfoAndSend() throws Exception {
        Information information = new Information();
        information.setStudentId("201524133112");
        information.setBeginTime("2018-02-25 00:00:00");
        information.setEndTime("2018-02-26 00:00:00");
        information.setNumber(4);
        information.setReason("test");
        information.setPhone("10086");
        Assert.assertNotNull(information);
        formHandle.setInfoAndSend(information);
    }

}