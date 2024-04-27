package xyz.luckypeak.playground.simplemallorderservice.port.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderReq {
  private List<OrderLineItemsDto> orderLineItemsDtoList;
}
