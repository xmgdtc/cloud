package org.xmgdtc.redis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfoDTO implements Serializable {

    private String uid;

    private String uname;

    private List<String> roleUrls;
}
