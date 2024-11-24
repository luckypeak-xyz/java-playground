package xyz.luckypeak.mybatisplusdemo.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.luckypeak.mybatisplusdemo.po.Address;

import java.util.List;

@SpringBootTest
public class AddressServiceTest {

    @Resource
    private IAddressService addressService;

    @Test
    void testDeleteByLogic() {
        // 删除方法与以前没有区别
        addressService.removeById(59L);
    }

    @Test
    void testQuery() {
        List<Address> list = addressService.list();
        list.forEach(System.out::println);
    }

}
