package xyz.luckypeak.playground.benchmarkjoin.infra.db.mapper.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.*;
import xyz.luckypeak.playground.benchmarkjoin.domain.user.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

	@Select(
			"select * from t_user where ${ew.sqlSegment}"
	)
	@Results({
			@Result(column = "id", property = "id"),
			@Result(column = "nickname", property = "nickName"),
			@Result(column = "id", property = "roles", many = @Many(
					select = "xyz.luckypeak.playground.benchmarkjoin.infra.db.mapper.user.RoleMapper.getListByUserId"
			))
	})
	List<User> selectList(@Param("ew") QueryWrapper<User> wrapper);

}
