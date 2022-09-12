package com.msb.dongbao.ums.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.msb.dongbao.ums.api.entity.UmsMember;
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
public interface UmsMemberService {

	public String register();
}
