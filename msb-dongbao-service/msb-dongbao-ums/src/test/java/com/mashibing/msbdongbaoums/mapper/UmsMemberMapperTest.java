package com.mashibing.msbdongbaoums.mapper;

import com.mashibing.msbdongbaoums.entity.UmsMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * @author xcy
 * @date 2022/9/10 - 11:41
 */
@SpringBootTest
public class UmsMemberMapperTest {

	@Autowired
	private UmsMemberMapper umsMemberMapper;
	@Test
	public void test() {
		UmsMember umsMember = new UmsMember();
		umsMember.setEmail("2234223312@qq.com");
		umsMember.setIcon("icon");
		umsMember.setNote("note");
		umsMember.setNickName("nick");
		umsMember.setStatus(0);
		LocalDateTime now = LocalDateTime.now();
		umsMember.setCreateTime(now);
		umsMember.setUpdateTime(now);
		umsMember.setLoginTime(now);
		umsMemberMapper.insert(umsMember);
	}
}
