package com.moon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.moon.utils.BaseController;

@Controller
public class HomeController extends BaseController {

	@RequestMapping("/")
	public String home() {
		return "myproj/home";
	}

	@RequestMapping("/openWork/{work}")
	public String openWork(@PathVariable("work") String work) {
		return "myproj/" + work;
	}

	@RequestMapping({"/success","/info"})
	public String success() {
		return "myproj/success";
	}

	@RequestMapping("/login")
	public String login(){
		return "myproj/login";
	}

	@RequestMapping("/reg")
	public String reg() {
		return "myproj/reg";
	}

	@RequestMapping("/userinfo")
	public String info() {
		return "myproj/info";
	}
}