package org.xmgdtc.api.view.account;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.xmgdtc.api.view.BaseView;
import org.xmgdtc.api.view.IdBaseView;


@EqualsAndHashCode(callSuper = true)
@Data
public class AccountView extends IdBaseView {

    private String uid;

    private String name;


}
