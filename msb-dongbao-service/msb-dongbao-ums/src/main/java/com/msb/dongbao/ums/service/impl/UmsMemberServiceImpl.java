package com.msb.dongbao.ums.service.impl;

import com.msb.dongbao.common.base.enums.StateCodeEnum;
import com.msb.dongbao.common.base.response.ResponseResult;
import com.msb.dongbao.common.util.utils.JwtUtils;
import com.msb.dongbao.ums.api.entity.UmsMember;
import com.msb.dongbao.ums.api.entity.dto.UmsMemberLoginParamDTO;
import com.msb.dongbao.ums.api.entity.dto.UmsMemberRegisterParamDTO;
import com.msb.dongbao.ums.api.service.UmsMemberService;
import com.msb.dongbao.ums.mapper.UmsMemberMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author xcy
 * @since 2022-09-10
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

	@Autowired
	private UmsMemberMapper umsMemberMapper;

	/**
	 * 使用 BCrypt 强散列函数的 PasswordEncoder 的实现加密功能
	 */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public ResponseResult registerUser(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO) {
		boolean result = selectUser(umsMemberRegisterParamDTO);
		if (result) {
			//用户已存在
			return ResponseResult.fail(StateCodeEnum.UMSMEMBER_ALREADY_EXISTS.getCode(),
					StateCodeEnum.UMSMEMBER_ALREADY_EXISTS.getMessage());
		}
		//获取密码值
		String password = umsMemberRegisterParamDTO.getPassword();
		//计算得到加密值
		String encryptedValue = bCryptPasswordEncoder.encode(password);
		umsMemberRegisterParamDTO.setPassword(encryptedValue);

		UmsMember umsMember = new UmsMember();
		BeanUtils.copyProperties(umsMemberRegisterParamDTO, umsMember);
		umsMemberMapper.insert(umsMember);
		return ResponseResult.success(umsMember);
	}

	@Override
	public boolean selectUser(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO) {
		String username = umsMemberRegisterParamDTO.getUsername();
		Map<String, Object> map = new HashMap<>();
		map.put("username", username);
		List<UmsMember> umsMembers = umsMemberMapper.selectByMap(map);
		//如果用户不为空，表示用户已经存在，否则用户不存在
		return !umsMembers.isEmpty();
	}

	@Override
	public ResponseResult verify(String token) {
		String tokenResult = JwtUtils.parseToken(token);
		//延时token的两种方式
		//方式一：根据当前的token生成新的token
		//String newToken = JwtUtils.createToken(tokenResult);
		//方式二：将当前的token存储到Redis，校验token时再从Redis中获取

		return ResponseResult.success(tokenResult);
	}

	@Override
	public ResponseResult loginUser(UmsMemberLoginParamDTO umsMemberLoginParamDTO) {
		String username = umsMemberLoginParamDTO.getUsername();
		Map<String, Object> map = new HashMap<>();
		map.put("username", username);
		List<UmsMember> umsMembers = umsMemberMapper.selectByMap(map);

		//用户已存在
		/*if (!umsMembers.isEmpty() && bCryptPasswordEncoder.matches(umsMemberLoginParamDTO.getPassword(), umsMembers.get(0).getPassword())) {
			return ResponseResult.fail(StateCodeEnum.UMSMEMBER_ALREADY_EXISTS.getCode(),
					StateCodeEnum.UMSMEMBER_ALREADY_EXISTS.getMessage());
		}*/
		String token = null;
		//查询数据库中是否存在用户信息
		if (!umsMembers.isEmpty()) {
			//用户名不能重复，所以一个用户名对应一个用户信息
			UmsMember umsMember = umsMembers.get(0);
			if (umsMember != null) {
				//获取数据库中用户密码信息
				String passwordDB = umsMember.getPassword();
				//校验密码是否正确
				if (!bCryptPasswordEncoder.matches(umsMemberLoginParamDTO.getPassword(), passwordDB)) {
					//用户密码不正确
					return ResponseResult.fail(StateCodeEnum.UMSMEMBER_PASSWORD_ERROR.getCode(),
							StateCodeEnum.UMSMEMBER_PASSWORD_ERROR.getMessage());
				}else {
					token = JwtUtils.createToken(umsMember.getUsername());
					LocalDateTime now = LocalDateTime.now();
					umsMember.setLoginTime(now);
					return ResponseResult.success(token, umsMember);
				}
				//生成token
			}
		} else {
			//用户不存在
			return ResponseResult.fail(StateCodeEnum.UMSMEMBER_NO_EXISTS.getCode(),
					StateCodeEnum.UMSMEMBER_NO_EXISTS.getMessage());
		}

		return ResponseResult.success();
	}

	@Override
	public ResponseResult updateUser(UmsMember umsMember) {
		Long id = umsMember.getId();
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		List<UmsMember> umsMembers = umsMemberMapper.selectByMap(map);

		if (umsMembers.isEmpty()) {
			//用户不存在
			return ResponseResult.fail(StateCodeEnum.UMSMEMBER_NO_EXISTS.getCode(),
					StateCodeEnum.UMSMEMBER_NO_EXISTS.getMessage());
		}else {
			//用户id不能重复，所以一个用户id对应一个用户信息
			UmsMember umsMemberDB = umsMembers.get(0);
			umsMemberMapper.updateById(umsMemberDB);
			return ResponseResult.success(umsMemberDB);
		}
	}
}
