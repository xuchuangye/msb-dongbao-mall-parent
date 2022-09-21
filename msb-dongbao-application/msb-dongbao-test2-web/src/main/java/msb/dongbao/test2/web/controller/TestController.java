package msb.dongbao.test2.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xcy
 * @date 2022/9/21 - 18:15
 */
@RestController
public class TestController {

	@GetMapping("/test")
	public String test() {
		return "test nginx";
	}
}
