package com.msb.dongbao.common.base.entity;

import lombok.Data;

/**
 * 生成签名的请求参数实体类
 * @author xcy
 * @date 2022/9/22 - 17:16
 */
@Data
public class SignDTO {

	private int appId;

	private String name;

	private String sign;
}
