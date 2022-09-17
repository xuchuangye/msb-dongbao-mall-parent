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


		HandlerMethod handlerMethod = null;
		if (handler instanceof HandlerMethod) {
			handlerMethod = (HandlerMethod) handler;
		} else {
			return true;
		}
		Method method = handlerMethod.getMethod();
		//如果方法上标注@TokenCheck注解，进行拦截
		//isAnnotationPresent(annotationClass) 等价于 getAnnotation(annotationClass) != null
		if (method.isAnnotationPresent(TokenCheck.class)) {
			TokenCheck annotation = method.getAnnotation(TokenCheck.class);
			//annotation.required() == true表示校验token
			if (annotation.required()) {
				String accessToken = request.getHeader("accessToken");
				//判断accessToken是否过期
				if (StringUtils.isBlank(accessToken)) {
					//如果accessToken过期，需要再判断refreshToken是否过期
					String refreshToken = request.getHeader("refreshToken");
					//如果refreshToken没有过期
					if (!StringUtils.isBlank(refreshToken)) {
						//重新生成accessToken
						String newAccessToken = JwtUtils.createAccessToken(refreshToken);
						String newRefreshToken = JwtUtils.createAccessToken(refreshToken);
						response.setHeader("accessToken", newAccessToken);
						response.setHeader("refreshToken", newRefreshToken);
						return true;
					}
					//如果refreshToken过期，需要重新进行登录
					else {
						throw new TokenException("距离上一次登录的时间过长，需要重新进行登录");
					}
				} else {
					try {
						JwtUtils.parseToken(accessToken);
						return true;
					} catch (Exception e) {
						//return false;
						throw new TokenException("token解析异常");
					}
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
