package com.oj.springchat.domain.member.domain;

//MemberConstant 로 한번에 관리
import com.oj.springchat.domain.member.domain.MemberConstant.Constraint;
import com.oj.springchat.domain.member.domain.MemberConstant.ColumnName;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oj.springchat.domain.member.dto.SignUpRequest;
import com.oj.springchat.domain.model.Email;
import com.oj.springchat.domain.model.Name;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Common Member Entity
 * @see MemberConstant
 */
@Entity
@Table(name = "member", uniqueConstraints = {
        @UniqueConstraint(columnNames = {ColumnName.EMAIL}, name = Constraint.EMAIL_UNIQUE_VIOLATION),
        @UniqueConstraint(columnNames = {ColumnName.NICKNAME}, name = Constraint.NICKNAME_UNIQUE_VIOLATION)
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"email", "name", "authority"})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ColumnName.ID, updatable = false)
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = ColumnName.EMAIL, nullable = false, updatable = false, length = 50))
    private Email email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = ColumnName.PASSWORD, nullable = false)
    private String password;

    @Embedded
    @AttributeOverride(name = "first", column = @Column(name = ColumnName.FIRST_NAME, nullable = false))
    @AttributeOverride(name = "middle", column = @Column(name = ColumnName.MIDDLE_NAME, nullable = false))
    @AttributeOverride(name = "last", column =  @Column(name = ColumnName.LAST_NAME, nullable = false))
    private Name name;

    @Column(name = ColumnName.NICKNAME, nullable = false)
    private String nickname;

    @Column(name = ColumnName.ROLE, nullable = false)
    @Convert(converter = AuthorityConverter.class)
    private Authority authority;


    @CreationTimestamp
    @Column(name = ColumnName.CREATED_AT, nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = ColumnName.UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Member(Email email, String password, Authority authority, Name name, String nickname) {
        this.email = email;
        this.password = password;
        this.authority = authority;
        this.name = name;
        this.nickname = nickname;
    }

    public static Member of(SignUpRequest request, String encodedPassword, Authority authority){
        return Member.builder()
                .email(request.getEmail())
                .name(request.getName())
                .nickname(request.getNickName())
                .authority(authority)
                .password(encodedPassword)
                .build();
    }

}
