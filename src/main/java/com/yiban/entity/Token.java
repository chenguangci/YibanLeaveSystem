package com.yiban.entity;

import java.io.Serializable;
import java.util.Date;

public class Token implements Serializable {

    private static final long serialVersionUID = -3758581923769645921L;
    private int tokenType;
    private String token;
    private Date addTime;

    @Override
    public String toString() {
        return "Token{" +
                "tokenType=" + tokenType +
                ", token='" + token + '\'' +
                ", addTime=" + addTime +
                '}';
    }

    public int getTokenType() {
        return tokenType;
    }

    public void setTokenType(int tokenType) {
        this.tokenType = tokenType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
