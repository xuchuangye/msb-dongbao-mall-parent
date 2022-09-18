package com.msb.dongbao.portal.web.controller;

import com.msb.dongbao.common.base.entity.VerificationVO;
import com.msb.dongbao.common.util.utils.SliderUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xcy
 * @date 2022/9/18 - 16:25
 */
@RestController
@RequestMapping("/slider")
public class SliderController {

	@GetMapping("/generator")
	public VerificationVO generatorCode(HttpServletRequest request, HttpServletResponse response) {
		return SliderUtils.cut();
	}
	@GetMapping("/verify")
	public String verify(String verifyCode, HttpServletRequest request) {
		/*Boolean aBoolean = kaptcha.validate(verifyCode, 10);
		if (aBoolean) {
			return "通过";
		}*/
		return "不通过";
	}
}
