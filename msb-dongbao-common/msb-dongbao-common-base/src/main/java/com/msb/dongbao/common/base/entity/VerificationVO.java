package com.msb.dongbao.common.base.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 验证码vo 对象
 *
 * @author xcy
 * @date 2022/9/18 - 15:48
 */
@Data
public class VerificationVO implements Serializable {


	/**
	 * 滑块图
	 */
	private String slidingImage;

	/**
	 * 原图
	 */
	private String originalImage;

	/**
	 * 宽
	 */
	private Integer xWidth;

	/**
	 * 高
	 */
	private Integer yHeight;

}
