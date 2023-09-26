package com.moon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.moon.utils.BaseController;

@Controller
public class HomeController extends BaseController {
	@Value("${moon.uums.info}")
	private String uums_info;
	@Value("${moon.uums.logout}")
	private String uums_logout;

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("uums_info", uums_info);
		model.addAttribute("uums_logout", uums_logout);
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

}