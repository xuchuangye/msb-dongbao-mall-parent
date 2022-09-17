package com.msb.dongbao.portal.web.controller;

import com.msb.dongbao.common.util.code.ImageCode;
import com.msb.dongbao.common.util.utils.JCaptchaUtils;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author xcy
 * @date 2022/9/16 - 17:43
 */
@RestController
@RequestMapping("/jcaptcha")
public class JCaptchaController {
	private static final String attrName = "verifyCode";

	@GetMapping("/generator")
	public void generatorBase64(HttpServletRequest request, HttpServletResponse response) {
		//sessionId与验证码一一对应
		String id = request.getSession().getId();
		BufferedImage BufferedImage = JCaptchaUtils.getService().getImageChallengeForID(id);
		//将BufferedImage创建成图片类型的形式
		//已经准备好了背景、字、字体，但是并没有画图片
		//使用JCaptchaUtils的框架将图片渲染出来，所以需要创建图片编解码的工具
		ServletOutputStream servletOutputStream = null;
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			//JPEG图像编码器
			JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(outputStream);

			jpegEncoder.encode(BufferedImage);
			response.setHeader("Cache-Control", "no-store");
			response.setContentType("image/jpeg");

			byte[] bytes = outputStream.toByteArray();
			servletOutputStream = response.getOutputStream();
			servletOutputStream.write(bytes);
			servletOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (servletOutputStream != null) {
					servletOutputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@GetMapping("/verify")
	public String verify(String verifyCode, HttpServletRequest request) {
		String id = request.getSession().getId();
		Boolean aBoolean = JCaptchaUtils.getService().validateResponseForID(id, verifyCode);
		if (aBoolean) {
			return "验证码校验通过";
		} else {
			return "验证码校验不通过";
		}

	}
}
