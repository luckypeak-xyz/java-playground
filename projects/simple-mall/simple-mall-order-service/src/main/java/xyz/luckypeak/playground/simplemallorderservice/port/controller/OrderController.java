package xyz.luckypeak.playground.simplemallorderservice.port.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.luckypeak.playground.simplemallorderservice.app.OrderService;
import xyz.luckypeak.playground.simplemallorderservice.domain.model.Order;
import xyz.luckypeak.playground.simplemallorderservice.port.converter.OrderReqConverter;
import xyz.luckypeak.playground.simplemallorderservice.port.dto.OrderReq;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;
  private final OrderReqConverter orderReqConverter;

  @PostMapping
  public String placeOrder(@RequestBody OrderReq orderReq) {
    Order newOrder = orderReqConverter.toOrder(orderReq);
    orderService.placeOrder(newOrder);
    return "Order placed successfully";
  }
}
