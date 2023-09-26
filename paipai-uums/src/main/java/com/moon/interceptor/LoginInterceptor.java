package com.moon.interceptor;

import com.moon.entity.SysUser;
import com.moon.utils.BaseController;
import com.moon.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class LoginInterceptor extends BaseController implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException {
        //从Cookie中获取token
        String token = super.getCookieVal(req, "token");

        //redis层面判断token是否过期
        ValueOperations ops = stringRedisTemplate.opsForValue();
        Object val = ops.get(token);
        if (val == null) {
            req.getRequestDispatcher("/login").forward(req, resp);
            return false;
        }

        //redis层面未过期，jwt层面过期，则续期
        SysUser sysUserProb = JwtUtils.getObject(val.toString(), SysUser.class);
        if (sysUserProb == null) {
            SysUser sysUser = JwtUtils.getObjectWithoutValidate(val.toString(), SysUser.class);
            String ntoken = JwtUtils.getToken(sysUser, 20 * 60 * 1000);
            ops.set(token, ntoken, 40 * 60 * 1000, TimeUnit.MILLISECONDS);
        }

        return true;
    }


}
