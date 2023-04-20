package org.xmgdtc.api.view.auth;

import lombok.*;
import org.xmgdtc.api.view.BaseView;
import org.xmgdtc.api.view.account.AccountView;
import org.xmgdtc.api.view.account.RoleView;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginView extends BaseView {

    /**
     * token
     */
    private TokenView token;

    /**
     * 用户信息
     */
    private AccountView account;
    /**
     * 权限信息
     */
    private List<RoleView> roles;


}
