package com.github.whatsame.springweb.study.handlermethodargumentresolver.controller;

import com.github.whatsame.springweb.study.handlermethodargumentresolver.annotation.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @GetMapping("/auth")
    public String getMemberId(@User final Long memberId) {
        return "회원 식별자: " + memberId;
    }
}
