package com.oj.springchat.domain.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString(of = {"value"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Email {

    @jakarta.validation.constraints.Email
    @Column(name = "email", length = 50)
    @NotEmpty
    private String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email of(String value){
        return new Email(value);
    }

    @Nullable
    public String getHost() {
        final int index = value.indexOf("@");
        return index == -1 ? null : value.substring(index + 1);
    }

    @Nullable
    public String getId(){
        final int index = value.indexOf("@");
        return index == -1 ? null : value.substring(0,index);
    }
}
