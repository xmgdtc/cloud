package org.xmgdtc.api.view;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class OrderView implements Serializable {


    private String by;

    private String direction;
}
