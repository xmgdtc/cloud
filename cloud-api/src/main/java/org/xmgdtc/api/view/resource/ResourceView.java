package org.xmgdtc.api.view.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.xmgdtc.api.view.BaseView;

import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceView extends BaseView {

    private String path;

    private String name;

    private String systemId;

    private Set<OperationView> operations;

}
