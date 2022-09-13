package com.msb.dongbao.ums.api.entity.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author xcy
 * @date 2022/9/13 - 9:15
 */
@Data
@ToString
public class UmsMemberRegisterParamDTO {

	private String username;
	private String password;
	private String icon;
	private String email;
	private String nickName;
}
