package xyz.luckypeak.playground.javawebappdemo.service;

import java.util.List;
import org.slf4j.Logger;
import xyz.luckypeak.playground.javawebappdemo.dao.UserDao;
import xyz.luckypeak.playground.javawebappdemo.model.UserDo;

public class UserService {

  private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);

  private UserDao userDao;

  public UserService() {
    this.userDao = new UserDao();
  }

  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  public List<UserDo> findAll() {
    return userDao.findAll();
  }

  public UserDo findById(Long id) {
    return userDao.findById(id);
  }

  public int insert(UserDo userDo) {
    return userDao.insert(userDo);
  }

  public int update(UserDo userDo) {
    return userDao.update(userDo);
  }

  public int deleteById(Long id) {
    return userDao.deleteById(id);
  }
}
