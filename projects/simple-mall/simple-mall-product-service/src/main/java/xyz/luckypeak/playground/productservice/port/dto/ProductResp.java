package xyz.luckypeak.playground.productservice.port.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResp {
  private String id;

  private String name;

  private String description;

  private BigDecimal price;
}
