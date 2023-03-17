package com.oj.springchat.domain.member.exception;

import com.oj.springchat.global.error.ErrorCode;
import com.oj.springchat.global.error.exception.InvalidValueException;

public class OccupiedNickNameException extends OccupiedMemberPropertyException {
    public OccupiedNickNameException(String nickName) {
        super(nickName, ErrorCode.OCCUPIED_NICKNAME);
    }
}
