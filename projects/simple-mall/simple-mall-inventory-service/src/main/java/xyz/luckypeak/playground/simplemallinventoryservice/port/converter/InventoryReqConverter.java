package xyz.luckypeak.playground.simplemallinventoryservice.port.converter;

import org.mapstruct.Mapper;
import xyz.luckypeak.playground.simplemallinventoryservice.domain.model.Inventory;
import xyz.luckypeak.playground.simplemallinventoryservice.port.dto.InventoryReq;

@Mapper(componentModel = "spring")
public interface InventoryReqConverter {
  Inventory toInventory(InventoryReq inventoryReq);
}
