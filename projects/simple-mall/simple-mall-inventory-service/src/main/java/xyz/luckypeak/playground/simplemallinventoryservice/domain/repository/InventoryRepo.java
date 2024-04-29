package xyz.luckypeak.playground.simplemallinventoryservice.domain.repository;

import java.util.List;
import java.util.Optional;
import xyz.luckypeak.playground.simplemallinventoryservice.domain.model.Inventory;

public interface InventoryRepo {
  Optional<Inventory> findBySkuCode(String skuCode);

  int save(Inventory inventory);

  List<Inventory> findBySkuCodeIn(List<String> skuCodes);
}
