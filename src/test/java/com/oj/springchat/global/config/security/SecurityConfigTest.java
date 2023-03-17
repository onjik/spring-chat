package com.oj.springchat.global.config.security;

import com.oj.springchat.test.IntegrationTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


class SecurityConfigTest{

    static private AnnotationConfigApplicationContext ac;

    @BeforeAll
    public static void givenApplicationContext(){
        //given
        ac = new AnnotationConfigApplicationContext(SecurityConfig.class);

    }

    @Nested
    @DisplayName("PasswordEncoder Test")
    class PasswordEncoderTest{

        @Test
        @DisplayName("Bean 등록")
        public void autowiredTest() throws Exception{
            //when
            PasswordEncoder passwordEncoder = ac.getBean("passwordEncoder", PasswordEncoder.class);

            //then
            assertNotNull(passwordEncoder);
            assertInstanceOf(PasswordEncoder.class,passwordEncoder);
        }

        @Test
        @DisplayName("인코딩 후 원본과 정상적으로 매칭")
        public void encodingAndMatching() {
            //given
            String testPassword = "1234569874";

            //when
            PasswordEncoder passwordEncoder = ac.getBean("passwordEncoder", PasswordEncoder.class);
            String encodedPassword = passwordEncoder.encode(testPassword);

            //then
            assertNotEquals(encodedPassword, testPassword);
            assertTrue(passwordEncoder.matches(testPassword, encodedPassword));
        }



    }


}