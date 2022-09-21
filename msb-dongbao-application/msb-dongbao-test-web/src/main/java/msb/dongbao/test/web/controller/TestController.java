package msb.dongbao.test.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xcy
 * @date 2022/9/21 - 11:23
 */
@RestController
public class TestController {

	@GetMapping("/test")
	public String test() {
		return "test";
	}
}
