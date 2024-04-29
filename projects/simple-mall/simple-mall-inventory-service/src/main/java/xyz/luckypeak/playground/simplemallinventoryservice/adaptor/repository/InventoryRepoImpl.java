package xyz.luckypeak.playground.simplemallinventoryservice.adaptor.repository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import xyz.luckypeak.playground.simplemallinventoryservice.adaptor.repository.mapper.InventoryMapper;
import xyz.luckypeak.playground.simplemallinventoryservice.domain.model.Inventory;
import xyz.luckypeak.playground.simplemallinventoryservice.domain.repository.InventoryRepo;

@Repository
@Slf4j
@RequiredArgsConstructor
public class InventoryRepoImpl implements InventoryRepo {
  private final InventoryMapper inventoryMapper;

  @Override
  public Optional<Inventory> findBySkuCode(String skuCode) {
    return Optional.ofNullable(inventoryMapper.findBySkuCode(skuCode));
  }

  @Override
  public int save(Inventory inventory) {
    return inventoryMapper.insert(inventory);
  }

  @Override
  public List<Inventory> findBySkuCodeIn(List<String> skuCodes) {
    return inventoryMapper.findBySkuCodeIn(skuCodes);
  }
}
