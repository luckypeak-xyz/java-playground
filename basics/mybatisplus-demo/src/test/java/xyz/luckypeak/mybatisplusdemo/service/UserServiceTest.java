package xyz.luckypeak.mybatisplusdemo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.luckypeak.mybatisplusdemo.po.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @Resource
    private IUserService userService;

    @Test
    void testSaveOneByOne() {
        long b = System.currentTimeMillis();
        for (int i = 1; i <= 100000; i++) {
            userService.save(buildUser(i));
        }
        long e = System.currentTimeMillis();
        System.out.println("耗时：" + (e - b));
    }

    private User buildUser(int i) {
        User user = new User();
        user.setUsername("user_" + i);
        user.setPassword("123");
        user.setPhone("" + (18688190000L + i));
        user.setBalance(2000);
        // user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(user.getCreateTime());
        return user;
    }

    @Test
    void testSaveBatch() {
        // 准备10万条数据
        List<User> list = new ArrayList<>(1000);
        long b = System.currentTimeMillis();
        for (int i = 1; i <= 100000; i++) {
            list.add(buildUser(i));
            // 每1000条批量插入一次
            if (i % 1000 == 0) {
                userService.saveBatch(list);
                list.clear();
            }
        }
        long e = System.currentTimeMillis();
        System.out.println("耗时：" + (e - b));
    }

    @Test
    void testService() {
        List<User> list = userService.list();
        list.forEach(System.out::println);
    }

    @Test
    void testPageQuery() {
        // 1.分页查询，new Page()的两个参数分别是：页码、每页大小
        Page<User> p = userService.page(new Page<>(2, 2));
        // 2.总条数
        System.out.println("total = " + p.getTotal());
        // 3.总页数
        System.out.println("pages = " + p.getPages());
        // 4.数据
        List<User> records = p.getRecords();
        records.forEach(System.out::println);
    }

}
