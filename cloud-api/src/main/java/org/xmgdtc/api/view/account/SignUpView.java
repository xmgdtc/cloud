package org.xmgdtc.api.view.account;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.xmgdtc.api.view.BaseView;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 用户注册view
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Valid
public class SignUpView extends BaseView {
    /**
     * 登录名
     */
    @NotBlank
    private String uid;

    /**
     * 姓名
     */
    @NotBlank
    private String name;

    /**
     * 密码
     */
    @NotBlank
    private String password;

}
