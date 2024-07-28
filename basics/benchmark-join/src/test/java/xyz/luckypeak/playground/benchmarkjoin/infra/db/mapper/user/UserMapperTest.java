package xyz.luckypeak.playground.benchmarkjoin.infra.db.mapper.user;

import static org.junit.jupiter.api.Assertions.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.luckypeak.playground.benchmarkjoin.domain.user.User;

@SpringBootTest
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testListUsers() {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.in("id", 1, 2);
		List<User> users = userMapper.selectList(wrapper);
    System.out.println(users);
	}

}
