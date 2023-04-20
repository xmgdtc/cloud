package org.xmgdtc.api.request.auth;

import lombok.Getter;
import lombok.Setter;
import org.xmgdtc.api.request.BaseRequest;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LogoutRequest extends BaseRequest {

    @NotNull
    private String token;
}
