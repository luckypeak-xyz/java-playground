package xyz.luckypeak.playground.simplemallorderservice.app;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.luckypeak.playground.simplemallorderservice.domain.model.Order;
import xyz.luckypeak.playground.simplemallorderservice.domain.repository.OrderRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
  private final OrderRepository orderRepository;

  public void placeOrder(Order newOrder) {
    newOrder.setOrderNo(UUID.randomUUID().toString());
    Order savedOrder = orderRepository.save(newOrder);
    log.info("Order saved: {}", savedOrder);
  }
}
