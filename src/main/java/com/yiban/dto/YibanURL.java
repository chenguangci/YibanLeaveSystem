package com.yiban.dto;

/**
 * 统一管理URL,将需要调用的易班接口统一在这个类中管理
 */
public class YibanURL {
    //消息发送URL
    public static final String SendLetter = "https://openapi.yiban.cn/msg/letter";
    //获取有效授权
    public static final String ResetToken = "https://openapi.yiban.cn/oauth/reset_token";
    //用户校方认证信息
    public static final String VerifyMe = "https://openapi.yiban.cn/user/verify_me";
}
