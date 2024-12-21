package xyz.luckypeak.playground.javawebappdemo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.slf4j.Logger;

public class DatabaseConfig {

  private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DatabaseConfig.class);

  private static HikariDataSource dataSource;

  static {
    Properties properties = new Properties();
    try (InputStream input =
        DatabaseConfig.class.getClassLoader().getResourceAsStream("database.properties")) {
      properties.load(input);
      HikariConfig config = new HikariConfig();
      Class.forName(properties.getProperty("db.driver"));
      config.setJdbcUrl(properties.getProperty("db.url"));
      config.setUsername(properties.getProperty("db.username"));
      config.setPassword(properties.getProperty("db.password"));
      dataSource = new HikariDataSource(config);
    } catch (Exception e) {
      logger.error("Failed to load database properties", e);
    }
  }

  public static Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }
}
