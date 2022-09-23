package com.msb.dongbao.portal.web.controller.captcha;

import com.baomidou.kaptcha.GoogleKaptcha;
import com.baomidou.kaptcha.Kaptcha;
import com.msb.dongbao.common.base.response.ResponseResult;
import com.msb.dongbao.portal.web.kaptcha.MyKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author xcy
 * @date 2022/9/17 - 15:25
 */
@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

	@Autowired
	private Kaptcha kaptcha;

	@GetMapping("/generator")
	public void generator() {
		kaptcha.render();

	}

	@PostMapping("/verify")
	public ResponseResult verify(@RequestParam String code) {
		//default timeout 900 seconds，可以自己设置时间，具有灵活性
		//对程序的扩展性有好处
		boolean result = kaptcha.validate(code, 10);
		if (result) {
			return ResponseResult.builder().code(1).message("验证码校验通过").build();
		}else {
			return ResponseResult.builder().code(0).message("验证码校验不通过").build();
		}
	}

	@PostMapping("/validTime")
	public void validCustomTime(@RequestParam String code) {
		kaptcha.validate(code, 60);
	}

	@Autowired
	private MyKaptcha myKaptcha;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@GetMapping("/generator-redis")
	public void generatorRedis() {
		myKaptcha.render();
	}

	@PostMapping("/verify-redis")
	public ResponseResult verifyRedis(@RequestParam String code) {
		//default timeout 900 seconds，可以自己设置时间，具有灵活性
		//对程序的扩展性有好处
		boolean result = myKaptcha.validate(code, 10);
		if (result) {
			return ResponseResult.builder().code(1).message("验证码校验通过").build();
		}else {
			return ResponseResult.builder().code(0).message("验证码校验不通过").build();
		}
	}
}
