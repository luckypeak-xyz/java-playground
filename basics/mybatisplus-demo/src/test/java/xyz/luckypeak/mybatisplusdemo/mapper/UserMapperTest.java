package xyz.luckypeak.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.luckypeak.mybatisplusdemo.po.User;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert() {
        User user = new User();
        user.setId(5L);
        user.setUsername("Lucy");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
        // user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Test
    void testSelectById() {
        User user = userMapper.selectById(5L);
        System.out.println("user = " + user);
    }


    @Test
    void testQueryUserByIds() {
        List<User> users = userMapper.selectByIds(List.of(1L, 2L, 3L, 4L));
        users.forEach(System.out::println);
    }

    @Test
    void testUpdateById() {
        User user = new User();
        user.setId(5L);
        user.setBalance(20000);
        userMapper.updateById(user);
    }

    @Test
    void testDeleteUser() {
        userMapper.deleteById(5L);
    }

    @Test
    void testQuery() {
        User user = userMapper.queryUserById(1L);
        System.out.println("user = " + user);
    }

    @Test
    void testQueryWrapper() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>().select("id", "username", "info", "balance")
                .like("username", "o")
                .ge("balance", 1000);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    void testUpdateByQueryWrapper() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("username", "Jack");
        // user 中的 非 null 字段都会作为 set 语句
        User user = new User();
        user.setBalance(10000);
        int updated = userMapper.update(user, wrapper);
        System.out.println("updated = " + updated);
    }

    @Test
    void testUpdateWrapper() {
        List<Long> ids = List.of(1L, 2L, 4L);
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>()
                .setSql("balance = balance - 200")
                .in("id", ids);
        userMapper.update(null, wrapper);
    }

    @Test
    void testLambdaQueryWrapper() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .select(User::getId, User::getUsername, User::getInfo, User::getBalance)
                .like(User::getUsername, "o")
                .ge(User::getBalance, 1000);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    void testCustomQuery() {
        List<Long> ids = List.of(1L, 2L, 4L);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>().in(User::getId, ids);
        userMapper.deductBalance(200, wrapper);
    }

    @Test
    void testCustomJoinWrapper() {
        // new LambdaQueryWrapper<User>()
        //         .in(User::getId, List.of(1L, 2L, 4L))
        //         .eq(Address::getCity, "北京"); // 这里会报错，Non-static method cannot be referenced from a static context

        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .in("u.id", List.of(1L, 2L, 4L))
                .eq("a.city", "北京");
        List<User> users = userMapper.queryUserByWrapper(wrapper);
        users.forEach(System.out::println);
    }

}