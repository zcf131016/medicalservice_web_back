package com.example.medicalservice.domain;

/**
 * @author Lin YuHang
 * @date 2021/7/1 10:04
 */
public class ForgotPasswordDto {
    String email;
    String password;
    String code;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
