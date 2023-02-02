package com.heloay.heloay.controller;

import com.heloay.heloay.domain.Member;
import com.heloay.heloay.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
   private final MemberService memberService;
    @GetMapping("/")
    public String member(Member member){
        Member board = memberService.getBoard(member);
        return "hello" + board.getUsername();
    }
}
