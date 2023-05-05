package org.xmgdtc.api.view;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class OrderView implements Serializable {


    /**
     * 排序属性
     */
    private String by;

    /**
     * 排序 ASC DESC
     */
    private String direction;
}
