package com.oj.springchat.test.util;

import com.oj.springchat.domain.member.domain.Authority;
import com.oj.springchat.domain.member.domain.Member;
import com.oj.springchat.domain.member.dto.SignUpRequest;
import com.oj.springchat.domain.model.Email;
import com.oj.springchat.domain.model.Name;

public class MemberTestUtil {

    private static Email testEmail = Email.of("test@a.a");
    private static Name testName = Name.builder().firstName("testFirst").lastName("testLast").build();
    private static Authority testAuthority = Authority.ROLE_USER;
    private static String testPassword = "testPw1234";
    public static Member createNewTestMember(){

        return Member.builder()
                .authority(testAuthority)
                .email(testEmail)
                .name(testName)
                .password(testPassword)
                .build();
    }
    public static Member createNewTestMember(Email testEmail){
        return Member.builder()
                .authority(testAuthority)
                .email(testEmail)
                .name(testName)
                .password(testPassword)
                .build();
    }

    public static SignUpRequest createSignUpRequest(){
        return new SignUpRequest(testEmail,testName,testPassword);
    }
}
