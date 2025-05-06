package com.entraId.oicd.Oicd_EntraId.controller;

import com.entraId.oicd.Oicd_EntraId.model.LoginRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final WebClient webClient = WebClient.builder().build();

    @PostMapping
    public Mono<Map<String, Object>> login(@RequestBody LoginRequest request) {
        return webClient.post()
                .uri("https://login.microsoftonline.com/{tenant}/oauth2/v2.0/token", "TENANT_ID")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(BodyInserters.fromFormData("client_id", "TU_CLIENT_ID")
                        .with("client_secret", "TU_CLIENT_SECRET")
                        .with("grant_type", "authorization_code")
                        .with("code", request.getCode())
                        .with("redirect_uri", request.getRedirectUri())
                        .with("scope", "openid profile email"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
    }
}