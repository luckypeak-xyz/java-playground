package xyz.luckypeak.playground.feign.adaptor.impl;

import jakarta.annotation.Resource;
import java.net.URI;
import org.springframework.stereotype.Service;
import xyz.luckypeak.playground.feign.adaptor.BrandAdaptor;

@Service
public class BrandAdaptorImpl implements BrandAdaptor {

  @Resource private BrandClient brandClient;

  public String queryInfo(String brand) {
    return brandClient.queryInfo(getUriByBrand(brand));
  }

  public URI getUriByBrand(String brand) {
    String uri;
    if ("a".equalsIgnoreCase(brand)) {
      uri = "http://localhost:8080/brand_a";
    } else if ("b".equalsIgnoreCase(brand)) {
      uri = "http://localhost:8080/brand_b";
    } else {
      throw new UnsupportedOperationException("unsupported brand " + brand);
    }
    return URI.create(uri);
  }
}
