package org.xmgdtc.api.excetpion;

import org.xmgdtc.api.enums.ICommonError;

public class CloudBizException extends CloudBaseException {


    public CloudBizException(ICommonError commonError) {
        super(commonError);
    }

    public CloudBizException(ICommonError commonError, String detailMessage) {
        super(commonError, detailMessage);
    }
}
