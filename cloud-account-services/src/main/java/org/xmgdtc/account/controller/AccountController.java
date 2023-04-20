package org.xmgdtc.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xmgdtc.account.services.AccountSVC;
import org.xmgdtc.api.request.ItemRequest;
import org.xmgdtc.api.request.RestfulRequest;
import org.xmgdtc.api.response.ItemResponse;
import org.xmgdtc.api.response.RestfulResponse;
import org.xmgdtc.api.view.account.AccountView;
import org.xmgdtc.api.view.account.SignUpView;
import org.xmgdtc.common.controller.BaseController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {

    @Autowired
    private AccountSVC accountSVC;

    @RequestMapping(value = "/signUp", method = {RequestMethod.GET, RequestMethod.POST})
    public RestfulResponse<ItemResponse<AccountView>> signUp(@RequestBody @NotNull @Valid RestfulRequest<ItemRequest<SignUpView>> request) {
        AccountView accountView = accountSVC.signUp(request.getPayload().getItem());
        return new RestfulResponse<>(new ItemResponse<>(accountView));
    }

    @GetMapping("/create")
    public AccountView createAcct(@RequestBody @NotNull @NotNull AccountView accountView) {
        accountView = accountSVC.save(accountView);
        return accountView;
    }

    @GetMapping("/update")
    public AccountView updateAcct(@RequestBody @NotNull @NotNull AccountView accountView) {
        accountView = accountSVC.update(accountView);
        return accountView;
    }

    @GetMapping("/findAll")
    public List<AccountView> findAll() {
        System.out.println(super.httpServletRequest);
        List<AccountView> list = accountSVC.findAll();
        return list;
    }
}
