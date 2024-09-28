package xyz.luckypeak.playground.benchmarkjoin.infra.db.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.luckypeak.playground.benchmarkjoin.domain.user.Role;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

	@Select(
			"select * from t_role left join t_user_role on t_role.id=t_user_role.role_id where t_user_role.user_id=#{userId}"
	)
	List<Role> getListByUserId(Long userId);

}
