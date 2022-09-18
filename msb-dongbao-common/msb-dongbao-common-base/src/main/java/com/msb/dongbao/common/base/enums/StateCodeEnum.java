package com.msb.dongbao.common.base.enums;

import lombok.Getter;

/**
 * 状态码枚举类
 * <p>
 * 状态码的标准：
 * 举例：
 * 100 ~ 199：用户业务
 * 200 ~ 299：支付业务
 *
 * @author xcy
 * @date 2022/9/13 - 16:54
 */
@Getter
public enum StateCodeEnum {

	/**
	 * 请求成功
	 */
	SUCCESS(200, "请求成功"),

	/**
	 * 请求失败
	 */
	FAIL(500, "请求失败"),

	/**
	 * token解析异常
	 */
	TOKEN_EXCEPTION(501, "token解析异常"),

	/**
	 * 算术异常
	 */
	ARITHMETIC_EXCEPTION(301, "算术异常"),

	/**
	 * token为空
	 */
	TOKEN_EMPTY(502, "token为空"),

	/**
	 * 未找到kaptcha异常
	 */
	KAPTCHA_NOTFOUND_EXCEPTION(601, "未找到kaptcha异常"),
	/**
	 * kaptcha超时异常
	 */
	KAPTCHA_TIMEOUT_EXCEPTION(602, "kaptcha超时异常"),
	/**
	 * kaptcha不正确异常
	 */
	KAPTCHA_INCORRECT_EXCEPTION(603, "kaptcha不正确异常"),
	/**
	 * kaptcha渲染异常
	 */
	KAPTCHA_RENDER_EXCEPTION(604, "kaptcha渲染异常"),

	/**
	 * 用户不存在
	 */
	UMSMEMBER_NO_EXISTS(1099, "用户不存在"),

	/**
	 * 用户已存在
	 */
	UMSMEMBER_ALREADY_EXISTS(1199, "用户已存在"),

	/**
	 * 用户密码错误
	 */
	UMSMEMBER_PASSWORD_ERROR(1299, "用户密码错误");


	private final int code;

	private final String message;

	StateCodeEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
