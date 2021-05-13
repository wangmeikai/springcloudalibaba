package com.wmk.common;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private int code;
    private String message;
    private T data;
    private Exception exception;

    public Result(int code, String message, T data, Exception exception) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.exception = exception;
    }


    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", exception=" + exception +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
