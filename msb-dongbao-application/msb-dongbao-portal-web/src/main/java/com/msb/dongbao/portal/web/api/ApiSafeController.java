package com.msb.dongbao.portal.web.api;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.msb.dongbao.common.base.entity.SignDTO;
import com.msb.dongbao.common.base.enums.StateCodeEnum;
import com.msb.dongbao.common.base.response.ResponseResult;
import com.msb.dongbao.common.util.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author xcy
 * @date 2022/9/22 - 10:30
 */
@RestController
@RequestMapping("/api-safe")
public class ApiSafeController {

	@GetMapping("/test")
	public String test() {
		Map<String, Object> map = new HashMap<>();

		return "test api-safe";
	}

	@GetMapping("/verify-sign")
	public ResponseResult verifySign(/*String appId, String name, Long timestamp,*/ String sign, HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		/*map.put("addId", appId);
		map.put("name", name);
		map.put("timestamp", timestamp);*/

		/*
		//为了测试，所以固定参数
		long current = System.currentTimeMillis() - timestamp;

		if (current > 1000 * 20) {
			return ResponseResult.builder()
					.code(StateCodeEnum.FAIL.getCode())
					.message("请求已过期")
					.build();
		}*/

		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			//获取参数名称
			String parameterName = parameterNames.nextElement();
			//获取参数值
			String parameter = request.getParameter(parameterName);
			map.put(parameterName, parameter);
		}
		String s = SignUtils.generatorSign(map);
		//f1f6e217c9974f1f01111c48a0df8cf1
		if (s.equals(sign)) {
			return ResponseResult.builder()
					.code(StateCodeEnum.SUCCESS.getCode())
					.message("校验通过")
					.data(map)
					.build();
		} else {
			return ResponseResult.builder()
					.code(StateCodeEnum.FAIL.getCode())
					.message("校验不通过")
					.data(map)
					.build();
		}
	}

	@PostMapping("/post-test")
	public ResponseResult postTest(@RequestBody SignDTO signDTO) {
		//对Map进行排序
		//生成sign
		//校验sign
		if (SignUtils.checkSign(signDTO)) {
			return ResponseResult.builder()
					.code(StateCodeEnum.SUCCESS.getCode())
					.message("校验通过")
					.build();
		}else {
			return ResponseResult.builder()
					.code(StateCodeEnum.FAIL.getCode())
					.message("校验不通过")
					.build();
		}
	}

}
