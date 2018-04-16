package com.yiban.dto;

import com.yiban.entity.Information;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回教师端的请假数据
 */
public class Data {
    private boolean success;
    private String message;
    private int total;
    private int page;
    private List<Information> rows =new ArrayList<Information>();

    public Data(boolean success, int total, int page, List<Information> list) {
        this.success = success;
        this.total = total;
        this.page = page;
        this.rows = list;
    }

    public Data(Dictionary dictionary) {
        this.success = dictionary.isSuccess();
        this.message = dictionary.getStateInfo();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Information> getRows() {
        return rows;
    }

    public void setRows(List<Information> list) {
        this.rows = list;
    }
}
