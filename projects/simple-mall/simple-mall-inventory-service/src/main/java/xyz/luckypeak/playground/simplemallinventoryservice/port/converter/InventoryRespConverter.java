package xyz.luckypeak.playground.simplemallinventoryservice.port.converter;

import java.util.List;
import org.mapstruct.Mapper;
import xyz.luckypeak.playground.simplemallinventoryservice.domain.model.Inventory;
import xyz.luckypeak.playground.simplemallinventoryservice.port.dto.InventoryResp;

@Mapper(componentModel = "spring")
public interface InventoryRespConverter {
  List<InventoryResp> toInventoriesResp(List<Inventory> inventories);
}
