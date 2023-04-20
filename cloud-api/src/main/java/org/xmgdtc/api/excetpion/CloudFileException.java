package org.xmgdtc.api.excetpion;

import lombok.Getter;
import lombok.Setter;
import org.xmgdtc.api.enums.ICommonError;

@Getter
@Setter
public class CloudFileException extends CloudBaseException {


    public CloudFileException(ICommonError commonError) {
        super(commonError);
    }

    public CloudFileException(ICommonError commonError, String detailMessage) {
        super(commonError, detailMessage);
    }

}
