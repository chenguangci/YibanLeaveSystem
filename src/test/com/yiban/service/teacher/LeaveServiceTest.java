package com.yiban.service.teacher;

import com.yiban.mapper.ContentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class LeaveServiceTest {
    @Resource
    private ContentMapper contentMapper;

    /*
     * JDBC Connection [com.mysql.jdbc.JDBC4Connection@7674b62c] will not be managed by Spring
     * ==>  Preparing: UPDATE information SET status = ? WHERE id = ?
     * ==> Parameters: 2(Integer), 1002(Long)
     * <==    Updates: 1
     */
    @Test
    public void updateLeave() throws Exception {
        int a = contentMapper.updateLeave(1002, 2);
        System.out.println(a);
    }

    @Test
    public void selectAll() throws Exception {
    }

}