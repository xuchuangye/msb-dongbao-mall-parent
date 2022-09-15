package com.msb.dongbao.common.util.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author xcy
 * @date 2022/9/14 - 10:55
 */
public class JwtUtils {

	private static final String PREFIX = "afaewerwe";

	/**
	 * 生成token
	 * @param subject
	 * @return
	 */
	public static String createToken(String subject) {
		return Jwts.builder()
				.setSubject(subject)
				//设置过期时间
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60))
				//使用指定的算法和指定的密钥对构造的JWT进行签名，生成JWS
				.signWith(SignatureAlgorithm.HS256, PREFIX)
				//返回一个紧凑的 URL 安全 JWT 字符串
				.compact();
	}

	/**
	 * 解析token
	 * @param token
	 * @return
	 */
	public static String parseToken(String token) {
		return Jwts.parser()
				.setSigningKey(PREFIX)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	/*public static void main(String[] args) {
		String token = JwtUtils.createToken("教育");
		System.out.println("生成token：" + token);

		System.out.println("解析token：" + JwtUtils.parseToken(token));
	}*/
}
