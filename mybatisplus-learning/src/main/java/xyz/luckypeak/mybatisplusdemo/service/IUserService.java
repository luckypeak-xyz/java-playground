package xyz.luckypeak.mybatisplusdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.luckypeak.mybatisplusdemo.po.User;
import xyz.luckypeak.mybatisplusdemo.vo.PageDTO;
import xyz.luckypeak.mybatisplusdemo.vo.UserQuery;
import xyz.luckypeak.mybatisplusdemo.vo.UserVO;

public interface IUserService extends IService<User> {
    void deductBalance(Long id, Integer amount);

    UserVO queryUserAndAddressById(Long userId);

    PageDTO<UserVO> queryUsersPage(UserQuery query);
}
