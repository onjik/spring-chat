package com.oj.springchat.domain.member.dao;

import com.oj.springchat.domain.member.domain.Authority;
import com.oj.springchat.domain.member.domain.Member;
import com.oj.springchat.domain.model.Email;
import com.oj.springchat.domain.model.Name;
import com.oj.springchat.test.RepositoryTest;
import com.oj.springchat.test.util.MemberTestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MemberRepository 테스트")
class MemberRepositoryTest extends RepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("정상적으로 Autowired로 불러와지는지 테스트")
    @Test
    public void autowiredTest(){
        assertNotNull(memberRepository);
        assertInstanceOf(MemberRepository.class,memberRepository);
    }


    @Test
    @DisplayName("save")
    public void saveTest(){
        //given
        Email testEmail = Email.of("test@a.a");
        Name testName = Name.builder().firstName("testFirst").lastName("testLast").build();
        Authority testAuthority = Authority.ROLE_USER;
        String testPassword = "testPw1234";

        Member testMember = Member.builder()
                .authority(testAuthority)
                .email(testEmail)
                .name(testName)
                .password(testPassword)
                .build();

        //when
        assertDoesNotThrow(() -> memberRepository.save(testMember));
        //then
        Member findedMember = memberRepository.findById(testMember.getId()).orElse(null);
        assertNotNull(findedMember);
        assertEquals(testMember,findedMember);
    }

    @Test
    @DisplayName("double save test")
    public void doubleSave(){
        //given
        Member newTestMember = MemberTestUtil.createNewTestMember();
        Member memberWithSameEmail = MemberTestUtil.createNewTestMember();
        memberRepository.save(newTestMember);
        //when then
        assertThrows(
                DataIntegrityViolationException.class,
                () -> memberRepository.save(memberWithSameEmail)
        );

    }

    @Test
    @DisplayName("findByEmail")
    public void findByEmailTest(){
        //given
        Member newTestMember = MemberTestUtil.createNewTestMember();
        memberRepository.save(newTestMember);
        //when
        Member findedMember = memberRepository.findByEmail(newTestMember.getEmail());

        //then
        assertNotNull(findedMember);
        assertEquals(findedMember,newTestMember);
    }

    @Test
    @DisplayName("existsByEmail")
    public void existsByEmailTest(){
        //given
        Member newTestMember = MemberTestUtil.createNewTestMember();
        memberRepository.save(newTestMember);
        //when
        boolean resultToBeTrue = memberRepository.existsByEmail(newTestMember.getEmail());
        boolean resultToBeFalse = memberRepository.existsByEmail(Email.of("differentEmail@a.a"));
        //then
        assertTrue(resultToBeTrue);
        assertFalse(resultToBeFalse);
    }

    @Test
    @DisplayName("deleteByEmail")
    public void deleteByEmailTest(){
        //given
        Member newTestMember = MemberTestUtil.createNewTestMember();
        memberRepository.save(newTestMember);
        //when
        memberRepository.deleteByEmail(newTestMember.getEmail());
        boolean isExist = memberRepository.existsByEmail(newTestMember.getEmail());
        //then
        assertFalse(isExist);
    }

}