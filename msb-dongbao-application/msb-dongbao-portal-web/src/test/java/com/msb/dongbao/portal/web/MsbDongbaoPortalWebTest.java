package com.msb.dongbao.portal.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author xcy
 * @date 2022/9/13 - 11:08
 */
@SpringBootTest
public class MsbDongbaoPortalWebTest {

	@Test
	public void testMd5() {
		String initialValue = "fhauiwehr231231234";
		String firstEncryptedValue = DigestUtils.md5DigestAsHex(initialValue.getBytes(StandardCharsets.UTF_8));
		System.out.println("第一次计算出的加密值：" + firstEncryptedValue);
		String secondEncryptedValue = DigestUtils.md5DigestAsHex(initialValue.getBytes(StandardCharsets.UTF_8));
		System.out.println("第二次计算出的加密值：" + secondEncryptedValue);
	}

	@Test
	public void test() {
		//初始值
		String initialValue = "fhauiwehr231231234";
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		//第一次加密值
		String firstEncryptedValue = bCryptPasswordEncoder.encode(initialValue);
		System.out.println("第一次计算出的加密值：" + firstEncryptedValue);

		boolean firstMatches = bCryptPasswordEncoder.matches(initialValue, firstEncryptedValue);
		System.out.println("第一次验证是否通过：" + firstMatches);
		//第二次加密值
		String secondEncryptedValue = bCryptPasswordEncoder.encode(initialValue);
		System.out.println("第二次计算出的加密值：" + secondEncryptedValue);

		boolean secondMatches = bCryptPasswordEncoder.matches(initialValue, secondEncryptedValue);
		System.out.println("第二次验证是否通过：" + secondMatches);
	}
}
