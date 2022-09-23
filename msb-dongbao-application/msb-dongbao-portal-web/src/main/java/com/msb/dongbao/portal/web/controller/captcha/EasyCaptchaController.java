package com.msb.dongbao.portal.web.controller.captcha;

import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author xcy
 * @date 2022/9/17 - 9:09
 */
@RestController
@RequestMapping("/easy-captcha")
public class EasyCaptchaController {
	private final String uuid = UUID.randomUUID().toString();

	@GetMapping("/generator")
	public void generatorBase64(HttpServletRequest request, HttpServletResponse response) {

		try {
			//基础的验证码
			//CaptchaUtil.out(request, response);

			//算术的验证码
			/*ArithmeticCaptcha arithmeticCaptcha = new ArithmeticCaptcha(200, 100);
			//设置算术运算的个数
			arithmeticCaptcha.setLen(3);
			CaptchaUtil.out(arithmeticCaptcha, request, response);*/

			//中文的验证码
			ChineseCaptcha chineseCaptcha = new ChineseCaptcha(200, 50);
			CaptchaUtil.out(chineseCaptcha, request, response);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@GetMapping("/verify")
	public String verify(String verifyCode, HttpServletRequest request) {
		//ignoreCase：忽略大小写
		boolean result = CaptchaUtil.ver(verifyCode, request);
		if (result) {
			CaptchaUtil.clear(request);
			return "验证码校验通过";
		} else {
			return "验证码校验不通过";
		}

	}

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@GetMapping("/generator-redis")
	public void generatorBase64Redis(HttpServletRequest request, HttpServletResponse response) {

		try {
			SpecCaptcha specCaptcha = new SpecCaptcha(100, 50);
			//获取验证码的内容
			String text = specCaptcha.text();
			//将验证码的内容存储到Redis
			//String sessionId = request.getSession().getId();
			//stringRedisTemplate.opsForValue().set(sessionId, uuid);

			/*String base64 = specCaptcha.toBase64();
			Map<String, String> map = new HashMap<>();
			map.put("uuid", uuid);
			map.put("base64", base64);*/
			stringRedisTemplate.opsForValue().set(uuid, text);

			CaptchaUtil.out(specCaptcha, request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@GetMapping("/verify-redis")
	public String verifyRedis(String verifyCode, HttpServletRequest request) {
		//String sessionId = request.getSession().getId();
		String key = stringRedisTemplate.opsForValue().get(uuid);


		//ignoreCase：忽略大小写
		if (verifyCode.equals(key)) {
			CaptchaUtil.clear(request);
			return "验证码校验通过";
		} else {
			return "验证码校验不通过";
		}

	}
}
