package com.msb.dongbao.portal.web.filter;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.msb.dongbao.common.base.enums.StateCodeEnum;
import com.msb.dongbao.common.base.response.ResponseResult;
import com.msb.dongbao.common.util.utils.SignUtils;
import com.msb.dongbao.portal.web.utls.HttpParameterUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

/**
 * @author xcy
 * @date 2022/9/22 - 19:51
 */
@Slf4j
public class SignFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("初始化");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		//签名的校验
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		request = new BodyReaderHttpServletRequestWrapper(request);
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		//1.获取参数
		//HttpParameterUtils.getUrlParams(request);
		//HttpParameterUtils.getBodyParams(request);

		SortedMap<String, String> allParams = HttpParameterUtils.getAllParams(request);

		//2.签名校验
		boolean checkSign = SignUtils.checkSign(allParams);
		log.info("校验结果：" + checkSign);
		if (checkSign) {
			//3.过滤器放行
			filterChain.doFilter(request, response);

		}else {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json.charset=utf-8");

			PrintWriter writer = response.getWriter();

			ResponseResult<Object> build = ResponseResult.builder()
					.code(StateCodeEnum.FAIL.getCode())
					.message("签名不正确")
					.build();

			String jsonString = JSONObject.toJSONString(build);
			writer.append(jsonString);
		}
		//System.out.println("过滤器生效了");
	}

	@Override
	public void destroy() {
		System.out.println("销毁");
	}
}
