package xyz.luckypeak.playground.javawebappdemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import xyz.luckypeak.playground.javawebappdemo.config.DatabaseConfig;
import xyz.luckypeak.playground.javawebappdemo.model.UserDo;
import xyz.luckypeak.playground.javawebappdemo.model.UserRoleEnum;

public class UserDao {

  private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserDao.class);

  public List<UserDo> findAll() {
    List<UserDo> userDoList = new ArrayList<>();
    String query = "SELECT * FROM t_basics_java_web_app_demo_user";
    try (Connection conn = DatabaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery()) {
      while (rs.next()) {
        UserDo userDo = new UserDo();
        userDo.setId(rs.getInt("id"));
        userDo.setUsername(rs.getString("username"));
        userDo.setPassword(rs.getString("password"));
        userDo.setEmail(rs.getString("email"));
        userDo.setPhoneNumber(rs.getString("phone_number"));
        userDo.setRole(UserRoleEnum.valueOf(rs.getString("role")));
        userDoList.add(userDo);
      }
    } catch (SQLException e) {
      logger.error("Failed to find all users", e);
    }
    return userDoList;
  }

  public UserDo findById(Long id) {
    UserDo userDo = null;
    String query = "SELECT * FROM t_basics_java_web_app_demo_user WHERE id = ?";
    try (Connection conn = DatabaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        userDo = new UserDo();
        userDo.setId(rs.getInt("id"));
        userDo.setUsername(rs.getString("username"));
        userDo.setPassword(rs.getString("password"));
        userDo.setEmail(rs.getString("email"));
        userDo.setPhoneNumber(rs.getString("phone_number"));
        userDo.setRole(UserRoleEnum.valueOf(rs.getString("role")));
      }
    } catch (SQLException e) {
      logger.error("Failed to find user by id", e);
    }
    return userDo;
  }

  public int insert(UserDo userDo) {
    int rowCount = 0;
    String query =
        "INSERT INTO t_basics_java_web_app_demo_user (username, password, email, phone_number, role) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = DatabaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setString(1, userDo.getUsername());
      stmt.setString(2, userDo.getPassword());
      stmt.setString(3, userDo.getEmail());
      stmt.setString(4, userDo.getPhoneNumber());
      stmt.setString(5, userDo.getRole().toString());
      rowCount = stmt.executeUpdate();
    } catch (SQLException e) {
      logger.error("Failed to insert user", e);
    }
    return rowCount;
  }

  public int update(UserDo userDo) {
    int rowCount = 0;
    String query =
        "UPDATE t_basics_java_web_app_demo_user SET username = ?, password = ?, email = ?, phone_number = ?, role = ? WHERE id = ?";
    try (Connection conn = DatabaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setString(1, userDo.getUsername());
      stmt.setString(2, userDo.getPassword());
      stmt.setString(3, userDo.getEmail());
      stmt.setString(4, userDo.getPhoneNumber());
      stmt.setString(5, userDo.getRole().toString());
      stmt.setLong(6, userDo.getId());
      rowCount = stmt.executeUpdate();
    } catch (SQLException e) {
      logger.error("Failed to update user", e);
    }
    return rowCount;
  }

  public int deleteById(Long id) {
    int rowCount = 0;
    String query = "DELETE FROM t_basics_java_web_app_demo_user WHERE id = ?";
    try (Connection conn = DatabaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setLong(1, id);
      rowCount = stmt.executeUpdate();
    } catch (SQLException e) {
      logger.error("Failed to delete user", e);
    }
    return rowCount;
  }
}
