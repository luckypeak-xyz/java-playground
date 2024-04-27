package xyz.luckypeak.playground.productservice.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Scheduler;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CaffeineCacheConfig {
  // 配置单个缓存
  //  @Bean
  //  public CacheManager cacheManager(Caffeine caffeine) {
  //    CaffeineCacheManager cacheManager = new CaffeineCacheManager("products");
  //    cacheManager.setCaffeine(caffeine);
  //    return cacheManager;
  //  }
  //
  //  @Bean
  //  Caffeine caffeineSpec() {
  //    return Caffeine.newBuilder()
  //        .initialCapacity(10)
  //        .maximumSize(100)
  //        .expireAfterAccess(1, TimeUnit.MINUTES);
  //  }

  // 配置多个缓存
  @Bean
  public CacheManager cacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager();
    cacheManager.registerCustomCache("products", buildCache(10, 100, 1, TimeUnit.MINUTES));
    // cacheManager.registerCustomCache("products2", buildCache(10, 100, 1, TimeUnit.MINUTES));
    return cacheManager;
  }

  private Cache buildCache(int initialCapacity, int maximumSize, int duration, TimeUnit unit) {
    return Caffeine.newBuilder()
        .initialCapacity(initialCapacity)
        .maximumSize(maximumSize)
        .expireAfterAccess(duration, unit)
        .evictionListener((k, v, c) -> log.info("Evict: {}", k))
        .removalListener((k, v, c) -> log.info("Remove: {}", k))
        .scheduler(Scheduler.systemScheduler())
        .build();
  }
}
