package com.heloay.heloay.service;

import com.heloay.heloay.domain.Member;
import com.heloay.heloay.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member getBoard(Member member){
        Member member1 = new Member(1L, "안녕");
        memberRepository.save(member1);
        return member1;
    }
}
