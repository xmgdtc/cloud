package org.xmgdtc.api.request;

import lombok.Getter;
import lombok.Setter;
import org.xmgdtc.api.request.payload.BasePayload;

import javax.validation.Valid;

@Getter
@Setter
public class RestfulRequest<T extends BasePayload> {

    @Valid
    private T payload;
}
