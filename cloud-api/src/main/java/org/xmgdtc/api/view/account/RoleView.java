package org.xmgdtc.api.view.account;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.xmgdtc.api.view.BaseView;


@EqualsAndHashCode(callSuper = true)
@Data
public class RoleView extends BaseView {

    private String name;

}
