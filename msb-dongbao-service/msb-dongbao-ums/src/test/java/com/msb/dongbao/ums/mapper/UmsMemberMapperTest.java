package com.msb.dongbao.ums.mapper;

import com.msb.dongbao.ums.api.entity.UmsMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xcy
 * @date 2022/9/10 - 11:41
 */
@SpringBootTest
public class UmsMemberMapperTest {

	@Autowired
	private UmsMemberMapper umsMemberMapper;

	@Test
	public void testInsert() {
		UmsMember umsMember = new UmsMember();
		umsMember.setEmail("2234223312@qq.com");
		umsMember.setIcon("icon");
		umsMember.setNote("note");
		umsMember.setNickName("nick");
		umsMember.setStatus(0);
		umsMemberMapper.insert(umsMember);
	}

	@Test
	public void testUpdate() {
		UmsMember umsMember = new UmsMember();
		umsMember.setId(61L);
		umsMember.setUsername("root");
		umsMember.setPassword("root");
		umsMemberMapper.updateById(umsMember);
	}
}
