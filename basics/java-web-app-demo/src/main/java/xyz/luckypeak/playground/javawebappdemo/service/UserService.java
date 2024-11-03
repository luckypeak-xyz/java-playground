package xyz.luckypeak.playground.javawebappdemo.service;

import java.util.List;
import org.slf4j.Logger;
import xyz.luckypeak.playground.javawebappdemo.model.UserDo;
import xyz.luckypeak.playground.javawebappdemo.model.UserRoleEnum;

public class UserService {

  private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);

  public List<UserDo> findAll() {
    logger.info("Finding all users");
    return List.of(new UserDo(1, "test", "test", "test", "test", UserRoleEnum.ADMIN));
  }
}
