package org.xmgdtc.api.response;

import lombok.Getter;
import lombok.Setter;

import static org.xmgdtc.api.enums.CloudExceptionEnum.NO_ERR;

@Getter
@Setter
public class RestfulResponse<T extends BaseResponse> {

    public RestfulResponse() {
        this.code = NO_ERR.getCode();
        this.message = NO_ERR.getMessage();
    }

    public RestfulResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestfulResponse(Integer code, String message, String detailMessage) {
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage;
    }

    public RestfulResponse(T payload) {
        this.code = NO_ERR.getCode();
        this.message = NO_ERR.getMessage();
        this.payload = payload;
    }

    private Integer code;

    private String message;

    private String detailMessage;

    private T payload;

}
