package org.xmgdtc.api.enums;

import lombok.Getter;

@Getter
public enum CloudAuthExceptionEnum implements ICommonError {

    //auth code失效了
    ERR_AUTH_CODE_FAIL(1001, "ERR_AUTH_CODE_FAIL"),
    //用户不存在
    ERR_USER_NOT_EXISTS(1002, "ERR_USER_NOT_EXISTS"),
    //密码错误
    ERR_PASSWORD_NOT_CORRECT(1003, "ERR_PASSWORD_NOT_CORRECT"),
    //token 过期
    ERR_TOKEN_EXPIRATION(1004, "ERR_TOKEN_EXPIRATION"),
    //token 过期
    ERR_REFRESH_TOKEN_EXPIRATION(1005, "ERR_REFRESH_TOKEN_EXPIRATION"),

    //item不存在
    ERR_ITEM_NOT_EXISTS(2001, "ERR_ITEM_NOT_EXISTS"),

    NO_ERR(0, "ok");

    CloudAuthExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public Integer getErrCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getErrMsg() {
        return this.message;
    }
}
