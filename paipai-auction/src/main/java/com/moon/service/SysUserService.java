package com.moon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.moon.entity.SysUser;
import com.moon.dao.SysUserMapper;

@Transactional
@Service
public class SysUserService {
	@Autowired
	private SysUserMapper sysuserMapper;

	public SysUserMapper getSysUserMapper() {
		return sysuserMapper;
	}

}