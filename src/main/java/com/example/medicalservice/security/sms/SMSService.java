package com.example.medicalservice.security.sms;

import com.aliyun.tea.*;
import com.aliyun.dysmsapi20170525.*;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.*;
import com.aliyun.teaopenapi.models.*;
import com.example.medicalservice.service.RedisService;
import com.example.medicalservice.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Lin YuHang
 * @date 2021/7/2 9:36
 */
@Service
public class SMSService {
    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */

    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.signName}")
    private String signName;

    @Value("${aliyun.templateCode}")
    private String templateCode;

    @Autowired
    RedisService redisService;


    private static final long CODE_EXPIRE_SECONDS = 600;


    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public void sendMsg(String phoneNumber) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = SMSService.createClient(accessKeyId, accessKeySecret);
        // 随机生成验证码
        String code = RandomUtil.getRandom(4).toString();
        System.out.println(code);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phoneNumber)
                .setSignName(signName)
                .setTemplateCode(templateCode)
                .setTemplateParam("{\"code\":" + code + "}");
        // 复制代码运行请自行打印 API 的返回值
        client.sendSms(sendSmsRequest);
        // 将发送的内容存入redis
        redisService.remove(phoneNumber);
        redisService.set(phoneNumber, code);
        redisService.expire(phoneNumber, CODE_EXPIRE_SECONDS);
    }
}
