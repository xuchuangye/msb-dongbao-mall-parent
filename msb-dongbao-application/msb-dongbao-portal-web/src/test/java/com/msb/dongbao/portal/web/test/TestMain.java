package com.msb.dongbao.portal.web.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xcy
 * @date 2022/9/13 - 15:42
 */
@SpringBootTest
public class TestMain {

	@Test
	public void test() {
		method(null);
	}

	public void method(String param) {
		if (param == null) {
			System.out.println("null");
		}else {
			System.out.println(param);
		}
	}
}
