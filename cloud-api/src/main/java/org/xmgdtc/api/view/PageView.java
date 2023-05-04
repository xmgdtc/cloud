package org.xmgdtc.api.view;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.io.Serializable;


@Getter
@Setter
public class PageView implements Serializable {

    private @Min(0L) Integer number;

    private @Min(1L) Integer size;

    private Long total;

    private Integer totalPages;

}
