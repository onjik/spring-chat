package com.oj.springchat.domain.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;



@Getter
@ToString(of = {"firstName", "middleName" , "lastName"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Name {

    @NotEmpty
    @Column(name = "first_name",length = 50)
    private String firstName;

    @Nullable
    @Column(name = "middle_name", length = 50)
    private String middleName;

    @NotEmpty
    @Column(name = "last_name", length = 50)
    private String lastName;


    @Builder
    public Name(String firstName, String middleName, String lastName){
        this.firstName = firstName;
        this.middleName = (middleName == null || middleName.isBlank()) ? null : middleName;
        this.lastName = lastName;
    }

    public String getFullName() {
        if (this.middleName == null){
            return String.format("%s %s",this.firstName, this.lastName);
        }
        return String.format("%s %s %s",this.firstName, this.middleName,this.lastName);
    }

    public String toString(){
        return getFullName();
    }
}
