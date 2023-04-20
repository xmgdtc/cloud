package org.xmgdtc.api.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

@Getter
@Setter
public class RestfulRequest<T extends BaseRequest> {

    @Valid
    private T payload;
}
