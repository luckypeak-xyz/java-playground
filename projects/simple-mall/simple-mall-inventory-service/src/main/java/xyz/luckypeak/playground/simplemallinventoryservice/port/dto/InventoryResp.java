package xyz.luckypeak.playground.simplemallinventoryservice.port.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResp {
  private Long id;
  private String skuCode;
  private Integer quantity;
}
