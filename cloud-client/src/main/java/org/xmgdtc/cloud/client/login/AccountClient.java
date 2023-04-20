package org.xmgdtc.cloud.client.login;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.xmgdtc.api.view.account.AccountView;

@Component
@FeignClient(name = "micro-server-account", url = "http://localhost:8081", path = "/account")
public interface AccountClient {

    @GetMapping("/get")
    AccountView getAcct();


}
