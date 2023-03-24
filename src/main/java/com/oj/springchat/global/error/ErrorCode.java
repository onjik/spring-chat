package com.oj.springchat.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    //common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST,"C001","Invalid Input Value"),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST,"C002","Invalid Type Value"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C003","Method Not Allowed"),
    ACCESS_DENIED(HttpStatus.FORBIDDEN,"C004","Access is Denied"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"C005","Internal Server Error"),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND,"C006","Entity Not Found" ),

    //DB
    UNIQUE_CONSTRAINT(HttpStatus.CONFLICT,"D001","Database Unique Constraint Violation");



    private String message;
    private HttpStatus status;
    private String code;

    ErrorCode(HttpStatus status, String code, String message) {
        this.message = message;
        this.status = status;
        this.code = code;
    }
}
