package xyz.luckypeak.playground.simplemallorderservice.domain.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "t_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String orderNo;

  @OneToMany(cascade = CascadeType.ALL)
  private List<OrderLineItems> orderLineItemsList;
}
