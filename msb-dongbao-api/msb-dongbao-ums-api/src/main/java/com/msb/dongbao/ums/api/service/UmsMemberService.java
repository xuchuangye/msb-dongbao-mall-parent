package com.msb.dongbao.ums.api.service;

import com.msb.dongbao.common.base.response.ResponseResult;
import com.msb.dongbao.ums.api.entity.dto.UmsMemberLoginParamDTO;
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
	public ResponseResult registerUser(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO);

	public ResponseResult loginUser(UmsMemberLoginParamDTO umsMemberLoginParamDTO);

	public boolean selectUser(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO);

}
