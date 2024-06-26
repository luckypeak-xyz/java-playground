package xyz.luckypeak.playground.creditcardprocessing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.luckypeak.playground.creditcardprocessing.domain.Payment;
import xyz.luckypeak.playground.creditcardprocessing.domain.PaymentEvent;
import xyz.luckypeak.playground.creditcardprocessing.domain.PaymentState;
import xyz.luckypeak.playground.creditcardprocessing.domain.repository.PaymentRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {
  private final PaymentRepository paymentRepository;
  private final StateMachineFactory<PaymentState, PaymentEvent> stateMachineFactory;
  private final PaymentStateChangeInterceptor paymentStateChangeInterceptor;

  @Override
  public Payment newPayment(Payment payment) {
    payment.setState(PaymentState.NEW);
    return paymentRepository.save(payment);
  }

  @Override
  public StateMachine<PaymentState, PaymentEvent> preAuth(Long paymentId) {
    StateMachine<PaymentState, PaymentEvent> stateMachine = build(paymentId);
    sendEvent(paymentId, stateMachine, PaymentEvent.PRE_AUTHORIZE);
    return stateMachine;
  }

  @Override
  public StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId) {
    StateMachine<PaymentState, PaymentEvent> stateMachine = build(paymentId);
    sendEvent(paymentId, stateMachine, PaymentEvent.AUTH_APPROVED);
    return stateMachine;
  }

  @Override
  public StateMachine<PaymentState, PaymentEvent> declineAuth(Long paymentId) {
    StateMachine<PaymentState, PaymentEvent> stateMachine = build(paymentId);
    sendEvent(paymentId, stateMachine, PaymentEvent.AUTH_DECLINED);
    return stateMachine;
  }

  private void sendEvent(
      Long paymentId, StateMachine<PaymentState, PaymentEvent> stateMachine, PaymentEvent event) {
    Message<PaymentEvent> message =
        MessageBuilder.withPayload(event).setHeader(PAYMENT_ID_HEADER, paymentId).build();
    stateMachine.sendEvent(message);
  }

  private StateMachine<PaymentState, PaymentEvent> build(Long paymentId) {
    Payment payment = paymentRepository.getReferenceById(paymentId);
    StateMachine<PaymentState, PaymentEvent> stateMachine =
        stateMachineFactory.getStateMachine(Long.toString(payment.getId()));
    stateMachine.stop();
    stateMachine
        .getStateMachineAccessor()
        .doWithAllRegions(
            sma -> {
              sma.addStateMachineInterceptor(paymentStateChangeInterceptor);
              sma.resetStateMachine(
                  new DefaultStateMachineContext<>(payment.getState(), null, null, null));
            });
    stateMachine.start();
    return stateMachine;
  }
}
