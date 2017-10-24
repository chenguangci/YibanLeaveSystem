package com.yiban.test;

import com.yiban.service.FormHandle;
import org.junit.Test;

public class SendTest {
    @Test
    public void sendLetter(){
        String[] information = new String[9];
        information[0] = "201524133102";
        information[1] = "小明";
        information[2] = "音乐学院";
        information[3] = "音乐";
        information[4] = "2016";
        information[5] = "1";
        information[6] = "2017-10-24 00:00:00";
        information[7] = "2017-10-26 00:00:00";
        information[8] = "测试";
        FormHandle formHandle = new FormHandle();
        formHandle.setInformation(information);
        formHandle.getResult();
    }
}
