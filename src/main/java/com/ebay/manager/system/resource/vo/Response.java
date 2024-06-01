package com.ebay.manager.system.resource.vo;

import com.ebay.manager.system.resource.common.MsgCode;

public class Response<T> {
    String msg;
    int code;
    T data;

    public static Response getResponse(MsgCode msgCode){
        Response response = new Response();
        response.setCode(msgCode.getCode());
        response.setMsg(msgCode.getMsg());
        return response;
    }

    public static Response getResponse(int code, String msg){
        Response response = new Response();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static<T> Response<T> SUCCESS(T data){
        Response response = new Response<T>();
        response.setCode(MsgCode.SUCCESS.getCode());
        response.setMsg(MsgCode.SUCCESS.getMsg());
        response.setData(data);
        return response;
    }
    public static<T> Response<T> FAILED(T data){
        Response response = new Response();
        response.setCode(MsgCode.FAILED.getCode());
        response.setMsg(MsgCode.FAILED.getMsg());
        response.setData(data);
        return response;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
