package com.entraId.oicd.Oicd_EntraId.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String code;
    private String redirectUri;
}
