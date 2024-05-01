package xyz.luckypeak.playground.simplemallorderservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
  @ResponseStatus(HttpStatus.CREATED)
  @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
  @TimeLimiter(name = "inventory")
  @Retry(name = "inventory")
  public CompletableFuture<String> placeOrder(@RequestBody OrderReq orderReq) {
    Order newOrder = orderReqConverter.toOrder(orderReq);
    return CompletableFuture.supplyAsync(() -> orderService.placeOrder(newOrder));
  }

  public CompletableFuture<String> fallbackMethod(
      OrderReq orderReq, RuntimeException runtimeException) {
    return CompletableFuture.supplyAsync(
        () -> "Oops! Something went wrong. Please try again later.");
  }
}
