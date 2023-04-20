package org.xmgdtc.redis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.xmgdtc.api.enums.CloudExceptionEnum;
import org.xmgdtc.api.excetpion.CloudAuthException;
import org.xmgdtc.api.excetpion.CloudBizException;
import org.xmgdtc.redis.dto.RefreshTokenDTO;
import org.xmgdtc.redis.dto.TokenInfoDTO;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TokenSVC {

    @Autowired
    private RedisTemplate<String, TokenInfoDTO> tokenRedisTemplate;

    @Autowired
    private RedisTemplate<String, RefreshTokenDTO> refreshTokenRedisTemplate;

    /**
     * 保存token
     *
     * @param token
     * @param userInfo
     * @param timeout
     */
    public void saveToken(String token, TokenInfoDTO userInfo, Integer timeout) {
        tokenRedisTemplate.opsForValue().set(token, userInfo, timeout, TimeUnit.SECONDS);
    }

    /**
     * 删除token
     *
     * @param token
     * @param token
     */
    public void deleteToken(String token) {
        tokenRedisTemplate.delete(token);
    }


    /**
     * 保存refreshToken
     *
     * @param token
     * @param refreshToken
     * @param timeout
     */
    public void saveRefreshToken(String token, RefreshTokenDTO refreshToken, Integer timeout) {
        refreshTokenRedisTemplate.opsForValue().set(token, refreshToken, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取token信息
     *
     * @param token
     * @return
     */
    public TokenInfoDTO getTokenInfo(String token) {
        try {
            TokenInfoDTO tokenInfo = Optional.ofNullable(tokenRedisTemplate.opsForValue().get(token)).orElseThrow(() -> new CloudAuthException(CloudExceptionEnum.ERR_TOKEN_EXPIRATION));
            return tokenInfo;
        } catch (QueryTimeoutException e) {
            throw new CloudBizException(CloudExceptionEnum.ERR_TOKEN_EXPIRATION);
        }
    }

    /**
     * 刷新token
     *
     * @param refreshToken
     * @param seconds
     * @return
     */
    public void refresh(String refreshToken, Integer seconds) {
        try {
            RefreshTokenDTO refresh = Optional.ofNullable(refreshTokenRedisTemplate.opsForValue().get(refreshToken)).orElseThrow(() -> new CloudAuthException(CloudExceptionEnum.ERR_TOKEN_EXPIRATION));
            refreshTokenRedisTemplate.opsForValue().getAndExpire(refresh.getToken(), Duration.ofSeconds(seconds));
        } catch (QueryTimeoutException e) {
            throw new CloudBizException(CloudExceptionEnum.ERR_TOKEN_EXPIRATION);
        }

    }

}
