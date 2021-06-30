package com.example.medicalservice.control;

import com.example.medicalservice.domain.User;
import com.example.medicalservice.exception.UserFriendException;
import com.example.medicalservice.security.jwt.JWTUtil;
import com.example.medicalservice.security.mail.MailMessage;
import com.example.medicalservice.security.mail.MailService;
import com.example.medicalservice.service.RedisService;
import com.example.medicalservice.service.UserService;
import com.example.medicalservice.util.RandomUtil;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Lin YuHang//zcf
 * @date 2021/6/17 15:02
 */
@Api(tags = "登录注册相关")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MailService mailService;

    private static long CODE_ESPIRE_SECONDS = 600;


    @ApiOperation(value="登录接口")
    @ResponseBody
    @PostMapping("/login")
    public Result login(@RequestBody User users) {
        String username = users.getUserName();
        String password = users.getPassWord();
        User user = userService.getUser(username);
        if (user != null && user.getPassWord().equals(password)) {
            System.out.println("登录成功");
            String token = JWTUtil.sign(username, password,user.getUserId());
            user.setPassWord(null);
            return Result.success().setToken(token).setData(user).setCode(ResultCodeEnum.OK.getCode()).setMsg("登录成功！");
        } else {
            return Result.success().setCode(ResultCodeEnum.LoginError.getCode()).setMsg("登录失败！");
        }
    }

    @ApiOperation(value = "注册用户")
    @ApiResponses({
            @ApiResponse(code=101,message="注册成功"),
            @ApiResponse(code=103,message="用户已存在")
    })
    @ResponseBody
    @PostMapping("/register")
    public Result register(@RequestBody User user) {

        try{
            userService.insertUser(user);
        }catch (UserFriendException e){
            return Result.success().setCode(ResultCodeEnum.RegisterAlreadyExist.getCode()).setMsg(e.getMsg());
        }
        return Result.success().setCode(ResultCodeEnum.Register.getCode()).setMsg("注册成功");
    }

    @ApiOperation(value="注销用户", notes = "请提示用户是否注销，注销将删除所有用户数据")
    @DeleteMapping("/logout")
    public Result logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Integer userId =  JWTUtil.getUserId(token);
        try {
            userService.deleteUserByUserId(userId);
        } catch (Exception e) {
            return Result.failure(ResultCodeEnum.NOT_IMPLEMENTED).setMsg("注销失败");
        }
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("注销成功");
    }

    @ApiOperation(value="获取邮件验证码")
    @GetMapping("/getMail/{mail}")
    public Result getMail(@PathVariable("mail") String mail) {
        // 随机生成验证码
        Integer validNumber = RandomUtil.getRandom(6);
        // 发送验证码
        try {
            mailService.sendMail(mail, "医疗教学平台", "您的验证码为: " + validNumber.toString());

            redisService.remove(mail);
            redisService.set(mail, validNumber.toString());
            redisService.expire(mail, CODE_ESPIRE_SECONDS);

        } catch (MessagingException e) {
            return Result.failure(ResultCodeEnum.NOT_IMPLEMENTED).setMsg("验证码发送失败");
        }
        return Result.success().setMsg("验证码发送成功,有效时长10分钟");
    }

    @ApiOperation(value="邮箱验证码登录")
    @PostMapping("/loginByEmail")
    public Result loginByMail(@RequestBody MailMessage mailMessage) {
        // 先判断邮箱是否存在用户表中
        User user = userService.getUserByEmail(mailMessage.getMail());
        if(user == null) return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST).setMsg("用户不存在");
        // 获取redis缓存中的验证码
        String code = redisService.get(mailMessage.getMail());
        if(code == mailMessage.getCode()) { // 验证码比对成功,签发token
            String token = JWTUtil.sign(user.getUserName(), user.getPassWord(),user.getUserId());
            user.setPassWord(null);
            return Result.success().setData(user).setToken(token).setMsg("登录成功");
        } else {
            return Result.failure(ResultCodeEnum.NOT_IMPLEMENTED).setMsg("请输入正确的验证码");
        }
    }



    @GetMapping("/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result unauthorized() {
        return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST);
    }
}
