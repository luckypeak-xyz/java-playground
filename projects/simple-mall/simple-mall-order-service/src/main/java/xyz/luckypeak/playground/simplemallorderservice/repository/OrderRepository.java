package xyz.luckypeak.playground.simplemallorderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.luckypeak.playground.simplemallorderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}
