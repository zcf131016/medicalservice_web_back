package com.example.medicalservice.security.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Lin YuHang
 * @date 2021/6/16 19:10
 */
public class JWTToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
