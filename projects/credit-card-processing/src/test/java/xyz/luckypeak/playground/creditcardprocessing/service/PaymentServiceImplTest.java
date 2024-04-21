package xyz.luckypeak.playground.creditcardprocessing.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.luckypeak.playground.creditcardprocessing.domain.Payment;
import xyz.luckypeak.playground.creditcardprocessing.domain.repository.PaymentRepository;

@SpringBootTest
class PaymentServiceImplTest {

  @Autowired PaymentService paymentService;

  @Autowired PaymentRepository paymentRepository;

  Payment payment;

  @BeforeEach
  void setUp() {
    payment = Payment.builder().amount(new BigDecimal("12.99")).build();
  }

  @Test
  void preAuth() {
    Payment savedPayment = paymentService.newPayment(payment);
    paymentService.preAuth(savedPayment.getId());
  }
}
