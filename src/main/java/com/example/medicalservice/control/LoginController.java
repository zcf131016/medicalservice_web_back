package com.example.medicalservice.control;

import com.example.medicalservice.domain.ForgotPasswordDto;
import com.example.medicalservice.domain.User;
import com.example.medicalservice.exception.UserFriendException;
import com.example.medicalservice.security.jwt.JWTUtil;
import com.example.medicalservice.security.mail.MailMessage;
import com.example.medicalservice.security.mail.MailService;
import com.example.medicalservice.security.shiro.IPasswordEncoder;
import com.example.medicalservice.security.sms.PhoneMessage;
import com.example.medicalservice.security.sms.SMSService;
import com.example.medicalservice.security.verifier.Verifier;
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
import java.security.NoSuchAlgorithmException;

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

    @Autowired
    private Verifier verifier;

    @Autowired
    IPasswordEncoder iPasswordEncoder;

    @Autowired
    SMSService smsService;

    private static long CODE_EXPIRE_SECONDS = 600;


    @ApiOperation(value="登录接口")
    @ResponseBody
    @PostMapping("/login")
    public Result login(@RequestBody User users) throws NoSuchAlgorithmException {
        String username = users.getUserName();
        String password = users.getPassWord();
        User user = userService.getUser(username);
        if (user != null && iPasswordEncoder.match(password, user.getPassWord())) {
            String token = JWTUtil.sign(username, user.getPassWord(),user.getUserId());
            user.setPassWord(null);
            return Result.success().setToken(token).setData(user).setCode(ResultCodeEnum.OK.getCode()).setMsg("登录成功！");
        } else {
            return Result.success().setCode(ResultCodeEnum.LoginError.getCode()).setMsg("登录失败，请检查账号密码！");
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
            user.setPassWord(iPasswordEncoder.encode(user.getPassWord()));
            userService.insertUser(user);
        }catch (UserFriendException e){
            return Result.success().setCode(ResultCodeEnum.RegisterAlreadyExist.getCode()).setMsg(e.getMsg());
        } catch (NoSuchAlgorithmException e) {
            return Result.failure(ResultCodeEnum.INTERNAL_SERVER_ERROR).setMsg("服务器内部错误");
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

    @ApiOperation(value="获取短信验证码")
    @GetMapping("/sms/{phoneNumber}")
    public Result getMsg(@PathVariable("phoneNumber") String phoneNumber) {
        if(!verifier.checkPhone(phoneNumber)) return Result.failure(ResultCodeEnum.NOT_IMPLEMENTED).setMsg("手机号无效！");
        try {
            smsService.sendMsg(phoneNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCodeEnum.NOT_IMPLEMENTED).setMsg("验证码发送失败");
        }
        return Result.success().setMsg("发送成功");
    }

    @ApiOperation(value="获取邮件验证码")
    @GetMapping("/getMail/{mail}")
    public Result getMail(@PathVariable("mail") String mail) {
        // 随机生成验证码
        Integer validNumber = RandomUtil.getRandom(6);
        // 发送验证码
        try {
            mailService.sendMail(mail,
                    "医疗教学平台",
                    "您的验证码为: " + validNumber.toString()
                            +"\n 若非您本人操作，请忽略！并留心账号安全！");
            redisService.remove(mail);
            redisService.set(mail, validNumber.toString());
            redisService.expire(mail, CODE_EXPIRE_SECONDS);
        } catch (MessagingException e) {
            return Result.failure(ResultCodeEnum.NOT_IMPLEMENTED).setMsg("验证码发送失败");
        }
        return Result.success().setMsg("验证码发送成功,有效时长10分钟");
    }

    @ApiOperation(value="检测邮箱验证码是否正确")
    @PostMapping("/checkEmailCode")
    public Result checkEmailCode(@RequestBody MailMessage mailMessage) {
        try {
            verifier.codeCheck(mailMessage.getMail(), mailMessage.getCode());
        } catch (UserFriendException e) {
            if(e.getCode().equals("901")) return Result.failure(ResultCodeEnum.VERIFICATION_CODE_EXPIRED).setMsg(e.getMsg());
            else if(e.getCode().equals("912")) return Result.failure(ResultCodeEnum.VERIFICATION_CODE_ERROR).setMsg(e.getMsg());
            else {
                return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("验证码正确");
            }
        }
        return Result.failure(ResultCodeEnum.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value="邮箱验证码登录")
    @PostMapping("/loginByEmail")
    public Result loginByMail(@RequestBody MailMessage mailMessage) {
        // 先判断邮箱是否存在用户表中
        User user = null;
        try {
            user = userService.getUserByEmail(mailMessage.getMail());
        } catch (UserFriendException e) {
            return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST).setMsg("该邮箱未绑定任何用户！");
        }
        // 验证验证码
        try {
            verifier.codeCheck(mailMessage.getMail(), mailMessage.getCode());
        } catch (UserFriendException e) {
            if(e.getCode().equals("901")) return Result.failure(ResultCodeEnum.VERIFICATION_CODE_EXPIRED).setMsg(e.getMsg());
            else if(e.getCode().equals("912")) return Result.failure(ResultCodeEnum.VERIFICATION_CODE_ERROR).setMsg(e.getMsg());
            else {
                String token = JWTUtil.sign(user.getUserName(), user.getPassWord(),user.getUserId());
                user.setPassWord(null);
                return Result.success().setData(user).setToken(token).setMsg("登录成功");
            }
        }
        return Result.failure(ResultCodeEnum.NOT_IMPLEMENTED);
    }

    @ApiOperation(value="手机验证码登录")
    @PostMapping("/loginByPhone")
    public Result loginByPhone(@RequestBody PhoneMessage phoneMessage) {
        // 先判断邮箱是否存在用户表中
        User user = null;
        try {
            user = userService.getUserByPhone(phoneMessage.getPhone());
        } catch (UserFriendException e) {
            return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST).setMsg("该手机号未绑定任何账户！");
        }
        // 验证验证码
        try {
            verifier.codeCheck(phoneMessage.getPhone(), phoneMessage.getCode());
        } catch (UserFriendException e) {
            if(e.getCode().equals("901")) return Result.failure(ResultCodeEnum.VERIFICATION_CODE_EXPIRED).setMsg(e.getMsg());
            else if(e.getCode().equals("912")) return Result.failure(ResultCodeEnum.VERIFICATION_CODE_ERROR).setMsg(e.getMsg());
            else {
                String token = JWTUtil.sign(user.getUserName(), user.getPassWord(),user.getUserId());
                user.setPassWord(null);
                return Result.success().setData(user).setToken(token).setMsg("登录成功");
            }
        }
        return Result.failure(ResultCodeEnum.NOT_IMPLEMENTED);
    }

    @ApiOperation(value="郵箱更新密码", notes = "需要邮箱验证")
    @PostMapping("/updatePassword")
    public Result updatePasswordByEmail(@RequestBody ForgotPasswordDto forgotPasswordDto) {
        if(forgotPasswordDto.getPassword() == null) {
            return Result.failure(ResultCodeEnum.PARAM_ERROR).setMsg("密码不能为空！");
        }
        try {
            verifier.codeCheck(forgotPasswordDto.getEmail(), forgotPasswordDto.getCode());
        } catch (UserFriendException e) {
            if(e.getCode().equals("901")) return Result.failure(ResultCodeEnum.VERIFICATION_CODE_EXPIRED).setMsg(e.getMsg());
            else if(e.getCode().equals("912")) return Result.failure(ResultCodeEnum.VERIFICATION_CODE_ERROR).setMsg(e.getMsg());
            else {
                // 更新密码
                User user = userService.getUserByEmail(forgotPasswordDto.getEmail());
                if(user == null) return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg("用户不存在！");
                try {
                    user.setPassWord(iPasswordEncoder.encode(forgotPasswordDto.getPassword()));
                    userService.updatePasswordByEmail(forgotPasswordDto.getEmail(), user.getPassWord());
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    noSuchAlgorithmException.printStackTrace();
                    return Result.failure(ResultCodeEnum.NOT_IMPLEMENTED).setMsg("密码修改失败");
                }
                return  Result.success().setMsg("密码更新成功！");
            }
        }
        return Result.failure(ResultCodeEnum.NOT_IMPLEMENTED);
    }


    @GetMapping("/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result unauthorized() {
        return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST);
    }
}
