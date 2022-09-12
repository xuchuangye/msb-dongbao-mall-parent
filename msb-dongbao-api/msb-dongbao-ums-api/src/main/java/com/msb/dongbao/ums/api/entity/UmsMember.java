package com.msb.dongbao.ums.api.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 后台用户表
 * </p>
 *
 * @author xcy
 * @since 2022-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsMember implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	private String username;

	private String password;

	/**
	 * 头像
	 */
	private String icon;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 备注信息
	 */
	private String note;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime gmtCreate;

	/**
	 * 修改时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime gmtModified;

	/**
	 * 最后登录时间
	 */
	private LocalDateTime loginTime;

	/**
	 * 帐号启用状态：0->禁用；1->启用
	 */
	private Integer status;


}
