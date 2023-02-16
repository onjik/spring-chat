package com.oj.springchat.global.error.exception;

import com.oj.springchat.global.error.ErrorCode;

public class EntityNotFoundException extends BusinessException{
    public EntityNotFoundException(String message){
        super(message,ErrorCode.ENTITY_NOT_FOUND);
    }

}
