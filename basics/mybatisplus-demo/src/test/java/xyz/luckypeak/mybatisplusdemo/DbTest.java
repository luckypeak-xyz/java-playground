package xyz.luckypeak.mybatisplusdemo;

import com.baomidou.mybatisplus.extension.toolkit.Db;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.luckypeak.mybatisplusdemo.po.User;

import java.util.List;

@SpringBootTest
public class DbTest {

    @Test
    void testDbGet() {
        User user = Db.getById(1L, User.class);
        System.out.println(user);
    }

    @Test
    void testDbList() {
        // 利用Db实现复杂条件查询
        List<User> list = Db.lambdaQuery(User.class)
                .like(User::getUsername, "o")
                .ge(User::getBalance, 1000)
                .list();
        list.forEach(System.out::println);
    }

    @Test
    void testDbUpdate() {
        Db.lambdaUpdate(User.class)
                .set(User::getBalance, 2000)
                .eq(User::getUsername, "Rose");
    }

}
