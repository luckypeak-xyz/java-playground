package xyz.luckypeak.playground.productservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

@Configuration
public class TransactionConfig {
  @Bean("mongoTransactionManager")
  public MongoTransactionManager transactionManager(MongoDatabaseFactory mongoDatabaseFactory) {
    return new MongoTransactionManager(mongoDatabaseFactory);
  }

  //  @Bean
  //  @Primary
  //  public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
  //    return new DataSourceTransactionManager(dataSource);
  //  }
}
