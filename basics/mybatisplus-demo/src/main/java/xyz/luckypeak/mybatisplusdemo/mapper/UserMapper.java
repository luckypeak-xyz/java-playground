package xyz.luckypeak.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.luckypeak.mybatisplusdemo.po.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    User queryUserById(long id);

    @Update("update user set balance = balance - #{amount} ${ew.customSqlSegment}")
    void deductBalance(@Param("amount") int amount, @Param(Constants.WRAPPER) LambdaQueryWrapper<User> wrapper);

    @Select("select u.* from user u inner join address a on u.id = a.user_id ${ew.customSqlSegment}")
    List<User> queryUserByWrapper(@Param(Constants.WRAPPER) QueryWrapper<User> wrapper);


    @Update("update user set balance = balance - #{amount} where id = #{id}")
    void deductMoneyById(@Param("id") Long id, @Param("amount") Integer amount);
}
