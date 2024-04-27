package xyz.luckypeak.playground.simplemallorderservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.luckypeak.playground.simplemallorderservice.domain.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}
