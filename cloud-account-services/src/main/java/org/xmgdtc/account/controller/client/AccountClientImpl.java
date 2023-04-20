package org.xmgdtc.account.controller.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmgdtc.api.view.account.AccountView;
import org.xmgdtc.cloud.client.login.AccountClient;

@RestController
@RequestMapping("/account")
public class AccountClientImpl implements AccountClient {
    @Override
    @GetMapping("/get")
    public AccountView getAcct() {
        System.out.println("call controller");
        AccountView acct = new AccountView();

        return acct;
    }
}
