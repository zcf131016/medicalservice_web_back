package com.example.medicalservice.security.shiro;


import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Lin YuHang
 * @date 2021/6/30 16:42
 */
@Service
public class IPasswordEncoder {

    MessageDigest messageDigest;

    public String encode(String password) throws NoSuchAlgorithmException {
        String encodePwd = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes("UTF-8"));
            encodePwd = byte2Hex(messageDigest.digest());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodePwd;
    }

    public boolean match(String password, String encodePwd) throws NoSuchAlgorithmException {
        if(encode(password).equals(encodePwd)){
            return true;
        }
        return false;
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
