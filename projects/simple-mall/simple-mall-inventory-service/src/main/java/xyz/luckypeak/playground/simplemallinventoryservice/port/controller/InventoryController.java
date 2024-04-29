package xyz.luckypeak.playground.simplemallinventoryservice.port.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.luckypeak.playground.simplemallinventoryservice.app.InventoryService;
import xyz.luckypeak.playground.simplemallinventoryservice.domain.model.Inventory;
import xyz.luckypeak.playground.simplemallinventoryservice.port.converter.InventoryReqConverter;
import xyz.luckypeak.playground.simplemallinventoryservice.port.converter.InventoryRespConverter;
import xyz.luckypeak.playground.simplemallinventoryservice.port.dto.InventoryReq;
import xyz.luckypeak.playground.simplemallinventoryservice.port.dto.InventoryResp;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {
  private final InventoryService inventoryService;
  private final InventoryReqConverter inventoryReqConverter;
  private final InventoryRespConverter inventoryRespConverter;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<InventoryResp> isInStock(@RequestParam(value = "sku-code") List<String> skuCodes) {
    List<Inventory> inventories = inventoryService.queryInventoryBySkuCode(skuCodes);
    log.info("inventories: {}", inventories);
    return inventoryRespConverter.toInventoriesResp(inventories);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void save(@RequestBody InventoryReq inventoryReq) {
    Inventory newInventory = inventoryReqConverter.toInventory(inventoryReq);
    inventoryService.save(newInventory);
  }
}
