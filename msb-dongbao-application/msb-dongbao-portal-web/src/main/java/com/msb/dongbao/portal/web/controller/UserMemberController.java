package com.msb.dongbao.portal.web.controller;

import com.msb.dongbao.ums.api.entity.dto.UmsMemberLoginParamDTO;
import com.msb.dongbao.ums.api.entity.dto.UmsMemberRegisterParamDTO;
import com.msb.dongbao.ums.api.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xcy
 * @date 2022/9/12 - 9:21
 */
@RestController
@RequestMapping("/user-member")
public class UserMemberController {

	@Autowired
	private UmsMemberService umsMemberService;

	@PostMapping("/register")
	public String registerUser(@RequestBody UmsMemberRegisterParamDTO umsMemberRegisterParamDTO) {
		boolean result = umsMemberService.selectUser(umsMemberRegisterParamDTO);
		if (result) {
			return "用户已存在";
		}
		umsMemberService.registerUser(umsMemberRegisterParamDTO);
		return "register";
	}

	@PostMapping("/login")
	public String loginUser(@RequestBody UmsMemberLoginParamDTO umsMemberLoginParamDTO) {
		return umsMemberService.loginUser(umsMemberLoginParamDTO);
	}
}
