package com.oj.springchat.domain.member.application;


import com.oj.springchat.domain.member.dao.MemberRepository;
import com.oj.springchat.domain.member.domain.Authority;
import com.oj.springchat.domain.member.domain.Member;
import com.oj.springchat.domain.member.domain.MemberConstant;
import com.oj.springchat.domain.member.dto.SignUpRequest;
import com.oj.springchat.domain.member.exception.OccupiedEmailException;
import com.oj.springchat.domain.member.exception.OccupiedNickNameException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSignUpService {

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;



    /**
     * sign up logic with check available
     * @param dto : SignUpRequest
     * @return : a registered member
     *
     * @throws OccupiedEmailException : sign up with occupied Email
     * @throws DataIntegrityViolationException : If an  unchecked data integrity violation occurs
     */
    public Member doSignUp(SignUpRequest dto){
        //After encryption to entity
        try {
            return memberRepository.save(
                    Member.of(dto,passwordEncoder.encode(dto.getPassword()),Authority.ROLE_USER)
            );
        } catch (DataIntegrityViolationException e){ //DataIntegrityViolation check
            String message = e.getMessage().toUpperCase();
            //if DataIntegrityViolation is caused by "Sign up with occupied email"
            if (message.contains(MemberConstant.Constraint.EMAIL_UNIQUE_VIOLATION.toUpperCase())){
                throw new OccupiedEmailException(dto.getEmail());
            } else if (message.contains(MemberConstant.Constraint.NICKNAME_UNIQUE_VIOLATION.toUpperCase())) {
                throw new OccupiedNickNameException(dto.getNickName());
            } else {
                throw e;
            }
        }
    }
}
