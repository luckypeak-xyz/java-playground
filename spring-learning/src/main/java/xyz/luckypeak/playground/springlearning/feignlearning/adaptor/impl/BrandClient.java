package xyz.luckypeak.playground.springlearning.feignlearning.adaptor.impl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@FeignClient(name = "brand", url = "http://localhost:8080/brand_a")
public interface BrandClient {

  @GetMapping("/info")
  String queryInfo(URI baseUrl);
}
