package com.oj.springchat.domain.member.exception;

import com.oj.springchat.global.error.ErrorCode;
import com.oj.springchat.global.error.exception.InvalidValueException;

/**
 * Parent class of exceptions that occur when a member's property is caught in a Unique Constraint
 * do not create directly
 */
public class OccupiedMemberPropertyException extends InvalidValueException {
    protected OccupiedMemberPropertyException(String value, ErrorCode errorCode) {
        super(value, errorCode);
    }

    //OccupiedMemberPropertyException must have ErrorCode
    private OccupiedMemberPropertyException(String value) {
        super(value);
    }
}
