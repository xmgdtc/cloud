package org.xmgdtc.common.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.joda.time.DateTime;
import org.springframework.util.CollectionUtils;

import java.time.Clock;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JWTUtil {

    public static String generateToken(String issuer, String secKey, Map<String, String> payload) {
        return generateToken(-1, issuer, secKey, payload);
    }


    public static String generateToken(Date expiresAt, String issuer, String secKey, Map<String, String> payload) {
        Algorithm algorithm = Algorithm.HMAC256(secKey);
        JWTCreator.Builder builder = JWT.create().withIssuer(issuer).withJWTId(UUID.randomUUID().toString());
        if (expiresAt != null) {
            builder.withExpiresAt(expiresAt);
        }
        if (!CollectionUtils.isEmpty(payload)) {
            payload.forEach(builder::withClaim);
        }
        return builder.sign(algorithm);
    }

    public static String generateToken(Integer validSeconds, String issuer, String secKey, Map<String, String> payload) {
        Date end = null;
        if (validSeconds != -1) {
            end = new DateTime().plusSeconds(validSeconds).toDate();
        }
        return generateToken(end, issuer, secKey, payload);
    }

    public static boolean verifyToken(String token, String issuer, String secKey, boolean validateExpiredTime) {
        Algorithm algorithm = Algorithm.HMAC256(secKey);
        JWTVerifier.BaseVerification verification = (JWTVerifier.BaseVerification) JWT.require(algorithm).withIssuer(new String[]{issuer});

        JWTVerifier verifier = validateExpiredTime ? verification.build(Clock.system(ZoneId.systemDefault())) : verification.build();

        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException var9) {
            return false;
        }
    }

    public static String getPayload(String token, String key) {
        DecodedJWT jwt = JWT.decode(token);
        Map<String, Claim> claims = jwt.getClaims();
        Claim claim = claims.get(key);
        return claim.asString();
    }


}