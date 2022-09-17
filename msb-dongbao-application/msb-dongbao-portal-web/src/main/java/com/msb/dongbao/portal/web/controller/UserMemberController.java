package com.msb.dongbao.portal.web.controller;

import com.msb.dongbao.common.base.annotation.TokenCheck;
import com.msb.dongbao.common.base.response.ResponseResult;
import com.msb.dongbao.common.base.response.ResponseToken;
import com.msb.dongbao.ums.api.entity.UmsMember;
import com.msb.dongbao.ums.api.entity.dto.UmsMemberLoginParamDTO;
import com.msb.dongbao.ums.api.entity.dto.UmsMemberRegisterParamDTO;
import com.msb.dongbao.ums.api.service.UmsMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
	public ResponseResult registerUser(@RequestBody @Valid UmsMemberRegisterParamDTO umsMemberRegisterParamDTO) {
		//int a = 10 / 0;
		return umsMemberService.registerUser(umsMemberRegisterParamDTO);
	}

	@PostMapping("/login")
	public ResponseResult loginUser(@RequestBody UmsMemberLoginParamDTO umsMemberLoginParamDTO) {
		return umsMemberService.loginUser(umsMemberLoginParamDTO);
	}

	@PostMapping("/update")
	@TokenCheck
	public ResponseResult updateUser(@RequestBody UmsMember umsMember) {
		return umsMemberService.updateUser(umsMember);
	}

	@GetMapping("/test-verify")
	public ResponseResult verify(@RequestParam("token") String token) {
		return umsMemberService.verify(token);
	}

	@GetMapping("/generator")
	public ResponseToken generator(HttpServletResponse response) {
		return umsMemberService.generator(response);
	}
}
