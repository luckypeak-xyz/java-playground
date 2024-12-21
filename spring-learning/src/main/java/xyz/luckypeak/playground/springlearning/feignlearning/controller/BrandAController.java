package xyz.luckypeak.playground.springlearning.feignlearning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand_a")
public class BrandAController {

  @GetMapping("/info")
  public String queryInfo() {
    return "brand_a";
  }
}