package xyz.luckypeak.playground.feign.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.luckypeak.playground.feign.adaptor.BrandAdaptor;

@RestController("/brand")
public class BrandController {

  @Resource private BrandAdaptor brandAdaptor;

  @GetMapping("/info")
  public String getInfo(@RequestParam("brand") String brand) {
    return brandAdaptor.queryInfo(brand);
  }
}
