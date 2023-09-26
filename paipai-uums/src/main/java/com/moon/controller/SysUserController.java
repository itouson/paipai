package com.moon.controller;

import com.moon.entity.SysUser;
import com.moon.utils.JwtUtils;
import com.moon.utils.VerifyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import com.moon.utils.BaseController;
import com.moon.service.SysUserService;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/moon/SysUser")
public class SysUserController extends BaseController {
    @Autowired
    private SysUserService sysuserService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //用户注册
    @RequestMapping("/reg")
    public String reg(String account, String password, String repass) {
        if (!password.equals(repass)) {
            return "两次输入密码不一致";
        }
        synchronized (account.intern()) {
            int r = sysuserService.insertIfNotExist(account, password, repass);
            return r > 0 ? "注册成功" : "注册失败/登录名已存在";
        }
    }

    //用户登录
    @RequestMapping("/login")
    public String login(String account, String password, HttpServletResponse resp) {
        String npassword = DigestUtils.md5DigestAsHex(password.getBytes());
        SysUser user = sysuserService.selectByAcctAndPwd(account, npassword);
        if (user == null) return "redirect:/login";

        //生成token,加入redis,redis时间是jwt时间的2倍
        user.setPassword("");
        String token = JwtUtils.getToken(user, 20 * 60 * 1000);
        ValueOperations ops = stringRedisTemplate.opsForValue();
        ops.set(token, token, 40 * 60 * 1000, TimeUnit.MILLISECONDS);
        super.setCookieVal(resp, "token", token);
        return "redirect:/";
    }

    //用户登出
    @RequestMapping("/logout")
    public String logout(HttpServletResponse resp) {
        super.setCookieVal(resp, "token", "");
        return "redirect:/login";
    }
}