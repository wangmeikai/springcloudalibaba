package com.wmk.util;

import java.io.Serializable;
/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/23
 * @TIME: 17:40
 **/
public class ResultCommon<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private T data;

    public static <T> ResultCommon<T> ok() {
        return restResult(null, CommonConstants.SUCCESS, null);
    }

    public static <T> ResultCommon<T> ok(T data) {
        return restResult(data, CommonConstants.SUCCESS, null);
    }

    public static <T> ResultCommon<T> ok(T data, String msg) {
        return restResult(data, CommonConstants.SUCCESS, msg);
    }

    public static <T> ResultCommon<T> failed() {
        return restResult(null, CommonConstants.FAIL, null);
    }

    public static <T> ResultCommon<T> failed(String msg) {
        return restResult(null, CommonConstants.FAIL, msg);
    }

    public static <T> ResultCommon<T> failed(T data) {
        return restResult(data, CommonConstants.FAIL, null);
    }

    public static <T> ResultCommon<T> failed(T data, String msg) {
        return restResult(data, CommonConstants.FAIL, msg);
    }

    private static <T> ResultCommon<T> restResult(T data, int code, String msg) {
        ResultCommon<T> apiResult = new ResultCommon<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
