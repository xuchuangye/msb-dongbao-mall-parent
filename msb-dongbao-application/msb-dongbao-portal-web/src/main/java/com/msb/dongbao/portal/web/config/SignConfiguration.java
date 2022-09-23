package com.msb.dongbao.portal.web.config;

import com.msb.dongbao.portal.web.filter.SignFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 签名的配置类
 * @author xcy
 * @date 2022/9/22 - 19:50
 */
@Configuration
public class SignConfiguration implements WebMvcConfigurer {

	@Bean
	public SignFilter signFilter() {
		return new SignFilter();
	}
}
