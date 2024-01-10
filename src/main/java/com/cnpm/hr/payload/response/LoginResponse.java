package com.cnpm.hr.payload.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginResponse {
    private String token;
    private String message;
//    private List<String> roles;
}
