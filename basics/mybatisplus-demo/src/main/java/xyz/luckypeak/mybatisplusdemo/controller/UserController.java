package xyz.luckypeak.mybatisplusdemo.controller;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.luckypeak.mybatisplusdemo.dto.UserFormDTO;
import xyz.luckypeak.mybatisplusdemo.po.User;
import xyz.luckypeak.mybatisplusdemo.service.IUserService;
import xyz.luckypeak.mybatisplusdemo.vo.PageDTO;
import xyz.luckypeak.mybatisplusdemo.vo.UserQuery;
import xyz.luckypeak.mybatisplusdemo.vo.UserVO;

import java.util.List;

@Tag(name = "用户管理接口")
@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final IUserService userService;

    @GetMapping("/page")
    public PageDTO<UserVO> queryUsersPage(UserQuery query){
        return userService.queryUsersPage(query);
    }

    @PostMapping
    @Operation(summary = "新增用户")
    public void saveUser(@RequestBody UserFormDTO userFormDTO){
        // 1.转换DTO为PO
        User user = BeanUtil.copyProperties(userFormDTO, User.class);
        // 2.新增
        userService.save(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户")
    public void removeUserById(@PathVariable("id") Long userId){
        userService.removeById(userId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据id查询用户")
    public UserVO queryUserById(@PathVariable("id") Long userId){
        return userService.queryUserAndAddressById(userId);
    }

    @GetMapping
    @Operation(summary = "根据id集合查询用户")
    public List<UserVO> queryUserByIds(@RequestParam("ids") List<Long> ids){
        // 1.查询用户
        List<User> users = userService.listByIds(ids);
        // 2.处理vo
        return BeanUtil.copyToList(users, UserVO.class);
    }

    @PutMapping("{id}/deduction/{money}")
    @Operation(summary = "扣减用户余额")
    public void deductBalance(@PathVariable("id") Long id, @PathVariable("money")Integer money){
        userService.deductBalance(id, money);
    }

    @GetMapping("/list")
    @Operation(summary = "根据id集合查询用户")
    public List<UserVO> queryUsers(UserQuery query){
        // 1.组织条件
        String username = query.getName();
        Integer status = query.getStatus();
        Integer minBalance = query.getMinBalance();
        Integer maxBalance = query.getMaxBalance();
        // LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda()
        //         .like(username != null, User::getUsername, username)
        //         .eq(status != null, User::getStatus, status)
        //         .ge(minBalance != null, User::getBalance, minBalance)
        //         .le(maxBalance != null, User::getBalance, maxBalance);
        // // 2.查询用户
        // List<User> users = userService.list(wrapper);
        // 2.查询用户
        List<User> users = userService.lambdaQuery()
                .like(username != null, User::getUsername, username)
                .eq(status != null, User::getStatus, status)
                .ge(minBalance != null, User::getBalance, minBalance)
                .le(maxBalance != null, User::getBalance, maxBalance)
                .list();
        // 3.处理vo
        return BeanUtil.copyToList(users, UserVO.class);
    }
}
