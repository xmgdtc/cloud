package org.xmgdtc.api.excetpion;

import lombok.Getter;
import lombok.Setter;
import org.xmgdtc.api.enums.ICommonError;

@Getter
@Setter
public class CloudBaseException extends RuntimeException {

    private Integer errCode;
    private String errMsg;
    private String message;


    public CloudBaseException(ICommonError commonError) {
        super(commonError.getErrCode() + "-" + commonError.getErrMsg());
        errCode = commonError.getErrCode();
        errMsg = commonError.getErrMsg();

    }

    public CloudBaseException(ICommonError commonError, String detailMessage) {
        super(commonError.getErrCode() + "-" + commonError.getErrMsg() + "-" + detailMessage);
        errCode = commonError.getErrCode();
        errMsg = commonError.getErrMsg();
        message = detailMessage;
    }

}
