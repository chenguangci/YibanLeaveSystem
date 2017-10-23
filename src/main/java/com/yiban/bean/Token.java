package com.yiban.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Token {
    private String tokenType;
    private String token;
    private String addTime;

    public Token(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        addTime = format.format(date);
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAddTime() {
        return addTime;
    }

}
