package xyz.luckypeak.playground.simplemallinventoryservice.port.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryReq {
  private String skuCode;
  private Integer quantity;
}
