package com.msb.dongbao.portal.web.interceptor;

import com.msb.dongbao.common.base.annotation.TokenCheck;
import com.msb.dongbao.common.base.exception.TokenException;
import com.msb.dongbao.common.util.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author xcy
 * @date 2022/9/15 - 9:14
 */
public class TokenCheckInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();

		//如果方法上标注@TokenCheck注解，进行拦截
		//isAnnotationPresent(annotationClass) 等价于 getAnnotation(annotationClass) != null
		if (method.isAnnotationPresent(TokenCheck.class)) {
			TokenCheck annotation = method.getAnnotation(TokenCheck.class);
			//annotation.required() == true表示校验token
			if (annotation.required()) {
				String token = request.getHeader("token");
				if (StringUtils.isBlank(token)) {
					//return false;
					throw new TokenException("token为空");
				}


				try {
					JwtUtils.parseToken(token);
					return true;
				} catch (Exception e) {
					//return false;
					throw new TokenException("token解析异常");
				}
			}
		}
		//如果方法上没有标注@TokenCheck注解，直接放行
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
