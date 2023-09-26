package com.moon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.moon.utils.BaseController;
import com.moon.entity.Type;
import com.moon.service.TypeService;

@Controller
@RequestMapping("/moon/Type")
public class TypeController extends BaseController {
	@Autowired
	private TypeService typeService;

}