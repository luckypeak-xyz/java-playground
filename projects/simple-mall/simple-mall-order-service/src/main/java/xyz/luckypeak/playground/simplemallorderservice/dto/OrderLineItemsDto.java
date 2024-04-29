package xyz.luckypeak.playground.simplemallorderservice.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {
  private String skuCode;

  private BigDecimal price;

  private Integer quantity;
}
