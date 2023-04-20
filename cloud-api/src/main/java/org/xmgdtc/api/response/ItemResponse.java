package org.xmgdtc.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.xmgdtc.api.view.BaseView;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse<T extends BaseView> extends BaseResponse {

    private T item;

}
