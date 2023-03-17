package com.oj.springchat.domain.member.exception;


import com.oj.springchat.domain.model.Email;
import com.oj.springchat.global.error.ErrorCode;
import com.oj.springchat.global.error.exception.InvalidValueException;

/**
 * throw when sign up with Occupied Email
 * @author kim-onji
 * @see com.oj.springchat.global.error.GlobalExceptionHandler
 * @see com.oj.springchat.global.error.exception.BusinessException
 */
public class OccupiedEmailException extends OccupiedMemberPropertyException {
    public OccupiedEmailException(Email email) {
        super(email.getValue(), ErrorCode.OCCUPIED_EMAIL);
    }
}
