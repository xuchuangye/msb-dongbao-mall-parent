package com.msb.dongbao.ums.api.service;

import com.msb.dongbao.common.base.response.ResponseResult;
import com.msb.dongbao.common.base.response.ResponseToken;
import com.msb.dongbao.ums.api.entity.UmsMember;
import com.msb.dongbao.ums.api.entity.dto.UmsMemberLoginParamDTO;
import com.msb.dongbao.ums.api.entity.dto.UmsMemberRegisterParamDTO;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author xcy
 * @since 2022-09-10
 */
public interface UmsMemberService {
	public ResponseResult getCaptcha(String phone);

	public ResponseResult registerUser(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO);

	public ResponseResult loginUser(UmsMemberLoginParamDTO umsMemberLoginParamDTO);

	public ResponseResult updateUser(UmsMember umsMember);

	public boolean selectUser(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO);

	public ResponseResult verify(String token);

	public ResponseToken generator(HttpServletResponse response);
}
