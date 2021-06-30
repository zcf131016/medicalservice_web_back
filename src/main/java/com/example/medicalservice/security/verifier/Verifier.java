package com.example.medicalservice.security.verifier;

import com.example.medicalservice.exception.UserFriendException;
import com.example.medicalservice.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lin YuHang
 * @date 2021/6/30 16:19
 */
@Service
public class Verifier {
    @Autowired
    RedisService redisService;

    public void MailCodeCheck(String mail, String code) {
        String rCode = redisService.get(mail);
        if(rCode == null){
            throw new UserFriendException("验证码已过期！","901");
        } else if (code.equals(rCode)){
            throw new UserFriendException( "验证码正确！","200");
        } else {
            throw new UserFriendException("验证码错误!","912");
        }
    }
}
