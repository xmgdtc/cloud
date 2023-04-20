package org.xmgdtc.api.view.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.xmgdtc.api.view.BaseView;
import org.xmgdtc.api.view.IdBaseView;

import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InitView extends IdBaseView {

    private String nonce;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expiry;


    public InitView(String id, String nonce, Date expiry) {
        this.id = id;
        this.nonce = nonce;
        this.expiry = expiry;
    }
}
