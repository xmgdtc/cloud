package org.xmgdtc.api.request.payload;

import lombok.Getter;
import lombok.Setter;
import org.xmgdtc.api.view.BaseView;

import javax.validation.Valid;

@Getter
@Setter
public class ItemPayload<T extends BaseView> extends BasePayload {

    @Valid
    private T item;

}
