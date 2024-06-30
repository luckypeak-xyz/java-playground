package xyz.luckypeak.playground.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RetryService {

  private RetryTemplate retryTemplate =
      RetryTemplate.builder()
          .maxAttempts(3)
          .fixedBackoff(1000)
          .retryOn(RuntimeException.class)
          .build();

  @Retryable(retryFor = RuntimeException.class)
  public void service() throws InterruptedException {
    log.info("retry");
    foo();
  }

  public void service2() throws InterruptedException {
    retryTemplate.execute(
        ctx -> {
          log.info("retry");
          try {
            foo();
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          return null;
        });
  }

  @Recover
  public void recover() {
    log.info("recover");
  }

  public void foo() throws InterruptedException {
    Thread.sleep(1000);
    throw new RuntimeException("test");
  }
}
