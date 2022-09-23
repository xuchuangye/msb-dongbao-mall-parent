package com.msb.dongbao.portal.web.controller.captcha;

import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xcy
 * @date 2022/9/17 - 9:09
 */
@RestController
@RequestMapping("/happy-captcha")
public class HappyCaptchaController {

	@GetMapping("/generator")
	public void generatorBase64(HttpServletRequest request, HttpServletResponse response) {

		HappyCaptcha.require(request, response)
				.style(CaptchaStyle.ANIM)
				.type(CaptchaType.ARITHMETIC_ZH)
				.build().finish();

	}

	@GetMapping("/verify")
	public String verify(String verifyCode, HttpServletRequest request) {
		//ignoreCase：忽略大小写
		boolean result = HappyCaptcha.verification(request, verifyCode, true);
		if (result) {
			HappyCaptcha.remove(request);
			return "验证码校验通过";
		} else {
			return "验证码校验不通过";
		}

	}
}
