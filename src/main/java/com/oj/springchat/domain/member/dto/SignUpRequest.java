package com.oj.springchat.domain.member.dto;

import com.oj.springchat.domain.model.Email;
import com.oj.springchat.domain.model.Name;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {

    @Valid
    private Email email;

    @Valid
    @NotNull
    private Name name;

    @NotBlank
    private String nickname;

    @NotBlank
    private String password;

}
