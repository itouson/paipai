package com.moon.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class CurrUserUtils extends BaseController {
    //从请求头或cookie获取当前用户
    public <T> T selectCurrUser(HttpServletRequest req, String tokenName, Class<T> userClass) {
        String headerToken = req.getHeader("token");
        String cookieToken = super.getCookieVal(req, "token");
        String token = headerToken == null ? cookieToken : headerToken;
        T sysUserJwt = JwtUtils.getObjectWithoutValidate(token, userClass);
        return sysUserJwt;
    }

    //从请求头或cookie获取当前用户
    public <T> T selectCurrUser(String tokenName, Class<T> userClass) {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = attrs.getRequest();
        String headerToken = req.getHeader("token");
        String cookieToken = super.getCookieVal(req, "token");
        String token = headerToken == null ? cookieToken : headerToken;
        T sysUserJwt = JwtUtils.getObjectWithoutValidate(token, userClass);
        return sysUserJwt;
    }

    //
}
