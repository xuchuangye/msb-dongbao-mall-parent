package com.msb.dongbao.ums.api.service;

import com.msb.dongbao.ums.api.entity.dto.UmsMemberRegisterParamDTO;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author xcy
 * @since 2022-09-10
 */
public interface UmsMemberService {
	public String registerUser(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO);

	public boolean selectUser(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO);
}
