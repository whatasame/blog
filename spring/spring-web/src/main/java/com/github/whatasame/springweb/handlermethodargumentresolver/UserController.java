package com.github.whatasame.springweb.handlermethodargumentresolver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/auth")
    public String getMemberId(@User final Long memberId) {
        return "회원 식별자: " + memberId;
    }
}
