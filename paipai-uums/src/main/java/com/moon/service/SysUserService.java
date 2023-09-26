package com.moon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.moon.entity.SysUser;
import com.moon.dao.SysUserMapper;
import org.springframework.util.DigestUtils;

@Transactional
@Service
public class SysUserService {
    @Autowired
    private SysUserMapper sysuserMapper;

    public SysUserMapper getSysUserMapper() {
        return sysuserMapper;
    }

    public int insertIfNotExist(String account, String password, String repass) {
        //判重
        SysUser crt = new SysUser();
        crt.setAccount(account);
        if (sysuserMapper.selectCount(crt) > 0) return 0;

        //新增
        SysUser user = new SysUser();
        user.setAccount(account);
        String npassword = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(npassword);

        sysuserMapper.insert(user);
        return 1;
    }

    public SysUser selectByAcctAndPwd(String account, String password) {
        SysUser crt = new SysUser();
        crt.setAccount(account);
        crt.setPassword(password);
        return sysuserMapper.selectOne(crt);
    }
}