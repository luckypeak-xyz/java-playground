package xyz.luckypeak.playground.simplemallinventoryservice.app;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.luckypeak.playground.simplemallinventoryservice.domain.model.Inventory;
import xyz.luckypeak.playground.simplemallinventoryservice.domain.repository.InventoryRepo;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryService {
  private final InventoryRepo inventoryRepo;

  @Transactional(readOnly = true)
  public List<Inventory> queryInventoryBySkuCode(List<String> skuCodes) {
    return inventoryRepo.findBySkuCodeIn(skuCodes);
  }

  @Transactional
  public int save(Inventory newInventory) {
    int affectedRows = inventoryRepo.save(newInventory);
    return affectedRows;
  }
}
