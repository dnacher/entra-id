package com.entraId.oicd.Oicd_EntraId.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public page.";
    }

    @GetMapping("/protected")
    public String protectedEndpoint(@AuthenticationPrincipal OidcUser user) {
        return "This is a protected page. Hello " + user.getFullName();
    }
}

