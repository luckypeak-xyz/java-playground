package xyz.luckypeak.playground.websocket_learning.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import xyz.luckypeak.playground.websocket_learning.entity.Result;
import xyz.luckypeak.playground.websocket_learning.entity.User;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public Result login(
            @RequestBody User user,
            HttpSession session
    ) {
        Result result = new Result();
        if (Objects.nonNull(user) && "123".equals(user.getPassword())) {
            result.setFlag(true);
            session.setAttribute("user", user.getUsername());
        } else {
            result.setFlag(false);
            result.setMsg("登录失败");
        }
        return result;
    }

    @GetMapping("/getUsername")
    public String getUsername(
            HttpSession session
    ) {
        return (String) session.getAttribute("user");
    }

}
