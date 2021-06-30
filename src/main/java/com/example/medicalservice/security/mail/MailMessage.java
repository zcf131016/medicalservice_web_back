package com.example.medicalservice.security.mail;

import io.swagger.annotations.ApiModel;

/**
 * @author Lin YuHang
 * @date 2021/6/30 10:19
 */
@ApiModel
public class MailMessage {
    String mail;
    String code;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
