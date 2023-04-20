package org.xmgdtc.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class KeyPairDTO {

    private String publicKey;
    private String privateKey;

}
