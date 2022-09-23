package com.msb.dongbao.portal.web.filter;

import com.msb.dongbao.common.base.enums.StateCodeEnum;
import com.msb.dongbao.common.base.response.ResponseResult;
import com.msb.dongbao.common.util.utils.SignUtils;
import com.msb.dongbao.portal.web.utls.HttpParameterUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xcy
 * @date 2022/9/22 - 19:51
 */

public class SignFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("初始化");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		//签名的校验
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		//1.获取参数
		//HttpParameterUtils.getUrlParams(request);
		//HttpParameterUtils.getBodyParams(request);

		HttpParameterUtils.getAllParams(request);
		//2.签名校验

		//3.过滤器放行
		filterChain.doFilter(request, response);
		//System.out.println("过滤器生效了");
	}

	@Override
	public void destroy() {
		System.out.println("销毁");
	}
}
