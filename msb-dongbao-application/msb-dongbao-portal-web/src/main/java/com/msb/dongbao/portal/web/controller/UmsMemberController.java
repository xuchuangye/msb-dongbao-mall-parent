package com.msb.dongbao.portal.web.controller;

import com.msb.dongbao.ums.api.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xcy
 * @date 2022/9/12 - 9:21
 */
@RestController
@RequestMapping("/user-member")
public class UmsMemberController {

	@Autowired
	private UmsMemberService umsMemberService;

	@GetMapping("/register")
	public String register() {
		umsMemberService.register();
		return "register";
	}
}
