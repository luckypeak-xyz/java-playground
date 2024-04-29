package xyz.luckypeak.playground.simplemallinventoryservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("xyz.luckypeak.playground.simplemallinventoryservice.adaptor.repository.mapper")
@EnableTransactionManagement
public class SimpleMallInventoryServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SimpleMallInventoryServiceApplication.class, args);
  }
}
