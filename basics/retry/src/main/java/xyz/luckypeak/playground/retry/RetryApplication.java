package xyz.luckypeak.playground.retry;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@Slf4j
@EnableRetry
public class RetryApplication implements CommandLineRunner {

  @Resource private RetryService myService;

  public static void main(String[] args) {
    SpringApplication.run(RetryApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    try {
      myService.service();
      myService.service2();
    } catch (Exception e) {
      log.error("run", e);
      throw new RuntimeException(e);
    }
  }
}
