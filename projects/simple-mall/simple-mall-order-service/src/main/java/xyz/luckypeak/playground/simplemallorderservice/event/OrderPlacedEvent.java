package xyz.luckypeak.playground.simplemallorderservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderPlacedEvent {
  private String orderNo;
}
