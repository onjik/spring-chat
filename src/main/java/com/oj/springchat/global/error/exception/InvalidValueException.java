package com.oj.springchat.global.error.exception;

import com.oj.springchat.global.error.ErrorCode;


/**
 * 로직상 유효하지 않은 값일 경우 사용하는 Exception
 * 쿠폰 만료, 아이디 중복 등
 */
public class InvalidValueException extends BusinessException{
    public InvalidValueException(String value) {
        super(value, ErrorCode.INVALID_INPUT_VALUE);
    }
    public InvalidValueException(String value, ErrorCode errorCode){
        super(value, errorCode);
    }
}
