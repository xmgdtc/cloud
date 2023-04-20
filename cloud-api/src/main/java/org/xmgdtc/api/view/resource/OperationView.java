package org.xmgdtc.api.view.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.xmgdtc.api.view.BaseView;


@EqualsAndHashCode(callSuper = true)
@Data
public class OperationView extends BaseView {

    private String path;

    private String desc;

}
