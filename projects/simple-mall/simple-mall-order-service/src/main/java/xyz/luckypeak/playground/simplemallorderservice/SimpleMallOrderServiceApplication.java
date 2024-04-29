package xyz.luckypeak.playground.simplemallorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SimpleMallOrderServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SimpleMallOrderServiceApplication.class, args);
  }
}
