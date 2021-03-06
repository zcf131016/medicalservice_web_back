package com.example.medicalservice.util;

import io.swagger.annotations.ApiResponse;

/**
 * @author cjbi
 */
public enum ResultCodeEnum {

    OK("200", "处理成功"),
    Login("100","登陆成功"),
    Register("101","注册成功"),
    RegisterError("102","注册失败"),
    RegisterAlreadyExist("103","注册用户已存在"),
    LoginError("104","登陆失败"),
    TokenError("-2","令牌验证失败"),
    ILLEGAL_REQUEST("401","非法请求"),
    INQUIRE_FAILED("303","查询失败"),
    CREATED("201", "创建成功"),
    CREATE_FAILED("202", "创建失败"),
    UPDATE_FAILED("902", "更新失败"),
    UPDATED("901", "更新成功"),
    DELETED("204", "删除成功"),
    DELETE_FAILED("205","删除失败"),
    BAD_REQUEST("400", "请求参数有误"),
    UNAUTHORIZED("401", "未授权"),
    FORBIDDEN("403", "被禁止访问"),
    PARAMS_MISS("483", "缺少接口中必填参数"),
    PARAM_ERROR("484", "参数非法"),
    FAILED_DEL_OWN("485", "不能删除自己"),
    FAILED_USER_ALREADY_EXIST("486", "该用户已存在"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误"),
    NOT_IMPLEMENTED("501", "业务异常"),
    UPLOAD_FAILED("505", "上传失败"),
    VERIFICATION_CODE_EXPIRED("911", "验证码失效"),
    VERIFICATION_CODE_ERROR("912","验证码错误");



    private String code;
    private String msg;

    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
