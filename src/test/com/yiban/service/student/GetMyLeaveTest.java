package com.yiban.service.student;

import com.yiban.mapper.ContentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class GetMyLeaveTest {
    @Resource
    private ContentMapper contentMapper;
    @Test
    public void getMyLeave() throws Exception {
        contentMapper.myLeaves("201524133112");
    }

}