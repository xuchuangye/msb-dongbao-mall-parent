package com.msb.dongbao.portal.web.controller;

import com.msb.dongbao.common.util.code.ImageCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

/**
 * @author xcy
 * @date 2022/9/15 - 16:43
 */
@RestController
@RequestMapping("/code")
public class VerifyCodeController {

	private static final String attrName = "verifyCode";

	@GetMapping("/generator")
	public void generator(HttpServletRequest request, HttpServletResponse response) {
		try {
			ImageCode instance = ImageCode.getInstance();

			//验证码的值
			String code = instance.getCode();
			//验证码图片
			ByteArrayInputStream image = instance.getImage();

			request.getSession().setAttribute(attrName, code);

			response.setContentType("image/jpeg");

			//直接写到浏览器
			byte[] bytes = new byte[1024];
			try (ServletOutputStream outputStream = response.getOutputStream()) {
				while (image.read(bytes) != -1) {
					outputStream.write(bytes);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			System.out.println("异常");
		}
	}

	@GetMapping("/generator-base64")
	public String generatorBase64(HttpServletRequest request) {
		try {
			ImageCode instance = ImageCode.getInstance();
			//验证码的值
			String code = instance.getCode();
			//验证码图片
			ByteArrayInputStream image = instance.getImage();

			//图片中的验证码存储到session中
			request.getSession().setAttribute(attrName, code);
			//将图片转成base64，所以该方法的返回值是String
			//将ByteArrayInputStream image转成字节数组
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buff = new byte[1024];
			int read = 0;
			while ((read = image.read(buff, 0, 1024)) > 0) {
				outputStream.write(buff, 0, read);
			}

			byte[] bytes = outputStream.toByteArray();
			outputStream.close();
			return Base64.getEncoder().encodeToString(bytes);

		} catch (Exception e) {
			System.out.println("异常");
			return "";
		}
	}

	@GetMapping("/verify")
	public String verify(String verifyCode, HttpServletRequest request) {
		String attribute = request.getSession().getAttribute(attrName).toString();

		if (verifyCode.equals(attribute)) {
			return "验证码校验通过";
		} else {
			return "验证码校验不通过";
		}
	}
}
