package org.xmgdtc.redis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.xmgdtc.api.enums.CloudExceptionEnum;
import org.xmgdtc.api.excetpion.CloudBizException;
import org.xmgdtc.redis.dto.NonceDTO;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class NonceSVC {

    @Autowired
    private RedisTemplate<String, NonceDTO> nonceRedisTemplate;

    public void save(String id, NonceDTO nonce, Integer timeout) {
        nonceRedisTemplate.opsForValue().set(id, nonce, timeout, TimeUnit.SECONDS);
    }


    public NonceDTO get(String id) {
        try {
            NonceDTO nonce = Optional.ofNullable(nonceRedisTemplate.opsForValue().get(id)).orElseThrow(() -> new CloudBizException(CloudExceptionEnum.ERR_AUTH_CODE_FAIL));
            return nonce;
        } catch (QueryTimeoutException e) {
            throw new CloudBizException(CloudExceptionEnum.ERR_AUTH_CODE_FAIL);
        }
    }

}
