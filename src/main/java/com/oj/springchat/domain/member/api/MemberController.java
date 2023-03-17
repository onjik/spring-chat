package com.oj.springchat.domain.member.api;

import com.oj.springchat.domain.member.application.MemberSignUpService;
import com.oj.springchat.domain.member.domain.Member;
import com.oj.springchat.domain.member.dto.MemberResponse;
import com.oj.springchat.domain.member.dto.SignUpRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {


    final MemberSignUpService memberSignUpService;

    @ResponseBody
    @PostMapping
    public MemberResponse signUp(@RequestBody @Valid SignUpRequest signUpRequest){
        return new MemberResponse(
                memberSignUpService.doSignUp(signUpRequest)
        );
    }
}
