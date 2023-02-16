package com.oj.springchat.global.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    //common
    INVALID_INPUT_VALUE(400,"C001","Invalid Input Value"),
    INVALID_TYPE_VALUE(400,"C002","Invalid Type Value"),
    METHOD_NOT_ALLOWED(405, "C003","Method Not Allowed"),
    ACCESS_DENIED(403,"C004","Access is Denied"),
    INTERNAL_SERVER_ERROR(500,"C005","Internal Server Error"),
    ENTITY_NOT_FOUND(404,"C006","Entity Not Found" );


    String message;
    int status;
    String code;

    ErrorCode(int status, String code, String message) {
        this.message = message;
        this.status = status;
        this.code = code;
    }
}
