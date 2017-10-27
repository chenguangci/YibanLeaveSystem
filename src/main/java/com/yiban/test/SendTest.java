package com.yiban.test;

import com.yiban.service.student.FormHandle;
import org.junit.Test;

public class SendTest {
    @Test
    public void sendLetter(){
        String[] information = new String[8];
        information[0] = "201524133102";
        information[1] = "小明";
        information[2] = "17876253432";
        information[3] = "音乐学院";
        information[4] = "音乐";
        information[5] = "2017-10-24 00:00:00";
        information[6] = "2017-10-26 00:00:00";
        information[7] = "测试";
        FormHandle formHandle = new FormHandle();
        System.out.println(formHandle.setInformation(information));
    }
}
