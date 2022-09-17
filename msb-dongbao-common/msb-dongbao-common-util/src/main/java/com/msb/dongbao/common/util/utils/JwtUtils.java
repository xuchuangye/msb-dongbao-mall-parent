package com.msb.dongbao.common.util.utils;

import com.msb.dongbao.common.base.exception.TokenException;
import com.msb.dongbao.common.base.response.ResponseToken;
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
	 * 生成access_token
	 *
	 * @param subject
	 * @return
	 */
	public static String createAccessToken(String subject) {
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
	 * 生成refresh_token
	 *
	 * @param subject
	 * @return
	 */
	public static String createRefreshToken(String subject) {
		return Jwts.builder()
				.setSubject(subject)
				//设置过期时间
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
				//使用指定的算法和指定的密钥对构造的JWT进行签名，生成JWS
				.signWith(SignatureAlgorithm.HS256, PREFIX)
				//返回一个紧凑的 URL 安全 JWT 字符串
				.compact();
	}


	/**
	 * 解析token
	 *
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

	/**
	 * 校验token，判断token是否异常
	 *
	 * @param token
	 * @return
	 */
	/*public static ResponseToken checkToken(String token) throws TokenException {
		ResponseToken responseToken = null;
		try {
			//a.对传入的请求头信息进行解析，解析出token
			parseToken(token);
			String accessToken = createAccessToken(token);
			String refreshToken = createRefreshToken(token);
			responseToken = new ResponseToken();
			responseToken.setAccessToken(accessToken);
			responseToken.setRefreshToken(refreshToken);
			return responseToken;
		}
		//其余异常
		catch (Exception e) {
			throw new TokenException("token解析异常");
		}
	}*/

	/*public static void main(String[] args) {
		String token = JwtUtils.createToken("教育");
		System.out.println("生成token：" + token);

		System.out.println("解析token：" + JwtUtils.parseToken(token));
	}*/
}
