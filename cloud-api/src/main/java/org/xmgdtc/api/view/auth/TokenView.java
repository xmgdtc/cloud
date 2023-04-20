package org.xmgdtc.api.view.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.xmgdtc.api.view.BaseView;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenView extends BaseView {

    private String token;

    private String refreshToken;

    /**
     * 失效时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date tokenExpiryDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date refreshTokenExpiryDate;
}
