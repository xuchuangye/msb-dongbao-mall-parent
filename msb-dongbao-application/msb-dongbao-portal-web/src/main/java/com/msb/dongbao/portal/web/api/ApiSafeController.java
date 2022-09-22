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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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
		Map<String, Object> map = new HashMap<>();
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

		//f1f6e217c9974f1f01111c48a0df8cf1
		String signDB = SignUtils.generatorSign(map);
		if (signDB.equals(sign)) {
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
		JSONObject object = JSONUtil.parseObj(signDTO);
		//将请求参数的请求体中的实体类转换成Map
		Map<String, Object> map = Convert.toMap(String.class, Object.class, object);
		//对Map进行排序
		Map<String, Object> sortedMap = SignUtils.returnSortedMap(map);
		//生成sign
		String signServer = SignUtils.generatorSign(sortedMap);
		//校验sign
		String signClient = (String) sortedMap.get("sign");
		if (signServer.equals(signServer)) {
			return ResponseResult.builder()
					.code(StateCodeEnum.SUCCESS.getCode())
					.message("校验通过")
					.data(map)
					.build();
		}else {
			return ResponseResult.builder()
					.code(StateCodeEnum.FAIL.getCode())
					.message("校验不通过")
					.data(map)
					.build();
		}
	}
}
