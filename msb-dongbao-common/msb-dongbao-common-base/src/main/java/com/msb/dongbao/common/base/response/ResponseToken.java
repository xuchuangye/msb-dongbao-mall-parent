package com.msb.dongbao.common.base.response;

import lombok.Data;

/**
 * @author xcy
 * @date 2022/9/15 - 11:57
 */
@Data
public class ResponseToken {
	private String accessToken;
	private String refreshToken;
}
