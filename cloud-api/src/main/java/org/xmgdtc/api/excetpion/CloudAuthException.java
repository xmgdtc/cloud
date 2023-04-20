package org.xmgdtc.api.excetpion;

import lombok.Getter;
import lombok.Setter;
import org.xmgdtc.api.enums.ICommonError;

@Getter
@Setter
public class CloudAuthException extends CloudBaseException {


    public CloudAuthException(ICommonError commonError) {
        super(commonError);
    }

    public CloudAuthException(ICommonError commonError, String detailMessage) {
        super(commonError, detailMessage);
    }

}
