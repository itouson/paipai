package com.moon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.moon.utils.BaseController;
import com.moon.entity.SysUser;
import com.moon.service.SysUserService;

@Controller
@RequestMapping("/moon/SysUser")
public class SysUserController extends BaseController {
	@Autowired
	private SysUserService sysuserService;

}