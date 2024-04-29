package xyz.luckypeak.playground.simplemallorderservice.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.luckypeak.playground.simplemallorderservice.client.InventoryClient;
import xyz.luckypeak.playground.simplemallorderservice.dto.InventoryResp;
import xyz.luckypeak.playground.simplemallorderservice.model.Order;
import xyz.luckypeak.playground.simplemallorderservice.model.OrderLineItems;
import xyz.luckypeak.playground.simplemallorderservice.repository.OrderRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
  private final OrderRepository orderRepository;
  private final InventoryClient inventoryClient;

  public void placeOrder(Order newOrder) {
    newOrder.setOrderNo(UUID.randomUUID().toString());
    // Call Inventory service, and place order if product is in
    // stock
    List<String> skuCodes =
        newOrder.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();
    List<InventoryResp> inventoryRespList = inventoryClient.queryInventoryBySkuCode(skuCodes);
    boolean allProductsInStack =
        inventoryRespList.size() == skuCodes.size()
            && inventoryRespList.stream()
                .allMatch(inventoryResp -> inventoryResp.getQuantity() > 0);
    if (allProductsInStack) {
      Order savedOrder = orderRepository.save(newOrder);
    } else {
      throw new IllegalArgumentException("Product is not in stock");
    }
  }
}
