package org.xmgdtc.account.services;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xmgdtc.api.constant.AccountConstants;
import org.xmgdtc.api.dto.KeyPairDTO;
import org.xmgdtc.api.view.account.AccountView;
import org.xmgdtc.api.view.account.RoleView;
import org.xmgdtc.api.view.auth.InitView;
import org.xmgdtc.api.view.auth.LoginView;
import org.xmgdtc.api.view.auth.TokenView;
import org.xmgdtc.common.service.BaseSVC;
import org.xmgdtc.common.utils.JWTUtil;
import org.xmgdtc.common.utils.RSAUtil;
import org.xmgdtc.redis.dto.NonceDTO;
import org.xmgdtc.redis.dto.RefreshTokenDTO;
import org.xmgdtc.redis.dto.TokenInfoDTO;
import org.xmgdtc.redis.services.NonceSVC;
import org.xmgdtc.redis.services.TokenSVC;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class AuthSVC extends BaseSVC {

    @Value("${token.pubKey.expiry}")
    private Integer tokenPubKeyExpiry;

    @Value("${token.expiry}")
    private Integer tokenExpiry;

    @Value("${token.refresh.expiry}")
    private Integer tokenRefreshExpiry;

    @Value("${token.issuer}")
    private String issuer;

    @Value("${token.secretKey}")
    private String secKey;

    @Value("${login.enc}")
    private Boolean loginEnc;

    @Autowired
    private NonceSVC nonceSVC;


    @Autowired
    private TokenSVC tokenSVC;

    @Autowired
    private AccountSVC accountSVC;

    /**
     * 初始化生成公钥私钥
     *
     * @return
     */

    public InitView init() {
        KeyPairDTO keyPair = RSAUtil.genKeyPair(512);

        String id = UUID.randomUUID().toString().replaceAll("-", "");

        NonceDTO nonce = new NonceDTO(keyPair.getPublicKey(), keyPair.getPrivateKey());

        nonceSVC.save(id, nonce, tokenPubKeyExpiry);

        InitView view = new InitView(id, keyPair.getPublicKey(), new DateTime().plusSeconds(tokenPubKeyExpiry).toDate());
        return view;
    }


    /**
     * 登录
     */

    public LoginView login(String nonce, String usernameEnc, String passwordEnc) {

        NonceDTO nonceKey = nonceSVC.get(nonce);

        //进行认证 并返回个人信息
        AccountView account;
        //传输是否加密开关
        if (loginEnc) {
            String username = RSAUtil.publicKeyEncrypt(usernameEnc, nonceKey.getPrivateKey());
            String password = RSAUtil.publicKeyEncrypt(passwordEnc, nonceKey.getPrivateKey());
            account = accountSVC.verifyAndGet(username, password);
        } else {
            account = accountSVC.verifyAndGet(usernameEnc, passwordEnc);
        }


        TokenView token = buildTokenView(account.getUid(), account.getName());

        //todo 权限还没控制 先随便写一个
        List<RoleView> roles = new ArrayList<>();
        TokenInfoDTO tokenInfoCache = buildTokenInfo(account, roles);
        RefreshTokenDTO refreshTokenCache = buildRefreshTokenInfo(token.getToken());

        //保存两个token
        tokenSVC.saveToken(token.getToken(), tokenInfoCache, tokenExpiry);
        tokenSVC.saveRefreshToken(token.getRefreshToken(), refreshTokenCache, tokenRefreshExpiry);

        LoginView loginView = new LoginView(token, account, roles);
        return loginView;
    }


    /**
     * 登出
     *
     * @param token
     */
    public void logout(String token) {
        tokenSVC.deleteToken(token);
    }

    /**
     * payload参数
     *
     * @param username
     * @return
     */
    public Map<String, String> buildTokenPayload(String username, String realName) {
        Map<String, String> payload = new HashMap<>();
        payload.put(AccountConstants.AUTH_ACCOUNT, username);
        payload.put(AccountConstants.AUTH_REAL_NAME, realName);
        return payload;

    }

    public Map<String, String> buildRefreshTokenPayload(String token) {
        Map<String, String> payload = new HashMap<>();
        payload.put(AccountConstants.AUTH_TOKEN, token);
        return payload;

    }


    /**
     * 生成token对象
     *
     * @param uid
     * @param uname
     * @return
     */
    private TokenView buildTokenView(String uid, String uname) {
        Date tokenExpiryDate = new DateTime().plusSeconds(tokenExpiry).toDate();
        Date refreshTokenExpiryDate = new DateTime().plusSeconds(tokenRefreshExpiry).toDate();

        /*
         * 生成token
         */
        Map<String, String> payload = buildTokenPayload(uid, uname);
        String tokenStr = JWTUtil.generateToken(tokenExpiryDate, issuer, secKey, payload);

        /*
         *生成refreshToken
         */
        Map<String, String> refreshPayload = buildRefreshTokenPayload(tokenStr);
        String refreshTokenStr = JWTUtil.generateToken(refreshTokenExpiryDate, issuer, secKey, refreshPayload);

        return new TokenView(tokenStr, refreshTokenStr, tokenExpiryDate, refreshTokenExpiryDate);
    }

    /**
     * 创建token的缓存内容
     *
     * @param account
     * @param roles
     * @return
     */
    public TokenInfoDTO buildTokenInfo(AccountView account, List<RoleView> roles) {
        return new TokenInfoDTO(account.getUid(), account.getName(), List.of("/account/get"));
    }

    /**
     * 创建refreshToken的缓存内容
     *
     * @param token
     * @return
     */
    public RefreshTokenDTO buildRefreshTokenInfo(String token) {
        return new RefreshTokenDTO(token);
    }
}
