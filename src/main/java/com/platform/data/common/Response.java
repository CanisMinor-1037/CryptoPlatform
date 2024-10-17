package com.platform.data.common;

import lombok.Data;

@Data
public class Response<T> {
    private Integer code;
    private String errorMsg;
    private T data;
    public static <T> Response<T> success(T data){
        Response<T> response = new Response<>();
        response.setCode(200);
        response.setErrorMsg("");
        response.setData(data);
        return response;
    }
    public static <T> Response<T> fail(T data){
        Response<T> response = new Response<>();
        response.setCode(500);
        response.setErrorMsg("fail");
        response.setData(data);
        return response;
    }
}
