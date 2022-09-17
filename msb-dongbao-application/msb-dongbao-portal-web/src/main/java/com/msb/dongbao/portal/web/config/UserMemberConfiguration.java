package com.msb.dongbao.portal.web.config;

import com.msb.dongbao.portal.web.interceptor.TokenCheckInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置类
 * @author xcy
 * @date 2022/9/15 - 9:21
 */
@Configuration
public class UserMemberConfiguration implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//配置需要拦截器排除的请求路径
		List<String> patterns = new ArrayList<>();
		patterns.add("/user-member/register");
		patterns.add("/user-member/login");
		patterns.add("/code/**");
		patterns.add("/jcaptcha/**");

		//添加拦截器
		registry.addInterceptor(tokenCheckInterceptor())
				//拦截所有请求
				.addPathPatterns("/**")
				//排除的请求
				.excludePathPatterns(patterns);
	}

	@Bean
	public TokenCheckInterceptor tokenCheckInterceptor() {
		return new TokenCheckInterceptor();
	}
}
