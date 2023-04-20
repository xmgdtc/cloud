package org.xmgdtc.common.utils;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.xmgdtc.api.constant.AccountConstants;
import org.xmgdtc.common.helper.IVerifyRoleHelper;

import javax.servlet.http.HttpServletRequest;

public class ServletUtil {

    public static String getApi(HttpServletRequest request, String contextPath) {
        return request.getRequestURI().replaceFirst(contextPath, "");
    }

    public static String getToken(HttpServletRequest request, String tokenPrefix) {
        String token = request.getHeader("Authorization");
        return !StringUtils.isEmpty(token) ? token.replaceFirst(tokenPrefix, "").trim() : null;
    }

    public static String getAcctFromToken(HttpServletRequest request, String tokenPrefix) {
        String token = getToken(request, tokenPrefix);
        if (!StringUtils.isEmpty(token)) {
            return JWTUtil.getPayload(token, AccountConstants.AUTH_ACCOUNT);

        } else {
            return null;
        }
    }

    /**
     * 验证权限
     *
     * @param request
     * @param contextPath
     * @param tokenPrefix
     * @param verifyRoleSvc 权限验证服务 还没想好是放在token里还是
     * @return
     */
    public static Boolean verifyRole(HttpServletRequest request, String contextPath, String tokenPrefix, IVerifyRoleHelper verifyRoleSvc) {
        String api = getApi(request, contextPath);
        String acct = getAcctFromToken(request, tokenPrefix);
        if (ObjectUtils.isEmpty(api) || ObjectUtils.isEmpty(acct)) {
            return false;
        }
        return verifyRoleSvc.verify(acct, api);
    }
}
