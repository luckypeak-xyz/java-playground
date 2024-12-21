package xyz.luckypeak.mybatisplusdemo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.luckypeak.mybatisplusdemo.enums.UserStatusEnum;
import xyz.luckypeak.mybatisplusdemo.mapper.UserMapper;
import xyz.luckypeak.mybatisplusdemo.po.Address;
import xyz.luckypeak.mybatisplusdemo.po.User;
import xyz.luckypeak.mybatisplusdemo.service.IUserService;
import xyz.luckypeak.mybatisplusdemo.vo.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    // @Override
    // public void deductBalance(Long id, Integer amount) {
    //     // 1.查询用户
    //     User user = getById(id);
    //     // 2.判断用户状态
    //     if (user == null || user.getStatus() == 2) {
    //         throw new RuntimeException("用户状态异常");
    //     }
    //     // 3.判断用户余额
    //     if (user.getBalance() < amount) {
    //         throw new RuntimeException("用户余额不足");
    //     }
    //     // 4.扣减余额
    //     baseMapper.deductMoneyById(id, amount);
    // }

    @Override
    @Transactional
    public void deductBalance(Long id, Integer money) {
        // 1.查询用户
        User user = getById(id);
        // 2.校验用户状态
        if (user == null || UserStatusEnum.FREEZE.equals(user.getStatus())) {
            throw new RuntimeException("用户状态异常！");
        }
        // 3.校验余额是否充足
        if (user.getBalance() < money) {
            throw new RuntimeException("用户余额不足！");
        }
        // 4.扣减余额 update tb_user set balance = balance - ?
        int remainBalance = user.getBalance() - money;
        lambdaUpdate()
                .set(User::getBalance, remainBalance) // 更新余额
                .set(remainBalance == 0, User::getStatus, 2) // 动态判断，是否更新status
                .eq(User::getId, id)
                .eq(User::getBalance, user.getBalance()) // 乐观锁
                .update();
    }

    @Override
    public UserVO queryUserAndAddressById(Long userId) {
        User user = getById(userId);

        List<Address> addresses = Db.lambdaQuery(Address.class)
                .eq(Address::getUserId, userId).list();

        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        userVO.setAddresses(BeanUtil.copyToList(addresses, AddressVO.class));
        return userVO;
    }

    @Override
    public PageDTO<UserVO> queryUsersPage(UserQuery query) {
        // 1.构建条件
        // 1.1.分页条件
        Page<User> page = Page.of(query.getPageNo(), query.getPageSize());
        // 1.2.排序条件
        if (query.getSortBy() != null) {
            OrderItem orderItem = new OrderItem();
            orderItem.setColumn(query.getSortBy());
            orderItem.setAsc(query.getIsAsc());
            page.addOrder(orderItem);
        } else {
            // 默认按照更新时间排序
            OrderItem orderItem = new OrderItem();
            orderItem.setColumn("update_time");
            orderItem.setAsc(false);
            page.addOrder(orderItem);
        }
        // 2.查询
        page(page);
        // 3.数据非空校验
        List<User> records = page.getRecords();
        if (records == null || records.size() <= 0) {
            // 无数据，返回空结果
            return new PageDTO<>(page.getTotal(), page.getPages(), Collections.emptyList());
        }
        // 4.有数据，转换
        List<UserVO> list = BeanUtil.copyToList(records, UserVO.class);
        // 5.封装返回
        return new PageDTO<UserVO>(page.getTotal(), page.getPages(), list);
    }
}
