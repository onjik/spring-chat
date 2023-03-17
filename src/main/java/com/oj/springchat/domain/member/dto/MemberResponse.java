package com.oj.springchat.domain.member.dto;

import com.oj.springchat.domain.member.domain.Member;
import com.oj.springchat.domain.model.Email;
import com.oj.springchat.domain.model.Name;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponse {
    private String name;
    private String nickName;
    private String email;

    public MemberResponse(Member member) {
        this.name = member.getName().getFullName();
        this.nickName = member.getNickname();
        this.email = member.getEmail().getValue();
    }
}
