package com.msb.dongbao.ums.controller;


import com.msb.dongbao.ums.api.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author xcy
 * @since 2022-09-10
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

