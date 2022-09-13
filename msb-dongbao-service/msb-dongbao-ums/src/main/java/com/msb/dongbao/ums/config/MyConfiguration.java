package com.msb.dongbao.ums.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

/**
 * 配置类
 * @author xcy
 * @date 2022/9/12 - 8:29
 */
@Configuration
public class MyConfiguration {

	@Bean
	public MetaObjectHandler metaObjectHandler() {
		return new MetaObjectHandler() {
			@Override
			public void insertFill(MetaObject metaObject) {
				System.out.println("添加插入时间");
				this.setFieldValByName("gmtCreate", LocalDateTime.now(), metaObject);
				this.setFieldValByName("gmtModified", LocalDateTime.now(), metaObject);
			}

			@Override
			public void updateFill(MetaObject metaObject) {
				System.out.println("添加更新时间");
				this.setFieldValByName("gmtModified", LocalDateTime.now(), metaObject);
			}
		};
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
