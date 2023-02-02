package com.heloay.heloay.service;

import com.heloay.heloay.domain.Member;
import com.heloay.heloay.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class MemberServiceTest {

    MemberRepository memberRepository;

    @Test
    public void test1(){
        Member member = new Member(1L, "안녕하세요");
        Member saveMember = memberRepository.save(member);
        log.info(saveMember.getUsername());

    }


}