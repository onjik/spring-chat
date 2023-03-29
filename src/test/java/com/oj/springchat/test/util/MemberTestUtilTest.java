package com.oj.springchat.test.util;

import com.oj.springchat.domain.member.dto.SignUpRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//pojo test
class MemberTestUtilTest {

    @Test
    public void createSignUpRequest(){
        SignUpRequest signUpRequest = MemberTestUtil.createSignUpRequest();
        assertEquals(signUpRequest.getName(),MemberTestUtil.testName);
        assertEquals(signUpRequest.getEmail(),MemberTestUtil.testEmail);
        assertEquals(signUpRequest.getPassword(),MemberTestUtil.testPassword);
        assertEquals(signUpRequest.getNickname(),MemberTestUtil.testNickname);
        assertEquals("a","differ"); //git hub action test를 위해 일부러 틀린 테스트 작성
    }

}