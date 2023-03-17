package com.oj.springchat.domain.member.application;

import com.oj.springchat.domain.member.dao.MemberRepository;
import com.oj.springchat.domain.member.domain.Authority;
import com.oj.springchat.domain.member.domain.Member;
import com.oj.springchat.domain.member.dto.SignUpRequest;
import com.oj.springchat.domain.member.exception.OccupiedEmailException;
import com.oj.springchat.domain.model.Email;
import com.oj.springchat.domain.model.Name;
import com.oj.springchat.test.IntegrationTest;
import com.oj.springchat.test.util.MemberTestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("MemberSignUpService 테스트")
class MemberSignUpServiceTest extends IntegrationTest {

    @Autowired
    MemberSignUpService memberSignUpService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Test
    @DisplayName("autowired 로 잘 불러와지는 지")
    public void autowiredTest(){
        assertNotNull(memberSignUpService);
    }

    @Nested
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DisplayName("doSignUp 테스트")
    class SignUpTest{

        @Test
        @DisplayName("처음 회원가입")
        public void newMember(){
            //given - testMember;
            Email testEmail = Email.of("test@a.a");
            Name testName = Name.builder().firstName("testFirst").lastName("testLast").build();
            String testPassword = "testPw1234";

            SignUpRequest signUpRequest = new SignUpRequest(
                    testEmail,
                    testName,
                    testPassword
            );
            //when
            Member resultMember = memberSignUpService.doSignUp(signUpRequest);
            //then
            assertEquals(testEmail,resultMember.getEmail());
            assertEquals(testName,resultMember.getName());
            assertTrue(passwordEncoder.matches(testPassword,resultMember.getPassword()));
            assertEquals(Authority.ROLE_USER,resultMember.getAuthority());
        }

        @Test
        @DisplayName("회원가입시 권한을 ROLE_USER 지정")
        public void authorityTest(){
            //given - testMember;
            Email testEmail = Email.of("test@a.a");
            Name testName = Name.builder().firstName("testFirst").lastName("testLast").build();
            String testPassword = "testPw1234";

            SignUpRequest signUpRequest = new SignUpRequest(
                    testEmail,
                    testName,
                    testPassword
            );
            //when
            Member resultMember = memberSignUpService.doSignUp(signUpRequest);
            //then
            assertEquals(Authority.ROLE_USER,resultMember.getAuthority());
        }

        @Test
        @DisplayName("이미 가입한 이메일로 회원가입 시 에러를 반환")
        public void joinWithOccupiedEmail(){
            //given
            Email testEmail = Email.of("test@a.a");
            Name testName = Name.builder().firstName("testFirst").lastName("testLast").build();
            String testPassword = "testPw1234";

            SignUpRequest signUpRequest = new SignUpRequest(
                    testEmail,
                    testName,
                    testPassword
            );
            memberSignUpService.doSignUp(signUpRequest);

            //then
            assertThrows(OccupiedEmailException.class,() -> memberSignUpService.doSignUp(signUpRequest));
        }

        @Test
        @DisplayName("비밀번호가 정상적으로 암호화")
        public void passwordEncodingTest(){
            //given
            Email testEmail = Email.of("test@a.a");
            Name testName = Name.builder().firstName("testFirst").lastName("testLast").build();
            String testPassword = "testPw1234";

            SignUpRequest signUpRequest = new SignUpRequest(
                    testEmail,
                    testName,
                    testPassword
            );

            //when
            Member savedMember = memberSignUpService.doSignUp(signUpRequest);

            //then
            assertNotEquals(testPassword, savedMember.getPassword());
            passwordEncoder.matches(testPassword, savedMember.getPassword());
        }
    }
}