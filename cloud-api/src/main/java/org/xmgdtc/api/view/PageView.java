package org.xmgdtc.api.view;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
public class PageView implements Serializable {

    /**
     * 第几页
     */
    private @Min(0L) Integer number;

    /**
     * 每页多少个
     */
    private @Min(1L) Integer size;

    /**
     * 总数
     */
    private Long total;

    /**
     * 总页数
     */
    private Integer totalPages;

    /**
     * 排序字段
     */
    private List<OrderView> orders;
}
