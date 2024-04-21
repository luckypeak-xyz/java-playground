package xyz.luckypeak.playground.creditcardprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CreditCardProcessingApplication {

  public static void main(String[] args) {
    SpringApplication.run(CreditCardProcessingApplication.class, args);
  }
}
