package xyz.luckypeak.playground.creditcardprocessing.service;

import org.springframework.statemachine.StateMachine;
import xyz.luckypeak.playground.creditcardprocessing.domain.Payment;
import xyz.luckypeak.playground.creditcardprocessing.domain.PaymentEvent;
import xyz.luckypeak.playground.creditcardprocessing.domain.PaymentState;

public interface PaymentService {
  static final String PAYMENT_ID_HEADER = "payment_id";

  Payment newPayment(Payment payment);

  StateMachine<PaymentState, PaymentEvent> preAuth(Long paymentId);

  StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId);

  StateMachine<PaymentState, PaymentEvent> declineAuth(Long paymentId);
}
