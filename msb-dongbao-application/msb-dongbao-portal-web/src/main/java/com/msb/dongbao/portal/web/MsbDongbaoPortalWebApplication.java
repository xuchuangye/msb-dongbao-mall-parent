package com.msb.dongbao.portal.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xcy
 * @date 2022/9/12 - 9:20
 */
@SpringBootApplication(scanBasePackages = {"com.msb"})
@MapperScan("com.msb.dongbao.ums.mapper")
public class MsbDongbaoPortalWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsbDongbaoPortalWebApplication.class, args);
	}
}
