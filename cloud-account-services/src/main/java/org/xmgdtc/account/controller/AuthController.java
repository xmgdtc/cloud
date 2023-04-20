package org.xmgdtc.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmgdtc.account.services.AuthSVC;
import org.xmgdtc.api.request.RestfulRequest;
import org.xmgdtc.api.request.auth.LoginRequest;
import org.xmgdtc.api.request.auth.LogoutRequest;
import org.xmgdtc.api.response.ItemResponse;
import org.xmgdtc.api.response.RestfulResponse;
import org.xmgdtc.api.view.auth.InitView;
import org.xmgdtc.api.view.auth.LoginView;
import org.xmgdtc.common.controller.BaseController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    @Autowired
    private AuthSVC authSVC;

    @RequestMapping("/init")
    public RestfulResponse<ItemResponse<InitView>> init() {
        InitView initView = authSVC.init();
        return new RestfulResponse<>(new ItemResponse<>(initView));
    }

    @RequestMapping("/login")
    public RestfulResponse<ItemResponse<LoginView>> login(@RequestBody @NotNull @Valid RestfulRequest<LoginRequest> request) {
        LoginView userInfo = authSVC.login(request.getPayload().getNonceId(), request.getPayload().getUsername(), request.getPayload().getPassword());
        return new RestfulResponse<>(new ItemResponse<>(userInfo));
    }

    @RequestMapping("/logout")
    public RestfulResponse logout(@RequestBody @NotNull @Valid RestfulRequest<LogoutRequest> request) {
        authSVC.logout(request.getPayload().getToken());
        return new RestfulResponse<>();
    }
}
