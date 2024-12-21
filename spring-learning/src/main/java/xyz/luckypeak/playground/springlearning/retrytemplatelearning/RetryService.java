package xyz.luckypeak.playground.springlearning.retrytemplatelearning;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RetryService {

  private RetryTemplate retryTemplate =
      RetryTemplate.builder()
          .maxAttempts(3)
          .fixedBackoff(1000)
          .retryOn(RuntimeException.class)
          .build();

  @Retryable(retryFor = RuntimeException.class)
  public void service() {
    log.info("retry");
    foo();
  }

  public void service2() {
    retryTemplate.execute(
        ctx -> {
          log.info("retry");
          foo();
          return null;
        });
  }

  @Recover
  public void recover() {
    log.info("recover");
  }

  public void foo() {
      try {
          Thread.sleep(1000);
      } catch (InterruptedException e) {
          throw new RuntimeException(e);
      }
      throw new RuntimeException("test");
  }
}
