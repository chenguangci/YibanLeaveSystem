package com.yiban.service.teacher;

/**
 * 生成验证码
 */
public class Random {
    public String getVerificationCode(){
        char[] c = {'0','1','2','3','4','5','6','7','8','9'};
        StringBuilder code = new StringBuilder();
        int j = 0;
        for (int i = 0; i<8; i++){
            j = (int)(Math.random()*10)%10;
            code.append(c[j]);
        }
        System.out.println(code);
        return code.toString();
    }
}
