package xyz.luckypeak.playground.simplemallorderservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.luckypeak.playground.simplemallorderservice.dto.OrderReq;
import xyz.luckypeak.playground.simplemallorderservice.dto.converter.OrderReqConverter;
import xyz.luckypeak.playground.simplemallorderservice.model.Order;
import xyz.luckypeak.playground.simplemallorderservice.service.OrderService;

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
