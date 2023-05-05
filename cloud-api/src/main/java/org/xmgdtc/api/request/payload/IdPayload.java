package org.xmgdtc.api.request.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class IdPayload extends BasePayload {

    @NotBlank
    private String id;

}
