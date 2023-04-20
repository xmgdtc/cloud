package org.xmgdtc.api.request;

import lombok.Getter;
import lombok.Setter;
import org.xmgdtc.api.view.BaseView;

import javax.validation.Valid;

@Getter
@Setter
public class ItemRequest<T extends BaseView> extends BaseRequest {

    @Valid
    private T item;

}
