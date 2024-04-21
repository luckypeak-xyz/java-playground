package xyz.luckypeak.playground.creditcardprocessing.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.luckypeak.playground.creditcardprocessing.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {}
