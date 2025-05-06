package com.entraId.oicd.Oicd_EntraId.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Hello public";
    }

    @GetMapping("/protected")
    public String protectedEndpoint(@AuthenticationPrincipal Jwt jwt) {
        return "Hello " + jwt.getClaimAsString("name");
    }
}

