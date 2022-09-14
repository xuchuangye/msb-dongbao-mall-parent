package com.msb.dongbao.ums.api.entity.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author xcy
 * @date 2022/9/13 - 9:15
 */
@Data
@ToString
public class UmsMemberRegisterParamDTO {

	/**
	 * 用户名
	 */
	@NotEmpty(message = "用户名不能为空")
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 头像
	 */
	private String icon;
	/**
	 * 邮箱
	 */
	@Email
	private String email;
	/**
	 * 昵称
	 */
	private String nickName;
}
