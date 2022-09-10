package com.mashibing.msbdongbaoums;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xcy
 * @date 2022/9/10 - 11:25
 */
@SpringBootApplication
@MapperScan("com.mashibing.msbdongbaoums.mapper")
public class MsbDongbaoUmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsbDongbaoUmsApplication.class, args);
	}
}
