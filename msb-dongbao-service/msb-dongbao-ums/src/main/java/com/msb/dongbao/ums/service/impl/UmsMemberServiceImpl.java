package com.msb.dongbao.ums.service.impl;

import com.msb.dongbao.ums.api.entity.UmsMember;
import com.msb.dongbao.ums.api.entity.dto.UmsMemberLoginParamDTO;
import com.msb.dongbao.ums.api.entity.dto.UmsMemberRegisterParamDTO;
import com.msb.dongbao.ums.api.service.UmsMemberService;
import com.msb.dongbao.ums.mapper.UmsMemberMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
	public String registerUser(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO) {
		//获取密码值
		String password = umsMemberRegisterParamDTO.getPassword();
		//计算得到加密值
		String encryptedValue = bCryptPasswordEncoder.encode(password);
		umsMemberRegisterParamDTO.setPassword(encryptedValue);

		UmsMember umsMember = new UmsMember();
		BeanUtils.copyProperties(umsMemberRegisterParamDTO, umsMember);
		umsMemberMapper.insert(umsMember);
		return "success";
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
	public String loginUser(UmsMemberLoginParamDTO umsMemberLoginParamDTO) {
		String username = umsMemberLoginParamDTO.getUsername();
		Map<String, Object> map = new HashMap<>();
		map.put("username", username);
		List<UmsMember> umsMembers = umsMemberMapper.selectByMap(map);
		if (!umsMembers.isEmpty() && bCryptPasswordEncoder.matches(umsMemberLoginParamDTO.getPassword(), umsMembers.get(0).getPassword())) {
			return "用户已存在";
		}
		//查询数据库中是否存在用户信息
		if (!umsMembers.isEmpty()) {
			//用户名不能重复，所以一个用户名对应一个用户信息
			UmsMember umsMember = umsMembers.get(0);
			if (umsMember != null) {
				//获取数据库中用户密码信息
				String passwordDB = umsMember.getPassword();
				//校验密码是否正确
				if (!bCryptPasswordEncoder.matches(umsMemberLoginParamDTO.getPassword(), passwordDB)) {
					return "用户密码不正确";
				}
			}
		}else {
			return "用户不存在";
		}
		return "token";
	}
}
