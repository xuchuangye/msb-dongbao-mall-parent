package com.msb.dongbao.ums.api.entity.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author xcy
 * @date 2022/9/13 - 15:03
 */
@Data
@ToString
public class UmsMemberLoginParamDTO {

	private String username;
	private String password;
	private LocalDateTime loginTime;
}
