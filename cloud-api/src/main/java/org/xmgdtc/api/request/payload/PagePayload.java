package org.xmgdtc.api.request.payload;

import lombok.Getter;
import lombok.Setter;
import org.xmgdtc.api.params.BaseParams;

import javax.validation.Valid;

@Getter
@Setter
public class PagePayload<T extends BaseParams> extends BasePayload {

    @Valid
    private T params;

    @Valid
    private T pageView;

}
