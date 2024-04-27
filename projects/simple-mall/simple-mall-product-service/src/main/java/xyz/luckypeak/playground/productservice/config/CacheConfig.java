package xyz.luckypeak.playground.productservice.config;

import java.util.concurrent.ConcurrentHashMap;
import javax.management.timer.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@Slf4j
public class CacheConfig {
  @Bean
  public CacheManager cacheManager() {
    return new ConcurrentMapCacheManager("products");
  }

  @Scheduled(fixedRate = Timer.ONE_MINUTE)
  @Caching(evict = {@CacheEvict(cacheNames = "products", allEntries = true)})
  public void clearCache() {
    printCaches(cacheManager());
    log.info("Clear all {} caches entries", "products");
  }

  private void printCaches(CacheManager cacheManager) {
    cacheManager
        .getCacheNames()
        .forEach(
            cacheName -> {
              ConcurrentHashMap cacheMap =
                  (ConcurrentHashMap) (cacheManager.getCache(cacheName).getNativeCache());
              log.info("Cache {} has {} entries", cacheName, cacheMap.size());
            });
  }
}
