package xyz.luckypeak.playground.simplemallorderservice.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.luckypeak.playground.simplemallorderservice.dto.InventoryResp;

@FeignClient(name = "simple-mall-inventory-service")
public interface InventoryClient {
  @GetMapping("/api/inventory")
  List<InventoryResp> queryInventoryBySkuCode(@RequestParam("sku-code") List<String> skuCode);
}
