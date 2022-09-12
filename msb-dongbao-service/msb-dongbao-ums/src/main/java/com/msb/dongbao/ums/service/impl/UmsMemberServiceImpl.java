package com.msb.dongbao.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msb.dongbao.ums.api.entity.UmsMember;
import com.msb.dongbao.ums.api.service.UmsMemberService;
import com.msb.dongbao.ums.mapper.UmsMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public String register() {
		UmsMember umsMember = new UmsMember();
		umsMember.setNickName("c");
		umsMemberMapper.insert(umsMember);
		return "success";
	}
}
