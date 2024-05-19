import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class MainTest {
  @Test
  public void TestGuavaRateLimiter() throws InterruptedException {
    RateLimiter limiter = RateLimiter.create(5);
    for (int i = 0; i < 100; i++) {
      log.info("{}", limiter.acquire());
    }
  }
}
