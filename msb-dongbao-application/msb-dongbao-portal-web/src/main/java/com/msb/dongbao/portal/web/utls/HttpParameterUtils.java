package com.msb.dongbao.portal.web.utls;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author xcy
 * @date 2022/9/22 - 20:20
 */
@Slf4j
public class HttpParameterUtils {


	public static SortedMap<String, String> getAllParams(HttpServletRequest request) {
		//获取URL参数
		Map<String, String> urlParams = getUrlParams(request);
		//获取Body参数
		Map<String, String> bodyParams = getBodyParams(request);

		SortedMap<String, String> allParams = new TreeMap<>();

		if (urlParams != null) {
			for (Map.Entry<String, String> entry : urlParams.entrySet()) {
				allParams.put(entry.getKey(), entry.getValue());
			}
		}

		if (bodyParams != null) {
			for (Map.Entry<String, String> entry : bodyParams.entrySet()) {
				allParams.put(entry.getKey(), entry.getValue());
			}
		}
		log.info("所有的参数：" + allParams);
		return allParams;
	}

	/**
	 * 获取POST请求方式的方法的Body上的参数
	 *
	 * @param request
	 * @return
	 */
	private static Map<String, String> getBodyParams(HttpServletRequest request) {
		StringBuilder stringBuilder = null;
		Map map = null;
		try {
			//@RequestBody是通过流传递参数
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));

			//将流读取到缓存区
			String buff = "";
			stringBuilder = new StringBuilder();
			while ((buff = bufferedReader.readLine()) != null) {
				stringBuilder.append(buff);
			}

			//转换成Map
			if (!StringUtils.isBlank(stringBuilder)) {
				map = JSONObject.parseObject(stringBuilder.toString(), Map.class);
			}
			log.info("body的参数：" + map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 获取GET请求方式的URL上的参数
	 *
	 * @param request
	 * @return
	 */
	private static Map<String, String> getUrlParams(HttpServletRequest request) {
		String params = "";
		try {
			String queryString = request.getQueryString();
			if (!StringUtils.isBlank(queryString)) {
				params = URLDecoder.decode(queryString, "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<>();
		String[] splits = params.split("&");
		for (String split : splits) {
			if (!"".equals(split)) {
				int index = split.indexOf("=");
				map.put(split.substring(0, index), split.substring(index + 1));
			}
		}

		log.info("url的参数：" + map);
		return map;
	}
}
