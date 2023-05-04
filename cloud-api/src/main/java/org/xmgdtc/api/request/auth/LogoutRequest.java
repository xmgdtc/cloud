package org.xmgdtc.api.request.auth;

import lombok.Getter;
import lombok.Setter;
import org.xmgdtc.api.request.payload.BasePayload;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LogoutRequest extends BasePayload {

    @NotNull
    private String token;
}
