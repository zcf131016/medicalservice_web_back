package com.example.medicalservice.security.verifier;

import com.example.medicalservice.exception.UserFriendException;
import com.example.medicalservice.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lin YuHang
 * @date 2021/6/30 16:19
 */
@Service
public class Verifier {
    @Autowired
    RedisService redisService;

    public void codeCheck(String key, String code) {
        String rCode = redisService.get(key);
        if(rCode == null){
            throw new UserFriendException("验证码已过期！","901");
        } else if (code.equals(rCode)){
            throw new UserFriendException( "验证码正确！","200");
        } else {
            throw new UserFriendException("验证码错误!","912");
        }
    }

    public boolean checkPhone(String phone) {
        Pattern p = Pattern.compile("^((13[0-9])|(14[0|5|6|7|9])|(15[0-3])|(15[5-9])|(16[6|7])|(17[2|3|5|6|7|8])|(18[0-9])|(19[1|8|9]))\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}
