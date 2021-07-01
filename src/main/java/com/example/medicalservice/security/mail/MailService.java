package com.example.medicalservice.security.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author Lin YuHang
 * @date 2021/6/30 8:56
 */
@Service
public final class MailService {

    @Resource
    private JavaMailSender javaMailSender;

    private String from = "1024770542@qq.com";


    /*
        发送富文本邮件
     */
    void sendRichMail(String to, String subject, String text, String filePath) throws MessagingException{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);

        helper.setText(text,true);
        // 图片占位写法  如果图片链接写入模板 注释下面这一行
        helper.addInline("qr",new FileSystemResource(filePath));
        javaMailSender.send(mimeMessage);
    }

    /*
        发送普通文本邮件
     */
    public void sendMail(String to, String subject, String text) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
