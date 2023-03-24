package com.oj.springchat.test.util;

import com.oj.springchat.domain.member.domain.Authority;
import com.oj.springchat.domain.member.domain.Member;
import com.oj.springchat.domain.member.dto.SignUpRequest;
import com.oj.springchat.domain.model.Email;
import com.oj.springchat.domain.model.Name;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class MemberTestUtil {

    public static final Email testEmail = Email.of("test@a.a");
    public static final Name testName = Name.builder().firstName("testFirst").lastName("testLast").build();
    public static final Authority testAuthority = Authority.ROLE_USER;
    public static final String testPassword = "testPw1234";
    public static final String testNickname = "funnyGuy";

    public static Member createNewTestMember(){

        return Member.builder()
                .authority(testAuthority)
                .email(testEmail)
                .name(testName)
                .password(testPassword)
                .nickname(testNickname)
                .build();
    }
    public static Member createNewTestMember(Email email){
        return Member.builder()
                .authority(testAuthority)
                .email(email)
                .name(testName)
                .password(testPassword)
                .nickname(testNickname)
                .build();
    }

    public static SignUpRequest createSignUpRequest(){
        //constructor is private so use reflection
        SignUpRequest signUpRequest;
        try{
            //instance creation
            Class<SignUpRequest> signUpRequestClass = SignUpRequest.class;
            Constructor<SignUpRequest> declaredConstructor = signUpRequestClass.getDeclaredConstructor(null);
            declaredConstructor.setAccessible(true);
            signUpRequest = declaredConstructor.newInstance();
            declaredConstructor.setAccessible(false);

            Map<String, Object> fiedMap = new HashMap<>();
            fiedMap.put("email",testEmail);
            fiedMap.put("name",testName);
            fiedMap.put("nickname",testNickname);
            fiedMap.put("password",testPassword);

            //field injection
            Field[] declaredFields = signUpRequestClass.getDeclaredFields();
            for (Field field : declaredFields){
                field.setAccessible(true);
                field.set(signUpRequest,fiedMap.get(field.getName()));
                field.setAccessible(false);
            }

        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return signUpRequest;

    }
}
