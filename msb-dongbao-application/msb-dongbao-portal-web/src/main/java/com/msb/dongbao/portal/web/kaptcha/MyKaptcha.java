package com.msb.dongbao.portal.web.kaptcha;

import com.baomidou.kaptcha.GoogleKaptcha;
import com.baomidou.kaptcha.Kaptcha;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaRenderException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.msb.dongbao.common.base.response.ResponseResult;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author xcy
 * @date 2022/9/17 - 17:29
 */
@Component
public class MyKaptcha implements Kaptcha {
	private static final Logger log = LoggerFactory.getLogger(GoogleKaptcha.class);
	private DefaultKaptcha kaptcha;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public MyKaptcha(DefaultKaptcha kaptcha) {
		this.kaptcha = kaptcha;
	}

	public String render() {
		this.response.setDateHeader("Expires", 0L);
		this.response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		this.response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		this.response.setHeader("Pragma", "no-cache");
		this.response.setContentType("image/jpeg");
		String sessionCode = this.kaptcha.createText();
		stringRedisTemplate.opsForValue().set("key", sessionCode);

		try {
			ServletOutputStream out = this.response.getOutputStream();
			Throwable var3 = null;

			String var4;
			try {
				this.request.getSession().setAttribute("KAPTCHA_SESSION_KEY", sessionCode);
				this.request.getSession().setAttribute("KAPTCHA_SESSION_DATE", System.currentTimeMillis());
				ImageIO.write(this.kaptcha.createImage(sessionCode), "jpg", out);
				var4 = sessionCode;
			} catch (Throwable var14) {
				var3 = var14;
				throw var14;
			} finally {
				if (out != null) {
					if (var3 != null) {
						try {
							out.close();
						} catch (Throwable var13) {
							var3.addSuppressed(var13);
						}
					} else {
						out.close();
					}
				}

			}

			return var4;
		} catch (IOException var16) {
			throw new KaptchaRenderException(var16);
		}
	}

	public boolean validate(String code) {
		return this.validate(code, 900L);
	}

	public boolean validate(@NonNull String code, long second) {
		HttpSession httpSession = this.request.getSession(false);
		String sessionCode = stringRedisTemplate.opsForValue().get("key");
		if (code.equals(sessionCode)) {
			stringRedisTemplate.delete("key");
			return true;
		} else {
			return false;
		}
	}
}
