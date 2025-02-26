package org.example.practice4.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.practice4.auth.dto.AuthLoginRequestDto;
import org.example.practice4.auth.dto.AuthLoginResponseDto;
import org.example.practice4.auth.dto.AuthSignupRequestDto;
import org.example.practice4.member.dto.MemberSaveResponseDto;
import org.example.practice4.member.entity.Member;
import org.example.practice4.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signup(AuthSignupRequestDto dto) {
        Member member = new Member(dto.getEmail());
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public AuthLoginResponseDto login(AuthLoginRequestDto dto) {
        Member member = memberRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new IllegalStateException("해당 id 없음")
        );
        return new AuthLoginResponseDto(member.getId());
    }
}
