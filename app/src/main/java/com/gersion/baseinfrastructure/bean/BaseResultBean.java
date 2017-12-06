package com.gersion.baseinfrastructure.bean;

/**
 * Created by aa326 on 2017/11/29.
 */

public class BaseResultBean {
    private int result;
    private String error;
    private int code;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
