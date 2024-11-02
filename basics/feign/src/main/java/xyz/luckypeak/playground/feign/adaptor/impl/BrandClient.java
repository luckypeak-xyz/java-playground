package xyz.luckypeak.playground.feign.adaptor.impl;

import java.net.URI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "brand", url = "http://localhost:8080/brand_a")
public interface BrandClient {

  @GetMapping("/info")
  String queryInfo(URI baseUrl);
}
